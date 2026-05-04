package com.fintrack.ui.home.snapshots

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.domain.UserScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID
import javax.inject.Inject

data class SnapshotListItem(
    val id: UUID,
    val date: LocalDate,
    val totalPortfolio: BigDecimal,
    val percentOfEarnings: BigDecimal,
    val deltaAbsolute: BigDecimal?,
    val deltaPercent: BigDecimal?,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SnapshotsListViewModel @Inject constructor(
    private val snapshotRepository: SnapshotRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val crore = BigDecimal("10000000")
    private val hundred = BigDecimal("100")

    val items: StateFlow<List<SnapshotListItem>> = userScope.activeUserId
        .flatMapLatest { userId ->
            if (userId == null) {
                flowOf(emptyList())
            } else {
                combine(
                    snapshotRepository.observeForUser(userId),
                    snapshotRepository.observeAllValuesForUser(userId),
                ) { snapshots, allValues ->
                    val valuesBySnapshot = allValues.groupBy { it.snapshotId }
                    // Process chronologically (ascending) so we can compute deltas,
                    // then reverse to newest-first for display.
                    val chrono = snapshots.sortedBy { it.snapshotDate }
                    val totals = chrono.associate { snap ->
                        snap.id to (
                            valuesBySnapshot[snap.id].orEmpty()
                                .fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
                        )
                    }
                    chrono.mapIndexed { index, snapshot ->
                        val total = totals.getValue(snapshot.id)
                        val previous = if (index > 0) totals.getValue(chrono[index - 1].id) else null
                        val percentOfEarnings = if (snapshot.earningsInCr.signum() == 0) {
                            BigDecimal.ZERO
                        } else {
                            // total is in rupees; earnings is in crore. Convert and ratio.
                            total.divide(snapshot.earningsInCr.multiply(crore), 4, RoundingMode.HALF_UP)
                                .multiply(hundred)
                        }
                        val deltaAbs = previous?.let { total.subtract(it) }
                        val deltaPct = previous?.takeIf { it.signum() != 0 }?.let { prev ->
                            total.subtract(prev)
                                .divide(prev, 4, RoundingMode.HALF_UP)
                                .multiply(hundred)
                        }
                        SnapshotListItem(
                            id = snapshot.id,
                            date = snapshot.snapshotDate,
                            totalPortfolio = total,
                            percentOfEarnings = percentOfEarnings,
                            deltaAbsolute = deltaAbs,
                            deltaPercent = deltaPct,
                        )
                    }.reversed()
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun deleteSnapshot(snapshotId: UUID) {
        val userId = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            snapshotRepository.deleteSnapshot(userId, snapshotId)
        }
    }

    fun duplicateSnapshot(snapshotId: UUID, newDate: LocalDate, onCreated: (UUID) -> Unit = {}) {
        val userId = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            val newId = snapshotRepository.duplicateSnapshot(userId, snapshotId, newDate)
            onCreated(newId)
        }
    }
}
