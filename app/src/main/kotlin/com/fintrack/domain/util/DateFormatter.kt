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

/**
 * Parses a `dd-MMM-yyyy` string back to a [LocalDate]. Use only when reading
 * CSV exports produced by the v3 export path; ISO-8601 strings (e.g.
 * `2026-05-05`) are still parsed via [LocalDate.parse].
 */
fun parseDateFormatted(value: String): LocalDate {
    val javaDate = java.time.LocalDate.parse(value, DD_MMM_YYYY)
    return LocalDate(javaDate.year, javaDate.monthValue, javaDate.dayOfMonth)
}

/**
 * Lenient parser used by CSV import: accepts either dd-MMM-yyyy (the v3
 * export format) or plain ISO-8601 (older exports / hand-edited files).
 */
fun parseDateLenient(value: String): LocalDate =
    runCatching { parseDateFormatted(value) }
        .getOrElse { LocalDate.parse(value) }
