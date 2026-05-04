package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Per-user singleton (PK = userId). The lastSnapshotMonth is stored as
 * "YYYY-MM" so range queries against current month are easy without
 * needing a kotlinx-datetime YearMonth type converter.
 */
@Entity(
    tableName = "streak_state",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class StreakStateEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: UUID,

    @ColumnInfo(name = "current_streak_months", defaultValue = "0")
    val currentStreakMonths: Int = 0,

    @ColumnInfo(name = "longest_streak_months", defaultValue = "0")
    val longestStreakMonths: Int = 0,

    /** Format: "YYYY-MM" or null if no snapshots yet. */
    @ColumnInfo(name = "last_snapshot_month")
    val lastSnapshotMonth: String? = null,
)
