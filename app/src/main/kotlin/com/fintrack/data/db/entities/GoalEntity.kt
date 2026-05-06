package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fintrack.domain.model.GoalType
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID

@Entity(
    tableName = "goals",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["user_id"]),
    ],
)
data class GoalEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "user_id")
    val userId: UUID,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "goal_type")
    val goalType: GoalType,

    /** For NET_WORTH goals, the target. For DEBT_FREE, ignored (target is liabilities = 0). */
    @ColumnInfo(name = "target_net_worth")
    val targetNetWorth: BigDecimal,

    @ColumnInfo(name = "target_date")
    val targetDate: LocalDate,

    @ColumnInfo(name = "created_at")
    val createdAt: Instant,

    @ColumnInfo(name = "achieved_at")
    val achievedAt: LocalDate? = null,

    @ColumnInfo(name = "is_archived", defaultValue = "0")
    val isArchived: Boolean = false,

    /**
     * Liabilities total captured the moment a DEBT_FREE goal was created.
     * Anchors gradual progress: `(starting − current) / starting × 100`.
     * Zero (the default) for NET_WORTH goals and for legacy DEBT_FREE goals
     * that pre-date this column — those fall back to binary 0/100% progress.
     */
    @ColumnInfo(name = "starting_liabilities", defaultValue = "0")
    val startingLiabilities: BigDecimal = BigDecimal.ZERO,
)
