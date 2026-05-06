package com.fintrack.ui.journey.goals

import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.domain.goals.GoalPace
import com.fintrack.domain.goals.GoalProgress
import com.fintrack.domain.model.GoalType
import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

class JourneyGoalsCardSubtitleTest {

    private val createdAt = Instant.parse("2026-01-01T00:00:00Z")
    private val targetDate = LocalDate.parse("2027-01-01")

    private fun progress(
        goalType: GoalType,
        currentValue: BigDecimal,
        targetValue: BigDecimal = BigDecimal.ZERO,
        startingLiabilities: BigDecimal = BigDecimal.ZERO,
        achievedAt: LocalDate? = null,
    ): GoalProgress {
        val goal = GoalEntity(
            id = UUID.randomUUID(),
            userId = UUID.randomUUID(),
            name = "Test goal",
            goalType = goalType,
            targetNetWorth = if (goalType == GoalType.NET_WORTH) targetValue else BigDecimal.ZERO,
            targetDate = targetDate,
            createdAt = createdAt,
            startingLiabilities = startingLiabilities,
            achievedAt = achievedAt,
        )
        return GoalProgress(
            goal = goal,
            currentValue = currentValue,
            targetValue = targetValue,
            progressPct = BigDecimal.ZERO,
            straightLinePct = BigDecimal.ZERO,
            pace = GoalPace.ON_TRACK,
        )
    }

    @Test
    @DisplayName("NET_WORTH: shows current of target")
    fun netWorth() {
        val p = progress(
            goalType = GoalType.NET_WORTH,
            currentValue = BigDecimal("5000000"),
            targetValue = BigDecimal("10000000"),
        )
        assertThat(goalCardSubtitle(p)).isEqualTo("₹50.00 L of ₹1.00 Cr")
    }

    @Test
    @DisplayName("DEBT_FREE with anchor: shows outstanding + paid of starting")
    fun debtFreePartialPaid() {
        val p = progress(
            goalType = GoalType.DEBT_FREE,
            currentValue = BigDecimal("3000000"),
            startingLiabilities = BigDecimal("5000000"),
        )
        assertThat(goalCardSubtitle(p))
            .isEqualTo("₹30.00 L outstanding · ₹20.00 L paid of ₹50.00 L")
    }

    @Test
    @DisplayName("DEBT_FREE with anchor, nothing paid yet: paid = ₹0")
    fun debtFreeNothingPaid() {
        val p = progress(
            goalType = GoalType.DEBT_FREE,
            currentValue = BigDecimal("5000000"),
            startingLiabilities = BigDecimal("5000000"),
        )
        assertThat(goalCardSubtitle(p))
            .isEqualTo("₹50.00 L outstanding · ₹0 paid of ₹50.00 L")
    }

    @Test
    @DisplayName("DEBT_FREE fully paid: outstanding = ₹0, paid = whole starting")
    fun debtFreeFullyPaid() {
        val p = progress(
            goalType = GoalType.DEBT_FREE,
            currentValue = BigDecimal.ZERO,
            startingLiabilities = BigDecimal("5000000"),
        )
        assertThat(goalCardSubtitle(p))
            .isEqualTo("₹0 outstanding · ₹50.00 L paid of ₹50.00 L")
    }

    @Test
    @DisplayName("DEBT_FREE legacy (startingLiabilities = 0): shows only outstanding")
    fun debtFreeLegacy() {
        val p = progress(
            goalType = GoalType.DEBT_FREE,
            currentValue = BigDecimal("3000000"),
            startingLiabilities = BigDecimal.ZERO,
        )
        assertThat(goalCardSubtitle(p)).isEqualTo("₹30.00 L outstanding")
    }

    @Test
    @DisplayName("DEBT_FREE with current > starting: paid clamps to ₹0 (defensive)")
    fun debtFreeCurrentExceedsStart() {
        val p = progress(
            goalType = GoalType.DEBT_FREE,
            currentValue = BigDecimal("6000000"),
            startingLiabilities = BigDecimal("5000000"),
        )
        // User took on MORE debt after creating the goal — paid stays at 0,
        // doesn't go negative.
        assertThat(goalCardSubtitle(p))
            .isEqualTo("₹60.00 L outstanding · ₹0 paid of ₹50.00 L")
    }
}
