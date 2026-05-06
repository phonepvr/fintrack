package com.fintrack.data.db.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * v3 → v4: adds GlobalSettings.has_completed_onboarding so the new
 * onboarding pager can persist completion. Default 0 means existing
 * v3 installs see the pager once on next launch — the four cards
 * frame the new privacy story + UX additions, so showing them once
 * to upgraded users is intentional.
 */
val MIGRATION_3_4: Migration = object : Migration(3, 4) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE global_settings " +
                "ADD COLUMN has_completed_onboarding INTEGER NOT NULL DEFAULT 0",
        )
    }
}

/**
 * v4 → v5: adds Goal.starting_liabilities. BigDecimal columns are stored as
 * TEXT via Converters.bigDecimalToString. Default '0' means existing
 * DEBT_FREE goals continue to show binary 0/100% progress (no regression);
 * new DEBT_FREE goals capture the user's current liabilities at creation
 * time and animate gradual progress as debt is paid down.
 */
val MIGRATION_4_5: Migration = object : Migration(4, 5) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE goals " +
                "ADD COLUMN starting_liabilities TEXT NOT NULL DEFAULT '0'",
        )
    }
}
