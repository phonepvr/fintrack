package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.seed.SeedData
import kotlinx.coroutines.flow.Flow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Per-user aim percentages, one row per active asset class. Sum-to-100 invariant
 * is enforced here at write time — Room can't express it as a CHECK constraint
 * cleanly across rows.
 *
 * v3 replacement for v2's UserSettingsRepository (which had four fixed columns).
 */
@Singleton
class AimAllocationRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.aimAllocationDao()

    fun observeForUser(userId: UUID): Flow<List<AimAllocationEntity>> = dao.observeForUser(userId)

    suspend fun getForUser(userId: UUID): List<AimAllocationEntity> = dao.getForUser(userId)

    /** Reads the user's allocations, falling back to the spec defaults if no rows exist yet. */
    suspend fun getOrDefault(userId: UUID): List<AimAllocationEntity> {
        val existing = dao.getForUser(userId)
        if (existing.isNotEmpty()) return existing
        return SeedData.defaultAimPercentByAssetClass.map { (acId, pct) ->
            AimAllocationEntity(userId = userId, assetClassId = acId, aimPercent = pct)
        }
    }

    /**
     * Replaces the active set for [userId]. Sum-to-100 across the rows passed in
     * is enforced; existing rows whose [AimAllocationEntity.assetClassId] is not
     * present in [rows] are kept.
     */
    suspend fun replace(userId: UUID, rows: List<AimAllocationEntity>) {
        require(rows.all { it.userId == userId }) {
            "All AimAllocation rows must reference userId $userId"
        }
        require(rows.sumOf { it.aimPercent } == 100) {
            "Aim percentages must sum to 100, got ${rows.sumOf { it.aimPercent }}"
        }
        dao.upsertAll(rows)
    }
}
