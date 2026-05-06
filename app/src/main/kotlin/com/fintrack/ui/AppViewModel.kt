package com.fintrack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.GlobalSettingsRepository
import com.fintrack.data.repo.GoalRepository
import com.fintrack.data.repo.MilestoneRepository
import com.fintrack.data.repo.StreakRepository
import com.fintrack.data.repo.UserRepository
import com.fintrack.domain.UserScope
import com.fintrack.security.InactivityTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * Top-level state machine for the lock + onboarding gate.
 *
 *   Loading → Locked → NeedsOnboarding → NeedsFirstProfile → ShowingPicker | Ready
 *
 * The combine block is pure — side effects (auto-activating a single user,
 * persisting active user id, flipping the onboarding flag) live in
 * [onUnlocked] / [onProfileCreated] / [activateUser] / [requestSwitchUser] /
 * [completeOnboarding].
 *
 * `NeedsOnboarding` fires whenever `hasCompletedOnboarding` is false —
 * both for first-launch installs (no users yet) and for v3-upgrade
 * installs (users exist, but the new flag defaults to 0). The four-card
 * pager is short enough that showing it once on upgrade is intentional;
 * after `completeOnboarding()` the state recomputes naturally.
 */
sealed interface AppState {
    data object Loading : AppState
    data object Locked : AppState
    data object BiometricUnavailable : AppState
    data object NeedsOnboarding : AppState
    data object NeedsFirstProfile : AppState
    data object ShowingPicker : AppState
    data class Ready(val activeUserId: UUID) : AppState
}

@HiltViewModel
class AppViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val globalSettingsRepository: GlobalSettingsRepository,
    private val milestoneRepository: MilestoneRepository,
    private val streakRepository: StreakRepository,
    private val goalRepository: GoalRepository,
    private val userScope: UserScope,
    private val inactivityTracker: InactivityTracker,
) : ViewModel() {

    private val biometricAvailable = MutableStateFlow(true)

    val state: StateFlow<AppState> = combine(
        inactivityTracker.isUnlocked,
        userRepository.observeActiveUsers(),
        userScope.activeUserId,
        biometricAvailable,
        globalSettingsRepository.observe(),
    ) { isUnlocked, users, activeUserId, available, settings ->
        when {
            !available -> AppState.BiometricUnavailable
            !isUnlocked -> AppState.Locked
            !settings.hasCompletedOnboarding -> AppState.NeedsOnboarding
            users.isEmpty() -> AppState.NeedsFirstProfile
            activeUserId != null && users.any { it.id == activeUserId } ->
                AppState.Ready(activeUserId)
            else -> AppState.ShowingPicker
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppState.Loading)

    init {
        viewModelScope.launch {
            val settings = globalSettingsRepository.get()
            inactivityTracker.setInactivityTimeout(settings.inactivityLockSeconds)
        }
    }

    fun onBiometricUnavailable() {
        biometricAvailable.value = false
    }

    fun onBiometricAvailable() {
        biometricAvailable.value = true
    }

    /**
     * Called by the lock screen after BiometricPrompt succeeds.
     * Auto-activates the only user when the picker-skip preference is on
     * and exactly one user exists; otherwise leaves activation to the picker.
     */
    fun onUnlocked() {
        inactivityTracker.markUnlocked()
        viewModelScope.launch {
            if (userScope.activeUserId.value != null) return@launch
            val settings = globalSettingsRepository.get()
            if (!settings.alwaysShowProfilePicker && userRepository.activeUserCount() == 1) {
                userRepository.firstActiveUser()?.let { user ->
                    userScope.setActiveUser(user.id)
                    globalSettingsRepository.setActiveUserId(user.id)
                    backfillEngagement(user.id)
                }
            }
        }
    }

    fun onProfileCreated(userId: UUID) {
        viewModelScope.launch {
            userScope.setActiveUser(userId)
            globalSettingsRepository.setActiveUserId(userId)
            backfillEngagement(userId)
        }
    }

    fun activateUser(userId: UUID) {
        viewModelScope.launch {
            userScope.setActiveUser(userId)
            globalSettingsRepository.setActiveUserId(userId)
            backfillEngagement(userId)
        }
    }

    /**
     * Silent backfill on user activation: re-runs streak + milestone
     * detection so any history that pre-dates the engagement layer (or
     * was added on another device via backup restore) shows the right
     * chip and timeline. Idempotent.
     */
    private suspend fun backfillEngagement(userId: UUID) {
        streakRepository.recompute(userId)
        milestoneRepository.detectAndPersist(userId)
        goalRepository.detectAndPersistAchievements(userId)
    }

    /** "Switch user" affordance: clear active user without re-locking biometric. */
    fun requestSwitchUser() {
        viewModelScope.launch {
            userScope.clear()
            globalSettingsRepository.setActiveUserId(null)
        }
    }

    fun onLocked() {
        inactivityTracker.forceLock()
        userScope.clear()
    }

    /**
     * Called by the onboarding pager's final-card CTA on first launch.
     * Flips the persistent flag so the gate doesn't re-fire on subsequent
     * unlocks. Replays from the About screen do NOT call this — they're a
     * normal NavController navigation that doesn't touch the flag.
     */
    fun completeOnboarding() {
        viewModelScope.launch {
            globalSettingsRepository.setHasCompletedOnboarding(true)
        }
    }
}
