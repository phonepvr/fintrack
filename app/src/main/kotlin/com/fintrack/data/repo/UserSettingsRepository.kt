package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.UserSettingsEntity
import com.fintrack.domain.model.AssetClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSettingsRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.userSettingsDao()

    fun observe(userId: UUID): Flow<UserSettingsEntity> = dao.observe(userId)
        .map { it ?: defaultsFor(userId) }

    /** Reads the user's settings, falling back to the spec default aim percentages. */
    suspend fun getOrDefault(userId: UUID): UserSettingsEntity =
        dao.get(userId) ?: defaultsFor(userId)

    suspend fun update(settings: UserSettingsEntity) {
        require(settings.aimPctMfNps + settings.aimPctEquity +
            settings.aimPctFixedReturn + settings.aimPctCrypto == 100) {
            "Aim percentages must sum to 100"
        }
        dao.upsert(settings)
    }

    private fun defaultsFor(userId: UUID): UserSettingsEntity = UserSettingsEntity(
        userId = userId,
        aimPctMfNps = AssetClass.MF_NPS.defaultAimPct,
        aimPctEquity = AssetClass.EQUITY.defaultAimPct,
        aimPctFixedReturn = AssetClass.FIXED_RETURN.defaultAimPct,
        aimPctCrypto = AssetClass.CRYPTO.defaultAimPct,
    )
}
