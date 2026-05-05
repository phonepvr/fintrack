package com.fintrack.ui.home.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.data.db.seed.SeedData
import com.fintrack.domain.analytics.SnapshotAnalytics
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatPercent
import com.fintrack.domain.util.formatSignedCurrency
import com.fintrack.domain.util.formatSignedPercent
import com.fintrack.domain.util.formatted
import com.fintrack.ui.journey.wins.WinsTimeline
import com.fintrack.ui.theme.DriftOff
import com.fintrack.ui.theme.DriftWithin
import java.math.BigDecimal
import java.util.UUID

private enum class JourneySubTab(val label: String) {
    Charts("Charts"),
    Wins("Wins"),
}

@Composable
fun OverviewTab(
    onSnapshotDetail: (UUID) -> Unit,
    viewModel: OverviewViewModel = hiltViewModel(),
) {
    val headline by viewModel.headline.collectAsState()
    val analytics by viewModel.filteredAnalytics.collectAsState()
    val history by viewModel.history.collectAsState()
    val period by viewModel.period.collectAsState()
    val chartView by viewModel.chartView.collectAsState()

    var subTab by rememberSaveable { mutableIntStateOf(0) }

    if (analytics.isEmpty() && headline == null) {
        EmptyOverview()
        return
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = subTab) {
            JourneySubTab.entries.forEachIndexed { idx, t ->
                Tab(
                    selected = subTab == idx,
                    onClick = { subTab = idx },
                    text = { Text(t.label) },
                )
            }
        }
        when (JourneySubTab.entries[subTab]) {
            JourneySubTab.Charts -> ChartsContent(
                headline = headline,
                analytics = analytics,
                history = history,
                period = period,
                chartView = chartView,
                onPeriod = viewModel::setPeriod,
                onChartView = viewModel::setChartView,
                onSnapshotDetail = onSnapshotDetail,
            )
            JourneySubTab.Wins -> WinsTimeline()
        }
    }
}

@Composable
private fun ChartsContent(
    headline: HeadlineCard?,
    analytics: List<SnapshotAnalytics>,
    history: List<HistoryRow>,
    period: Period,
    chartView: ChartView,
    onPeriod: (Period) -> Unit,
    onChartView: (ChartView) -> Unit,
    onSnapshotDetail: (UUID) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        headline?.let { item("headline") { HeadlineCardsRow(it) } }

        item("filters") {
            PeriodChips(selected = period, onSelected = onPeriod)
        }
        item("chart_tabs") {
            ChartViewChips(selected = chartView, onSelected = onChartView)
        }
        item("chart") {
            ChartCard(view = chartView, analytics = analytics)
        }
        item("history_header") {
            HistoryTableHeader()
        }
        items(history, key = { it.analytics.snapshotId }) { row ->
            HistoryTableRow(row, onTap = { onSnapshotDetail(row.analytics.snapshotId) })
        }
    }
}


@Composable
private fun EmptyOverview() {
    Box(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            "Add at least one snapshot to see trends and allocation drift.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun HeadlineCardsRow(card: HeadlineCard) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
        HeadlineCardBox(
            modifier = Modifier.weight(1f),
            label = "Net Worth",
            value = formatIndianCurrency(card.netWorth),
            sub = card.deltaAbsolute?.let { abs ->
                val pct = card.deltaPercent
                if (pct == null) formatSignedCurrency(abs)
                else "${formatSignedCurrency(abs)} (${formatSignedPercent(pct)})"
            } ?: "First snapshot",
            subColor = card.deltaAbsolute?.let {
                if (it.signum() >= 0) DriftWithin else DriftOff
            } ?: MaterialTheme.colorScheme.onSurfaceVariant,
        )
        HeadlineCardBox(
            modifier = Modifier.weight(1f),
            label = "Total Invested",
            value = formatIndianCurrency(card.totalInvested),
            sub = card.gainPercent?.let { "Gain ${formatSignedPercent(it)}" },
            subColor = card.gainPercent?.let {
                if (it.signum() >= 0) DriftWithin else DriftOff
            } ?: MaterialTheme.colorScheme.onSurfaceVariant,
        )
        HeadlineCardBox(
            modifier = Modifier.weight(1f),
            label = "% of Earnings",
            value = formatPercent(card.percentOfEarnings),
        )
    }
}

@Composable
private fun HeadlineCardBox(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    sub: String? = null,
    subColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(2.dp))
            Text(value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            if (sub != null) {
                Spacer(Modifier.height(2.dp))
                Text(sub, style = MaterialTheme.typography.labelSmall, color = subColor)
            }
        }
    }
}

@Composable
private fun PeriodChips(selected: Period, onSelected: (Period) -> Unit) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Period.entries.forEach { p ->
            FilterChip(
                selected = p == selected,
                onClick = { onSelected(p) },
                label = { Text(p.label) },
            )
        }
    }
}

@Composable
private fun ChartViewChips(selected: ChartView, onSelected: (ChartView) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
    ) {
        ChartView.entries.forEach { v ->
            FilterChip(
                selected = v == selected,
                onClick = { onSelected(v) },
                label = { Text(v.label, style = MaterialTheme.typography.labelMedium) },
            )
        }
    }
}

@Composable
private fun ChartCard(view: ChartView, analytics: List<SnapshotAnalytics>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            val series = seriesFor(view, analytics)
            val isPercent = view == ChartView.PERCENT_OF_EARNINGS
            LineChart(
                series = series,
                valueFormatter = if (isPercent) { v -> formatPercent(v, decimals = 0) }
                                 else { v -> formatIndianCurrency(v) },
            )
            ChartLegend(series)
        }
    }
}

