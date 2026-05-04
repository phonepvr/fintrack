package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fintrack.domain.model.MilestoneType
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID

@Entity(
    tableName = "milestones",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = SnapshotEntity::class,
            parentColumns = ["id"],
            childColumns = ["achieved_at_snapshot_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["user_id"]),
        // Non-repeating types are unique per (user, type); BACK_FROM_BREAK
        // can recur. Enforced in repository, not via DB index, so we don't
        // collide on legitimate repeats.
        Index(value = ["user_id", "type"]),
        Index(value = ["achieved_at_snapshot_id"]),
    ],
)
data class MilestoneEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "user_id")
    val userId: UUID,

    @ColumnInfo(name = "type")
    val type: MilestoneType,

    @ColumnInfo(name = "achieved_at_snapshot_id")
    val achievedAtSnapshotId: UUID,

    @ColumnInfo(name = "achieved_at_date")
    val achievedAtDate: LocalDate,

    @ColumnInfo(name = "amount_at_achievement")
    val amountAtAchievement: BigDecimal? = null,

    @ColumnInfo(name = "is_celebrated", defaultValue = "0")
    val isCelebrated: Boolean = false,

    @ColumnInfo(name = "created_at")
    val createdAt: Instant,
)
