package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.fintrack.domain.model.AssetClass
import java.util.UUID

@Entity(
    tableName = "holdings",
    indices = [
        Index(value = ["asset_class"]),
        Index(value = ["name"], unique = true),
    ],
)
data class HoldingEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: UUID,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "asset_class")
    val assetClass: AssetClass,

    @ColumnInfo(name = "track_invested", defaultValue = "0")
    val trackInvested: Boolean,

    @ColumnInfo(name = "track_sip", defaultValue = "0")
    val trackSip: Boolean,

    @ColumnInfo(name = "is_bank_account", defaultValue = "0")
    val isBankAccount: Boolean,

    @ColumnInfo(name = "is_active", defaultValue = "1")
    val isActive: Boolean = true,

    @ColumnInfo(name = "display_order", defaultValue = "0")
    val displayOrder: Int = 0,
)
