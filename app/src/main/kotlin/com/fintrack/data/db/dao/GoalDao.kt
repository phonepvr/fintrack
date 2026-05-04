package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.GoalEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface GoalDao {

    @Query(
        """
        SELECT * FROM goals
        WHERE user_id = :userId AND is_archived = 0
        ORDER BY target_date ASC, created_at ASC
        """,
    )
    fun observeActiveForUser(userId: UUID): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE user_id = :userId ORDER BY target_date ASC, created_at ASC")
    fun observeAllForUser(userId: UUID): Flow<List<GoalEntity>>

    @Query("SELECT * FROM goals WHERE user_id = :userId AND id = :goalId LIMIT 1")
    suspend fun getForUser(userId: UUID, goalId: UUID): GoalEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(row: GoalEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(rows: List<GoalEntity>)

    @Update
    suspend fun update(row: GoalEntity)

    @Query("DELETE FROM goals WHERE user_id = :userId AND id = :goalId")
    suspend fun deleteForUser(userId: UUID, goalId: UUID)
}
