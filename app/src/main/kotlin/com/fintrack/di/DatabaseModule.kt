package com.fintrack.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.dao.GlobalSettingsDao
import com.fintrack.data.db.dao.HoldingDao
import com.fintrack.data.db.dao.HoldingValueDao
import com.fintrack.data.db.dao.SnapshotDao
import com.fintrack.data.db.dao.UserDao
import com.fintrack.data.db.dao.UserSettingsDao
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
        // Load the SQLCipher native library before any DB open. Idempotent —
        // safe to call from a Hilt provider that may be invoked once per
        // process.
        System.loadLibrary("sqlcipher")

        val passphrase: ByteArray = passphraseStore.getOrCreate()
        val factory = SupportOpenHelperFactory(passphrase)

        return Room.databaseBuilder(
            context,
            FintrackDatabase::class.java,
            FintrackDatabase.DATABASE_NAME,
        )
            .openHelperFactory(factory)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // First-launch seed: holdings catalog + singleton GlobalSettings row.
                    seedOnCreate(db)
                }

                override fun onOpen(db: SupportSQLiteDatabase) {
                    super.onOpen(db)
                    // Self-heal: ensure the GlobalSettings singleton row is always present.
                    db.execSQL(
                        "INSERT OR IGNORE INTO global_settings " +
                            "(id, inactivity_lock_seconds, default_currency_symbol, active_user_id, always_show_profile_picker) " +
                            "VALUES (1, 60, '₹', NULL, 0)",
                    )
                }
            })
            .fallbackToDestructiveMigrationOnDowngrade(false)
            .build()
        // Note: Room is lazy — the file isn't actually opened until the first
        // DAO call, so the SQLCipher key check happens off the main thread on
        // first use rather than during DI provisioning.
    }

    private fun seedOnCreate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "INSERT OR IGNORE INTO global_settings " +
                "(id, inactivity_lock_seconds, default_currency_symbol, active_user_id, always_show_profile_picker) " +
                "VALUES (?, ?, ?, NULL, 0)",
            arrayOf<Any>(1, 60, "₹"),
        )
        for (h in SeedData.defaultHoldings) {
            db.execSQL(
                "INSERT OR IGNORE INTO holdings " +
                    "(id, name, asset_class, track_invested, track_sip, is_bank_account, is_active, display_order) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                arrayOf(
                    h.id.toString(),
                    h.name,
                    h.assetClass.name,
                    if (h.trackInvested) 1 else 0,
                    if (h.trackSip) 1 else 0,
                    if (h.isBankAccount) 1 else 0,
                    if (h.isActive) 1 else 0,
                    h.displayOrder,
                ),
            )
        }
    }

    @Provides fun provideUserDao(db: FintrackDatabase): UserDao = db.userDao()
    @Provides fun provideUserSettingsDao(db: FintrackDatabase): UserSettingsDao = db.userSettingsDao()
    @Provides fun provideGlobalSettingsDao(db: FintrackDatabase): GlobalSettingsDao = db.globalSettingsDao()
    @Provides fun provideHoldingDao(db: FintrackDatabase): HoldingDao = db.holdingDao()
    @Provides fun provideSnapshotDao(db: FintrackDatabase): SnapshotDao = db.snapshotDao()
    @Provides fun provideHoldingValueDao(db: FintrackDatabase): HoldingValueDao = db.holdingValueDao()
}
