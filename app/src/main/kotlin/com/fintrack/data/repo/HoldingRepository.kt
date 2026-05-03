package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.domain.model.AssetClass
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HoldingRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.holdingDao()

    fun observeAll(): Flow<List<HoldingEntity>> = dao.observeAll()
    fun observeActive(): Flow<List<HoldingEntity>> = dao.observeActive()
    fun observeByAssetClass(assetClass: AssetClass): Flow<List<HoldingEntity>> =
        dao.observeByAssetClass(assetClass)
}
