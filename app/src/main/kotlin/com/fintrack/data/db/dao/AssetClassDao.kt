package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.AssetClassEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface AssetClassDao {

    @Query("SELECT * FROM asset_classes ORDER BY display_order ASC, name ASC")
    fun observeAll(): Flow<List<AssetClassEntity>>

    @Query("SELECT * FROM asset_classes WHERE is_active = 1 ORDER BY display_order ASC, name ASC")
    fun observeActive(): Flow<List<AssetClassEntity>>

    @Query("SELECT * FROM asset_classes WHERE is_active = 1 ORDER BY display_order ASC, name ASC")
    suspend fun getActive(): List<AssetClassEntity>

    @Query("SELECT * FROM asset_classes WHERE id = :id LIMIT 1")
    suspend fun get(id: UUID): AssetClassEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(rows: List<AssetClassEntity>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(row: AssetClassEntity)

    @Update
    suspend fun update(row: AssetClassEntity)

    @Query("UPDATE asset_classes SET is_active = :active WHERE id = :id")
    suspend fun setActive(id: UUID, active: Boolean)

    @Query("DELETE FROM asset_classes WHERE id = :id AND is_seeded = 0")
    suspend fun deleteIfNotSeeded(id: UUID)
}
