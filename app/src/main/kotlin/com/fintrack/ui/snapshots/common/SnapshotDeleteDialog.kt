package com.fintrack.ui.snapshots.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.fintrack.domain.snapshots.SnapshotDeleteImpact
import com.fintrack.domain.util.formatted
import kotlinx.datetime.LocalDate

/**
 * Shared confirm dialog used from both the Snapshots tab long-press menu and
 * the Snapshot Detail overflow. Renders extra warning lines when the impact
 * flags say the delete is non-trivial (latest snapshot or breaks an active
 * monthly streak).
 */
@Composable
fun SnapshotDeleteDialog(
    date: LocalDate,
    impact: SnapshotDeleteImpact,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete snapshot?") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    "Snapshot from ${date.formatted()} will be permanently removed " +
                        "from this profile.",
                )
                if (impact.breaksActiveStreak) {
                    Text(
                        "This will end your ${impact.streakMonths}-month tracking streak.",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                if (impact.isLatest) {
                    Text(
                        "This is your most recent snapshot. Goal progress and " +
                            "the Journey chart will use the previous one.",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
    )
}
