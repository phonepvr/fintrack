package com.fintrack.ui.settings.loans

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatted
import com.fintrack.security.MoneyTextField
import com.fintrack.ui.common.CommonDatePickerSheet
import com.fintrack.ui.snapshots.entry.parseAmountOrNull
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.math.BigDecimal

@Composable
fun LoansManagementRoute(
    onBack: () -> Unit,
    viewModel: LoansManagementViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarState = remember { SnackbarHostState() }
    var showAdd by remember { mutableStateOf(false) }
    var editTarget by remember { mutableStateOf<LoanEntity?>(null) }
    var deleteTarget by remember { mutableStateOf<LoanEntity?>(null) }
    var closeTarget by remember { mutableStateOf<LoanEntity?>(null) }

    LaunchedEffect(state.error) {
        state.error?.let {
            snackbarState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text("Loans") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAdd = true },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text("Add loan") },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item("toggle") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    FilterChip(
                        selected = !state.showInactive,
                        onClick = { viewModel.setShowInactive(false) },
                        label = { Text("Active only") },
                    )
                    FilterChip(
                        selected = state.showInactive,
                        onClick = { viewModel.setShowInactive(true) },
                        label = { Text("Show all") },
                    )
                }
            }

            if (state.loans.isEmpty()) {
                item("empty") {
                    Text(
                        if (state.showInactive)
                            "No loans on this profile."
                        else "No active loans.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 24.dp),
                    )
                }
            } else {
                items(state.loans, key = { it.id }) { loan ->
                    LoanCard(
                        loan = loan,
                        onEdit = { editTarget = loan },
                        onMarkClosed = { closeTarget = loan },
                        onDelete = { deleteTarget = loan },
                    )
                }
            }
            item { Spacer(Modifier.height(96.dp)) }
        }
    }

    if (showAdd) {
        LoanFormDialog(
            initial = null,
            today = todayLocal(),
            onSubmit = { name, original, taken, emi ->
                viewModel.createLoan(name, original, taken, emi)
                showAdd = false
            },
            onDismiss = { showAdd = false },
        )
    }
    editTarget?.let { loan ->
        LoanFormDialog(
            initial = loan,
            today = todayLocal(),
            onSubmit = { name, original, taken, emi ->
                viewModel.updateLoan(
                    loan.copy(
                        name = name,
                        originalAmount = original,
                        takenDate = taken,
                        monthlyEmi = emi,
                    ),
                )
                editTarget = null
            },
            onDismiss = { editTarget = null },
        )
    }
    closeTarget?.let { loan ->
        AlertDialog(
            onDismissRequest = { closeTarget = null },
            title = { Text("Mark loan closed?") },
            text = { Text("${loan.name} will be moved to inactive. You can still see it under Show all.") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.markClosed(loan.id, todayLocal())
                    closeTarget = null
                }) { Text("Mark closed") }
            },
            dismissButton = { TextButton(onClick = { closeTarget = null }) { Text("Cancel") } },
        )
    }
    deleteTarget?.let { loan ->
        TypedNameConfirmDialog(
            target = loan,
            onDismiss = { deleteTarget = null },
            onConfirm = {
                viewModel.deleteLoan(loan.id)
                deleteTarget = null
            },
        )
    }
}

@Composable
private fun LoanCard(
    loan: LoanEntity,
    onEdit: () -> Unit,
    onMarkClosed: () -> Unit,
    onDelete: () -> Unit,
) {
    var menuOpen by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Text(loan.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(
                        if (loan.isActive) "Active" else "Closed ${loan.closedDate?.formatted() ?: ""}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                IconButton(onClick = { menuOpen = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Actions")
                    DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                        DropdownMenuItem(
                            text = { Text("Edit") },
                            onClick = { menuOpen = false; onEdit() },
                        )
                        if (loan.isActive) {
                            DropdownMenuItem(
                                text = { Text("Mark closed") },
                                onClick = { menuOpen = false; onMarkClosed() },
                            )
                        }
                        DropdownMenuItem(
                            text = { Text("Delete") },
                            onClick = { menuOpen = false; onDelete() },
                        )
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Meta("Original", formatIndianCurrency(loan.originalAmount))
                Meta("EMI", formatIndianCurrency(loan.monthlyEmi))
                Meta("Taken", loan.takenDate.formatted())
            }
        }
    }
}

@Composable
private fun Meta(label: String, value: String) {
    Column {
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
    }
}

@Composable
private fun LoanFormDialog(
    initial: LoanEntity?,
    today: LocalDate,
    onSubmit: (name: String, originalAmount: BigDecimal, takenDate: LocalDate, monthlyEmi: BigDecimal) -> Unit,
    onDismiss: () -> Unit,
) {
    var name by remember { mutableStateOf(initial?.name.orEmpty()) }
    var original by remember { mutableStateOf(initial?.originalAmount?.toPlainString().orEmpty()) }
    var emi by remember { mutableStateOf(initial?.monthlyEmi?.toPlainString().orEmpty()) }
    var takenDate by remember { mutableStateOf(initial?.takenDate ?: today) }
    var showTakenPicker by remember { mutableStateOf(false) }

    val canSubmit = name.isNotBlank() && original.parseAmountOrNull()?.signum() == 1

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initial == null) "Add loan" else "Edit loan") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
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
                    label = "Monthly EMI",
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
            ) { Text(if (initial == null) "Add" else "Save") }
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
private fun TypedNameConfirmDialog(
    target: LoanEntity,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    var typed by remember { mutableStateOf("") }
    val matches = typed.trim().equals(target.name, ignoreCase = true)
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete loan?") },
        text = {
            Column {
                Text(
                    "This will remove ${target.name} and all of its values from every snapshot. " +
                        "Type the loan's name to confirm.",
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = typed,
                    onValueChange = { typed = it },
                    label = { Text("Type \"${target.name}\"") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            TextButton(enabled = matches, onClick = onConfirm) {
                Text("Delete")
            }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}

private fun todayLocal(): LocalDate =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
