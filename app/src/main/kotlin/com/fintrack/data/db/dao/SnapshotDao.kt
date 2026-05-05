package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.SnapshotEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import java.util.UUID

/**
 * Per-user reads MUST pass [userId]. The privacy acceptance test
 * `SnapshotDaoUserScopedTest` enforces this on every read method via reflection
 * — adding a method that returns rows without a `userId` parameter will fail
 * the build.
 */
@Dao
interface SnapshotDao {

    @Query("SELECT * FROM snapshots WHERE user_id = :userId ORDER BY snapshot_date DESC, created_at DESC")
    fun observeForUser(userId: UUID): Flow<List<SnapshotEntity>>

    @Query(
        """
        SELECT * FROM snapshots
        WHERE user_id = :userId AND snapshot_date BETWEEN :from AND :to
        ORDER BY snapshot_date ASC, created_at ASC
        """,
    )
    fun observeRangeForUser(userId: UUID, from: LocalDate, to: LocalDate): Flow<List<SnapshotEntity>>

    @Query("SELECT * FROM snapshots WHERE user_id = :userId AND id = :snapshotId LIMIT 1")
    fun observeOne(userId: UUID, snapshotId: UUID): Flow<SnapshotEntity?>

    @Query("SELECT * FROM snapshots WHERE user_id = :userId AND id = :snapshotId LIMIT 1")
    suspend fun getForUser(userId: UUID, snapshotId: UUID): SnapshotEntity?

    @Query(
        """
        SELECT * FROM snapshots
        WHERE user_id = :userId AND snapshot_date < :before
        ORDER BY snapshot_date DESC, created_at DESC
        LIMIT 1
        """,
    )
    suspend fun previousForUser(userId: UUID, before: LocalDate): SnapshotEntity?

    @Query("SELECT COUNT(*) FROM snapshots WHERE user_id = :userId")
    suspend fun countForUser(userId: UUID): Int

    @Query("SELECT snapshot_date FROM snapshots WHERE user_id = :userId")
    suspend fun getDatesForUser(userId: UUID): List<LocalDate>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(snapshot: SnapshotEntity)

    @Update
    suspend fun update(snapshot: SnapshotEntity)

    @Query("DELETE FROM snapshots WHERE user_id = :userId AND id = :snapshotId")
    suspend fun deleteForUser(userId: UUID, snapshotId: UUID)
}
