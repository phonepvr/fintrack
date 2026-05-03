package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.UserSettingsEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface UserSettingsDao {

    @Query("SELECT * FROM user_settings WHERE user_id = :userId LIMIT 1")
    fun observe(userId: UUID): Flow<UserSettingsEntity?>

    @Query("SELECT * FROM user_settings WHERE user_id = :userId LIMIT 1")
    suspend fun get(userId: UUID): UserSettingsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(settings: UserSettingsEntity)

    @Update
    suspend fun update(settings: UserSettingsEntity)
}
