package com.fintrack.ui.home.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.data.repo.UserSettingsRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.analytics.DriftBand
import com.fintrack.domain.analytics.SnapshotAnalytics
import com.fintrack.domain.analytics.SnapshotAnalyticsCalculator
import com.fintrack.domain.model.AssetClass
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
    TOTAL("Total"),
    BY_ASSET_CLASS("By class"),
    FIXED_VS_INVESTMENT("Fixed vs Inv"),
    PERCENT_OF_EARNINGS("% of earnings"),
}

data class HistoryRow(
    val analytics: SnapshotAnalytics,
    val fixedReturn: BigDecimal,
    val investmentValue: BigDecimal,
    val total: BigDecimal,
    val deltaTotal: BigDecimal?,
)

data class HeadlineCard(
    val totalPortfolio: BigDecimal,
    val deltaAbsolute: BigDecimal?,
    val deltaPercent: BigDecimal?,
    val percentOfEarnings: BigDecimal,
    val classesWithinTarget: Int,
    val totalClasses: Int,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class OverviewViewModel @Inject constructor(
    private val snapshotRepository: SnapshotRepository,
    private val holdingRepository: HoldingRepository,
    private val userSettingsRepository: UserSettingsRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val _period = MutableStateFlow(Period.ALL)
    val period: StateFlow<Period> = _period.asStateFlow()

    private val _chartView = MutableStateFlow(ChartView.TOTAL)
    val chartView: StateFlow<ChartView> = _chartView.asStateFlow()

    fun setPeriod(p: Period) { _period.value = p }
    fun setChartView(v: ChartView) { _chartView.value = v }

    /**
     * Computed analytics for every snapshot in the active user's history,
     * sorted ascending by date so deltas align with chart x-positions.
     */
    val allAnalytics: StateFlow<List<SnapshotAnalytics>> = userScope.activeUserId
        .flatMapLatest { userId ->
            if (userId == null) flowOf(emptyList())
            else combine(
                snapshotRepository.observeForUser(userId),
                snapshotRepository.observeAllValuesForUser(userId),
                holdingRepository.observeAll(),
                userSettingsRepository.observe(userId),
            ) { snapshots, allValues, catalog, settings ->
                val byId = allValues.groupBy { it.snapshotId }
                val chrono = snapshots.sortedBy { it.snapshotDate }
                val list = mutableListOf<SnapshotAnalytics>()
                var prevTotal: BigDecimal? = null
                for (snap in chrono) {
                    val a = SnapshotAnalyticsCalculator.compute(
                        snapshot = snap,
                        values = byId[snap.id].orEmpty(),
                        catalog = catalog,
                        settings = settings,
                        previousTotalPortfolio = prevTotal,
                    )
                    list += a
                    prevTotal = a.totalPortfolio
                }
                list.toList()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

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
                    fixedReturn = a.byAssetClass.getValue(AssetClass.FIXED_RETURN).current,
                    investmentValue = a.investmentValue,
                    total = a.totalPortfolio,
                    deltaTotal = a.deltaAbsolute,
                )
            }.reversed()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private fun buildHeadline(latest: SnapshotAnalytics): HeadlineCard = HeadlineCard(
        totalPortfolio = latest.totalPortfolio,
        deltaAbsolute = latest.deltaAbsolute,
        deltaPercent = latest.deltaPercent,
        percentOfEarnings = latest.percentOfEarnings,
        classesWithinTarget = latest.byAssetClass.values.count { it.driftBand == DriftBand.WITHIN },
        totalClasses = AssetClass.entries.size,
    )

    private fun today(): LocalDate =
        Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    private fun cutoffFor(period: Period, today: LocalDate): LocalDate? = when (period) {
        Period.THREE_MONTHS -> today.minus(DatePeriod(months = 3))
        Period.SIX_MONTHS -> today.minus(DatePeriod(months = 6))
        Period.ONE_YEAR -> today.minus(DatePeriod(years = 1))
        Period.ALL -> null
    }
}
