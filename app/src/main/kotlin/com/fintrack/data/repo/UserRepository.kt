package com.fintrack.data.repo

import androidx.room.withTransaction
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.data.db.entities.UserSettingsEntity
import com.fintrack.domain.model.AssetClass
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val users get() = database.userDao()
    private val settings get() = database.userSettingsDao()
    private val global get() = database.globalSettingsDao()

    fun observeActiveUsers(): Flow<List<UserEntity>> = users.observeActiveUsers()

    fun observeUser(userId: UUID): Flow<UserEntity?> = users.observeUser(userId)

    suspend fun activeUserCount(): Int = users.activeUserCount()

    suspend fun firstActiveUser(): UserEntity? = users.firstActiveUser()

    suspend fun getUser(userId: UUID): UserEntity? = users.getUser(userId)

    /**
     * Creates a user, seeds their [UserSettingsEntity] with default aim
     * percentages from [AssetClass], and (when [makeActive] is true) writes
     * the new id into [GlobalSettingsEntity.activeUserId]. Wrapped in a
     * transaction so partial state can never reach disk.
     */
    suspend fun createUser(
        name: String,
        colorHex: String,
        makeActive: Boolean = true,
    ): UserEntity {
        val user = UserEntity(
            id = UUID.randomUUID(),
            name = name,
            colorHex = colorHex,
            createdAt = Clock.System.now(),
            isActive = true,
        )
        database.withTransaction {
            users.insert(user)
            settings.upsert(
                UserSettingsEntity(
                    userId = user.id,
                    aimPctMfNps = AssetClass.MF_NPS.defaultAimPct,
                    aimPctEquity = AssetClass.EQUITY.defaultAimPct,
                    aimPctFixedReturn = AssetClass.FIXED_RETURN.defaultAimPct,
                    aimPctCrypto = AssetClass.CRYPTO.defaultAimPct,
                ),
            )
            if (makeActive) {
                global.setActiveUserId(user.id)
            }
        }
        return user
    }

    suspend fun renameUser(userId: UUID, name: String, colorHex: String) {
        val existing = users.getUser(userId) ?: return
        users.update(existing.copy(name = name, colorHex = colorHex))
    }

    /**
     * Hard-cascade delete: snapshots and user_settings go via FK ON DELETE
     * CASCADE. Caller must verify [activeUserCount] > 1 before invoking — the
     * UI also disables the affordance, this is belt-and-braces.
     */
    suspend fun deleteUser(userId: UUID) {
        check(users.activeUserCount() > 1) { "Cannot delete the last remaining user." }
        database.withTransaction {
            users.deleteById(userId)
            val settings = global.get()
            if (settings?.activeUserId == userId) {
                global.setActiveUserId(null)
            }
        }
    }
}
