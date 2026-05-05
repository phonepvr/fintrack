package com.fintrack.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.entities.GlobalSettingsEntity
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.data.repo.AimAllocationRepository
import com.fintrack.data.repo.GlobalSettingsRepository
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.LoanRepository
import com.fintrack.data.repo.UserRepository
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
    val activeUserAim: List<AimAllocationEntity> = emptyList(),
    val global: GlobalSettingsEntity? = null,
    val activeHoldingCount: Int = 0,
    val totalHoldingCount: Int = 0,
    val userCount: Int = 0,
    val activeLoanCount: Int = 0,
    val totalLoanCount: Int = 0,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SettingsTabViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val aimAllocationRepository: AimAllocationRepository,
    private val globalSettingsRepository: GlobalSettingsRepository,
    private val holdingRepository: HoldingRepository,
    private val loanRepository: LoanRepository,
    private val userScope: UserScope,
    private val inactivityTracker: InactivityTracker,
) : ViewModel() {

    val state: StateFlow<SettingsTabUiState> = combine(
        userScope.activeUserId.flatMapLatest { id ->
            if (id == null) flowOf<UserEntity?>(null) else userRepository.observeUser(id)
        },
        userScope.activeUserId.flatMapLatest { id ->
            if (id == null) flowOf<List<AimAllocationEntity>>(emptyList())
            else aimAllocationRepository.observeForUser(id)
        },
        globalSettingsRepository.observe(),
        holdingRepository.observeAll(),
        userRepository.observeActiveUsers(),
        userScope.activeUserId.flatMapLatest { id ->
            if (id == null) flowOf(emptyList())
            else loanRepository.observeLoansForUser(id)
        },
    ) { values: Array<Any?> ->
        @Suppress("UNCHECKED_CAST")
        val user = values[0] as UserEntity?
        @Suppress("UNCHECKED_CAST")
        val aim = values[1] as List<AimAllocationEntity>
        val global = values[2] as GlobalSettingsEntity?
        @Suppress("UNCHECKED_CAST")
        val allHoldings = values[3] as List<com.fintrack.data.db.entities.HoldingEntity>
        @Suppress("UNCHECKED_CAST")
        val users = values[4] as List<UserEntity>
        @Suppress("UNCHECKED_CAST")
        val loans = values[5] as List<com.fintrack.data.db.entities.LoanEntity>
        SettingsTabUiState(
            activeUser = user,
            activeUserAim = aim,
            global = global,
            activeHoldingCount = allHoldings.count { it.isActive },
            totalHoldingCount = allHoldings.size,
            userCount = users.size,
            activeLoanCount = loans.count { it.isActive },
            totalLoanCount = loans.size,
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
