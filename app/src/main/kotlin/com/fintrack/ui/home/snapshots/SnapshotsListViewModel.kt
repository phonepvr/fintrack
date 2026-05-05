package com.fintrack.ui.home.snapshots

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.LoanRepository
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

/**
 * Per spec §5.4: every snapshot card now shows
 *  - Net Worth (assets - liabilities), the headline number
 *  - Δ vs previous snapshot (absolute + percent), based on Net Worth
 *  - Assets / Liabilities / Invested / % of Earnings in a 2×2 grid
 *
 * `liabilities` is 0 in Phase B (Loan UI lands in Phase C); the row
 * already reads the data from `LoanRepository` so when seed loans
 * exist the math is right end-to-end.
 */
data class SnapshotListItem(
    val id: UUID,
    val date: LocalDate,
    val totalAssets: BigDecimal,
    val totalLiabilities: BigDecimal,
    val netWorth: BigDecimal,
    val invested: BigDecimal,
    val percentOfEarnings: BigDecimal,
    val deltaAbsolute: BigDecimal?,
    val deltaPercent: BigDecimal?,
    val isLatest: Boolean,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SnapshotsListViewModel @Inject constructor(
    private val snapshotRepository: SnapshotRepository,
    private val loanRepository: LoanRepository,
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
                    loanRepository.observeAllValuesForUser(userId),
                ) { snapshots, holdingValues, loanValues ->
                    val holdingsBySnapshot = holdingValues.groupBy { it.snapshotId }
                    val loansBySnapshot = loanValues.groupBy { it.snapshotId }
                    val chrono = snapshots.sortedBy { it.snapshotDate }
                    val perSnapshot = chrono.map { snap ->
                        val hvs = holdingsBySnapshot[snap.id].orEmpty()
                        val lvs = loansBySnapshot[snap.id].orEmpty()
                        val assets = hvs.fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
                        val liabilities = lvs.fold(BigDecimal.ZERO) { acc, v -> acc + v.outstanding }
                        val invested = hvs.fold(BigDecimal.ZERO) { acc, v ->
                            acc + (v.invested ?: BigDecimal.ZERO)
                        }
                        Triple(snap, assets - liabilities, Quad(assets, liabilities, invested, snap))
                    }
                    perSnapshot.mapIndexed { index, (snap, netWorth, q) ->
                        val previousNet = if (index > 0) perSnapshot[index - 1].second else null
                        val percentOfEarnings = if (snap.earningsInCr.signum() == 0) {
                            BigDecimal.ZERO
                        } else {
                            netWorth.divide(snap.earningsInCr.multiply(crore), 4, RoundingMode.HALF_UP)
                                .multiply(hundred)
                        }
                        val deltaAbs = previousNet?.let { netWorth.subtract(it) }
                        val deltaPct = previousNet?.takeIf { it.signum() != 0 }?.let { prev ->
                            netWorth.subtract(prev)
                                .divide(prev, 4, RoundingMode.HALF_UP)
                                .multiply(hundred)
                        }
                        SnapshotListItem(
                            id = snap.id,
                            date = snap.snapshotDate,
                            totalAssets = q.assets,
                            totalLiabilities = q.liabilities,
                            netWorth = netWorth,
                            invested = q.invested,
                            percentOfEarnings = percentOfEarnings,
                            deltaAbsolute = deltaAbs,
                            deltaPercent = deltaPct,
                            isLatest = index == perSnapshot.lastIndex,
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

    private data class Quad(
        val assets: BigDecimal,
        val liabilities: BigDecimal,
        val invested: BigDecimal,
        val snap: com.fintrack.data.db.entities.SnapshotEntity,
    )
}
