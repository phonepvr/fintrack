package com.fintrack.ui.home.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.AimAllocationRepository
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.LoanRepository
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.data.repo.TaxonomyRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.analytics.SnapshotAnalytics
import com.fintrack.domain.analytics.SnapshotAnalyticsCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import java.math.BigDecimal
import javax.inject.Inject

enum class Period(val label: String) {
    THREE_MONTHS("3M"),
    SIX_MONTHS("6M"),
    ONE_YEAR("1Y"),
    ALL("All"),
}

enum class ChartView(val label: String) {
    WEALTH_EARNING_INVESTMENT("Wealth/Earn/Inv"),
    BY_ASSET_CLASS("By class"),
    FIXED_VS_INVESTMENT("Fixed vs Inv"),
    PERCENT_OF_EARNINGS("% of earnings"),
}

data class HistoryRow(
    val analytics: SnapshotAnalytics,
    val totalAssets: BigDecimal,
    val totalLiabilities: BigDecimal,
    val totalInvested: BigDecimal,
    val netWorth: BigDecimal,
    val earningsRupees: BigDecimal,
    val gainPercent: BigDecimal?,                // (assets − invested) / invested × 100
    val deltaNetWorth: BigDecimal?,
)

data class HeadlineCard(
    val netWorth: BigDecimal,
    val deltaAbsolute: BigDecimal?,
    val deltaPercent: BigDecimal?,
    val totalInvested: BigDecimal,
    val gainPercent: BigDecimal?,
    val percentOfEarnings: BigDecimal,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val snapshotRepository: SnapshotRepository,
    private val holdingRepository: HoldingRepository,
    private val taxonomyRepository: TaxonomyRepository,
    private val aimRepository: AimAllocationRepository,
    private val loanRepository: LoanRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val _period = MutableStateFlow(Period.ALL)
    val period: StateFlow<Period> = _period.asStateFlow()

    private val _chartView = MutableStateFlow(ChartView.WEALTH_EARNING_INVESTMENT)
    val chartView: StateFlow<ChartView> = _chartView.asStateFlow()

    fun setPeriod(p: Period) { _period.value = p }
    fun setChartView(v: ChartView) { _chartView.value = v }

    /**
     * Computed analytics for every snapshot in the active user's history,
     * sorted ascending by date so deltas align with chart x-positions.
     *
     * v3: now also pulls the AssetClass + SubBucket taxonomy + AimAllocations
     * + Loan/LoanValue rows for the user; analytics keys/maps are UUID-driven.
     */
    val allAnalytics: StateFlow<List<SnapshotAnalytics>> = userScope.activeUserId
        .flatMapLatest { userId ->
            if (userId == null) flowOf(emptyList())
            else combineAnalyticsInputs(userId)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private fun combineAnalyticsInputs(userId: java.util.UUID) = combine(
        snapshotRepository.observeForUser(userId),
        snapshotRepository.observeAllValuesForUser(userId),
        holdingRepository.observeAll(),
        taxonomyRepository.observeSubBuckets(),
        taxonomyRepository.observeAssetClasses(),
        aimRepository.observeForUser(userId),
        loanRepository.observeLoansForUser(userId),
        loanRepository.observeAllValuesForUser(userId),
    ) { values: Array<Any?> ->
        @Suppress("UNCHECKED_CAST")
        val snapshots = values[0] as List<com.fintrack.data.db.entities.SnapshotEntity>
        @Suppress("UNCHECKED_CAST")
        val allValues = values[1] as List<com.fintrack.data.db.entities.HoldingValueEntity>
        @Suppress("UNCHECKED_CAST")
        val catalog = values[2] as List<com.fintrack.data.db.entities.HoldingEntity>
        @Suppress("UNCHECKED_CAST")
        val subBuckets = values[3] as List<com.fintrack.data.db.entities.SubBucketEntity>
        @Suppress("UNCHECKED_CAST")
        val assetClasses = values[4] as List<com.fintrack.data.db.entities.AssetClassEntity>
        @Suppress("UNCHECKED_CAST")
        val aim = values[5] as List<com.fintrack.data.db.entities.AimAllocationEntity>
        @Suppress("UNCHECKED_CAST")
        val loans = values[6] as List<com.fintrack.data.db.entities.LoanEntity>
        @Suppress("UNCHECKED_CAST")
        val loanValues = values[7] as List<com.fintrack.data.db.entities.LoanValueEntity>

        val hvBySnap = allValues.groupBy { it.snapshotId }
        val lvBySnap = loanValues.groupBy { it.snapshotId }
        val chrono = snapshots.sortedBy { it.snapshotDate }
        val list = mutableListOf<SnapshotAnalytics>()
        var prevNetWorth: BigDecimal? = null
        for (snap in chrono) {
            val a = SnapshotAnalyticsCalculator.compute(
                snapshot = snap,
                values = hvBySnap[snap.id].orEmpty(),
                catalog = catalog,
                subBuckets = subBuckets,
                assetClasses = assetClasses,
                aimAllocations = aim,
                loans = loans,
                loanValues = lvBySnap[snap.id].orEmpty(),
                previousNetWorth = prevNetWorth,
            )
            list += a
            prevNetWorth = a.netWorth
        }
        list.toList()
    }

    val filteredAnalytics: StateFlow<List<SnapshotAnalytics>> =
        combine(allAnalytics, _period) { all, p ->
            val cutoff = cutoffFor(p, today())
            if (cutoff == null) all else all.filter { it.date >= cutoff }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val headline: StateFlow<HeadlineCard?> = allAnalytics
        .map { list -> list.lastOrNull()?.let(::buildHeadline) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)

    /** Newest first for the history table. */
    val history: StateFlow<List<HistoryRow>> = filteredAnalytics
        .map { list ->
            list.map { a ->
                HistoryRow(
                    analytics = a,
                    totalAssets = a.totalAssets,
                    totalLiabilities = a.totalLiabilities,
                    totalInvested = a.totalInvested,
                    netWorth = a.netWorth,
                    earningsRupees = a.earningsInCr.multiply(BigDecimal("10000000")),
                    gainPercent = gainPercent(a.totalAssets, a.totalInvested),
                    deltaNetWorth = a.deltaAbsolute,
                )
            }.reversed()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private fun buildHeadline(latest: SnapshotAnalytics): HeadlineCard = HeadlineCard(
        netWorth = latest.netWorth,
        deltaAbsolute = latest.deltaAbsolute,
        deltaPercent = latest.deltaPercent,
        totalInvested = latest.totalInvested,
        gainPercent = gainPercent(latest.totalAssets, latest.totalInvested),
        percentOfEarnings = latest.percentOfEarnings,
    )

    private fun gainPercent(totalAssets: BigDecimal, totalInvested: BigDecimal): BigDecimal? {
        if (totalInvested.signum() == 0) return null
        return totalAssets.subtract(totalInvested)
            .divide(totalInvested, 6, java.math.RoundingMode.HALF_UP)
            .multiply(BigDecimal("100"))
            .setScale(2, java.math.RoundingMode.HALF_UP)
    }

    private fun today(): LocalDate =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    private fun cutoffFor(period: Period, today: LocalDate): LocalDate? = when (period) {
        Period.THREE_MONTHS -> today.minus(DatePeriod(months = 3))
        Period.SIX_MONTHS -> today.minus(DatePeriod(months = 6))
        Period.ONE_YEAR -> today.minus(DatePeriod(years = 1))
        Period.ALL -> null
    }
}
