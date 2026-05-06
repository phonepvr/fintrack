package com.fintrack.ui.settings.goals

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
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.ui.help.HelpIconButton
import com.fintrack.ui.help.HelpSheet
import com.fintrack.ui.help.HelpSheetContent
import com.fintrack.domain.model.GoalType
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
fun GoalsManagementRoute(
    onBack: () -> Unit,
    onNavigateToAbout: (anchor: String) -> Unit = {},
    viewModel: GoalsManagementViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarState = remember { SnackbarHostState() }
    var showAdd by remember { mutableStateOf(false) }
    var editTarget by remember { mutableStateOf<GoalEntity?>(null) }
    var deleteTarget by remember { mutableStateOf<GoalEntity?>(null) }
    var showHelp by remember { mutableStateOf(false) }

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
                title = { Text("Goals") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = { HelpIconButton(onClick = { showHelp = true }) },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAdd = true },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text("Add goal") },
            )
        },
    ) { padding ->
        if (state.goals.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text("No goals yet", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(6.dp))
                Text(
                    "Set a Net Worth target or aim to be debt-free by a date.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.goals, key = { it.id }) { goal ->
                    GoalCard(
                        goal = goal,
                        onEdit = { editTarget = goal },
                        onArchive = { viewModel.archive(goal.id) },
                        onDelete = { deleteTarget = goal },
                    )
                }
                item { Spacer(Modifier.height(96.dp)) }
            }
        }
    }

    if (showAdd) {
        GoalFormDialog(
            initial = null,
            today = todayLocal(),
            onSubmit = { name, type, target, targetDate ->
                viewModel.create(name, type, target, targetDate)
                showAdd = false
            },
            onDismiss = { showAdd = false },
        )
    }
    editTarget?.let { goal ->
        GoalFormDialog(
            initial = goal,
            today = todayLocal(),
            onSubmit = { name, type, target, targetDate ->
                viewModel.applyEdit(goal, name, type, target, targetDate)
                editTarget = null
            },
            onDismiss = { editTarget = null },
        )
    }
    deleteTarget?.let { goal ->
        AlertDialog(
            onDismissRequest = { deleteTarget = null },
            title = { Text("Delete goal?") },
            text = { Text("\"${goal.name}\" will be removed from this profile.") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.delete(goal.id)
                    deleteTarget = null
                }) { Text("Delete") }
            },
            dismissButton = { TextButton(onClick = { deleteTarget = null }) { Text("Cancel") } },
        )
    }

    if (showHelp) {
        HelpSheet(
            sheet = HelpSheetContent.GOALS_HOW_THEY_WORK,
            onDismiss = { showHelp = false },
            onLearnMore = onNavigateToAbout,
        )
    }
}

@Composable
private fun GoalCard(
    goal: GoalEntity,
    onEdit: () -> Unit,
    onArchive: () -> Unit,
    onDelete: () -> Unit,
) {
    var menuOpen by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (goal.isArchived) MaterialTheme.colorScheme.surfaceContainerLow
            else MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(goal.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                    Text(
                        when (goal.goalType) {
                            GoalType.NET_WORTH -> "Target: ${formatIndianCurrency(goal.targetNetWorth)}"
                            GoalType.DEBT_FREE -> "Become debt-free"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                IconButton(onClick = { menuOpen = true }) {
                    Icon(Icons.Filled.MoreVert, contentDescription = "Actions")
                    DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
                        DropdownMenuItem(text = { Text("Edit") }, onClick = { menuOpen = false; onEdit() })
                        if (!goal.isArchived) {
                            DropdownMenuItem(text = { Text("Archive") }, onClick = { menuOpen = false; onArchive() })
                        }
                        DropdownMenuItem(text = { Text("Delete") }, onClick = { menuOpen = false; onDelete() })
                    }
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            )
            Text(
                "By ${goal.targetDate.formatted()}" +
                    if (goal.achievedAt != null) " · achieved ${goal.achievedAt.formatted()}" else "",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun GoalFormDialog(
    initial: GoalEntity?,
    today: LocalDate,
    onSubmit: (name: String, type: GoalType, target: BigDecimal, targetDate: LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {
    var name by remember { mutableStateOf(initial?.name.orEmpty()) }
    var type by remember { mutableStateOf(initial?.goalType ?: GoalType.NET_WORTH) }
    var target by remember { mutableStateOf(initial?.targetNetWorth?.toPlainString().orEmpty()) }
    var targetDate by remember { mutableStateOf(initial?.targetDate ?: today) }
    var showDatePicker by remember { mutableStateOf(false) }

    // Past target dates are only allowed when editing an existing goal —
    // back-dating supports tracking historical pace. New goals must aim at
    // today or later, otherwise pace = MISSED on creation.
    val isDateValid = initial != null || targetDate >= today
    val canSubmit = name.isNotBlank() && isDateValid && (
        type == GoalType.DEBT_FREE || target.parseAmountOrNull()?.signum() == 1
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initial == null) "Add goal" else "Edit goal") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name (e.g. First Crore)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilterChip(
                        selected = type == GoalType.NET_WORTH,
                        onClick = { type = GoalType.NET_WORTH },
                        label = { Text("Net worth target") },
                    )
                    FilterChip(
                        selected = type == GoalType.DEBT_FREE,
                        onClick = { type = GoalType.DEBT_FREE },
                        label = { Text("Debt-free") },
                    )
                }
                if (type == GoalType.NET_WORTH) {
                    Spacer(Modifier.height(8.dp))
                    MoneyTextField(
                        label = "Target net worth",
                        value = target,
                        onValueChange = { target = it },
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = targetDate.formatted(),
                    onValueChange = {},
                    readOnly = true,
                    isError = !isDateValid,
                    label = { Text("Target date") },
                    supportingText = {
                        if (!isDateValid) {
                            Text("Target date must be today or later.")
                        }
                    },
                    trailingIcon = {
                        TextButton(onClick = { showDatePicker = true }) { Text("Change") }
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
                        type,
                        if (type == GoalType.NET_WORTH) target.parseAmountOrNull() ?: BigDecimal.ZERO
                        else BigDecimal.ZERO,
                        targetDate,
                    )
                },
            ) { Text(if (initial == null) "Add" else "Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )

    if (showDatePicker) {
        CommonDatePickerSheet(
            initial = targetDate,
            onPicked = {
                targetDate = it
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false },
        )
    }
}

private fun todayLocal(): LocalDate =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
