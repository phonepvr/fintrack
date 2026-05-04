package com.fintrack.ui.snapshots.detail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.domain.analytics.AssetClassSummary
import com.fintrack.domain.analytics.DriftBand
import com.fintrack.domain.analytics.HoldingDetailRow
import com.fintrack.domain.analytics.SnapshotAnalytics
import com.fintrack.domain.model.AssetClass
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatPercent
import com.fintrack.domain.util.formatSignedCurrency
import com.fintrack.domain.util.formatSignedPercent
import com.fintrack.ui.theme.DriftOff
import com.fintrack.ui.theme.DriftWarn
import com.fintrack.ui.theme.DriftWithin
import java.math.BigDecimal

@Composable
fun SnapshotDetailRoute(
    onBack: () -> Unit,
    onEdit: () -> Unit,
    viewModel: SnapshotDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text(state.analytics?.date?.toString() ?: "Snapshot") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")
                    }
                },
            )
        },
    ) { padding ->
        val analytics = state.analytics
        when {
            state.loading -> Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center,
            ) { CircularProgressIndicator() }

            state.error != null -> Box(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentAlignment = Alignment.Center,
            ) {
                Text(state.error.orEmpty(), color = MaterialTheme.colorScheme.error)
            }

            analytics != null -> SnapshotDetailContent(
                analytics = analytics,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            )
        }
    }
}

@Suppress("LongMethod")
@Composable
private fun SnapshotDetailContent(
    analytics: SnapshotAnalytics,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { AllocationTable(analytics) }
        item { FooterChips(analytics) }

        for (assetClass in AssetClass.entries) {
            val rows = analytics.rowsByAssetClass[assetClass].orEmpty()
            if (rows.isEmpty()) continue
            item(key = "card_$assetClass") {
                if (assetClass == AssetClass.FIXED_RETURN) {
                    FixedReturnBreakdownCard(rows = rows, savingsRollup = analytics.savingsRollup)
                } else {
                    BreakdownCard(title = assetClass.displayName, rows = rows)
                }
            }
        }

        // Banks card — separate from Fixed Return per spec §4.6.
        val banks = analytics.rowsByAssetClass[AssetClass.FIXED_RETURN]
            ?.filter { it.isBank }.orEmpty()
        if (banks.isNotEmpty()) {
            item(key = "banks") {
                BanksCard(rows = banks)
            }
        }

        analytics.notes?.let { notes ->
            item(key = "notes") {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Notes", style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(Modifier.height(4.dp))
                        Text(notes, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Composable
private fun AllocationTable(analytics: SnapshotAnalytics) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            AllocationHeaderRow()
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 6.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            )
            for (assetClass in AssetClass.entries) {
                val summary = analytics.byAssetClass.getValue(assetClass)
                AllocationDataRow(summary)
            }
        }
    }
}

@Composable
private fun AllocationHeaderRow() {
    Row(modifier = Modifier.fillMaxWidth()) {
        AllocCell("Class", weight = 1.4f, header = true)
        AllocCell("Risk", weight = 1f, header = true)
        AllocCell("Current", weight = 1.2f, header = true)
        AllocCell("Cur %", weight = 0.8f, header = true)
        AllocCell("Aim %", weight = 0.8f, header = true)
        AllocCell("Drift", weight = 1f, header = true)
    }
}

@Composable
private fun AllocationDataRow(summary: AssetClassSummary) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        AllocCell(summary.assetClass.displayName, weight = 1.4f)
        AllocCell(summary.assetClass.riskLabel, weight = 1f)
        AllocCell(formatIndianCurrency(summary.current), weight = 1.2f)
        AllocCell(formatPercent(summary.currentPct), weight = 0.8f)
        AllocCell("${summary.aimPct}%", weight = 0.8f)
        AllocCell(
            text = formatSignedPercent(summary.driftPct),
            weight = 1f,
            color = summary.driftBand.toColor(),
            bold = true,
        )
    }
}

