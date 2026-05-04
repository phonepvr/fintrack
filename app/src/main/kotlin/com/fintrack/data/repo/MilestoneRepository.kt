package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.MilestoneEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Phase A: skeleton with reads + idempotent insert. Detection logic lands
 * in Phase G (MilestoneDetector) along with the celebration sheet.
 */
@Singleton
class MilestoneRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.milestoneDao()

    fun observeForUser(userId: UUID): Flow<List<MilestoneEntity>> = dao.observeForUser(userId)
    fun observeUncelebrated(userId: UUID): Flow<List<MilestoneEntity>> =
        dao.observeUncelebratedForUser(userId)

    suspend fun getAllForUser(userId: UUID): List<MilestoneEntity> = dao.getAllForUser(userId)

    suspend fun markCelebrated(userId: UUID, id: UUID) = dao.markCelebrated(userId, id)
}
