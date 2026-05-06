package com.fintrack.ui.snapshots.entry

import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
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
import com.fintrack.domain.util.formatted
import com.fintrack.security.MoneyTextField
import com.fintrack.ui.common.CommonDatePickerSheet
import com.fintrack.ui.help.HelpIconButton
import com.fintrack.ui.help.HelpSheet
import com.fintrack.ui.help.HelpSheetContent
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID

@Composable
fun SnapshotEntryRoute(
    onDone: () -> Unit,
    onNavigateToAbout: (anchor: String) -> Unit = {},
    viewModel: SnapshotEntryViewModel = hiltViewModel(),
) {
    // [snapshotId] (null for new) is read from SavedStateHandle by [SnapshotEntryViewModel].
    val state by viewModel.state.collectAsState()
    val snackbarState = remember { SnackbarHostState() }
    var helpSheetKey by remember { mutableStateOf<HelpSheetContent.Sheet?>(null) }

    LaunchedEffect(state.savedSnapshotId) {
        if (state.savedSnapshotId != null) onDone()
    }
    LaunchedEffect(state.error) {
        state.error?.let {
            snackbarState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    val runningTotals = remember(state.rows, state.loanRows) {
        val assets = state.rows.fold(BigDecimal.ZERO) { acc, r -> acc + r.current.parseAmountOrZero() }
        val liabilities = state.loanRows.fold(BigDecimal.ZERO) { acc, r -> acc + r.outstanding.parseAmountOrZero() }
        RunningTotals(assets, liabilities, assets - liabilities)
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
                    HelpIconButton(onClick = { helpSheetKey = HelpSheetContent.SNAPSHOT_WHAT_IS })
                    TextButton(onClick = viewModel::save, enabled = !state.saving) {
                        Text(if (state.saving) "Saving…" else "Save")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
        bottomBar = {
            RunningTotalBar(totals = runningTotals)
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
            onSetLoanOutstanding = viewModel::setLoanOutstanding,
            onAddLoan = viewModel::createAndAttachLoan,
            onShowEarningsHelp = { helpSheetKey = HelpSheetContent.SNAPSHOT_EARNINGS },
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        )
    }

    helpSheetKey?.let { sheet ->
        HelpSheet(
            sheet = sheet,
            onDismiss = { helpSheetKey = null },
            onLearnMore = onNavigateToAbout,
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
    onSetLoanOutstanding: (UUID, String) -> Unit,
    onAddLoan: (String, BigDecimal, LocalDate, BigDecimal) -> Unit,
    onShowEarningsHelp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val orderedAssetClassNames: List<String> = remember(state.rows) {
        state.rows.map { it.assetClassName }.distinct()
    }
    val rowsByClass: Map<String, List<HoldingFieldsState>> = remember(state.rows) {
        state.rows.groupBy { it.assetClassName }
    }
    var showDatePicker by remember { mutableStateOf(false) }
    var showAddLoan by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    ) {
        item {
            DateField(state.date, onClick = { showDatePicker = true })
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = state.earnings,
                    onValueChange = onSetEarnings,
                    label = { Text("Earnings (₹ in cr)") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    modifier = Modifier.weight(1f),
                )
                HelpIconButton(onClick = onShowEarningsHelp)
            }
            Spacer(Modifier.height(8.dp))
        }

        if (orderedAssetClassNames.isNotEmpty()) {
            item(key = "holdings_helper") {
                Text(
                    text = "Leave a holding's current value blank to skip it for this " +
                        "month, or enter 0 to record an explicit zero.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 4.dp),
                )
            }
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
                val suppressRowName = sbRows.size == 1 &&
                    sbRows.single().name.equals(sbName, ignoreCase = true)
                items(sbRows, key = { "row_${it.holdingId}" }) { row ->
                    HoldingRow(
                        row = row,
                        suppressName = suppressRowName,
                        onInvested = { onSetInvested(row.holdingId, it) },
                        onCurrent = { onSetCurrent(row.holdingId, it) },
                        onSip = { onSetSip(row.holdingId, it) },
                    )
                }
            }
        }

        item(key = "section_loans") { SectionHeader("Loans") }
        if (state.loanRows.isEmpty()) {
            item(key = "loans_empty") {
                Text(
                    text = "No active loans. Use the button below to add one.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(vertical = 4.dp),
                )
            }
        } else {
            items(state.loanRows, key = { "loan_${it.loanId}" }) { row ->
                LoanRow(
                    row = row,
                    onOutstanding = { onSetLoanOutstanding(row.loanId, it) },
                )
            }
        }
        item(key = "loans_add") {
            Spacer(Modifier.height(8.dp))
            TextButton(onClick = { showAddLoan = true }) {
                Text("+ Add new loan")
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
            Spacer(Modifier.height(120.dp)) // breathing room above the running-total bar
        }
    }

    if (showDatePicker) {
        CommonDatePickerSheet(
            initial = state.date,
            onPicked = {
                onSetDate(it)
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false },
        )
    }

    if (showAddLoan) {
        AddLoanDialog(
            today = state.date,
            onSubmit = { name, original, taken, emi ->
                onAddLoan(name, original, taken, emi)
                showAddLoan = false
            },
            onDismiss = { showAddLoan = false },
        )
    }
}

@Composable
private fun DateField(date: LocalDate, onClick: () -> Unit) {
    OutlinedTextField(
        value = date.formatted(),
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
private fun LoanRow(
    row: LoanFieldsState,
    onOutstanding: (String) -> Unit,
) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(row.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        Text(
            "Original: ${formatIndianCurrency(row.originalAmount)}",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(4.dp))
        MoneyTextField(
            label = "Outstanding",
            value = row.outstanding,
            onValueChange = onOutstanding,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun AddLoanDialog(
    today: LocalDate,
    onSubmit: (name: String, originalAmount: BigDecimal, takenDate: LocalDate, monthlyEmi: BigDecimal) -> Unit,
    onDismiss: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var original by remember { mutableStateOf("") }
    var emi by remember { mutableStateOf("") }
    var takenDate by remember { mutableStateOf(today) }
    var showTakenPicker by remember { mutableStateOf(false) }

    val canSubmit = name.isNotBlank() && original.parseAmountOrNull()?.signum() == 1

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add new loan") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name (e.g. Home Loan)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(8.dp))
                MoneyTextField(
                    label = "Original amount",
                    value = original,
                    onValueChange = { original = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(8.dp))
                MoneyTextField(
                    label = "Monthly EMI (optional)",
                    value = emi,
                    onValueChange = { emi = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = takenDate.formatted(),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Taken on") },
                    trailingIcon = {
                        TextButton(onClick = { showTakenPicker = true }) { Text("Change") }
                    },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            TextButton(
                enabled = canSubmit,
                onClick = {
                    onSubmit(
                        name.trim(),
                        original.parseAmountOrNull() ?: BigDecimal.ZERO,
                        takenDate,
                        emi.parseAmountOrNull() ?: BigDecimal.ZERO,
                    )
                },
            ) { Text("Add") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )

    if (showTakenPicker) {
        CommonDatePickerSheet(
            initial = takenDate,
            onPicked = {
                takenDate = it
                showTakenPicker = false
            },
            onDismiss = { showTakenPicker = false },
        )
    }
}

@Composable
private fun HoldingRow(
    row: HoldingFieldsState,
    suppressName: Boolean,
    onInvested: (String) -> Unit,
    onCurrent: (String) -> Unit,
    onSip: (String) -> Unit,
) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        if (!suppressName) {
            Text(row.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
            Spacer(Modifier.height(4.dp))
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            if (row.trackInvested) {
                MoneyTextField(
                    label = "Invested",
                    value = row.invested,
                    onValueChange = onInvested,
                    modifier = Modifier.weight(1f),
                )
            }
            MoneyTextField(
                label = "Current",
                value = row.current,
                onValueChange = onCurrent,
                modifier = Modifier.weight(1f),
            )
            if (row.trackSip) {
                MoneyTextField(
                    label = "SIP",
                    value = row.sip,
                    onValueChange = onSip,
                    modifier = Modifier.weight(1f),
                )
            }
        }
    }
}

internal data class RunningTotals(
    val assets: BigDecimal,
    val liabilities: BigDecimal,
    val netWorth: BigDecimal,
)

@Composable
private fun RunningTotalBar(totals: RunningTotals) {
    Surface(
        tonalElevation = 4.dp,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TotalChip("Assets", totals.assets, Modifier.weight(1f))
            TotalChip("Liabilities", totals.liabilities, Modifier.weight(1f))
            TotalChip(
                "Net Worth",
                totals.netWorth,
                Modifier.weight(1f),
                emphasised = true,
            )
        }
    }
}

@Composable
private fun TotalChip(
    label: String,
    value: BigDecimal,
    modifier: Modifier = Modifier,
    emphasised: Boolean = false,
) {
    val containerColor = if (emphasised) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
    }
    val labelColor = if (emphasised) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurfaceVariant
    }
    Box(
        modifier = modifier
            .background(containerColor, shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp))
            .padding(horizontal = 10.dp, vertical = 8.dp),
    ) {
        Column {
            Text(label, style = MaterialTheme.typography.labelSmall, color = labelColor)
            Text(
                formatIndianCurrency(value),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = labelColor,
            )
        }
    }
}
