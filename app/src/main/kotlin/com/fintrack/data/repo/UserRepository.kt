package com.fintrack.data.repo

import androidx.room.withTransaction
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.data.db.seed.SeedData
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
    private val aim get() = database.aimAllocationDao()
    private val global get() = database.globalSettingsDao()

    fun observeActiveUsers(): Flow<List<UserEntity>> = users.observeActiveUsers()

    fun observeUser(userId: UUID): Flow<UserEntity?> = users.observeUser(userId)

    suspend fun activeUserCount(): Int = users.activeUserCount()

    suspend fun firstActiveUser(): UserEntity? = users.firstActiveUser()

    suspend fun getUser(userId: UUID): UserEntity? = users.getUser(userId)

    /**
     * Creates a user, seeds their AimAllocation rows from the spec defaults
     * (Market Linked 70 / Fixed Return 25 / Crypto 5), and (when [makeActive]
     * is true) writes the new id into GlobalSettings.activeUserId. Wrapped in
     * a transaction so partial state can never reach disk.
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
            val seedRows = SeedData.defaultAimPercentByAssetClass.map { (acId, pct) ->
                AimAllocationEntity(userId = user.id, assetClassId = acId, aimPercent = pct)
            }
            aim.upsertAll(seedRows)
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
     * Hard-cascade delete: snapshots, aim allocations, loans, milestones,
     * goals, streak state all cascade via the FK on userId. Caller must
     * verify [activeUserCount] > 1 before invoking — the UI also disables
     * the affordance, this is belt-and-braces.
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
