package com.fintrack.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.GlobalSettingsEntity
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.data.db.entities.UserSettingsEntity
import com.fintrack.data.repo.GlobalSettingsRepository
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.UserRepository
import com.fintrack.data.repo.UserSettingsRepository
import com.fintrack.domain.UserScope
import com.fintrack.security.InactivityTracker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SettingsTabUiState(
    val activeUser: UserEntity? = null,
    val activeUserSettings: UserSettingsEntity? = null,
    val global: GlobalSettingsEntity? = null,
    val activeHoldingCount: Int = 0,
    val totalHoldingCount: Int = 0,
    val userCount: Int = 0,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SettingsTabViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userSettingsRepository: UserSettingsRepository,
    private val globalSettingsRepository: GlobalSettingsRepository,
    private val holdingRepository: HoldingRepository,
    private val userScope: UserScope,
    private val inactivityTracker: InactivityTracker,
) : ViewModel() {

    val state: StateFlow<SettingsTabUiState> = combine(
        userScope.activeUserId.flatMapLatest { id ->
            if (id == null) flowOf<UserEntity?>(null) else userRepository.observeUser(id)
        },
        userScope.activeUserId.flatMapLatest { id ->
            if (id == null) flowOf<UserSettingsEntity?>(null) else userSettingsRepository.observe(id)
        },
        globalSettingsRepository.observe(),
        holdingRepository.observeAll(),
        userRepository.observeActiveUsers(),
    ) { user, settings, global, allHoldings, users ->
        SettingsTabUiState(
            activeUser = user,
            activeUserSettings = settings,
            global = global,
            activeHoldingCount = allHoldings.count { it.isActive },
            totalHoldingCount = allHoldings.size,
            userCount = users.size,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), SettingsTabUiState())

    fun setInactivityTimeout(seconds: Int) {
        viewModelScope.launch {
            globalSettingsRepository.setInactivityLockSeconds(seconds)
            inactivityTracker.setInactivityTimeout(seconds)
        }
    }

    fun setAlwaysShowProfilePicker(value: Boolean) {
        viewModelScope.launch {
            globalSettingsRepository.setAlwaysShowProfilePicker(value)
        }
    }
}
