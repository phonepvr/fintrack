package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.SubBucketEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * AssetClass + SubBucket reads. The taxonomy is global (shared across users).
 * Mutations land in Phase D once the Holdings management UI is ready.
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
}
