package com.fintrack.data.db.seed

import androidx.room.withTransaction
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.repo.SnapshotRepository
import kotlinx.coroutines.flow.first
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Inserts the spec §5 sample users and snapshots on first launch of a debug build.
 * Idempotent: looks for "Mr. X" before doing any writes.
 *
 * Call site: [com.fintrack.FintrackApp.onCreate], gated on `BuildConfig.DEBUG`.
 */
@Singleton
class DevSeedRunner @Inject constructor(
    private val database: FintrackDatabase,
    private val snapshotRepository: SnapshotRepository,
) {

    /** @return true if seeding occurred, false if the data was already present. */
    suspend fun runIfNeeded(): Boolean {
        val users = database.userDao()
        if (users.getUser(DevSeedData.userAId) != null) return false

        val byName: Map<String, UUID> = database.holdingDao()
            .observeAll()
            .first()
            .associate { it.name to it.id }

        database.withTransaction {
            users.insert(DevSeedData.userA)
            users.insert(DevSeedData.userB)
            database.userSettingsDao().upsert(DevSeedData.userASettings)
            database.userSettingsDao().upsert(DevSeedData.userBSettings)
        }

        for (snap in DevSeedData.snapshotsForUserA(byName)) {
            snapshotRepository.createSnapshot(
                userId = DevSeedData.userAId,
                date = snap.date,
                earningsInCr = snap.earningsInCr,
                notes = null,
                holdingValues = snap.rows,
            )
        }
        for (snap in DevSeedData.snapshotsForUserB(byName)) {
            snapshotRepository.createSnapshot(
                userId = DevSeedData.userBId,
                date = snap.date,
                earningsInCr = snap.earningsInCr,
                notes = null,
                holdingValues = snap.rows,
            )
        }
        return true
    }
}
