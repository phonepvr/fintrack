package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.domain.model.GoalType
import kotlinx.coroutines.flow.Flow
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

    fun observeActive(userId: UUID): Flow<List<GoalEntity>> = dao.observeActiveForUser(userId)
    fun observeAll(userId: UUID): Flow<List<GoalEntity>> = dao.observeAllForUser(userId)

    suspend fun create(
        userId: UUID,
        name: String,
        goalType: GoalType,
        targetNetWorth: BigDecimal,
        targetDate: LocalDate,
    ): UUID {
        val entity = GoalEntity(
            id = UUID.randomUUID(),
            userId = userId,
            name = name.trim(),
            goalType = goalType,
            targetNetWorth = targetNetWorth,
            targetDate = targetDate,
            createdAt = Clock.System.now(),
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
}
