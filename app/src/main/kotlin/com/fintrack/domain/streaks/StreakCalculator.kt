package com.fintrack.domain.streaks

import kotlinx.datetime.LocalDate

/**
 * Streak math per spec §6.1.
 *
 * A "streak month" is any calendar month in which the user took at least
 * one snapshot. The current streak is the run of consecutive months ending
 * with the latest snapshot's month. Months without any snapshots break the
 * streak.
 *
 * **Current-month grace** (spec): the current calendar month is "in
 * progress" — if the user has no snapshot yet for the current month and
 * the most recent snapshot was in the previous month, the streak is NOT
 * broken; we just keep the value computed up to the previous month.
 *
 * Returns counts in months and the YYYY-MM tag of the last snapshot's
 * month (null when there are no snapshots).
 */
data class StreakResult(
    val currentStreakMonths: Int,
    val longestStreakMonths: Int,
    val lastSnapshotMonth: String?,
)

object StreakCalculator {

    /**
     * @param snapshotDates every snapshot date for the user (any order, can
     *   contain duplicates of the same calendar month — they collapse).
     * @param today the current calendar date (used to apply current-month
     *   grace).
     */
    fun compute(snapshotDates: Collection<LocalDate>, today: LocalDate): StreakResult {
        if (snapshotDates.isEmpty()) {
            return StreakResult(0, 0, null)
        }
        val months = snapshotDates.map { YearMonth(it.year, it.monthNumber) }.toSortedSet()
        val ordered = months.toList()                       // ascending
        val mostRecent = ordered.last()

        // Longest run anywhere in history.
        var longest = 1
        var run = 1
        for (i in 1 until ordered.size) {
            if (ordered[i].previous() == ordered[i - 1]) {
                run++
                if (run > longest) longest = run
            } else {
                run = 1
            }
        }
        if (longest < run) longest = run

        // Current run ends at mostRecent — walk back.
        var current = 1
        for (i in ordered.size - 2 downTo 0) {
            if (ordered[i + 1].previous() == ordered[i]) current++ else break
        }

        // Current-month grace: if the user hasn't taken a snapshot this month yet
        // and the most-recent snapshot was last month, current is unchanged. If
        // most-recent is older than that, the run has already been broken.
        val nowYm = YearMonth(today.year, today.monthNumber)
        val effectiveCurrent = when {
            mostRecent == nowYm -> current                                // already stamped this month
            mostRecent == nowYm.previous() -> current                     // grace — last month counts
            else -> 0                                                      // missed at least one full month
        }

        return StreakResult(
            currentStreakMonths = effectiveCurrent,
            longestStreakMonths = maxOf(longest, effectiveCurrent),
            lastSnapshotMonth = mostRecent.tag(),
        )
    }
}

/** Internal helper representation; not exposed. */
internal data class YearMonth(val year: Int, val month: Int) : Comparable<YearMonth> {
    init {
        require(month in 1..12) { "month must be 1..12, got $month" }
    }

    fun previous(): YearMonth =
        if (month == 1) YearMonth(year - 1, 12) else YearMonth(year, month - 1)

    fun tag(): String = "%04d-%02d".format(year, month)

    override fun compareTo(other: YearMonth): Int =
        if (year != other.year) year.compareTo(other.year) else month.compareTo(other.month)
}
