package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.GlobalSettingsEntity
import com.fintrack.data.db.seed.SeedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlobalSettingsRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.globalSettingsDao()

    fun observe(): Flow<GlobalSettingsEntity> = dao.observe().filterNotNull()

    suspend fun get(): GlobalSettingsEntity = dao.get() ?: run {
        // Self-heal if the singleton row was wiped — should not happen in
        // practice because the seeder writes it on first create.
        dao.upsert(SeedData.defaultGlobalSettings)
        SeedData.defaultGlobalSettings
    }

    suspend fun setActiveUserId(userId: UUID?) = dao.setActiveUserId(userId)
    suspend fun setInactivityLockSeconds(seconds: Int) = dao.setInactivityLockSeconds(seconds)
    suspend fun setAlwaysShowProfilePicker(value: Boolean) = dao.setAlwaysShowProfilePicker(value)
    suspend fun setHasCompletedOnboarding(value: Boolean) = dao.setHasCompletedOnboarding(value)
}
