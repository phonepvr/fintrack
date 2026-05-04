package com.fintrack.ui.snapshots.entry

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.domain.util.formatIndianCurrency
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import java.math.BigDecimal
import java.util.UUID

@Composable
fun SnapshotEntryRoute(
    onDone: () -> Unit,
    viewModel: SnapshotEntryViewModel = hiltViewModel(),
) {
    // [snapshotId] (null for new) is read from SavedStateHandle by [SnapshotEntryViewModel].
    val state by viewModel.state.collectAsState()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(state.savedSnapshotId) {
        if (state.savedSnapshotId != null) onDone()
    }
    LaunchedEffect(state.error) {
        state.error?.let {
            snackbarState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    val runningTotal = remember(state.rows) {
        state.rows.fold(BigDecimal.ZERO) { acc, r -> acc + r.current.parseAmountOrZero() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text(if (state.editing) "Edit snapshot" else "New snapshot") },
                navigationIcon = {
                    IconButton(onClick = onDone) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(onClick = viewModel::save, enabled = !state.saving) {
                        Text(if (state.saving) "Saving…" else "Save")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
        bottomBar = {
            RunningTotalBar(value = runningTotal)
        },
    ) { padding ->
        if (state.loading) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        SnapshotEntryForm(
            state = state,
            onSetDate = viewModel::setDate,
            onSetEarnings = viewModel::setEarnings,
            onSetInvested = viewModel::setRowInvested,
            onSetCurrent = viewModel::setRowCurrent,
            onSetSip = viewModel::setRowSip,
            onSetNotes = viewModel::setNotes,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        )
    }
}

@Composable
private fun SnapshotEntryForm(
    state: SnapshotEntryUiState,
    onSetDate: (LocalDate) -> Unit,
    onSetEarnings: (String) -> Unit,
    onSetInvested: (UUID, String) -> Unit,
    onSetCurrent: (UUID, String) -> Unit,
    onSetSip: (UUID, String) -> Unit,
    onSetNotes: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    // Phase A: order rows by assetClass display order (the asset-class id is
    // already on each row from the VM hydrator). Phase B introduces the new
    // three-level grouping. For now we render assetClass → sub-bucket sub-headers.
    val orderedAssetClassNames: List<String> = remember(state.rows) {
        state.rows.map { it.assetClassName }.distinct()
    }
    val rowsByClass: Map<String, List<HoldingFieldsState>> = remember(state.rows) {
        state.rows.groupBy { it.assetClassName }
    }
    var showDatePicker by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    ) {
        item {
            DateField(state.date, onClick = { showDatePicker = true })
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = state.earnings,
                onValueChange = onSetEarnings,
                label = { Text("Earnings (₹ in cr)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(8.dp))
        }

        for (acName in orderedAssetClassNames) {
            val rows = rowsByClass[acName].orEmpty()
            if (rows.isEmpty()) continue
            item(key = "section_$acName") { SectionHeader(acName) }
            val rowsBySubBucket = rows.groupBy { it.subBucketName }
            for (sbName in rows.map { it.subBucketName }.distinct()) {
                val sbRows = rowsBySubBucket[sbName].orEmpty()
                if (sbRows.isEmpty()) continue
                item(key = "subhead_${acName}_$sbName") { SubSectionHeader(sbName) }
                items(sbRows, key = { "row_${it.holdingId}" }) { row ->
                    HoldingRow(
                        row = row,
                        onInvested = { onSetInvested(row.holdingId, it) },
                        onCurrent = { onSetCurrent(row.holdingId, it) },
                        onSip = { onSetSip(row.holdingId, it) },
                    )
                }
            }
        }

        item {
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = state.notes,
                onValueChange = onSetNotes,
                label = { Text("Notes (optional)") },
                minLines = 2,
                maxLines = 6,
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(Modifier.height(96.dp)) // breathing room above the running-total bar
        }
    }

    if (showDatePicker) {
        DatePickerSheet(
            initial = state.date,
            onPicked = {
                onSetDate(it)
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false },
        )
    }
}

@Composable
private fun DateField(date: LocalDate, onClick: () -> Unit) {
    OutlinedTextField(
        value = date.toString(),
        onValueChange = {},
        readOnly = true,
        label = { Text("Date") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        trailingIcon = {
            TextButton(onClick = onClick) { Text("Change") }
        },
    )
}

@Composable
private fun DatePickerSheet(
    initial: LocalDate,
    onPicked: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {
    val initialMillis = initial.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()
    val state = rememberDatePickerState(initialSelectedDateMillis = initialMillis)
    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                val millis = state.selectedDateMillis
                if (millis != null) {
                    val date = Instant.fromEpochMilliseconds(millis)
                        .toLocalDateTime(TimeZone.UTC).date
                    onPicked(date)
                } else {
                    onDismiss()
                }
            }) { Text("OK") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    ) {
        DatePicker(state = state)
    }
}

@Composable
private fun SectionHeader(name: String) {
    Spacer(Modifier.height(20.dp))
    Text(
        text = name,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.SemiBold,
    )
    HorizontalDivider(
        modifier = Modifier.padding(vertical = 6.dp),
        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
    )
}

@Composable
private fun SubSectionHeader(name: String) {
    Spacer(Modifier.height(8.dp))
    Text(
        text = name,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
    )
    Spacer(Modifier.height(4.dp))
}

@Composable
private fun HoldingRow(
    row: HoldingFieldsState,
    onInvested: (String) -> Unit,
    onCurrent: (String) -> Unit,
    onSip: (String) -> Unit,
) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(row.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        Spacer(Modifier.height(4.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (row.trackInvested) {
                MoneyField(
                    label = "Invested",
                    value = row.invested,
                    onChange = onInvested,
                    modifier = Modifier.weight(1f),
                )
            }
            MoneyField(
                label = "Current",
                value = row.current,
                onChange = onCurrent,
                modifier = Modifier.weight(1f),
            )
            if (row.trackSip) {
                MoneyField(
                    label = "SIP",
                    value = row.sip,
                    onChange = onSip,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

@Composable
private fun MoneyField(
    label: String,
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        label = { Text(label) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        modifier = modifier,
    )
}

@Composable
private fun RunningTotalBar(value: BigDecimal) {
    Surface(
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Running total",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            AssistChip(
                onClick = {},
                label = {
                    Text(
                        text = formatIndianCurrency(value),
                        fontWeight = FontWeight.SemiBold,
                    )
                },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
            )
        }
    }
}
