package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import java.util.UUID

@Entity(
    tableName = "asset_classes",
    indices = [
        Index(value = ["name"], unique = true),
    ],
)
data class AssetClassEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "display_order", defaultValue = "0")
    val displayOrder: Int,

    /** Seeded classes can be deactivated, never deleted; user-created classes can be deleted. */
    @ColumnInfo(name = "is_seeded", defaultValue = "0")
    val isSeeded: Boolean,

    @ColumnInfo(name = "is_active", defaultValue = "1")
    val isActive: Boolean = true,

    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
)
