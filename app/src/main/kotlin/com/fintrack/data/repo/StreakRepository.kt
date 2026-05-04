package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.StreakStateEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Phase A: read-side wrapper around StreakStateDao. Recompute job lands in
 * Phase F (StreakCalculator) with the chip + nudge banner.
 */
@Singleton
class StreakRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.streakStateDao()

    fun observeForUser(userId: UUID): Flow<StreakStateEntity?> = dao.observeForUser(userId)

    suspend fun getForUser(userId: UUID): StreakStateEntity? = dao.getForUser(userId)

    suspend fun upsert(state: StreakStateEntity) = dao.upsert(state)
}
