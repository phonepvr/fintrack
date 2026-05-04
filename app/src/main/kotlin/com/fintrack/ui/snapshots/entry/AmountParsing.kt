package com.fintrack.ui.snapshots.entry

import java.math.BigDecimal

internal fun String.parseAmountOrNull(): BigDecimal? {
    val trimmed = trim()
    if (trimmed.isEmpty()) return null
    return runCatching { BigDecimal(trimmed) }.getOrNull()
}

internal fun String.parseAmountOrZero(): BigDecimal = parseAmountOrNull() ?: BigDecimal.ZERO

internal fun String.filterNumeric(): String =
    filterIndexed { idx, ch ->
        ch.isDigit() || (ch == '.' && idx > 0 && !this.substring(0, idx).contains('.'))
    }
