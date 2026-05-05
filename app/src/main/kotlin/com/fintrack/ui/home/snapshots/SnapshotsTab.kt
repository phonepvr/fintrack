package com.fintrack.ui.home.snapshots

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.R
import com.fintrack.domain.snapshots.SnapshotDeleteImpact
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatPercent
import com.fintrack.domain.util.formatSignedCurrency
import com.fintrack.domain.util.formatSignedPercent
import com.fintrack.domain.util.formatted
import com.fintrack.ui.snapshots.common.SnapshotDeleteDialog
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.UUID

@Composable
fun SnapshotsTab(
    onNewSnapshot: () -> Unit,
    onSnapshotDetail: (UUID) -> Unit,
    onEditSnapshot: (UUID) -> Unit,
    viewModel: SnapshotsListViewModel = hiltViewModel(),
) {
    val items by viewModel.items.collectAsState()
    val streak by viewModel.streakMeta.collectAsState()

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onNewSnapshot,
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text("New snapshot") },
            )
        },
    ) { padding ->
        if (items.isEmpty()) {
            EmptyState(modifier = Modifier.padding(padding))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(top = 8.dp, bottom = 96.dp),
            ) {
                if (streak.showNudge) {
                    item("nudge") { StreakNudgeBanner(onNewSnapshot = onNewSnapshot) }
                }
                items(items, key = { it.id }) { item ->
                    SnapshotRow(
                        item = item,
                        streakMonths = if (item.isLatest) streak.currentStreakMonths else 0,
                        onTap = { onSnapshotDetail(item.id) },
                        onEdit = { onEditSnapshot(item.id) },
                        onDuplicate = {
                            viewModel.duplicateSnapshot(item.id, todayLocal())
                        },
                        onDelete = { viewModel.deleteSnapshot(item.id) },
                        impactProvider = { viewModel.impactFor(item.id) },
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f))
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SnapshotRow(
    item: SnapshotListItem,
    streakMonths: Int,
    onTap: () -> Unit,
    onEdit: () -> Unit,
    onDuplicate: () -> Unit,
    onDelete: () -> Unit,
    impactProvider: () -> SnapshotDeleteImpact,
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var deletePromptOpen by remember { mutableStateOf(false) }
    var deleteImpact by remember { mutableStateOf(SnapshotDeleteImpact()) }
    val showLiabilities = item.totalLiabilities.signum() != 0

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .combinedClickable(
                onClick = onTap,
                onLongClick = { menuExpanded = true },
            )
            .padding(horizontal = 20.dp, vertical = 14.dp),
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        item.date.formatted(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        formatIndianCurrency(item.netWorth),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.SemiBold,
                    )
                    com.fintrack.ui.metrics.LabelWithHelp(
                        text = "Net Worth",
                        info = com.fintrack.ui.metrics.Metrics.NET_WORTH,
                    )
                    if (item.earningsInCr.signum() != 0) {
                        Text(
                            "is ${formatPercent(item.percentOfEarnings)} of " +
                                "Earnings (${formatIndianCurrency(
                                    item.earningsInCr.multiply(CRORE_RUPEES),
                                )})",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
                Column(horizontalAlignment = Alignment.End) {
                    if (item.isLatest) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (streakMonths > 0) {
                                StreakChip(months = streakMonths)
                                Spacer(Modifier.size(6.dp))
                            }
                            Text(
                                "Latest",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                        Spacer(Modifier.size(2.dp))
                    }
                    if (item.deltaAbsolute != null && item.deltaPercent != null) {
                        Text(
                            formatSignedCurrency(item.deltaAbsolute),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            formatSignedPercent(item.deltaPercent),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    } else {
                        Text(
                            "First snapshot",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
            Spacer(Modifier.size(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                MetricCell(
                    label = "Assets",
                    value = formatIndianCurrency(item.totalAssets),
                    info = com.fintrack.ui.metrics.Metrics.TOTAL_ASSETS,
                    modifier = Modifier.weight(1f),
                )
                if (showLiabilities) {
                    MetricCell(
                        label = "Liabilities",
                        value = formatIndianCurrency(item.totalLiabilities),
                        info = com.fintrack.ui.metrics.Metrics.TOTAL_LIABILITIES,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
            Spacer(Modifier.size(6.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                MetricCell(
                    label = "Investment Value",
                    value = formatIndianCurrency(item.investmentValue),
                    info = com.fintrack.ui.metrics.Metrics.INVESTMENT_VALUE,
                    modifier = Modifier.weight(1f),
                )
                MetricCell(
                    label = "Fixed Returns",
                    value = formatIndianCurrency(item.fixedReturns),
                    info = com.fintrack.ui.metrics.Metrics.FIXED_RETURNS,
                    modifier = Modifier.weight(1f),
                )
            }
            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false },
            ) {
                DropdownMenuItem(
                    text = { Text("Edit") },
                    onClick = {
                        menuExpanded = false
                        onEdit()
                    },
                )
                DropdownMenuItem(
                    text = { Text("Duplicate to today") },
                    onClick = {
                        menuExpanded = false
                        onDuplicate()
                    },
                )
                DropdownMenuItem(
                    text = { Text("Delete") },
                    onClick = {
                        menuExpanded = false
                        deleteImpact = impactProvider()
                        deletePromptOpen = true
                    },
                )
            }
        }
    }

    if (deletePromptOpen) {
        SnapshotDeleteDialog(
            date = item.date,
            impact = deleteImpact,
            onConfirm = {
                deletePromptOpen = false
                onDelete()
            },
            onDismiss = { deletePromptOpen = false },
        )
    }
}

@Composable
private fun MetricCell(
    label: String,
    value: String,
    info: com.fintrack.ui.metrics.MetricInfo,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
    ) {
        Column {
            com.fintrack.ui.metrics.LabelWithHelp(text = label, info = info)
            Text(
                value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
private fun StreakChip(months: Int) {
    Box(
        modifier = Modifier
            .background(
                MaterialTheme.colorScheme.tertiaryContainer,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(50),
            )
            .padding(horizontal = 8.dp, vertical = 2.dp),
    ) {
        Text(
            text = if (months == 1) "🔥 1 mo" else "🔥 $months mos",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            fontWeight = FontWeight.SemiBold,
        )
    }
}

@Composable
private fun StreakNudgeBanner(onNewSnapshot: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = 16.dp, vertical = 10.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(modifier = Modifier.padding(end = 8.dp)) {
                Text(
                    "Don't break the streak",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
                Text(
                    "Take a snapshot before the month ends to keep your streak alive.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onTertiaryContainer,
                )
            }
            TextButton(onClick = onNewSnapshot) { Text("Add now") }
        }
    }
}

@Composable
private fun EmptyState(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = stringResource(R.string.snapshots_empty_title),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = stringResource(R.string.snapshots_empty_message),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}

internal fun todayLocal(): LocalDate =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

/** 1 crore in rupees — earningsInCr * this gives the absolute rupee value
 *  for `formatIndianCurrency` to render as "₹X Cr" / "₹Y L". */
private val CRORE_RUPEES: java.math.BigDecimal = java.math.BigDecimal("10000000")
