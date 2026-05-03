package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.domain.model.AssetClass
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * Holdings catalog is GLOBAL (shared across users). Mutations affect every
 * profile on the device.
 */
@Dao
interface HoldingDao {

    @Query("SELECT * FROM holdings ORDER BY display_order ASC, name ASC")
    fun observeAll(): Flow<List<HoldingEntity>>

    @Query("SELECT * FROM holdings WHERE is_active = 1 ORDER BY display_order ASC, name ASC")
    fun observeActive(): Flow<List<HoldingEntity>>

    @Query("SELECT * FROM holdings WHERE asset_class = :assetClass AND is_active = 1 ORDER BY display_order ASC, name ASC")
    fun observeByAssetClass(assetClass: AssetClass): Flow<List<HoldingEntity>>

    @Query("SELECT * FROM holdings WHERE id = :holdingId LIMIT 1")
    suspend fun get(holdingId: UUID): HoldingEntity?

    @Query("SELECT COUNT(*) FROM holdings")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(holdings: List<HoldingEntity>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(holding: HoldingEntity)

    @Update
    suspend fun update(holding: HoldingEntity)

    @Query("UPDATE holdings SET is_active = :active WHERE id = :holdingId")
    suspend fun setActive(holdingId: UUID, active: Boolean)
}
