package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.HoldingValueEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * HoldingValue rows belong indirectly to a user via Snapshot.user_id. Every
 * read method JOINs `snapshots` and filters on `snapshots.user_id = :userId`
 * to prevent leaking another profile's values. The privacy acceptance test
 * `SnapshotDaoUserScopedTest` enforces the `userId` parameter via reflection.
 */
@Dao
interface HoldingValueDao {

    @Query(
        """
        SELECT hv.* FROM holding_values hv
        INNER JOIN snapshots s ON s.id = hv.snapshot_id
        WHERE s.user_id = :userId AND hv.snapshot_id = :snapshotId
        """,
    )
    fun observeForSnapshot(userId: UUID, snapshotId: UUID): Flow<List<HoldingValueEntity>>

    @Query(
        """
        SELECT hv.* FROM holding_values hv
        INNER JOIN snapshots s ON s.id = hv.snapshot_id
        WHERE s.user_id = :userId AND hv.snapshot_id = :snapshotId
        """,
    )
    suspend fun getForSnapshot(userId: UUID, snapshotId: UUID): List<HoldingValueEntity>

    @Query(
        """
        SELECT hv.* FROM holding_values hv
        INNER JOIN snapshots s ON s.id = hv.snapshot_id
        WHERE s.user_id = :userId
        """,
    )
    fun observeAllForUser(userId: UUID): Flow<List<HoldingValueEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(values: List<HoldingValueEntity>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(value: HoldingValueEntity)

    @Update
    suspend fun update(value: HoldingValueEntity)

    @Query("DELETE FROM holding_values WHERE snapshot_id = :snapshotId")
    suspend fun deleteAllForSnapshot(snapshotId: UUID)
}
