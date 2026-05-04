package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import java.util.UUID

/**
 * Composite-PK row replacing v2's four-column UserSettings table. The
 * (active class) sum-to-100 invariant is enforced in the repository layer
 * (no DB constraint can express it cleanly).
 */
@Entity(
    tableName = "aim_allocations",
    primaryKeys = ["user_id", "asset_class_id"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = AssetClassEntity::class,
            parentColumns = ["id"],
            childColumns = ["asset_class_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["asset_class_id"]),
    ],
)
data class AimAllocationEntity(
    @ColumnInfo(name = "user_id")
    val userId: UUID,

    @ColumnInfo(name = "asset_class_id")
    val assetClassId: UUID,

    @ColumnInfo(name = "aim_percent")
    val aimPercent: Int,
) {
    init {
        require(aimPercent in 0..100) { "Aim percent must be 0..100, got $aimPercent" }
    }
}
