package com.fintrack.ui.home.snapshots

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.db.seed.SeedData
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.LoanRepository
import com.fintrack.data.repo.MilestoneRepository
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.data.repo.StreakRepository
import com.fintrack.data.repo.TaxonomyRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.snapshots.SnapshotDeleteImpact
import com.fintrack.domain.snapshots.SnapshotDeleteImpactAnalyzer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID
import javax.inject.Inject

/**
 * Per spec §5.4: every snapshot card shows
 *  - Net Worth (assets - liabilities), the headline number
 *  - Δ vs previous snapshot (absolute + percent), based on Net Worth
 *  - Assets / Liabilities / Invested / % of Earnings in a 2×2 grid
 *  - 🔥 streak chip on the latest card (when streak > 0)
 */
data class SnapshotListItem(
    val id: UUID,
    val date: LocalDate,
    val totalAssets: BigDecimal,
    val totalLiabilities: BigDecimal,
    val netWorth: BigDecimal,
    val invested: BigDecimal,
    val percentOfEarnings: BigDecimal,
    /** Sum of current values of holdings in the seeded "Fixed Return" asset class. */
    val fixedReturns: BigDecimal,
    /** Total assets minus the Fixed-Return total — i.e. the equity / risk
     *  side of the portfolio. Mirrors `SnapshotAnalytics.investmentValue`. */
    val investmentValue: BigDecimal,
    /** Earnings field captured on this snapshot, in crores. Used by the
     *  inline "is X% of Earnings (₹Y Cr)" subtitle next to Net Worth. */
    val earningsInCr: BigDecimal,
    val deltaAbsolute: BigDecimal?,
    val deltaPercent: BigDecimal?,
    val isLatest: Boolean,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SnapshotsListViewModel @Inject constructor(
    private val snapshotRepository: SnapshotRepository,
    private val loanRepository: LoanRepository,
    private val streakRepository: StreakRepository,
    private val milestoneRepository: MilestoneRepository,
    private val taxonomyRepository: TaxonomyRepository,
    private val holdingRepository: HoldingRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val crore = BigDecimal("10000000")
    private val hundred = BigDecimal("100")

    /**
     * Set of holding ids that belong to the seeded "Fixed Return" asset
     * class — used to compute the per-snapshot Fixed Returns total surfaced
     * in the bottom-right metric cell. Mirrors the fallback at
     * SnapshotAnalyticsCalculator.kt:189-192 so list and detail report the
     * same number even if the user has renamed/replaced the seeded class.
     */
    private val fixedReturnHoldingIds: Flow<Set<UUID>> = combine(
        taxonomyRepository.observeAssetClasses(),
        taxonomyRepository.observeSubBuckets(),
        holdingRepository.observeAll(),
    ) { classes, buckets, holdings ->
        val fixedClass = classes.firstOrNull { it.id == SeedData.FIXED_RETURN_ID }
            ?: classes.firstOrNull { it.name.startsWith("Fixed", ignoreCase = true) }
            ?: return@combine emptySet()
        val fixedBucketIds = buckets.filter { it.assetClassId == fixedClass.id }
            .map { it.id }
            .toSet()
        holdings.filter { it.subBucketId in fixedBucketIds }.map { it.id }.toSet()
    }

    val items: StateFlow<List<SnapshotListItem>> = userScope.activeUserId
        .flatMapLatest { userId ->
            if (userId == null) {
                flowOf(emptyList())
            } else {
                combine(
                    snapshotRepository.observeForUser(userId),
                    snapshotRepository.observeAllValuesForUser(userId),
                    loanRepository.observeAllValuesForUser(userId),
                    fixedReturnHoldingIds,
                ) { snapshots, holdingValues, loanValues, fixedHoldingIds ->
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
                        val fixedReturns = hvs.filter { it.holdingId in fixedHoldingIds }
                            .fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
                        Triple(snap, assets - liabilities, Quad(assets, liabilities, invested, fixedReturns))
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
                            fixedReturns = q.fixedReturns,
                            investmentValue = q.assets.subtract(q.fixedReturns),
                            earningsInCr = snap.earningsInCr,
                            deltaAbsolute = deltaAbs,
                            deltaPercent = deltaPct,
                            isLatest = index == perSnapshot.lastIndex,
                        )
                    }.reversed()
                }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    /**
     * Streak meta surfaced on the latest snapshot card and as a top-of-list
     * nudge banner per spec §6.2. Banner shows past the 15th of the current
     * month if the user has not stamped a snapshot in the current month yet.
     */
    val streakMeta: StateFlow<StreakMeta> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(null)
            else streakRepository.observeForUser(uid)
        }
        .map { state ->
            val today = todayLocal()
            val currentTag = "%04d-%02d".format(today.year, today.monthNumber)
            val showNudge = state != null &&
                today.dayOfMonth >= 15 &&
                state.lastSnapshotMonth != currentTag
            StreakMeta(
                currentStreakMonths = state?.currentStreakMonths ?: 0,
                longestStreakMonths = state?.longestStreakMonths ?: 0,
                showNudge = showNudge,
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), StreakMeta())

    private val snapshotEntities: StateFlow<List<SnapshotEntity>> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(emptyList()) else snapshotRepository.observeForUser(uid)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun impactFor(snapshotId: UUID): SnapshotDeleteImpact =
        SnapshotDeleteImpactAnalyzer.analyze(
            targetSnapshotId = snapshotId,
            allSnapshots = snapshotEntities.value,
            currentStreakMonths = streakMeta.value.currentStreakMonths,
        )

    fun deleteSnapshot(snapshotId: UUID) {
        val userId = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            snapshotRepository.deleteSnapshot(userId, snapshotId)
            streakRepository.recompute(userId)
            milestoneRepository.detectAndPersist(userId)
        }
    }

    fun duplicateSnapshot(snapshotId: UUID, newDate: LocalDate, onCreated: (UUID) -> Unit = {}) {
        val userId = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            val newId = snapshotRepository.duplicateSnapshot(userId, snapshotId, newDate)
            streakRepository.recompute(userId)
            milestoneRepository.detectAndPersist(userId)
            onCreated(newId)
        }
    }

    private data class Quad(
        val assets: BigDecimal,
        val liabilities: BigDecimal,
        val invested: BigDecimal,
        val fixedReturns: BigDecimal,
    )
}

data class StreakMeta(
    val currentStreakMonths: Int = 0,
    val longestStreakMonths: Int = 0,
    val showNudge: Boolean = false,
)
