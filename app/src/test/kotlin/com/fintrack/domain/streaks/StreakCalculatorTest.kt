package com.fintrack.domain.streaks

import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StreakCalculatorTest {

    private fun d(s: String) = LocalDate.parse(s)

    @Test
    @DisplayName("Empty history yields zero current and longest streaks")
    fun empty() {
        val out = StreakCalculator.compute(emptyList(), today = d("2026-05-05"))
        assertThat(out.currentStreakMonths).isEqualTo(0)
        assertThat(out.longestStreakMonths).isEqualTo(0)
        assertThat(out.lastSnapshotMonth).isNull()
    }

    @Test
    @DisplayName("Three consecutive months ending this month → current=3")
    fun threeInARow() {
        val out = StreakCalculator.compute(
            listOf(d("2026-03-15"), d("2026-04-10"), d("2026-05-02")),
            today = d("2026-05-05"),
        )
        assertThat(out.currentStreakMonths).isEqualTo(3)
        assertThat(out.longestStreakMonths).isEqualTo(3)
        assertThat(out.lastSnapshotMonth).isEqualTo("2026-05")
    }

    @Test
    @DisplayName("Multiple snapshots in a month collapse to one streak month")
    fun deduplicatesMonths() {
        val out = StreakCalculator.compute(
            listOf(d("2026-04-01"), d("2026-04-15"), d("2026-04-30"), d("2026-05-01")),
            today = d("2026-05-05"),
        )
        assertThat(out.currentStreakMonths).isEqualTo(2)
        assertThat(out.longestStreakMonths).isEqualTo(2)
    }

    @Test
    @DisplayName("Current-month grace: streak survives if last snapshot was last month")
    fun currentMonthGrace() {
        val out = StreakCalculator.compute(
            listOf(d("2026-02-15"), d("2026-03-10"), d("2026-04-12")),
            today = d("2026-05-05"),
        )
        // No snapshot in May yet; April was the last month → grace keeps streak at 3.
        assertThat(out.currentStreakMonths).isEqualTo(3)
    }

    @Test
    @DisplayName("Streak breaks when last snapshot is older than the previous month")
    fun streakBreaksOnGap() {
        val out = StreakCalculator.compute(
            listOf(d("2026-01-15"), d("2026-02-10"), d("2026-03-12")),
            today = d("2026-05-05"),
        )
        // Last snapshot = March, today = May → April is a missed month, streak broken.
        assertThat(out.currentStreakMonths).isEqualTo(0)
        assertThat(out.longestStreakMonths).isEqualTo(3)
    }

    @Test
    @DisplayName("Longest streak captures historical run even when current is broken")
    fun longestRunHistorical() {
        val out = StreakCalculator.compute(
            listOf(
                // Long run early in 2025, then a gap, then a short fresh run.
                d("2025-01-15"), d("2025-02-15"), d("2025-03-15"), d("2025-04-15"), d("2025-05-15"),
                d("2026-04-12"), d("2026-05-02"),
            ),
            today = d("2026-05-05"),
        )
        assertThat(out.longestStreakMonths).isEqualTo(5)
        assertThat(out.currentStreakMonths).isEqualTo(2)
    }

    @Test
    @DisplayName("Year boundary (Dec → Jan) counts as consecutive")
    fun yearBoundary() {
        val out = StreakCalculator.compute(
            listOf(d("2025-11-10"), d("2025-12-10"), d("2026-01-10"), d("2026-02-10")),
            today = d("2026-02-15"),
        )
        assertThat(out.currentStreakMonths).isEqualTo(4)
    }

    @Test
    @DisplayName("Single snapshot in current month → streak of 1")
    fun singleSnapshotCurrent() {
        val out = StreakCalculator.compute(listOf(d("2026-05-02")), today = d("2026-05-05"))
        assertThat(out.currentStreakMonths).isEqualTo(1)
        assertThat(out.longestStreakMonths).isEqualTo(1)
        assertThat(out.lastSnapshotMonth).isEqualTo("2026-05")
    }

    @Test
    @DisplayName("February edge case: short month doesn't change month-counting")
    fun februaryEdge() {
        val out = StreakCalculator.compute(
            listOf(d("2026-01-31"), d("2026-02-28"), d("2026-03-01")),
            today = d("2026-03-05"),
        )
        assertThat(out.currentStreakMonths).isEqualTo(3)
    }
}
