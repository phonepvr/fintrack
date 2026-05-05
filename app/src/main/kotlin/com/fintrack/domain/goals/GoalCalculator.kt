package com.fintrack.domain.goals

import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.domain.model.GoalType
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.math.BigDecimal
import java.math.RoundingMode

enum class GoalPace { AHEAD, ON_TRACK, BEHIND, MISSED, ACHIEVED }

/**
 * Computed view of a goal at a particular moment.
 *
 *   progressPct     = currentValue / target × 100   (or 100 when achieved)
 *   straightLinePct = elapsed-time / total-time × 100
 *
 * The pace bucket is the gap between the two:
 *   diff > +5  → AHEAD
 *   |diff| ≤ 5 → ON_TRACK
 *   diff < -5 and target not yet past → BEHIND
 *   target past with target unmet → MISSED
 *   target met any time → ACHIEVED
 */
data class GoalProgress(
    val goal: GoalEntity,
    val currentValue: BigDecimal,
    val targetValue: BigDecimal,
    val progressPct: BigDecimal,
    val straightLinePct: BigDecimal,
    val pace: GoalPace,
)

object GoalCalculator {

    private val HUNDRED = BigDecimal("100")
    private val ON_TRACK_BAND = BigDecimal("5")

    fun progress(
        goal: GoalEntity,
        latestNetWorth: BigDecimal,
        latestLiabilities: BigDecimal,
        today: LocalDate,
    ): GoalProgress {
        // Targets vary by type.
        val targetValue = when (goal.goalType) {
            GoalType.NET_WORTH -> goal.targetNetWorth
            GoalType.DEBT_FREE -> BigDecimal.ZERO
        }
        val currentValue = when (goal.goalType) {
            GoalType.NET_WORTH -> latestNetWorth
            GoalType.DEBT_FREE -> latestLiabilities
        }

        val achieved = goal.achievedAt != null || when (goal.goalType) {
            GoalType.NET_WORTH -> currentValue >= targetValue && targetValue.signum() > 0
            GoalType.DEBT_FREE -> currentValue.signum() == 0
        }

        val progressPct = computeProgressPct(goal.goalType, currentValue, targetValue, achieved)
        val straightLinePct = computeStraightLinePct(goal, today)

        val pace = when {
            achieved -> GoalPace.ACHIEVED
            today > goal.targetDate -> GoalPace.MISSED
            else -> {
                val diff = progressPct.subtract(straightLinePct)
                when {
                    diff > ON_TRACK_BAND -> GoalPace.AHEAD
                    diff < ON_TRACK_BAND.negate() -> GoalPace.BEHIND
                    else -> GoalPace.ON_TRACK
                }
            }
        }

        return GoalProgress(
            goal = goal,
            currentValue = currentValue,
            targetValue = targetValue,
            progressPct = progressPct,
            straightLinePct = straightLinePct,
            pace = pace,
        )
    }

    private fun computeProgressPct(
        type: GoalType,
        current: BigDecimal,
        target: BigDecimal,
        achieved: Boolean,
    ): BigDecimal {
        if (achieved) return HUNDRED
        return when (type) {
            GoalType.NET_WORTH -> {
                if (target.signum() <= 0) BigDecimal.ZERO
                else current.coerceAtLeast(BigDecimal.ZERO)
                    .divide(target, 6, RoundingMode.HALF_UP)
                    .multiply(HUNDRED)
                    .setScale(2, RoundingMode.HALF_UP)
                    .coerceIn(BigDecimal.ZERO, HUNDRED)
            }
            GoalType.DEBT_FREE -> {
                // Without an "original liability" anchor, surface a binary
                // 0 / 100% — DEBT_FREE goals are checked once-and-done.
                if (current.signum() == 0) HUNDRED else BigDecimal.ZERO
            }
        }
    }

    private fun computeStraightLinePct(goal: GoalEntity, today: LocalDate): BigDecimal {
        val createdAtDate = goal.createdAt.toLocalDateTime(TimeZone.UTC).date
        val totalDays = goal.targetDate.toEpochDays() - createdAtDate.toEpochDays()
        if (totalDays <= 0) return HUNDRED
        val elapsedDays = today.toEpochDays() - createdAtDate.toEpochDays()
        val raw = BigDecimal(elapsedDays.coerceAtLeast(0L))
            .divide(BigDecimal(totalDays.toLong()), 6, RoundingMode.HALF_UP)
            .multiply(HUNDRED)
            .setScale(2, RoundingMode.HALF_UP)
        return when {
            raw.compareTo(BigDecimal.ZERO) < 0 -> BigDecimal.ZERO.setScale(2)
            raw.compareTo(HUNDRED) > 0 -> HUNDRED
            else -> raw
        }
    }
}
