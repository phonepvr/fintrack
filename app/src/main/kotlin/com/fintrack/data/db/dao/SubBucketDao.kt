package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.SubBucketEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface SubBucketDao {

    @Query("SELECT * FROM sub_buckets ORDER BY display_order ASC, name ASC")
    fun observeAll(): Flow<List<SubBucketEntity>>

    @Query(
        """
        SELECT * FROM sub_buckets
        WHERE asset_class_id = :assetClassId
        ORDER BY display_order ASC, name ASC
        """,
    )
    fun observeForAssetClass(assetClassId: UUID): Flow<List<SubBucketEntity>>

    @Query("SELECT * FROM sub_buckets WHERE is_active = 1 ORDER BY display_order ASC, name ASC")
    suspend fun getActive(): List<SubBucketEntity>

    @Query("SELECT * FROM sub_buckets WHERE id = :id LIMIT 1")
    suspend fun get(id: UUID): SubBucketEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(rows: List<SubBucketEntity>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(row: SubBucketEntity)

    @Update
    suspend fun update(row: SubBucketEntity)

    @Query("UPDATE sub_buckets SET is_active = :active WHERE id = :id")
    suspend fun setActive(id: UUID, active: Boolean)
}
