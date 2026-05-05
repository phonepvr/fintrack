package com.fintrack.domain.goals

import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.domain.model.GoalType
import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

class GoalCalculatorTest {

    private val createdAtInstant = Instant.parse("2026-01-01T00:00:00Z")

    private fun netWorthGoal(target: String, targetDate: String) = GoalEntity(
        id = UUID.randomUUID(),
        userId = UUID.randomUUID(),
        name = "First Crore",
        goalType = GoalType.NET_WORTH,
        targetNetWorth = BigDecimal(target),
        targetDate = LocalDate.parse(targetDate),
        createdAt = createdAtInstant,
    )

    private fun debtFreeGoal(targetDate: String) = GoalEntity(
        id = UUID.randomUUID(),
        userId = UUID.randomUUID(),
        name = "Debt-free",
        goalType = GoalType.DEBT_FREE,
        targetNetWorth = BigDecimal.ZERO,
        targetDate = LocalDate.parse(targetDate),
        createdAt = createdAtInstant,
    )

    @Test
    @DisplayName("ON_TRACK when progress matches the straight line within ±5%")
    fun onTrack() {
        // 6 months elapsed of a 1-year goal → 50% straight-line.
        val out = GoalCalculator.progress(
            goal = netWorthGoal("10000000", "2026-12-31"),
            latestNetWorth = BigDecimal("5300000"),  // 53% — within 5% of 50% line
            latestLiabilities = BigDecimal.ZERO,
            today = LocalDate.parse("2026-07-01"),
        )
        assertThat(out.pace).isEqualTo(GoalPace.ON_TRACK)
    }

    @Test
    @DisplayName("AHEAD when progress > line + 5%")
    fun ahead() {
        val out = GoalCalculator.progress(
            goal = netWorthGoal("10000000", "2026-12-31"),
            latestNetWorth = BigDecimal("8000000"),  // 80% vs 50% line
            latestLiabilities = BigDecimal.ZERO,
            today = LocalDate.parse("2026-07-01"),
        )
        assertThat(out.pace).isEqualTo(GoalPace.AHEAD)
    }

    @Test
    @DisplayName("BEHIND when progress < line - 5%")
    fun behind() {
        val out = GoalCalculator.progress(
            goal = netWorthGoal("10000000", "2026-12-31"),
            latestNetWorth = BigDecimal("2000000"),  // 20% vs 50% line
            latestLiabilities = BigDecimal.ZERO,
            today = LocalDate.parse("2026-07-01"),
        )
        assertThat(out.pace).isEqualTo(GoalPace.BEHIND)
    }

    @Test
    @DisplayName("ACHIEVED when net worth is at or beyond target")
    fun achievedNetWorth() {
        val out = GoalCalculator.progress(
            goal = netWorthGoal("10000000", "2026-12-31"),
            latestNetWorth = BigDecimal("12000000"),
            latestLiabilities = BigDecimal.ZERO,
            today = LocalDate.parse("2026-08-01"),
        )
        assertThat(out.pace).isEqualTo(GoalPace.ACHIEVED)
        assertThat(out.progressPct).isEqualTo(BigDecimal("100"))
    }

    @Test
    @DisplayName("MISSED when target date passes without reaching the goal")
    fun missed() {
        val out = GoalCalculator.progress(
            goal = netWorthGoal("10000000", "2026-04-01"),
            latestNetWorth = BigDecimal("4000000"),
            latestLiabilities = BigDecimal.ZERO,
            today = LocalDate.parse("2026-05-01"),
        )
        assertThat(out.pace).isEqualTo(GoalPace.MISSED)
    }

    @Test
    @DisplayName("DEBT_FREE goal flips to ACHIEVED when liabilities = 0")
    fun debtFreeAchieved() {
        val out = GoalCalculator.progress(
            goal = debtFreeGoal("2027-01-01"),
            latestNetWorth = BigDecimal("8000000"),
            latestLiabilities = BigDecimal.ZERO,
            today = LocalDate.parse("2026-07-01"),
        )
        assertThat(out.pace).isEqualTo(GoalPace.ACHIEVED)
        assertThat(out.progressPct).isEqualTo(BigDecimal("100"))
    }

    @Test
    @DisplayName("DEBT_FREE goal stays at 0% while liabilities > 0")
    fun debtFreeNotAchieved() {
        val out = GoalCalculator.progress(
            goal = debtFreeGoal("2027-01-01"),
            latestNetWorth = BigDecimal("8000000"),
            latestLiabilities = BigDecimal("500000"),
            today = LocalDate.parse("2026-07-01"),
        )
        assertThat(out.progressPct).isEqualTo(BigDecimal.ZERO)
    }

    @Test
    @DisplayName("Negative net worth coerces to 0% progress, not below")
    fun negativeNetWorth() {
        val out = GoalCalculator.progress(
            goal = netWorthGoal("10000000", "2026-12-31"),
            latestNetWorth = BigDecimal("-100000"),
            latestLiabilities = BigDecimal.ZERO,
            today = LocalDate.parse("2026-07-01"),
        )
        assertThat(out.progressPct).isEqualTo(BigDecimal("0.00"))
    }

    @Test
    @DisplayName("Pre-creation today date clamps straight line to 0%")
    fun beforeCreation() {
        val out = GoalCalculator.progress(
            goal = netWorthGoal("10000000", "2026-12-31"),
            latestNetWorth = BigDecimal.ZERO,
            latestLiabilities = BigDecimal.ZERO,
            today = LocalDate.parse("2025-12-15"),  // before createdAt
        )
        assertThat(out.straightLinePct).isEqualTo(BigDecimal("0.00"))
    }
}
