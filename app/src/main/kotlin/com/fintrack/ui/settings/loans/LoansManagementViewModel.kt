package com.fintrack.ui.settings.loans

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.data.repo.LoanRepository
import com.fintrack.domain.UserScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject

data class LoansManagementUiState(
    val loans: List<LoanEntity> = emptyList(),
    val showInactive: Boolean = false,
    val error: String? = null,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class LoansManagementViewModel @Inject constructor(
    private val loanRepository: LoanRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val showInactive = kotlinx.coroutines.flow.MutableStateFlow(false)
    private val errorState = kotlinx.coroutines.flow.MutableStateFlow<String?>(null)

    val state: StateFlow<LoansManagementUiState> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(emptyList())
            else loanRepository.observeLoansForUser(uid)
        }
        .let { loans ->
            kotlinx.coroutines.flow.combine(loans, showInactive, errorState) { l, inactive, err ->
                LoansManagementUiState(
                    loans = if (inactive) l else l.filter { it.isActive },
                    showInactive = inactive,
                    error = err,
                )
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), LoansManagementUiState())

    fun setShowInactive(value: Boolean) {
        showInactive.value = value
    }

    fun createLoan(
        name: String,
        originalAmount: BigDecimal,
        takenDate: LocalDate,
        monthlyEmi: BigDecimal,
    ) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            try {
                loanRepository.createLoan(uid, name, originalAmount, takenDate, monthlyEmi)
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not add loan"
            }
        }
    }

    fun updateLoan(loan: LoanEntity) {
        viewModelScope.launch {
            try {
                loanRepository.updateLoan(loan)
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not update loan"
            }
        }
    }

    fun markClosed(loanId: UUID, closedDate: LocalDate) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            try {
                loanRepository.markClosed(uid, loanId, closedDate)
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not close loan"
            }
        }
    }

    fun deleteLoan(loanId: UUID) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            try {
                loanRepository.deleteLoan(uid, loanId)
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not delete loan"
            }
        }
    }

    fun clearError() {
        errorState.value = null
    }
}
