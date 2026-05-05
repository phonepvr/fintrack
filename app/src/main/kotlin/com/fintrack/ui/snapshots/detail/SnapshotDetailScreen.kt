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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.fintrack.domain.analytics.LoanDetailRow
import com.fintrack.domain.analytics.SnapshotAnalytics
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatPercent
import com.fintrack.domain.util.formatSignedCurrency
import com.fintrack.domain.util.formatSignedPercent
import com.fintrack.domain.util.formatted
import com.fintrack.ui.snapshots.common.SnapshotDeleteDialog
import com.fintrack.ui.theme.DriftOff
import com.fintrack.ui.theme.DriftWarn
import com.fintrack.ui.theme.DriftWithin
import java.util.UUID

@Composable
fun SnapshotDetailRoute(
    onBack: () -> Unit,
    onEdit: () -> Unit,
    onDeleted: () -> Unit,
    viewModel: SnapshotDetailViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    var menuExpanded by remember { mutableStateOf(false) }
    var deletePromptOpen by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text(state.analytics?.date?.formatted() ?: "Snapshot") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Filled.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "More actions")
                    }
                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false },
                    ) {
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = {
                                menuExpanded = false
                                deletePromptOpen = true
                            },
                        )
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
            ) { Text(state.error.orEmpty(), color = MaterialTheme.colorScheme.error) }

            analytics != null -> SnapshotDetailContent(
                analytics = analytics,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            )
        }
    }
    val analytics = state.analytics
    if (deletePromptOpen && analytics != null) {
        SnapshotDeleteDialog(
            date = analytics.date,
            impact = state.deleteImpact,
            onConfirm = {
                deletePromptOpen = false
                viewModel.delete(onDeleted)
            },
            onDismiss = { deletePromptOpen = false },
        )
    }
}

@Suppress("LongMethod")
@Composable
private fun SnapshotDetailContent(
    analytics: SnapshotAnalytics,
    modifier: Modifier = Modifier,
) {
    val assetClassOrder: List<UUID> = analytics.byAssetClass.keys.toList()

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item { AllocationTable(analytics, assetClassOrder) }
        item { FooterChips(analytics) }

        for (assetClassId in assetClassOrder) {
            val rows = analytics.rowsByAssetClass[assetClassId].orEmpty()
            if (rows.isEmpty()) continue
            val name = analytics.byAssetClass[assetClassId]?.assetClassName ?: "Asset class"
            item(key = "card_$assetClassId") {
                BreakdownCard(title = name, rows = rows)
            }
        }

        if (analytics.loanRows.isNotEmpty()) {
            item(key = "loans") { LoansCard(rows = analytics.loanRows, totalLiabilities = analytics.totalLiabilities) }
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
private fun AllocationTable(analytics: SnapshotAnalytics, assetClassOrder: List<UUID>) {
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
            for (assetClassId in assetClassOrder) {
                val summary = analytics.byAssetClass[assetClassId] ?: continue
                AllocationDataRow(summary)
            }
        }
    }
}

@Composable
private fun AllocationHeaderRow() {
    Row(modifier = Modifier.fillMaxWidth()) {
        AllocCell("Class", weight = 1.4f, header = true)
        AllocCell("Current", weight = 1.2f, header = true)
        AllocCell("Cur %", weight = 0.8f, header = true)
        AllocCell("Aim %", weight = 0.8f, header = true)
        AllocCell("Drift", weight = 1f, header = true)
    }
}

@Composable
private fun AllocationDataRow(summary: AssetClassSummary) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        AllocCell(summary.assetClassName, weight = 1.4f)
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
            FooterRow("Net Worth", formatIndianCurrency(analytics.netWorth), bold = true)
            FooterRow("Total Assets", formatIndianCurrency(analytics.totalAssets))
            if (analytics.totalLiabilities.signum() > 0) {
                FooterRow("Total Liabilities", formatIndianCurrency(analytics.totalLiabilities), color = DriftOff)
            }
            FooterRow("Total Invested", formatIndianCurrency(analytics.totalInvested))
            FooterRow("Total SIP", formatIndianCurrency(analytics.totalSip))
            FooterRow("% of Earnings", formatPercent(analytics.percentOfEarnings))
            FooterRow("Investment value", formatIndianCurrency(analytics.investmentValue))
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
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
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
            Text(
                title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 6.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            )
            // Group inside the card by sub-bucket so the user sees the v3 hierarchy.
            val bySub = rows.groupBy { it.subBucketName }
            for (subName in rows.map { it.subBucketName }.distinct()) {
                val sbRows = bySub[subName].orEmpty()
                if (sbRows.isEmpty()) continue
                if (rows.map { it.subBucketName }.distinct().size > 1) {
                    Spacer(Modifier.height(4.dp))
                    Text(
                        subName,
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 4.dp, bottom = 2.dp),
                    )
                }
                sbRows.forEach { row -> HoldingLine(row) }
            }
        }
    }
}

@Composable
private fun LoansCard(rows: List<LoanDetailRow>, totalLiabilities: java.math.BigDecimal) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "Loans",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 6.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            )
            rows.forEach { row ->
                Column(modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(row.name, style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Medium)
                        Text(formatIndianCurrency(row.outstanding),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = DriftOff)
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        MetaChip("Original", formatIndianCurrency(row.originalAmount))
                        MetaChip("EMI", formatIndianCurrency(row.monthlyEmi))
                        MetaChip("Taken", row.takenDate.formatted())
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 6.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            )
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total Liabilities", fontWeight = FontWeight.SemiBold)
                Text(
                    formatIndianCurrency(totalLiabilities),
                    fontWeight = FontWeight.SemiBold,
                    color = DriftOff,
                )
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
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            row.invested?.let { v -> MetaChip("Invested", formatIndianCurrency(v)) }
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
                Text(label, style = MaterialTheme.typography.labelSmall,
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

// Avoid an unused-import warning if someone builds without RoundedCornerShape ref.
@Suppress("unused")
private val ROUNDED_REF = RoundedCornerShape(0.dp)

// Keep `background` import live for future refactors.
@Suppress("unused")
private val BG_REF = Modifier.background(Color.Transparent)
