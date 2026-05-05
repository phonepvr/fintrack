package com.fintrack.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.migrations.MIGRATION_3_4
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
import com.fintrack.data.db.seed.SeedData
import com.fintrack.security.KeystorePassphraseStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFintrackDatabase(
        @ApplicationContext context: Context,
        passphraseStore: KeystorePassphraseStore,
    ): FintrackDatabase {
        System.loadLibrary("sqlcipher")
        val passphrase: ByteArray = passphraseStore.getOrCreate()
        val factory = SupportOpenHelperFactory(passphrase)

        return Room.databaseBuilder(
            context,
            FintrackDatabase::class.java,
            FintrackDatabase.DATABASE_NAME,
        )
            .openHelperFactory(factory)
            // v3 → v4 ALTER TABLE runs first; the destructive fallback stays
            // as a last resort for users coming from v1/v2 (or any future
            // version without a registered path). v3 onward holds real data.
            .addMigrations(MIGRATION_3_4)
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    seedOnCreate(db)
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    db.execSQL(
                        "INSERT OR IGNORE INTO global_settings " +
                            "(id, inactivity_lock_seconds, default_currency_symbol, active_user_id, always_show_profile_picker, has_completed_onboarding) " +
                            "VALUES (1, 60, '₹', NULL, 0, 0)",
                    )
                }
            })
            .build()
    }

    private fun seedOnCreate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "INSERT OR IGNORE INTO global_settings " +
                "(id, inactivity_lock_seconds, default_currency_symbol, active_user_id, always_show_profile_picker, has_completed_onboarding) " +
                "VALUES (?, ?, ?, NULL, 0, 0)",
            arrayOf<Any>(1, 60, "₹"),
        )

        // Seed AssetClass rows first.
        val nowIso = kotlinx.datetime.Clock.System.now().toString()
        for (ac in SeedData.defaultAssetClasses) {
            db.execSQL(
                "INSERT OR IGNORE INTO asset_classes " +
                    "(id, name, display_order, is_seeded, is_active, created_at) " +
                    "VALUES (?, ?, ?, 1, 1, ?)",
                arrayOf(ac.id.toString(), ac.name, ac.displayOrder, nowIso),
            )
        }
        // Then SubBucket rows.
        for (sb in SeedData.defaultSubBuckets) {
            db.execSQL(
                "INSERT OR IGNORE INTO sub_buckets " +
                    "(id, asset_class_id, name, display_order, is_seeded, is_active, created_at) " +
                    "VALUES (?, ?, ?, ?, 1, 1, ?)",
                arrayOf(
                    sb.id.toString(), sb.assetClassId.toString(),
                    sb.name, sb.displayOrder, nowIso,
                ),
            )
        }
        // Then Holdings — all reference a seeded SubBucket.
        for (h in SeedData.defaultHoldings) {
            db.execSQL(
                "INSERT OR IGNORE INTO holdings " +
                    "(id, sub_bucket_id, name, track_invested, track_sip, is_active, display_order) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)",
                arrayOf(
                    h.id.toString(), h.subBucketId.toString(), h.name,
                    if (h.trackInvested) 1 else 0,
                    if (h.trackSip) 1 else 0,
                    if (h.isActive) 1 else 0,
                    h.displayOrder,
                ),
            )
        }
    }

    @Provides fun provideUserDao(db: FintrackDatabase): UserDao = db.userDao()
    @Provides fun provideGlobalSettingsDao(db: FintrackDatabase): GlobalSettingsDao = db.globalSettingsDao()
    @Provides fun provideAssetClassDao(db: FintrackDatabase): AssetClassDao = db.assetClassDao()
    @Provides fun provideSubBucketDao(db: FintrackDatabase): SubBucketDao = db.subBucketDao()
    @Provides fun provideHoldingDao(db: FintrackDatabase): HoldingDao = db.holdingDao()
    @Provides fun provideAimAllocationDao(db: FintrackDatabase): AimAllocationDao = db.aimAllocationDao()
    @Provides fun provideSnapshotDao(db: FintrackDatabase): SnapshotDao = db.snapshotDao()
    @Provides fun provideHoldingValueDao(db: FintrackDatabase): HoldingValueDao = db.holdingValueDao()
    @Provides fun provideLoanDao(db: FintrackDatabase): LoanDao = db.loanDao()
    @Provides fun provideLoanValueDao(db: FintrackDatabase): LoanValueDao = db.loanValueDao()
    @Provides fun provideStreakStateDao(db: FintrackDatabase): StreakStateDao = db.streakStateDao()
    @Provides fun provideMilestoneDao(db: FintrackDatabase): MilestoneDao = db.milestoneDao()
    @Provides fun provideGoalDao(db: FintrackDatabase): GoalDao = db.goalDao()
}
