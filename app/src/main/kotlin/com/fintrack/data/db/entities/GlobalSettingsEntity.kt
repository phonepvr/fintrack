package com.fintrack.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

/** Singleton row; [id] is always [SINGLETON_ID]. */
@Entity(tableName = "global_settings")
data class GlobalSettingsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = SINGLETON_ID,

    @ColumnInfo(name = "inactivity_lock_seconds", defaultValue = "60")
    val inactivityLockSeconds: Int = 60,

    @ColumnInfo(name = "default_currency_symbol", defaultValue = "₹")
    val defaultCurrencySymbol: String = "₹",

    @ColumnInfo(name = "active_user_id")
    val activeUserId: UUID? = null,

    @ColumnInfo(name = "always_show_profile_picker", defaultValue = "0")
    val alwaysShowProfilePicker: Boolean = false,
) {
    companion object {
        const val SINGLETON_ID = 1
    }
}
