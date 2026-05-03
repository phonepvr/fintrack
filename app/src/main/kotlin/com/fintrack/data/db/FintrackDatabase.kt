package com.fintrack.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fintrack.data.db.dao.GlobalSettingsDao
import com.fintrack.data.db.dao.HoldingDao
import com.fintrack.data.db.dao.HoldingValueDao
import com.fintrack.data.db.dao.SnapshotDao
import com.fintrack.data.db.dao.UserDao
import com.fintrack.data.db.dao.UserSettingsDao
import com.fintrack.data.db.entities.GlobalSettingsEntity
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.data.db.entities.UserSettingsEntity

@Database(
    entities = [
        UserEntity::class,
        UserSettingsEntity::class,
        GlobalSettingsEntity::class,
        HoldingEntity::class,
        SnapshotEntity::class,
        HoldingValueEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class FintrackDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userSettingsDao(): UserSettingsDao
    abstract fun globalSettingsDao(): GlobalSettingsDao
    abstract fun holdingDao(): HoldingDao
    abstract fun snapshotDao(): SnapshotDao
    abstract fun holdingValueDao(): HoldingValueDao

    companion object {
        const val DATABASE_NAME = "fintrack.db"
    }
}