@Composable
private fun RowScope.AllocCell(
    text: String,
    weight: Float,
    header: Boolean = false,
    color: Color = if (header) MaterialTheme.colorScheme.onSurfaceVariant
                   else MaterialTheme.colorScheme.onSurface,
    bold: Boolean = false,
) {
    Text(
        text = text,
        modifier = Modifier.weight(weight),
        style = if (header) MaterialTheme.typography.labelMedium else MaterialTheme.typography.bodySmall,
        fontWeight = if (bold) FontWeight.SemiBold else FontWeight.Normal,
        color = color,
    )
}

@Composable
private fun FooterChips(analytics: SnapshotAnalytics) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            FooterRow("Total Portfolio", formatIndianCurrency(analytics.totalPortfolio), bold = true)
            FooterRow("Total Invested", formatIndianCurrency(analytics.totalInvested))
            FooterRow("Total SIP", formatIndianCurrency(analytics.totalSip))
            FooterRow("% of Earnings", formatPercent(analytics.percentOfEarnings))
            FooterRow("Investment value", formatIndianCurrency(analytics.investmentValue))
            FooterRow("Fixed Return total",
                formatIndianCurrency(analytics.byAssetClass.getValue(AssetClass.FIXED_RETURN).current))
            val deltaAbs = analytics.deltaAbsolute
            val deltaPct = analytics.deltaPercent
            if (deltaAbs != null && deltaPct != null) {
                FooterRow(
                    label = "Δ vs previous",
                    value = "${formatSignedCurrency(deltaAbs)}  (${formatSignedPercent(deltaPct)})",
                    color = if (deltaAbs.signum() >= 0) DriftWithin else DriftOff,
                )
            }
        }
    }
}

@Composable
private fun FooterRow(
    label: String,
    value: String,
    bold: Boolean = false,
    color: Color = MaterialTheme.colorScheme.onSurface,
) {
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = color,
            fontWeight = if (bold) FontWeight.SemiBold else FontWeight.Normal,
        )
    }
}

@Composable
private fun BreakdownCard(title: String, rows: List<HoldingDetailRow>) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(title, style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            rows.forEach { row -> HoldingLine(row) }
        }
    }
}

@Composable
private fun FixedReturnBreakdownCard(
    rows: List<HoldingDetailRow>,
    savingsRollup: BigDecimal,
) {
    val nonBank = rows.filter { !it.isBank }
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Fixed Return", style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            nonBank.forEach { row -> HoldingLine(row) }
            // Spec §4.6: synthetic "Savings (Banks)" row inside Fixed Return card.
            if (savingsRollup.signum() > 0) {
                Spacer(Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainer)
                    .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Savings (Banks)", style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium)
                    Text(formatIndianCurrency(savingsRollup),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
private fun BanksCard(rows: List<HoldingDetailRow>) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("Banks", style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.SemiBold)
            HorizontalDivider(modifier = Modifier.padding(vertical = 6.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            rows.forEach { row ->
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(row.name, style = MaterialTheme.typography.bodyMedium)
                    Text(formatIndianCurrency(row.current), style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
private fun HoldingLine(row: HoldingDetailRow) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(row.name, style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium)
            Text(formatIndianCurrency(row.current),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold)
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            row.invested?.let { v ->
                MetaChip("Invested", formatIndianCurrency(v))
            }
            row.sip?.takeIf { it.signum() > 0 }?.let { v ->
                MetaChip("SIP", formatIndianCurrency(v))
            }
        }
    }
}

@Composable
private fun MetaChip(label: String, value: String) {
    AssistChip(
        onClick = {},
        label = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.width(6.dp))
                Text(value, style = MaterialTheme.typography.labelMedium)
            }
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    )
}

private fun DriftBand.toColor(): Color = when (this) {
    DriftBand.WITHIN -> DriftWithin
    DriftBand.WARN -> DriftWarn
    DriftBand.OFF -> DriftOff
}
