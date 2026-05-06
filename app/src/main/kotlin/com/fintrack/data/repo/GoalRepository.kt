package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.domain.goals.GoalAchievementDetector
import com.fintrack.domain.model.GoalType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoalRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.goalDao()
    private val snapshots get() = database.snapshotDao()
    private val holdingValues get() = database.holdingValueDao()
    private val loanValues get() = database.loanValueDao()

    fun observeActive(userId: UUID): Flow<List<GoalEntity>> = dao.observeActiveForUser(userId)
    fun observeAll(userId: UUID): Flow<List<GoalEntity>> = dao.observeAllForUser(userId)

    suspend fun create(
        userId: UUID,
        name: String,
        goalType: GoalType,
        targetNetWorth: BigDecimal,
        targetDate: LocalDate,
        startingLiabilities: BigDecimal = BigDecimal.ZERO,
    ): UUID {
        val entity = GoalEntity(
            id = UUID.randomUUID(),
            userId = userId,
            name = name.trim(),
            goalType = goalType,
            targetNetWorth = targetNetWorth,
            targetDate = targetDate,
            createdAt = Clock.System.now(),
            startingLiabilities = if (goalType == GoalType.DEBT_FREE) startingLiabilities else BigDecimal.ZERO,
        )
        dao.insert(entity)
        return entity.id
    }

    suspend fun update(goal: GoalEntity) = dao.update(goal)

    suspend fun archive(userId: UUID, goalId: UUID) {
        val existing = dao.getForUser(userId, goalId) ?: return
        dao.update(existing.copy(isArchived = true))
    }

    suspend fun delete(userId: UUID, goalId: UUID) = dao.deleteForUser(userId, goalId)

    /**
     * Stamp `achievedAt` on any non-archived goal whose target is met by
     * the user's most recent snapshot, using that snapshot's date as the
     * achievement marker. One-way latch: goals already carrying an
     * `achievedAt` are skipped, so a later metric dip can never un-achieve
     * a goal. Idempotent — re-running with no new achievements is a no-op.
     */
    suspend fun detectAndPersistAchievements(userId: UUID) {
        val candidates = dao.observeActiveForUser(userId).first()
            .filter { it.achievedAt == null }
        if (candidates.isEmpty()) return

        val latestSnapshot = snapshots.getAllForUser(userId)
            .maxByOrNull { it.snapshotDate } ?: return
        val hv = holdingValues.getForSnapshot(userId, latestSnapshot.id)
        val lv = loanValues.getForSnapshot(userId, latestSnapshot.id)
        val currentAssets = hv.fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
        val currentLiabilities = lv.fold(BigDecimal.ZERO) { acc, v -> acc + v.outstanding }
        val currentNetWorth = currentAssets - currentLiabilities

        val newlyAchieved = GoalAchievementDetector.newlyAchieved(
            goals = candidates,
            currentNetWorth = currentNetWorth,
            currentLiabilities = currentLiabilities,
        )
        for (goal in newlyAchieved) {
            dao.update(goal.copy(achievedAt = latestSnapshot.snapshotDate))
        }
    }
}
