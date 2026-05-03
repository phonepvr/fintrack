package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(
    tableName = "holding_values",
    foreignKeys = [
        ForeignKey(
            entity = SnapshotEntity::class,
            parentColumns = ["id"],
            childColumns = ["snapshot_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = HoldingEntity::class,
            parentColumns = ["id"],
            childColumns = ["holding_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
    ],
    indices = [
        Index(value = ["snapshot_id", "holding_id"], unique = true),
        Index(value = ["holding_id"]),
    ],
)
data class HoldingValueEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "snapshot_id")
    val snapshotId: UUID,

    @ColumnInfo(name = "holding_id")
    val holdingId: UUID,

    @ColumnInfo(name = "invested")
    val invested: BigDecimal? = null,

    @ColumnInfo(name = "current")
    val current: BigDecimal,

    @ColumnInfo(name = "sip")
    val sip: BigDecimal? = null,
)
