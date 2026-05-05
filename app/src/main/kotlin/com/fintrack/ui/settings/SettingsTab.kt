package com.fintrack.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.ui.onboarding.parseHex

@Composable
fun SettingsTab(
    onAimEditor: () -> Unit,
    onHoldings: () -> Unit,
    onLoans: () -> Unit,
    onGoals: () -> Unit,
    onManageUsers: () -> Unit,
    onBackup: () -> Unit,
    onExcel: () -> Unit,
    onAbout: () -> Unit,
    viewModel: SettingsTabViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item("active_user") { ActiveUserCard(state) }
        item("aim") { LinkRow(label = "Aim allocation", subtitle = aimSummary(state), onClick = onAimEditor) }
        item("holdings") {
            LinkRow(
                label = "Holdings",
                subtitle = "${state.activeHoldingCount} active · ${state.totalHoldingCount} total · shared across users",
                onClick = onHoldings,
            )
        }
        item("loans") {
            LinkRow(
                label = "Loans",
                subtitle = loansSummary(state),
                onClick = onLoans,
            )
        }
        item("goals") {
            LinkRow(
                label = "Goals",
                subtitle = "Net worth + debt-free targets",
                onClick = onGoals,
            )
        }
        item("inactivity") {
            InactivityCard(
                seconds = state.global?.inactivityLockSeconds ?: 60,
                onSelect = viewModel::setInactivityTimeout,
                alwaysShowPicker = state.global?.alwaysShowProfilePicker ?: false,
                onTogglePicker = viewModel::setAlwaysShowProfilePicker,
            )
        }
        item("users") {
            LinkRow(
                label = "Manage users",
                subtitle = "${state.userCount} profile(s)",
                onClick = onManageUsers,
            )
        }
        item("backup") { LinkRow(label = "Backup", subtitle = "Export / import data", onClick = onBackup) }
        item("excel") {
            LinkRow(
                label = "Excel template",
                subtitle = "Download · fill offline · import",
                onClick = onExcel,
            )
        }
        item("about") { LinkRow(label = "About", subtitle = "Version, privacy guarantees", onClick = onAbout) }
    }
}

@Composable
private fun ActiveUserCard(state: SettingsTabUiState) {
    val user = state.activeUser ?: return
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(parseHex(user.colorHex)),
            )
            Spacer(Modifier.size(12.dp))
            Column {
                Text("Active profile",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text(user.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
private fun LinkRow(label: String, subtitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(label, style = MaterialTheme.typography.titleMedium)
                Text(subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun InactivityCard(
    seconds: Int,
    onSelect: (Int) -> Unit,
    alwaysShowPicker: Boolean,
    onTogglePicker: (Boolean) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Inactivity lock", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(2.dp))
            Text("Re-prompt biometric after this much time in the background.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf(30 to "30s", 60 to "60s", 120 to "2m", 300 to "5m").forEach { (s, label) ->
                    FilterChip(
                        selected = s == seconds,
                        onClick = { onSelect(s) },
                        label = { Text(label) },
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 12.dp),
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("Always show profile picker", style = MaterialTheme.typography.bodyMedium)
                    Text("Show the picker even when only one profile exists.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
                Switch(checked = alwaysShowPicker, onCheckedChange = onTogglePicker)
            }
        }
    }
}

private fun loansSummary(state: SettingsTabUiState): String =
    when {
        state.activeLoanCount == 0 && state.totalLoanCount == 0 -> "No loans yet"
        state.activeLoanCount == state.totalLoanCount -> "${state.activeLoanCount} active"
        else -> "${state.activeLoanCount} active · ${state.totalLoanCount} total"
    }

private fun aimSummary(state: SettingsTabUiState): String {
    val rows = state.activeUserAim
    if (rows.isEmpty()) return "Configure per-class aim percentages"
    val total = rows.sumOf { it.aimPercent }
    val joined = rows.sortedBy { it.assetClassId }.joinToString("/") { "${it.aimPercent}" }
    return if (total == 100) "$joined (sums to 100%)" else "$joined (sums to $total%)"
}
