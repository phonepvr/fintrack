package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.HoldingEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HoldingRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.holdingDao()

    fun observeAll(): Flow<List<HoldingEntity>> = dao.observeAll()
    fun observeActive(): Flow<List<HoldingEntity>> = dao.observeActive()
    fun observeBySubBucket(subBucketId: UUID): Flow<List<HoldingEntity>> =
        dao.observeBySubBucket(subBucketId)

    suspend fun createHolding(
        subBucketId: UUID,
        name: String,
        trackInvested: Boolean,
        trackSip: Boolean,
        displayOrder: Int,
    ): UUID {
        val entity = HoldingEntity(
            id = UUID.randomUUID(),
            subBucketId = subBucketId,
            name = name.trim(),
            trackInvested = trackInvested,
            trackSip = trackSip,
            isActive = true,
            displayOrder = displayOrder,
        )
        dao.insert(entity)
        return entity.id
    }

    suspend fun update(holding: HoldingEntity) {
        dao.update(holding)
    }

    suspend fun setActive(holdingId: UUID, active: Boolean) {
        dao.setActive(holdingId, active)
    }

    suspend fun reorderWithinSubBucket(subBucketId: UUID, orderedIds: List<UUID>) {
        orderedIds.forEachIndexed { index, id ->
            val existing = dao.get(id) ?: return@forEachIndexed
            if (existing.subBucketId != subBucketId) return@forEachIndexed
            if (existing.displayOrder != index) {
                dao.update(existing.copy(displayOrder = index))
            }
        }
    }
}
