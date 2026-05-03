package com.fintrack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.GlobalSettingsRepository
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
 *   Loading  →  Locked  ─unlock─▶  needsFirstProfile? ──yes─▶ NeedsFirstProfile
 *                                          │
 *                                         no
 *                                          ▼
 *                                       Ready(activeUserId)
 *
 * State transitions are pure: the [combine] block has no side effects.
 * Activating a profile after unlock happens in [onUnlocked]/[onProfileCreated]
 * — never as a side effect of state observation.
 */
sealed interface AppState {
    data object Loading : AppState
    data object Locked : AppState
    data object BiometricUnavailable : AppState
    data object NeedsFirstProfile : AppState
    data class Ready(val activeUserId: UUID) : AppState
}

@HiltViewModel
class AppViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val globalSettingsRepository: GlobalSettingsRepository,
    private val userScope: UserScope,
    private val inactivityTracker: InactivityTracker,
) : ViewModel() {

    private val biometricAvailable = MutableStateFlow(true)

    val state: StateFlow<AppState> = combine(
        inactivityTracker.isUnlocked,
        userRepository.observeActiveUsers(),
        userScope.activeUserId,
        biometricAvailable,
    ) { isUnlocked, users, activeUserId, available ->
        when {
            !available -> AppState.BiometricUnavailable
            !isUnlocked -> AppState.Locked
            users.isEmpty() -> AppState.NeedsFirstProfile
            activeUserId != null && users.any { it.id == activeUserId } ->
                AppState.Ready(activeUserId)
            // No active user yet but users exist — onUnlocked auto-activates the
            // single-user case; this branch shows Loading until that completes.
            else -> AppState.Loading
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
     * Called by the lock screen after BiometricPrompt succeeds. Marks the
     * session unlocked and, if exactly one user exists, auto-activates them.
     * Zero-user case routes to the onboarding form via [AppState.NeedsFirstProfile].
     */
    fun onUnlocked() {
        inactivityTracker.markUnlocked()
        viewModelScope.launch {
            if (userScope.activeUserId.value == null && userRepository.activeUserCount() == 1) {
                userRepository.firstActiveUser()?.let { user ->
                    userScope.setActiveUser(user.id)
                    globalSettingsRepository.setActiveUserId(user.id)
                }
            }
        }
    }

    fun onProfileCreated(userId: UUID) {
        viewModelScope.launch {
            userScope.setActiveUser(userId)
            globalSettingsRepository.setActiveUserId(userId)
        }
    }

    fun activateUser(userId: UUID) {
        viewModelScope.launch {
            userScope.setActiveUser(userId)
            globalSettingsRepository.setActiveUserId(userId)
        }
    }

    fun onLocked() {
        inactivityTracker.forceLock()
        userScope.clear()
    }
}
