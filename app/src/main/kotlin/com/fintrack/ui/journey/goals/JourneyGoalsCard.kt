package com.fintrack.ui.journey.goals

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fintrack.domain.goals.GoalPace
import com.fintrack.domain.goals.GoalProgress
import com.fintrack.domain.model.GoalType
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatPercent
import com.fintrack.domain.util.formatted
import java.math.BigDecimal
import java.util.UUID

/**
 * Goals card surfaced on the Journey &amp; Goals tab between the headline
 * row and the chart. Tapping a row notifies the parent to overlay the
 * goal's target line on the chart.
 */
@Composable
fun JourneyGoalsCard(
    progresses: List<GoalProgress>,
    selectedGoalId: UUID?,
    onSelect: (UUID?) -> Unit,
) {
    if (progresses.isEmpty()) return
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                "Goals",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(8.dp))
            progresses.forEachIndexed { index, p ->
                if (index > 0) Spacer(Modifier.height(10.dp))
                GoalProgressRow(
                    progress = p,
                    selected = selectedGoalId == p.goal.id,
                    onTap = {
                        if (selectedGoalId == p.goal.id) onSelect(null) else onSelect(p.goal.id)
                    },
                )
            }
        }
    }
}

/**
 * Subtitle shown under a goal's name on the Journey card. NET_WORTH formats
 * "current of target". DEBT_FREE shows the outstanding amount plus how much
 * has been paid of the captured starting anchor. Legacy DEBT_FREE goals with
 * `startingLiabilities = 0` (binary fallback) show only the outstanding.
 */
internal fun goalCardSubtitle(progress: GoalProgress): String =
    when (progress.goal.goalType) {
        GoalType.NET_WORTH ->
            "${formatIndianCurrency(progress.currentValue)} of " +
                formatIndianCurrency(progress.targetValue)
        GoalType.DEBT_FREE -> {
            val current = progress.currentValue
            val start = progress.goal.startingLiabilities
            if (start.signum() > 0) {
                val paid = (start - current).coerceAtLeast(BigDecimal.ZERO)
                "${formatIndianCurrency(current)} outstanding · " +
                    "${formatIndianCurrency(paid)} paid of ${formatIndianCurrency(start)}"
            } else {
                "${formatIndianCurrency(current)} outstanding"
            }
        }
    }

@Composable
private fun GoalProgressRow(
    progress: GoalProgress,
    selected: Boolean,
    onTap: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onTap)
            .padding(vertical = 4.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(progress.goal.name, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Medium)
                Text(
                    "${goalCardSubtitle(progress)} · by ${progress.goal.targetDate.formatted()}",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            PaceBadge(progress.pace)
            if (selected) {
                Spacer(Modifier.height(0.dp))
                Text(
                    "•",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 6.dp),
                )
            }
        }
        Spacer(Modifier.height(4.dp))
        LinearProgressIndicator(
            progress = { progress.progressPct.toFloat() / 100f },
            modifier = Modifier.fillMaxWidth(),
            color = paceColor(progress.pace),
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        Text(
            "${formatPercent(progress.progressPct, decimals = 0)} done · target line ${formatPercent(progress.straightLinePct, decimals = 0)}",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}

@Composable
private fun PaceBadge(pace: GoalPace) {
    val (text, color) = when (pace) {
        GoalPace.AHEAD -> "Ahead" to Color(0xFF1B5E20)
        GoalPace.ON_TRACK -> "On track" to Color(0xFF1976D2)
        GoalPace.BEHIND -> "Behind" to Color(0xFFE65100)
        GoalPace.MISSED -> "Missed" to Color(0xFFB71C1C)
        GoalPace.ACHIEVED -> "Done" to Color(0xFF1B5E20)
    }
    Text(
        text,
        style = MaterialTheme.typography.labelSmall,
        color = color,
        fontWeight = FontWeight.SemiBold,
    )
}

private fun paceColor(pace: GoalPace): Color = when (pace) {
    GoalPace.AHEAD, GoalPace.ACHIEVED -> Color(0xFF388E3C)
    GoalPace.ON_TRACK -> Color(0xFF1976D2)
    GoalPace.BEHIND -> Color(0xFFF57C00)
    GoalPace.MISSED -> Color(0xFFD32F2F)
}
