package com.fintrack.ui.settings.excel

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.data.xlsx.XlsxConflictStrategy
import com.fintrack.data.xlsx.XlsxImportSummary
import com.fintrack.data.xlsx.XlsxWorkbookData

@Composable
fun ExcelRoute(
    onBack: () -> Unit,
    viewModel: ExcelViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarState = remember { SnackbarHostState() }

    val saveLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.CreateDocument("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    ) { uri -> uri?.let(viewModel::exportTemplate) }

    val openLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument(),
    ) { uri -> uri?.let(viewModel::preview) }

    LaunchedEffect(state.status) {
        when (val s = state.status) {
            is ExcelStatus.Failed -> {
                snackbarState.showSnackbar(s.message)
                viewModel.reset()
            }
            is ExcelStatus.TemplateExported -> {
                snackbarState.showSnackbar("Saved ${s.name}")
                viewModel.reset()
            }
            is ExcelStatus.ImportSucceeded -> Unit
            else -> Unit
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text("Excel template") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item("intro") {
                Text(
                    "Download a 5-sheet Excel template, fill it offline, then import. " +
                        "All processing happens on this device — no cloud round-trip.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            item("download") {
                OutlinedButton(
                    onClick = { saveLauncher.launch("fintrack-template.xlsx") },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Download empty template") }
            }
            item("import") {
                Button(
                    onClick = {
                        openLauncher.launch(
                            arrayOf(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
                                "application/octet-stream",
                            ),
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                ) { Text("Import from Excel") }
            }
            when (val s = state.status) {
                ExcelStatus.Working -> item("working") {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(top = 24.dp),
                        horizontalArrangement = Arrangement.Center,
                    ) { CircularProgressIndicator() }
                }
                is ExcelStatus.Preview -> item("preview") {
                    PreviewCard(
                        data = s.data,
                        strategy = state.strategy,
                        onSelectStrategy = viewModel::setStrategy,
                        onApply = viewModel::applyPreview,
                        onCancel = viewModel::reset,
                    )
                }
                is ExcelStatus.ImportSucceeded -> item("done") {
                    DoneCard(summary = s.summary, onClose = viewModel::reset)
                }
                else -> Unit
            }
        }
    }
}

@Composable
private fun PreviewCard(
    data: XlsxWorkbookData,
    strategy: XlsxConflictStrategy,
    onSelectStrategy: (XlsxConflictStrategy) -> Unit,
    onApply: () -> Unit,
    onCancel: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Import preview", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Text("• ${data.snapshots.size} snapshot(s)")
            Text("• ${data.holdingValues.size} holding value row(s)")
            Text("• ${data.loans.size} loan(s)")
            Text("• ${data.loanValues.size} loan value row(s)")
            Text("• ${data.goals.size} goal(s)")
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
            )
            Text(
                "If a snapshot already exists for an imported date:",
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(4.dp))
            StrategyRow(
                label = "Skip — leave existing snapshots untouched",
                selected = strategy == XlsxConflictStrategy.SKIP_EXISTING,
                onClick = { onSelectStrategy(XlsxConflictStrategy.SKIP_EXISTING) },
            )
            StrategyRow(
                label = "Replace — drop existing rows, re-import the new ones",
                selected = strategy == XlsxConflictStrategy.REPLACE_EXISTING,
                onClick = { onSelectStrategy(XlsxConflictStrategy.REPLACE_EXISTING) },
            )
            Spacer(Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(onClick = onCancel, modifier = Modifier.weight(1f)) { Text("Cancel") }
                Button(onClick = onApply, modifier = Modifier.weight(1f)) { Text("Apply") }
            }
        }
    }
}

@Composable
private fun StrategyRow(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
private fun DoneCard(summary: XlsxImportSummary, onClose: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Import complete", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            Text("Snapshots created: ${summary.snapshotsCreated}")
            Text("Snapshots skipped: ${summary.snapshotsSkipped}")
            Text("Snapshots replaced: ${summary.snapshotsReplaced}")
            Text("Holding values: ${summary.holdingValuesCreated}")
            Text("Loans: ${summary.loansCreated} · loan values: ${summary.loanValuesCreated}")
            Text("Goals: ${summary.goalsCreated}")
            if (summary.warnings.isNotEmpty()) {
                Spacer(Modifier.height(8.dp))
                Text(
                    "Warnings:",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                summary.warnings.take(20).forEach {
                    Text("• $it", style = MaterialTheme.typography.labelSmall)
                }
                if (summary.warnings.size > 20) {
                    Text("• …and ${summary.warnings.size - 20} more", style = MaterialTheme.typography.labelSmall)
                }
            }
            Spacer(Modifier.height(12.dp))
            Button(onClick = onClose, modifier = Modifier.fillMaxWidth()) { Text("Close") }
        }
    }
}

