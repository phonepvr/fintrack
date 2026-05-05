package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.MilestoneEntity
import com.fintrack.domain.milestones.AwardedMilestone
import com.fintrack.domain.milestones.MilestoneDetector
import com.fintrack.domain.milestones.SnapshotPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Read-side wrapper plus the detection job called after every snapshot
 * mutation and on user activation (silent backfill for any history that
 * predates the v3 milestone surface).
 */
@Singleton
class MilestoneRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.milestoneDao()
    private val snapshots get() = database.snapshotDao()
    private val holdingValues get() = database.holdingValueDao()
    private val loanValues get() = database.loanValueDao()

    fun observeForUser(userId: UUID): Flow<List<MilestoneEntity>> = dao.observeForUser(userId)
    fun observeUncelebrated(userId: UUID): Flow<List<MilestoneEntity>> =
        dao.observeUncelebratedForUser(userId)

    suspend fun getAllForUser(userId: UUID): List<MilestoneEntity> = dao.getAllForUser(userId)

    suspend fun markCelebrated(userId: UUID, id: UUID) = dao.markCelebrated(userId, id)

    /**
     * Recompute milestone awards for [userId] from the current snapshot
     * history and persist any new ones. Idempotent — re-running with no
     * new threshold crossings inserts nothing.
     */
    suspend fun detectAndPersist(userId: UUID) {
        val snaps = snapshots.getAllForUser(userId)
        if (snaps.isEmpty()) return

        val points = snaps.map { snap ->
            val hv = holdingValues.getForSnapshot(userId, snap.id)
            val lv = loanValues.getForSnapshot(userId, snap.id)
            val assets = hv.fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
            val liabilities = lv.fold(BigDecimal.ZERO) { acc, v -> acc + v.outstanding }
            SnapshotPoint(
                snapshotId = snap.id,
                date = snap.snapshotDate,
                netWorth = assets - liabilities,
                totalLiabilities = liabilities,
            )
        }

        val existing = dao.getAllForUser(userId)
            .map { AwardedMilestone(it.type, it.achievedAtSnapshotId) }
        val awards = MilestoneDetector.detect(points, existing)
        if (awards.isEmpty()) return

        val now = Clock.System.now()
        // Awards anchored to a snapshot older than CELEBRATION_GRACE_DAYS
        // arrive pre-celebrated — that's the silent backfill on v3 first
        // launch / device restore. Recent organic unlocks pop the sheet.
        val celebrationCutoff = now.toLocalDateTime(TimeZone.currentSystemDefault()).date
            .minus(DatePeriod(days = CELEBRATION_GRACE_DAYS))
        val rows = awards.map { a ->
            MilestoneEntity(
                id = UUID.randomUUID(),
                userId = userId,
                type = a.type,
                achievedAtSnapshotId = a.snapshotId,
                achievedAtDate = a.date,
                amountAtAchievement = a.amount,
                isCelebrated = a.date < celebrationCutoff,
                createdAt = now,
            )
        }
        dao.insertAll(rows)
    }

    private companion object {
        const val CELEBRATION_GRACE_DAYS = 7
    }
}