@Composable
private fun ChartLegend(series: List<ChartSeries>) {
    if (series.isEmpty()) return
    Spacer(Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        series.forEach { s ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp)
                        .padding(end = 4.dp),
                ) {
                    androidx.compose.foundation.Canvas(modifier = Modifier.fillMaxSize()) {
                        drawRect(s.color)
                    }
                }
                Spacer(Modifier.width(4.dp))
                Text(s.name, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

private fun seriesFor(view: ChartView, analytics: List<SnapshotAnalytics>): List<ChartSeries> {
    if (analytics.isEmpty()) return emptyList()
    return when (view) {
        ChartView.WEALTH_EARNING_INVESTMENT -> listOf(
            ChartSeries(
                name = "Wealth",
                color = WealthColor,
                points = analytics.map { it.date to it.netWorth },
            ),
            ChartSeries(
                name = "Earning",
                color = EarningColor,
                points = analytics.map { it.date to it.earningsInCr.multiply(BigDecimal("10000000")) },
            ),
            ChartSeries(
                name = "Investment",
                color = InvestmentColor,
                points = analytics.map { it.date to it.totalInvested },
            ),
        )

        ChartView.BY_ASSET_CLASS -> {
            val classOrder = analytics.last().byAssetClass.keys.toList()
            classOrder.mapIndexed { idx, acId ->
                val name = analytics.last().byAssetClass[acId]?.assetClassName ?: "Class"
                ChartSeries(
                    name = name,
                    color = AssetClassColor(idx),
                    points = analytics.map { a -> a.date to (a.byAssetClass[acId]?.current ?: BigDecimal.ZERO) },
                )
            }
        }

        ChartView.FIXED_VS_INVESTMENT -> listOf(
            ChartSeries(
                name = "Fixed Return",
                color = AssetClassColor(2),
                points = analytics.map { a ->
                    a.date to (a.byAssetClass[SeedData.FIXED_RETURN_ID]?.current ?: BigDecimal.ZERO)
                },
            ),
            ChartSeries(
                name = "Investment",
                color = InvestmentColor,
                points = analytics.map { it.date to it.investmentValue },
            ),
        )

        ChartView.PERCENT_OF_EARNINGS -> listOf(
            ChartSeries(
                name = "% of earnings",
                color = WealthColor,
                points = analytics.map { it.date to it.percentOfEarnings },
            ),
        )
    }
}

private val WealthColor = Color(0xFF1976D2)        // blue
private val EarningColor = Color(0xFF2E7D32)       // green
private val InvestmentColor = Color(0xFFF57C00)    // orange

private fun AssetClassColor(index: Int): Color = when (index % 4) {
    0 -> Color(0xFF1976D2)
    1 -> Color(0xFFE91E63)
    2 -> Color(0xFF2E7D32)
    else -> Color(0xFFF57C00)
}

@Composable
private fun HistoryTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(top = 8.dp),
    ) {
        FixedCell("Date", widthDp = 96, header = true)
        FixedCell("Net Worth", widthDp = 110, header = true)
        FixedCell("Δ", widthDp = 90, header = true)
        FixedCell("Assets", widthDp = 100, header = true)
        FixedCell("Liab", widthDp = 90, header = true)
        FixedCell("Invested", widthDp = 100, header = true)
        FixedCell("Earnings", widthDp = 100, header = true)
        FixedCell("% earn", widthDp = 70, header = true)
        FixedCell("Gain %", widthDp = 80, header = true)
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
}

@Composable
private fun HistoryTableRow(row: HistoryRow, onTap: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onTap)
            .horizontalScroll(rememberScrollState())
            .padding(vertical = 8.dp),
    ) {
        FixedCell(row.analytics.date.formatted(), widthDp = 96)
        FixedCell(formatIndianCurrency(row.netWorth), widthDp = 110, bold = true)
        FixedCell(
            text = row.deltaNetWorth?.let(::formatSignedCurrency).orEmpty(),
            widthDp = 90,
            color = row.deltaNetWorth?.let {
                if (it.signum() >= 0) DriftWithin else DriftOff
            } ?: MaterialTheme.colorScheme.onSurfaceVariant,
        )
        FixedCell(formatIndianCurrency(row.totalAssets), widthDp = 100)
        FixedCell(
            text = if (row.totalLiabilities.signum() == 0) "—"
            else formatIndianCurrency(row.totalLiabilities),
            widthDp = 90,
        )
        FixedCell(formatIndianCurrency(row.totalInvested), widthDp = 100)
        FixedCell(formatIndianCurrency(row.earningsRupees), widthDp = 100)
        FixedCell(formatPercent(row.analytics.percentOfEarnings), widthDp = 70)
        FixedCell(
            text = row.gainPercent?.let(::formatSignedPercent) ?: "—",
            widthDp = 80,
            color = row.gainPercent?.let {
                if (it.signum() >= 0) DriftWithin else DriftOff
            } ?: MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
}

@Composable
private fun FixedCell(
    text: String,
    widthDp: Int,
    header: Boolean = false,
    bold: Boolean = false,
    color: Color = if (header) MaterialTheme.colorScheme.onSurfaceVariant
                   else MaterialTheme.colorScheme.onSurface,
) {
    Text(
        text = text,
        modifier = Modifier
            .width(widthDp.dp)
            .padding(horizontal = 4.dp),
        style = if (header) MaterialTheme.typography.labelMedium else MaterialTheme.typography.bodySmall,
        fontWeight = if (bold) FontWeight.SemiBold else FontWeight.Normal,
        color = color,
        maxLines = 1,
    )
}

