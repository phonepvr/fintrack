package com.fintrack.domain.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

private val LAKH = BigDecimal("100000")
private val CRORE = BigDecimal("10000000")

private fun rupeeGroupingFormat(): DecimalFormat = DecimalFormat("#,##0.##").apply {
    isGroupingUsed = true
    groupingSize = 3
}

/**
 * Formats a rupee amount in the Indian numbering system.
 *
 * Boundaries (covered by [com.fintrack.domain.util.IndianCurrencyFormatterTest]):
 *   0          → "₹0"
 *   1          → "₹1"
 *   999        → "₹999"
 *   1,000      → "₹1,000"
 *   99,999     → "₹99,999"
 *   1,00,000   → "₹1.00 L"
 *   10,00,000  → "₹10.00 L"
 *   1,00,00,000→ "₹1.00 Cr"
 *
 * Negative values are emitted with a leading minus sign.
 */
fun formatIndianCurrency(amount: BigDecimal, includeSymbol: Boolean = true): String {
    val symbol = if (includeSymbol) "₹" else ""
    val abs = amount.abs()
    val sign = if (amount.signum() < 0) "-" else ""
    return when {
        abs >= CRORE -> {
            val v = abs.divide(CRORE, 2, RoundingMode.HALF_UP).toPlainString()
            "$sign$symbol$v Cr"
        }
        abs >= LAKH -> {
            val v = abs.divide(LAKH, 2, RoundingMode.HALF_UP).toPlainString()
            "$sign$symbol$v L"
        }
        else -> "$sign$symbol${rupeeGroupingFormat().format(abs)}"
    }
}

/**
 * Compact short form used on the snapshot list rows (no symbol prefix).
 * Same boundary rules as [formatIndianCurrency].
 */
fun formatIndianCurrencyShort(amount: BigDecimal): String =
    formatIndianCurrency(amount, includeSymbol = true)

/**
 * Renders a percentage as e.g. "21.3%" or "-2.4%". Trims trailing zeros after one decimal.
 */
fun formatPercent(value: BigDecimal, decimals: Int = 1): String {
    val rounded = value.setScale(decimals, RoundingMode.HALF_UP).toPlainString()
    return "$rounded%"
}

/**
 * Renders a signed delta percentage with leading + for non-negative values.
 */
fun formatSignedPercent(value: BigDecimal, decimals: Int = 1): String {
    val sign = if (value.signum() >= 0) "+" else ""
    return sign + formatPercent(value, decimals)
}

/**
 * Renders a signed currency delta: `+₹1.23 L` / `-₹4.50 L`.
 */
fun formatSignedCurrency(value: BigDecimal): String {
    val sign = if (value.signum() >= 0) "+" else ""
    return sign + formatIndianCurrency(value)
}
