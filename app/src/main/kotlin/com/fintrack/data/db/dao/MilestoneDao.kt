package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.MilestoneEntity
import com.fintrack.domain.model.MilestoneType
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface MilestoneDao {

    @Query(
        """
        SELECT * FROM milestones
        WHERE user_id = :userId
        ORDER BY achieved_at_date DESC, created_at DESC
        """,
    )
    fun observeForUser(userId: UUID): Flow<List<MilestoneEntity>>

    @Query("SELECT * FROM milestones WHERE user_id = :userId")
    suspend fun getAllForUser(userId: UUID): List<MilestoneEntity>

    @Query("SELECT * FROM milestones WHERE user_id = :userId AND type = :type LIMIT 1")
    suspend fun getOneForUser(userId: UUID, type: MilestoneType): MilestoneEntity?

    @Query(
        """
        SELECT * FROM milestones
        WHERE user_id = :userId AND is_celebrated = 0
        ORDER BY achieved_at_date ASC, created_at ASC
        """,
    )
    fun observeUncelebratedForUser(userId: UUID): Flow<List<MilestoneEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(row: MilestoneEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(rows: List<MilestoneEntity>)

    @Update
    suspend fun update(row: MilestoneEntity)

    @Query("UPDATE milestones SET is_celebrated = 1 WHERE id = :id AND user_id = :userId")
    suspend fun markCelebrated(userId: UUID, id: UUID)
}
