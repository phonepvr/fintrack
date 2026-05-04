package com.fintrack.ui.home.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.domain.analytics.SnapshotAnalytics
import com.fintrack.domain.model.AssetClass
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatPercent
import com.fintrack.domain.util.formatSignedCurrency
import com.fintrack.domain.util.formatSignedPercent
import com.fintrack.ui.theme.DriftOff
import com.fintrack.ui.theme.DriftWithin
import java.math.BigDecimal
import java.util.UUID

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

    if (analytics.isEmpty() && headline == null) {
        EmptyOverview()
        return
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        headline?.let { item("headline") { HeadlineCardsRow(it) } }

        item("filters") {
            PeriodChips(selected = period, onSelected = viewModel::setPeriod)
        }
        item("chart_tabs") {
            ChartViewChips(selected = chartView, onSelected = viewModel::setChartView)
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
            label = "Total portfolio",
            value = formatIndianCurrency(card.totalPortfolio),
            sub = card.deltaAbsolute?.let { abs ->
                val pct = card.deltaPercent
                if (pct == null) formatSignedCurrency(abs)
                else "${formatSignedCurrency(abs)} (${formatSignedPercent(pct)})"
            },
            subColor = card.deltaAbsolute?.let {
                if (it.signum() >= 0) DriftWithin else DriftOff
            } ?: MaterialTheme.colorScheme.onSurfaceVariant,
        )
        HeadlineCardBox(
            modifier = Modifier.weight(1f),
            label = "% of earnings",
            value = formatPercent(card.percentOfEarnings),
        )
        HeadlineCardBox(
            modifier = Modifier.weight(1f),
            label = "Allocation",
            value = "${card.classesWithinTarget}/${card.totalClasses}",
            sub = "within target",
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
        modifier = Modifier.fillMaxWidth(),
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
        }
    }
}

private fun seriesFor(view: ChartView, analytics: List<SnapshotAnalytics>): List<ChartSeries> {
    if (analytics.isEmpty()) return emptyList()
    return when (view) {
        ChartView.TOTAL -> listOf(
            ChartSeries(
                name = "Total",
                color = TotalLineColor,
                points = analytics.map { it.date to it.totalPortfolio },
            ),
        )

        ChartView.BY_ASSET_CLASS -> AssetClass.entries.map { ac ->
            ChartSeries(
                name = ac.displayName,
                color = AssetClassColor(ac),
                points = analytics.map { a -> a.date to a.byAssetClass.getValue(ac).current },
            )
        }

        ChartView.FIXED_VS_INVESTMENT -> listOf(
            ChartSeries(
                name = "Fixed Return",
                color = AssetClassColor(AssetClass.FIXED_RETURN),
                points = analytics.map { a ->
                    a.date to a.byAssetClass.getValue(AssetClass.FIXED_RETURN).current
                },
            ),
            ChartSeries(
                name = "Investment",
                color = TotalLineColor,
                points = analytics.map { it.date to it.investmentValue },
            ),
        )

        ChartView.PERCENT_OF_EARNINGS -> listOf(
            ChartSeries(
                name = "% of earnings",
                color = TotalLineColor,
                points = analytics.map { it.date to it.percentOfEarnings },
            ),
        )
    }
}

private val TotalLineColor = Color(0xFF1976D2)
private fun AssetClassColor(ac: AssetClass): Color = when (ac) {
    AssetClass.MF_NPS -> Color(0xFF1976D2)
    AssetClass.EQUITY -> Color(0xFFE91E63)
    AssetClass.FIXED_RETURN -> Color(0xFF2E7D32)
    AssetClass.CRYPTO -> Color(0xFFF57C00)
}

@Composable
private fun HistoryTableHeader() {
    Row(modifier = Modifier.fillMaxWidth().padding(top = 8.dp)) {
        TableCell("Date", weight = 1.2f, header = true)
        TableCell("Fixed", weight = 1f, header = true)
        TableCell("Inv", weight = 1f, header = true)
        TableCell("Total", weight = 1.1f, header = true)
        TableCell("% earn", weight = 0.9f, header = true)
        TableCell("Δ Total", weight = 1.1f, header = true)
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
}

@Composable
private fun HistoryTableRow(row: HistoryRow, onTap: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onTap)
            .padding(vertical = 8.dp),
    ) {
        TableCell(row.analytics.date.toString(), weight = 1.2f)
        TableCell(formatIndianCurrency(row.fixedReturn), weight = 1f)
        TableCell(formatIndianCurrency(row.investmentValue), weight = 1f)
        TableCell(formatIndianCurrency(row.total), weight = 1.1f, bold = true)
        TableCell(formatPercent(row.analytics.percentOfEarnings), weight = 0.9f)
        TableCell(
            text = row.deltaTotal?.let(::formatSignedCurrency).orEmpty(),
            weight = 1.1f,
            color = row.deltaTotal?.let {
                if (it.signum() >= 0) DriftWithin else DriftOff
            } ?: MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
}

@Composable
private fun RowScope.TableCell(
    text: String,
    weight: Float,
    header: Boolean = false,
    bold: Boolean = false,
    color: Color = if (header) MaterialTheme.colorScheme.onSurfaceVariant
                   else MaterialTheme.colorScheme.onSurface,
) {
    Text(
        text = text,
        modifier = Modifier.weight(weight),
        style = if (header) MaterialTheme.typography.labelMedium else MaterialTheme.typography.bodySmall,
        fontWeight = if (bold) FontWeight.SemiBold else FontWeight.Normal,
        color = color,
        maxLines = 1,
    )
}
