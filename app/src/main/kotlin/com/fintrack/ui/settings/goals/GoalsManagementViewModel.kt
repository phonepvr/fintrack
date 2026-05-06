package com.fintrack.ui.settings.goals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.data.repo.GoalRepository
import com.fintrack.data.repo.LoanRepository
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.model.GoalType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject

data class GoalsUiState(
    val goals: List<GoalEntity> = emptyList(),
    val error: String? = null,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GoalsManagementViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val snapshotRepository: SnapshotRepository,
    private val loanRepository: LoanRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val errorState = MutableStateFlow<String?>(null)

    val state: StateFlow<GoalsUiState> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(emptyList())
            else goalRepository.observeAll(uid)
        }
        .let { goals ->
            combine(goals, errorState) { g, err ->
                GoalsUiState(g, err)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), GoalsUiState())

    /**
     * Latest-snapshot total liabilities for the active user, captured into a
     * DEBT_FREE goal at creation time so progress can animate from
     * "₹X.X L outstanding" down to ₹0 instead of flipping binary.
     */
    private val latestLiabilities: StateFlow<BigDecimal> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(BigDecimal.ZERO)
            else combine(
                snapshotRepository.observeForUser(uid),
                loanRepository.observeAllValuesForUser(uid),
            ) { snaps, loanValues ->
                val latest = snaps.maxByOrNull { it.snapshotDate } ?: return@combine BigDecimal.ZERO
                loanValues.filter { it.snapshotId == latest.id }
                    .fold(BigDecimal.ZERO) { acc, v -> acc + v.outstanding }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), BigDecimal.ZERO)

    fun create(name: String, type: GoalType, target: BigDecimal, targetDate: LocalDate) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            try {
                goalRepository.create(
                    userId = uid,
                    name = name,
                    goalType = type,
                    targetNetWorth = target,
                    targetDate = targetDate,
                    startingLiabilities = if (type == GoalType.DEBT_FREE) latestLiabilities.value else BigDecimal.ZERO,
                )
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not create goal"
            }
        }
    }

    fun update(goal: GoalEntity) {
        viewModelScope.launch {
            try {
                goalRepository.update(goal)
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not update goal"
            }
        }
    }

    /**
     * Edit-flow update that takes the user-edited fields and the original
     * goal. If the type changed, re-captures `startingLiabilities` from
     * the latest snapshot (when switching to DEBT_FREE) and clears any
     * existing `achievedAt` since the achievement criterion is no longer
     * the same. When the type is unchanged, the rest of the entity (incl.
     * `startingLiabilities` and `achievedAt`) is preserved.
     */
    fun applyEdit(
        original: GoalEntity,
        name: String,
        type: GoalType,
        target: BigDecimal,
        targetDate: LocalDate,
    ) {
        viewModelScope.launch {
            try {
                val typeChanged = original.goalType != type
                val nextStartingLiabilities = when {
                    !typeChanged -> original.startingLiabilities
                    type == GoalType.DEBT_FREE -> latestLiabilities.value
                    else -> BigDecimal.ZERO
                }
                val nextAchievedAt = if (typeChanged) null else original.achievedAt
                goalRepository.update(
                    original.copy(
                        name = name,
                        goalType = type,
                        targetNetWorth = target,
                        targetDate = targetDate,
                        startingLiabilities = nextStartingLiabilities,
                        achievedAt = nextAchievedAt,
                    ),
                )
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not update goal"
            }
        }
    }

    fun archive(id: UUID) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch { goalRepository.archive(uid, id) }
    }

    fun delete(id: UUID) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch { goalRepository.delete(uid, id) }
    }

    fun clearError() { errorState.value = null }
}
