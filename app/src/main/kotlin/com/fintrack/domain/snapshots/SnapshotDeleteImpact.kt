package com.fintrack.domain.snapshots

import com.fintrack.data.db.entities.SnapshotEntity
import java.util.UUID

/**
 * Risk signals shown in the snapshot delete confirmation dialog. Both flags
 * default to false — when neither is true the dialog only renders the base
 * message.
 *
 * `streakMonths` is the user's current streak length, surfaced only when
 * [breaksActiveStreak] is true so the warning copy can name the number.
 */
data class SnapshotDeleteImpact(
    val isLatest: Boolean = false,
    val breaksActiveStreak: Boolean = false,
    val streakMonths: Int = 0,
)

/**
 * Pure helper that classifies a pending snapshot delete. The streak signal
 * is a heuristic ("this snapshot is the only one in its calendar month and
 * the user has an active streak") — `StreakRepository.recompute` produces
 * the actually-correct streak post-delete; this is just for warning copy.
 */
object SnapshotDeleteImpactAnalyzer {
    fun analyze(
        targetSnapshotId: UUID,
        allSnapshots: List<SnapshotEntity>,
        currentStreakMonths: Int,
    ): SnapshotDeleteImpact {
        val target = allSnapshots.firstOrNull { it.id == targetSnapshotId }
            ?: return SnapshotDeleteImpact()

        val latestDate = allSnapshots.maxOf { it.snapshotDate }
        val isLatest = target.snapshotDate == latestDate &&
            allSnapshots.filter { it.snapshotDate == latestDate }
                .maxByOrNull { it.createdAt }?.id == targetSnapshotId

        val targetMonthCount = allSnapshots.count {
            it.snapshotDate.year == target.snapshotDate.year &&
                it.snapshotDate.monthNumber == target.snapshotDate.monthNumber
        }
        val breaksActiveStreak = currentStreakMonths > 0 && targetMonthCount == 1

        return SnapshotDeleteImpact(
            isLatest = isLatest,
            breaksActiveStreak = breaksActiveStreak,
            streakMonths = if (breaksActiveStreak) currentStreakMonths else 0,
        )
    }
}
