package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fintrack.data.db.entities.StreakStateEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface StreakStateDao {

    @Query("SELECT * FROM streak_state WHERE user_id = :userId LIMIT 1")
    fun observeForUser(userId: UUID): Flow<StreakStateEntity?>

    @Query("SELECT * FROM streak_state WHERE user_id = :userId LIMIT 1")
    suspend fun getForUser(userId: UUID): StreakStateEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(row: StreakStateEntity)
}
