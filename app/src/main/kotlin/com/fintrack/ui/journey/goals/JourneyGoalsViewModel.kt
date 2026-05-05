package com.fintrack.ui.journey.goals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.GoalRepository
import com.fintrack.data.repo.LoanRepository
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.goals.GoalCalculator
import com.fintrack.domain.goals.GoalProgress
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.math.BigDecimal
import javax.inject.Inject

/**
 * Latest-snapshot net-worth + liabilities for the active user, reused by
 * the goals card and chart overlay.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class JourneyGoalsViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val snapshotRepository: SnapshotRepository,
    private val loanRepository: LoanRepository,
    private val userScope: UserScope,
) : ViewModel() {

    val progresses: StateFlow<List<GoalProgress>> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(emptyList())
            else combine(
                goalRepository.observeActive(uid),
                snapshotRepository.observeForUser(uid),
                snapshotRepository.observeAllValuesForUser(uid),
                loanRepository.observeAllValuesForUser(uid),
            ) { goals, snaps, holdingValues, loanValues ->
                if (snaps.isEmpty() || goals.isEmpty()) return@combine emptyList()
                val latest = snaps.maxByOrNull { it.snapshotDate } ?: return@combine emptyList()
                val hv = holdingValues.filter { it.snapshotId == latest.id }
                val lv = loanValues.filter { it.snapshotId == latest.id }
                val assets = hv.fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
                val liabilities = lv.fold(BigDecimal.ZERO) { acc, v -> acc + v.outstanding }
                val netWorth = assets - liabilities
                val today = todayLocal()
                goals.map { g ->
                    GoalCalculator.progress(
                        goal = g,
                        latestNetWorth = netWorth,
                        latestLiabilities = liabilities,
                        today = today,
                    )
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}

private fun todayLocal(): LocalDate =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
