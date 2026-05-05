package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.SubBucketEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * AssetClass + SubBucket reads and mutations. The taxonomy is GLOBAL —
 * shared across every profile on the device. Mutations affect every user.
 */
@Singleton
class TaxonomyRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val acDao get() = database.assetClassDao()
    private val sbDao get() = database.subBucketDao()

    fun observeAssetClasses(): Flow<List<AssetClassEntity>> = acDao.observeAll()
    fun observeActiveAssetClasses(): Flow<List<AssetClassEntity>> = acDao.observeActive()

    suspend fun getActiveAssetClasses(): List<AssetClassEntity> = acDao.getActive()
    suspend fun getActiveSubBuckets(): List<SubBucketEntity> = sbDao.getActive()

    fun observeSubBuckets(): Flow<List<SubBucketEntity>> = sbDao.observeAll()
    fun observeSubBucketsForAssetClass(assetClassId: UUID): Flow<List<SubBucketEntity>> =
        sbDao.observeForAssetClass(assetClassId)

    // -- AssetClass mutations -------------------------------------------------

    suspend fun createAssetClass(name: String, displayOrder: Int): UUID {
        val entity = AssetClassEntity(
            id = UUID.randomUUID(),
            name = name.trim(),
            displayOrder = displayOrder,
            isSeeded = false,
            isActive = true,
            createdAt = Clock.System.now(),
        )
        acDao.insert(entity)
        return entity.id
    }

    suspend fun renameAssetClass(id: UUID, name: String) {
        val existing = acDao.get(id) ?: return
        acDao.update(existing.copy(name = name.trim()))
    }

    suspend fun setAssetClassActive(id: UUID, active: Boolean) {
        acDao.setActive(id, active)
    }

    suspend fun reorderAssetClasses(orderedIds: List<UUID>) {
        orderedIds.forEachIndexed { index, id ->
            val existing = acDao.get(id) ?: return@forEachIndexed
            if (existing.displayOrder != index) {
                acDao.update(existing.copy(displayOrder = index))
            }
        }
    }

    // -- SubBucket mutations --------------------------------------------------

    suspend fun createSubBucket(assetClassId: UUID, name: String, displayOrder: Int): UUID {
        val entity = SubBucketEntity(
            id = UUID.randomUUID(),
            assetClassId = assetClassId,
            name = name.trim(),
            displayOrder = displayOrder,
            isSeeded = false,
            isActive = true,
            createdAt = Clock.System.now(),
        )
        sbDao.insert(entity)
        return entity.id
    }

    suspend fun renameSubBucket(id: UUID, name: String) {
        val existing = sbDao.get(id) ?: return
        sbDao.update(existing.copy(name = name.trim()))
    }

    suspend fun setSubBucketActive(id: UUID, active: Boolean) {
        sbDao.setActive(id, active)
    }

    suspend fun reorderSubBuckets(assetClassId: UUID, orderedIds: List<UUID>) {
        orderedIds.forEachIndexed { index, id ->
            val existing = sbDao.get(id) ?: return@forEachIndexed
            if (existing.assetClassId != assetClassId) return@forEachIndexed
            if (existing.displayOrder != index) {
                sbDao.update(existing.copy(displayOrder = index))
            }
        }
    }
}
