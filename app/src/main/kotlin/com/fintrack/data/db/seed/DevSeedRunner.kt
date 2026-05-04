package com.fintrack.data.db.seed

import androidx.room.withTransaction
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.LoanValueEntity
import com.fintrack.data.repo.SnapshotRepository
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Inserts the spec §3.4-3.6 sample users, snapshots, loans, loan values and
 * goals on first launch of a debug build. Idempotent — looks for "Mr. X"
 * before doing any writes.
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

        val now = Clock.System.now()

        database.withTransaction {
            users.insert(DevSeedData.userA)
            users.insert(DevSeedData.userB)
            database.aimAllocationDao().upsertAll(DevSeedData.userAAimAllocations)
            database.aimAllocationDao().upsertAll(DevSeedData.userBAimAllocations)
            // Loans for User A.
            for (loan in DevSeedData.seedLoansForUserA) {
                database.loanDao().insert(loan.toEntity(DevSeedData.userAId, now))
            }
            // Goals for User A.
            database.goalDao().insertAll(DevSeedData.goalsForUserA(now))
        }

        // Snapshots for User A — also insert matching LoanValue rows for each.
        val loanByName: Map<String, UUID> = DevSeedData.seedLoansForUserA.associate { it.name to it.id }
        for (snap in DevSeedData.snapshotsForUserA(byName)) {
            val snapshotId = snapshotRepository.createSnapshot(
                userId = DevSeedData.userAId,
                date = snap.date,
                earningsInCr = snap.earningsInCr,
                notes = null,
                holdingValues = snap.rows,
            )
            val loanValuesForDate = DevSeedData.loanValuesForUserA[snap.date].orEmpty()
            if (loanValuesForDate.isNotEmpty()) {
                val rows = loanValuesForDate.map { (loanName, outstanding) ->
                    val loanId = loanByName[loanName] ?: error("Seed references missing loan: $loanName")
                    LoanValueEntity(
                        id = UUID.randomUUID(),
                        snapshotId = snapshotId,
                        loanId = loanId,
                        outstanding = outstanding,
                    )
                }
                database.loanValueDao().insertAll(rows)
            }
        }

        // Snapshots for User B (no loans).
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
