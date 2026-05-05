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
import androidx.compose.material3.AlertDialog
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
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatPercent
import com.fintrack.domain.util.formatSignedCurrency
import com.fintrack.domain.util.formatSignedPercent
import com.fintrack.domain.util.formatted
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
                items(items, key = { it.id }) { item ->
                    SnapshotRow(
                        item = item,
                        onTap = { onSnapshotDetail(item.id) },
                        onEdit = { onEditSnapshot(item.id) },
                        onDuplicate = {
                            viewModel.duplicateSnapshot(item.id, todayLocal())
                        },
                        onDelete = { viewModel.deleteSnapshot(item.id) },
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
    onTap: () -> Unit,
    onEdit: () -> Unit,
    onDuplicate: () -> Unit,
    onDelete: () -> Unit,
) {
    var menuExpanded by remember { mutableStateOf(false) }
    var deletePromptOpen by remember { mutableStateOf(false) }
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
                Column {
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
                    Text(
                        "Net Worth",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    if (item.isLatest) {
                        Text(
                            "Latest",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary,
                        )
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
                MetricCell("Assets", formatIndianCurrency(item.totalAssets), Modifier.weight(1f))
                if (showLiabilities) {
                    MetricCell(
                        "Liabilities",
                        formatIndianCurrency(item.totalLiabilities),
                        Modifier.weight(1f),
                    )
                }
            }
            Spacer(Modifier.size(6.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                MetricCell(
                    "Invested",
                    formatIndianCurrency(item.invested),
                    Modifier.weight(1f),
                )
                MetricCell(
                    "% of earnings",
                    formatPercent(item.percentOfEarnings),
                    Modifier.weight(1f),
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
                        deletePromptOpen = true
                    },
                )
            }
        }
    }

    if (deletePromptOpen) {
        AlertDialog(
            onDismissRequest = { deletePromptOpen = false },
            title = { Text("Delete snapshot?") },
            text = {
                Text(
                    "Snapshot from ${item.date.formatted()} will be permanently removed " +
                        "from this profile.",
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    deletePromptOpen = false
                    onDelete()
                }) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { deletePromptOpen = false }) {
                    Text("Cancel")
                }
            },
        )
    }
}

@Composable
private fun MetricCell(label: String, value: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
            )
            .padding(horizontal = 10.dp, vertical = 8.dp),
    ) {
        Column {
            Text(
                label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
            )
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
