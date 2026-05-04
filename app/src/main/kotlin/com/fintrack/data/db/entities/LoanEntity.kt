package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID

@Entity(
    tableName = "loans",
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
data class LoanEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "user_id")
    val userId: UUID,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "original_amount")
    val originalAmount: BigDecimal,

    @ColumnInfo(name = "taken_date")
    val takenDate: LocalDate,

    @ColumnInfo(name = "monthly_emi")
    val monthlyEmi: BigDecimal,

    @ColumnInfo(name = "is_active", defaultValue = "1")
    val isActive: Boolean = true,

    @ColumnInfo(name = "closed_date")
    val closedDate: LocalDate? = null,

    @ColumnInfo(name = "created_at")
    val createdAt: Instant,

    @ColumnInfo(name = "updated_at")
    val updatedAt: Instant,
)
