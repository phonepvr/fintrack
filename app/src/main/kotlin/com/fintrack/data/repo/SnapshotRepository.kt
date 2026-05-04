package com.fintrack.data.repo

import androidx.room.withTransaction
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/** Mutable shape for entry forms — values are nullable until the user types something. */
data class HoldingValueDraft(
    val holdingId: UUID,
    val invested: BigDecimal?,
    val current: BigDecimal,
    val sip: BigDecimal?,
)

@Singleton
class SnapshotRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val snapshots get() = database.snapshotDao()
    private val values get() = database.holdingValueDao()

    fun observeForUser(userId: UUID): Flow<List<SnapshotEntity>> =
        snapshots.observeForUser(userId)

    fun observeAllValuesForUser(userId: UUID): Flow<List<HoldingValueEntity>> =
        values.observeAllForUser(userId)

    fun observeValuesForSnapshot(userId: UUID, snapshotId: UUID): Flow<List<HoldingValueEntity>> =
        values.observeForSnapshot(userId, snapshotId)

    suspend fun getValuesForSnapshot(userId: UUID, snapshotId: UUID): List<HoldingValueEntity> =
        values.getForSnapshot(userId, snapshotId)

    suspend fun getForUser(userId: UUID, snapshotId: UUID): SnapshotEntity? =
        snapshots.getForUser(userId, snapshotId)

    suspend fun previousForUser(userId: UUID, before: LocalDate): SnapshotEntity? =
        snapshots.previousForUser(userId, before)

    suspend fun countForUser(userId: UUID): Int = snapshots.countForUser(userId)

    /**
     * Atomically inserts a snapshot and its holding values. Validation
     * (date / non-negative amounts / non-empty holdings) is the caller's
     * responsibility — the form layer handles it before reaching here.
     */
    suspend fun createSnapshot(
        userId: UUID,
        date: LocalDate,
        earningsInCr: BigDecimal,
        notes: String?,
        holdingValues: List<HoldingValueDraft>,
    ): UUID {
        val now = Clock.System.now()
        val snapshot = SnapshotEntity(
            id = UUID.randomUUID(),
            userId = userId,
            snapshotDate = date,
            earningsInCr = earningsInCr,
            notes = notes,
            createdAt = now,
            updatedAt = now,
        )
        database.withTransaction {
            snapshots.insert(snapshot)
            values.insertAll(holdingValues.map { it.toEntity(snapshot.id) })
        }
        return snapshot.id
    }

    /**
     * Replaces the snapshot's metadata and the entire set of holding values.
     * Easier than diffing additions/removals/edits — the snapshot's value set
     * is small (≤ catalog size, currently 13).
     */
    suspend fun updateSnapshot(
        userId: UUID,
        snapshotId: UUID,
        date: LocalDate,
        earningsInCr: BigDecimal,
        notes: String?,
        holdingValues: List<HoldingValueDraft>,
    ) {
        val existing = snapshots.getForUser(userId, snapshotId)
            ?: error("Snapshot $snapshotId not found for user $userId")
        val updated = existing.copy(
            snapshotDate = date,
            earningsInCr = earningsInCr,
            notes = notes,
            updatedAt = Clock.System.now(),
        )
        database.withTransaction {
            snapshots.update(updated)
            values.deleteAllForSnapshot(snapshotId)
            values.insertAll(holdingValues.map { it.toEntity(snapshotId) })
        }
    }

    /** Clones an existing snapshot with a new id and date (defaults to source's date). */
    suspend fun duplicateSnapshot(
        userId: UUID,
        snapshotId: UUID,
        newDate: LocalDate,
    ): UUID {
        val original = snapshots.getForUser(userId, snapshotId)
            ?: error("Snapshot $snapshotId not found for user $userId")
        val originalValues = values.getForSnapshot(userId, snapshotId)
        return createSnapshot(
            userId = userId,
            date = newDate,
            earningsInCr = original.earningsInCr,
            notes = original.notes,
            holdingValues = originalValues.map {
                HoldingValueDraft(
                    holdingId = it.holdingId,
                    invested = it.invested,
                    current = it.current,
                    sip = it.sip,
                )
            },
        )
    }

    suspend fun deleteSnapshot(userId: UUID, snapshotId: UUID) {
        snapshots.deleteForUser(userId, snapshotId)
    }

    private fun HoldingValueDraft.toEntity(snapshotId: UUID): HoldingValueEntity =
        HoldingValueEntity(
            id = UUID.randomUUID(),
            snapshotId = snapshotId,
            holdingId = holdingId,
            invested = invested,
            current = current,
            sip = sip,
        )
}
