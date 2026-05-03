package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fintrack.data.db.entities.GlobalSettingsEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface GlobalSettingsDao {

    @Query("SELECT * FROM global_settings WHERE id = ${GlobalSettingsEntity.SINGLETON_ID} LIMIT 1")
    fun observe(): Flow<GlobalSettingsEntity?>

    @Query("SELECT * FROM global_settings WHERE id = ${GlobalSettingsEntity.SINGLETON_ID} LIMIT 1")
    suspend fun get(): GlobalSettingsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(settings: GlobalSettingsEntity)

    @Query("UPDATE global_settings SET active_user_id = :userId WHERE id = ${GlobalSettingsEntity.SINGLETON_ID}")
    suspend fun setActiveUserId(userId: UUID?)

    @Query("UPDATE global_settings SET inactivity_lock_seconds = :seconds WHERE id = ${GlobalSettingsEntity.SINGLETON_ID}")
    suspend fun setInactivityLockSeconds(seconds: Int)

    @Query("UPDATE global_settings SET always_show_profile_picker = :value WHERE id = ${GlobalSettingsEntity.SINGLETON_ID}")
    suspend fun setAlwaysShowProfilePicker(value: Boolean)
}
