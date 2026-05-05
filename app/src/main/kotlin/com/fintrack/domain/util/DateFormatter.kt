package com.fintrack.domain.util

import kotlinx.datetime.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

/**
 * App-wide date formatter per spec §4.
 *
 * Renders LocalDate as `dd-MMM-yyyy` (e.g. `05-May-2026`) using a fixed
 * Locale.US so Mar/May/Jun/etc are stable across device locales —
 * rendering the same string on every device matches the user's mental
 * model and avoids surprises in CSV/UI/snapshot text.
 *
 * Use [LocalDate.formatted] everywhere a snapshot date appears in the
 * UI. JSON backups still use ISO-8601 (`LocalDate.toString()`).
 */
private val DD_MMM_YYYY: DateTimeFormatter =
    DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.US)

fun LocalDate.formatted(): String {
    val javaDate = java.time.LocalDate.of(year, monthNumber, dayOfMonth)
    return DD_MMM_YYYY.format(javaDate)
}
