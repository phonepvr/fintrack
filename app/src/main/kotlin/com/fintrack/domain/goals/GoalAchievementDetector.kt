package com.fintrack.domain.goals

import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.domain.model.GoalType
import java.math.BigDecimal

/**
 * Pure detector for the goal-achievement latch. Runs after every snapshot
 * mutation; for any non-archived goal whose `achievedAt` is still null and
 * whose target condition is met against the latest metrics, returns the
 * goal id so the caller can stamp `achievedAt = today`.
 *
 * Mirrors the achievement condition inside [GoalCalculator.progress] —
 * NET_WORTH meets target when `currentNetWorth >= goal.targetNetWorth`
 * (and target > 0); DEBT_FREE meets when `currentLiabilities == 0`.
 *
 * The latch is one-way: we only return goals that aren't already achieved.
 * Once stamped, [GoalCalculator] keeps `pace = ACHIEVED` regardless of
 * later metric dips, so the user never loses a milestone they reached.
 */
object GoalAchievementDetector {

    fun newlyAchieved(
        goals: List<GoalEntity>,
        currentNetWorth: BigDecimal,
        currentLiabilities: BigDecimal,
    ): List<GoalEntity> = goals.filter { goal ->
        goal.achievedAt == null && when (goal.goalType) {
            GoalType.NET_WORTH ->
                goal.targetNetWorth.signum() > 0 &&
                    currentNetWorth >= goal.targetNetWorth
            GoalType.DEBT_FREE -> currentLiabilities.signum() == 0
        }
    }
}
