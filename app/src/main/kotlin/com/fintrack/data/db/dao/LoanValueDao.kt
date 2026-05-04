package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.LoanValueEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * LoanValue rows belong indirectly to a user via Snapshot.user_id. Every read
 * method JOINs `snapshots` and filters on `snapshots.user_id = :userId`,
 * mirroring HoldingValueDao. The privacy gate (SnapshotDaoUserScopedTest)
 * enforces the `userId` parameter via reflection.
 */
@Dao
interface LoanValueDao {

    @Query(
        """
        SELECT lv.* FROM loan_values lv
        INNER JOIN snapshots s ON s.id = lv.snapshot_id
        WHERE s.user_id = :userId AND lv.snapshot_id = :snapshotId
        """,
    )
    fun observeForSnapshot(userId: UUID, snapshotId: UUID): Flow<List<LoanValueEntity>>

    @Query(
        """
        SELECT lv.* FROM loan_values lv
        INNER JOIN snapshots s ON s.id = lv.snapshot_id
        WHERE s.user_id = :userId AND lv.snapshot_id = :snapshotId
        """,
    )
    suspend fun getForSnapshot(userId: UUID, snapshotId: UUID): List<LoanValueEntity>

    @Query(
        """
        SELECT lv.* FROM loan_values lv
        INNER JOIN snapshots s ON s.id = lv.snapshot_id
        WHERE s.user_id = :userId
        """,
    )
    fun observeAllForUser(userId: UUID): Flow<List<LoanValueEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(values: List<LoanValueEntity>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(value: LoanValueEntity)

    @Update
    suspend fun update(value: LoanValueEntity)

    @Query("DELETE FROM loan_values WHERE snapshot_id = :snapshotId")
    suspend fun deleteAllForSnapshot(snapshotId: UUID)
}
