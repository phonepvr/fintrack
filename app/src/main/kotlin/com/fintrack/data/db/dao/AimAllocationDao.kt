package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fintrack.data.db.entities.AimAllocationEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface AimAllocationDao {

    @Query("SELECT * FROM aim_allocations WHERE user_id = :userId")
    fun observeForUser(userId: UUID): Flow<List<AimAllocationEntity>>

    @Query("SELECT * FROM aim_allocations WHERE user_id = :userId")
    suspend fun getForUser(userId: UUID): List<AimAllocationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(rows: List<AimAllocationEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(row: AimAllocationEntity)

    @Query("DELETE FROM aim_allocations WHERE user_id = :userId AND asset_class_id = :assetClassId")
    suspend fun deleteForUser(userId: UUID, assetClassId: UUID)
}
