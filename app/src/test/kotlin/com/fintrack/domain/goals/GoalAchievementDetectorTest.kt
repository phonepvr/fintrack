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

class GoalAchievementDetectorTest {

    private val createdAt = Instant.parse("2026-01-01T00:00:00Z")
    private val targetDate = LocalDate.parse("2027-01-01")

    private fun netWorthGoal(
        target: String = "10000000",
        achievedAt: LocalDate? = null,
    ) = GoalEntity(
        id = UUID.randomUUID(),
        userId = UUID.randomUUID(),
        name = "First Crore",
        goalType = GoalType.NET_WORTH,
        targetNetWorth = BigDecimal(target),
        targetDate = targetDate,
        createdAt = createdAt,
        achievedAt = achievedAt,
    )

    private fun debtFreeGoal(
        startingLiabilities: String = "5000000",
        achievedAt: LocalDate? = null,
    ) = GoalEntity(
        id = UUID.randomUUID(),
        userId = UUID.randomUUID(),
        name = "Debt-free",
        goalType = GoalType.DEBT_FREE,
        targetNetWorth = BigDecimal.ZERO,
        targetDate = targetDate,
        createdAt = createdAt,
        startingLiabilities = BigDecimal(startingLiabilities),
        achievedAt = achievedAt,
    )

    @Test
    @DisplayName("NET_WORTH: net worth at or above target → newly achieved")
    fun netWorthAtTarget() {
        val goal = netWorthGoal(target = "10000000")
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(goal),
            currentNetWorth = BigDecimal("10000000"),
            currentLiabilities = BigDecimal("0"),
        )
        assertThat(newly).containsExactly(goal)
    }

    @Test
    @DisplayName("NET_WORTH: net worth above target → newly achieved")
    fun netWorthAboveTarget() {
        val goal = netWorthGoal(target = "10000000")
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(goal),
            currentNetWorth = BigDecimal("12000000"),
            currentLiabilities = BigDecimal("0"),
        )
        assertThat(newly).containsExactly(goal)
    }

    @Test
    @DisplayName("NET_WORTH: net worth below target → not yet achieved")
    fun netWorthBelowTarget() {
        val goal = netWorthGoal(target = "10000000")
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(goal),
            currentNetWorth = BigDecimal("9999999"),
            currentLiabilities = BigDecimal("0"),
        )
        assertThat(newly).isEmpty()
    }

    @Test
    @DisplayName("NET_WORTH: target = 0 never triggers achievement")
    fun netWorthZeroTargetNeverAchieves() {
        val goal = netWorthGoal(target = "0")
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(goal),
            currentNetWorth = BigDecimal("12000000"),
            currentLiabilities = BigDecimal("0"),
        )
        assertThat(newly).isEmpty()
    }

    @Test
    @DisplayName("DEBT_FREE: liabilities at zero → newly achieved")
    fun debtFreeZeroLiabilities() {
        val goal = debtFreeGoal(startingLiabilities = "5000000")
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(goal),
            currentNetWorth = BigDecimal("8000000"),
            currentLiabilities = BigDecimal.ZERO,
        )
        assertThat(newly).containsExactly(goal)
    }

    @Test
    @DisplayName("DEBT_FREE: liabilities still positive → not yet achieved")
    fun debtFreeStillHasDebt() {
        val goal = debtFreeGoal(startingLiabilities = "5000000")
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(goal),
            currentNetWorth = BigDecimal("8000000"),
            currentLiabilities = BigDecimal("100000"),
        )
        assertThat(newly).isEmpty()
    }

    @Test
    @DisplayName("Latch is one-way: already-achieved goal is never re-flagged")
    fun alreadyAchievedSkipped() {
        val goal = netWorthGoal(
            target = "10000000",
            achievedAt = LocalDate.parse("2026-03-15"),
        )
        // Net worth has since dropped well below target.
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(goal),
            currentNetWorth = BigDecimal("5000000"),
            currentLiabilities = BigDecimal.ZERO,
        )
        assertThat(newly).isEmpty()
    }

    @Test
    @DisplayName("Latch is one-way: already-achieved goal at target is still skipped")
    fun alreadyAchievedAtTargetSkipped() {
        val goal = debtFreeGoal(
            startingLiabilities = "5000000",
            achievedAt = LocalDate.parse("2026-03-15"),
        )
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(goal),
            currentNetWorth = BigDecimal("8000000"),
            currentLiabilities = BigDecimal.ZERO,
        )
        assertThat(newly).isEmpty()
    }

    @Test
    @DisplayName("Mixed list: only un-achieved meeting their target are returned")
    fun mixedList() {
        val nwAchieved = netWorthGoal(target = "10000000", achievedAt = LocalDate.parse("2026-02-01"))
        val nwReady = netWorthGoal(target = "10000000")
        val nwNotReady = netWorthGoal(target = "20000000")
        val debt1 = debtFreeGoal(startingLiabilities = "5000000")
        val debt2 = debtFreeGoal(startingLiabilities = "5000000")

        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(nwAchieved, nwReady, nwNotReady, debt1, debt2),
            currentNetWorth = BigDecimal("12000000"),
            currentLiabilities = BigDecimal.ZERO,
        )
        // nwAchieved skipped by the latch; nwReady fires; nwNotReady stays
        // out (target unmet); both DEBT_FREE goals fire because liabs == 0
        // (the detector keys off current liabilities only).
        assertThat(newly).containsExactly(nwReady, debt1, debt2)
    }

    @Test
    @DisplayName("Mixed list with liabilities present: DEBT_FREE goals stay un-achieved")
    fun mixedListWithDebt() {
        val nwReady = netWorthGoal(target = "10000000")
        val debtNotReady = debtFreeGoal(startingLiabilities = "5000000")

        val newly = GoalAchievementDetector.newlyAchieved(
            goals = listOf(nwReady, debtNotReady),
            currentNetWorth = BigDecimal("12000000"),
            currentLiabilities = BigDecimal("100000"),
        )
        assertThat(newly).containsExactly(nwReady)
    }

    @Test
    @DisplayName("Empty input → empty output")
    fun emptyInput() {
        val newly = GoalAchievementDetector.newlyAchieved(
            goals = emptyList<GoalEntity>(),
            currentNetWorth = BigDecimal("12000000"),
            currentLiabilities = BigDecimal.ZERO,
        )
        assertThat(newly).isEmpty()
    }
}
