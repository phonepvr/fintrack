package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import java.util.UUID

@Entity(
    tableName = "sub_buckets",
    foreignKeys = [
        ForeignKey(
            entity = AssetClassEntity::class,
            parentColumns = ["id"],
            childColumns = ["asset_class_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
    ],
    indices = [
        Index(value = ["asset_class_id"]),
        Index(value = ["asset_class_id", "name"], unique = true),
    ],
)
data class SubBucketEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "asset_class_id")
    val assetClassId: UUID,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "display_order", defaultValue = "0")
    val displayOrder: Int,

    @ColumnInfo(name = "is_seeded", defaultValue = "0")
    val isSeeded: Boolean,

    @ColumnInfo(name = "is_active", defaultValue = "1")
    val isActive: Boolean = true,

    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
)
