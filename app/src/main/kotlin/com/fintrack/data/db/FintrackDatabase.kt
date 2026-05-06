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
 * v3 schema rebuilt on first launch via fallbackToDestructiveMigration; from
 * v3 onward, real user data exists, so column changes ride explicit migrations
 * registered in DatabaseModule (the destructive fallback stays as a last
 * resort for users coming from older builds without a registered path).
 *
 * v4 (v3.1 release) — adds GlobalSettings.has_completed_onboarding.
 * v5 (v3.4 release) — adds Goal.starting_liabilities so DEBT_FREE goals can
 *   show gradual progress instead of binary 0/100%.
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
    version = 5,
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
