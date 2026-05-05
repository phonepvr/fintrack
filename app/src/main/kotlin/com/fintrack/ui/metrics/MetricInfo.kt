package com.fintrack.ui.metrics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Definition + computation of a single metric, surfaced behind the
 * help (?) icon next to its label. One source of truth so the snapshot
 * card and the detail screen agree on copy.
 */
data class MetricInfo(
    val name: String,
    val description: String,
    val formula: String,
)

object Metrics {
    val NET_WORTH = MetricInfo(
        name = "Net Worth",
        description = "Your total wealth on this snapshot date — everything you own " +
            "minus everything you owe.",
        formula = "Net Worth = Total Assets − Total Liabilities",
    )

    val TOTAL_ASSETS = MetricInfo(
        name = "Total Assets",
        description = "Sum of the current value of every holding you tracked on this " +
            "snapshot — investments, savings, fixed-return instruments, crypto, " +
            "everything on the asset side.",
        formula = "Total Assets = Σ current value of all holdings on this snapshot",
    )

    val TOTAL_LIABILITIES = MetricInfo(
        name = "Total Liabilities",
        description = "Sum of the outstanding amounts on every active loan attached " +
            "to this snapshot.",
        formula = "Total Liabilities = Σ outstanding amount of all loans on this snapshot",
    )

    val INVESTMENT_VALUE = MetricInfo(
        name = "Investment Value",
        description = "The growth-oriented part of your portfolio — total assets " +
            "excluding the Fixed Return asset class (PF, PPF, FD, bank, etc.). " +
            "Use this to track how the equity / risk side is doing.",
        formula = "Investment Value = Total Assets − Fixed Returns",
    )

    val FIXED_RETURNS = MetricInfo(
        name = "Fixed Returns",
        description = "Current value of the holdings in the Fixed Return asset class " +
            "(EPF, PPF, FDs, Wint Wealth, bank balances). The principal-protected " +
            "side of your portfolio.",
        formula = "Fixed Returns = Σ current value of holdings whose asset class is " +
            "\"Fixed Return\"",
    )

    val TOTAL_INVESTED = MetricInfo(
        name = "Total Invested",
        description = "Cumulative money you've put into the holdings that track an " +
            "invested amount — what you can compare against today's value to see " +
            "absolute gain.",
        formula = "Total Invested = Σ invested amount of holdings (where tracked) on this snapshot",
    )

    val TOTAL_SIP = MetricInfo(
        name = "Total SIP",
        description = "Sum of the monthly SIP contributions across holdings that " +
            "track SIP — your committed monthly investment outflow.",
        formula = "Total SIP = Σ monthly SIP across holdings (where tracked)",
    )

    val PERCENT_OF_EARNINGS = MetricInfo(
        name = "% of Earnings",
        description = "How big your net worth is relative to the lifetime earnings " +
            "you recorded on this snapshot. A 100% reading means net worth equals " +
            "everything you've ever earned.",
        formula = "% of Earnings = Net Worth ÷ Earnings × 100",
    )

    val DELTA_VS_PREV = MetricInfo(
        name = "Δ vs previous",
        description = "Change in net worth from the previous snapshot — both as an " +
            "absolute amount and as a percentage.",
        formula = "Δ = Net Worth (this) − Net Worth (previous)",
    )
}

/**
 * Renders [text] with a tiny help (?) icon to the right. Tapping the icon
 * opens an explanatory dialog.
 *
 * Used everywhere a metric label appears: snapshot card cells, detail
 * footer rows, etc. Keeps copy + UX consistent across surfaces.
 */
@Composable
fun LabelWithHelp(
    text: String,
    info: MetricInfo,
    modifier: Modifier = Modifier,
    style: androidx.compose.ui.text.TextStyle = MaterialTheme.typography.labelSmall,
    color: androidx.compose.ui.graphics.Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    var open by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Text(text = text, style = style, color = color)
        IconButton(
            onClick = { open = true },
            modifier = Modifier.size(20.dp),
        ) {
            Icon(
                imageVector = Icons.Outlined.HelpOutline,
                contentDescription = "What is $text?",
                tint = color,
                modifier = Modifier.size(14.dp),
            )
        }
    }
    if (open) {
        MetricInfoDialog(info = info, onDismiss = { open = false })
    }
}

@Composable
private fun MetricInfoDialog(info: MetricInfo, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(info.name) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    info.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
                Text(
                    "How it's calculated",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    info.formula,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start,
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) { Text("Got it") }
        },
    )
}
