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
