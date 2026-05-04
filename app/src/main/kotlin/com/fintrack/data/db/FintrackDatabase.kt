package com.fintrack.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fintrack.data.db.dao.AimAllocationDao
import com.fintrack.data.db.dao.AssetClassDao
import com.fintrack.data.db.dao.GlobalSettingsDao
import com.fintrack.data.db.dao.GoalDao
import com.fintrack.data.db.dao.HoldingDao
import com.fintrack.data.db.dao.HoldingValueDao
import com.fintrack.data.db.dao.LoanDao
import com.fintrack.data.db.dao.LoanValueDao
import com.fintrack.data.db.dao.MilestoneDao
import com.fintrack.data.db.dao.SnapshotDao
import com.fintrack.data.db.dao.StreakStateDao
import com.fintrack.data.db.dao.SubBucketDao
import com.fintrack.data.db.dao.UserDao
import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.GlobalSettingsEntity
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.data.db.entities.LoanValueEntity
import com.fintrack.data.db.entities.MilestoneEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.db.entities.StreakStateEntity
import com.fintrack.data.db.entities.SubBucketEntity
import com.fintrack.data.db.entities.UserEntity

/**
 * v3 schema (version 3 — skipping 2 to keep the file/backup format unambiguous).
 * The schema is rebuilt on first launch via fallbackToDestructiveMigration in
 * DatabaseModule because v2 only ever held dummy data on test devices.
 */
@Database(
    entities = [
        UserEntity::class,
        GlobalSettingsEntity::class,
        AssetClassEntity::class,
        SubBucketEntity::class,
        HoldingEntity::class,
        AimAllocationEntity::class,
        SnapshotEntity::class,
        HoldingValueEntity::class,
        LoanEntity::class,
        LoanValueEntity::class,
        StreakStateEntity::class,
        MilestoneEntity::class,
        GoalEntity::class,
    ],
    version = 3,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class FintrackDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun globalSettingsDao(): GlobalSettingsDao
    abstract fun assetClassDao(): AssetClassDao
    abstract fun subBucketDao(): SubBucketDao
    abstract fun holdingDao(): HoldingDao
    abstract fun aimAllocationDao(): AimAllocationDao
    abstract fun snapshotDao(): SnapshotDao
    abstract fun holdingValueDao(): HoldingValueDao
    abstract fun loanDao(): LoanDao
    abstract fun loanValueDao(): LoanValueDao
    abstract fun streakStateDao(): StreakStateDao
    abstract fun milestoneDao(): MilestoneDao
    abstract fun goalDao(): GoalDao

    companion object {
        const val DATABASE_NAME = "fintrack.db"
    }
}
