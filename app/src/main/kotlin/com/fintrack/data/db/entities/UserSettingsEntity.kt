package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(
    tableName = "user_settings",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class UserSettingsEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: UUID,

    @ColumnInfo(name = "aim_pct_mf_nps") val aimPctMfNps: Int,
    @ColumnInfo(name = "aim_pct_equity") val aimPctEquity: Int,
    @ColumnInfo(name = "aim_pct_fixed_return") val aimPctFixedReturn: Int,
    @ColumnInfo(name = "aim_pct_crypto") val aimPctCrypto: Int,
) {
    init {
        require(aimPctMfNps + aimPctEquity + aimPctFixedReturn + aimPctCrypto == 100) {
            "Aim percentages must sum to 100"
        }
        require(listOf(aimPctMfNps, aimPctEquity, aimPctFixedReturn, aimPctCrypto).all { it in 0..100 }) {
            "Each aim percentage must be between 0 and 100"
        }
    }
}
