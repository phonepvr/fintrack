package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.util.UUID

@Entity(
    tableName = "loan_values",
    foreignKeys = [
        ForeignKey(
            entity = SnapshotEntity::class,
            parentColumns = ["id"],
            childColumns = ["snapshot_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = LoanEntity::class,
            parentColumns = ["id"],
            childColumns = ["loan_id"],
            onDelete = ForeignKey.RESTRICT,
        ),
    ],
    indices = [
        Index(value = ["snapshot_id", "loan_id"], unique = true),
        Index(value = ["loan_id"]),
    ],
)
data class LoanValueEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "snapshot_id")
    val snapshotId: UUID,

    @ColumnInfo(name = "loan_id")
    val loanId: UUID,

    @ColumnInfo(name = "outstanding")
    val outstanding: BigDecimal,
)
