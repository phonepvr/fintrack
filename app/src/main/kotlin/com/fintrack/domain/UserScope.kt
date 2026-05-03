package com.fintrack.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Holder for the currently active profile id.
 *
 * Repositories that read or write per-user data MUST take this as a dependency
 * and pass [requireActiveUserId] into every DAO call. There is intentionally
 * no convenience overload that omits the userId parameter — keeping the
 * userId at the DAO boundary is enforced by [com.fintrack.privacy.SnapshotDaoUserScopedTest].
 *
 * Profile switching:
 *  - [setActiveUser] swaps the id in-place. ViewModels observe [activeUserId]
 *    and must invalidate any cached snapshot data when the value changes.
 *  - The biometric inactivity gate is independent: switching profiles does NOT
 *    re-prompt for biometric within the inactivity window.
 */
@Singleton
class UserScope @Inject constructor() {

    private val _activeUserId = MutableStateFlow<UUID?>(null)
    val activeUserId: StateFlow<UUID?> = _activeUserId.asStateFlow()

    fun setActiveUser(userId: UUID?) {
        _activeUserId.value = userId
    }

    fun requireActiveUserId(): UUID =
        _activeUserId.value
            ?: error("UserScope has no active user. Show profile picker before reading per-user data.")

    fun clear() {
        _activeUserId.value = null
    }
}
