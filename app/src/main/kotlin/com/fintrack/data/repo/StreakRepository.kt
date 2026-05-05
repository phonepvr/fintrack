package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.StreakStateEntity
import com.fintrack.domain.streaks.StreakCalculator
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Read-side wrapper around StreakStateDao plus the recompute job called
 * after any snapshot create/update/delete on the active user.
 */
@Singleton
class StreakRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val dao get() = database.streakStateDao()
    private val snapshots get() = database.snapshotDao()

    fun observeForUser(userId: UUID): Flow<StreakStateEntity?> = dao.observeForUser(userId)

    suspend fun getForUser(userId: UUID): StreakStateEntity? = dao.getForUser(userId)

    suspend fun upsert(state: StreakStateEntity) = dao.upsert(state)

    /**
     * Recomputes the user's streak from the current set of snapshots and
     * persists the result. Cheap (one query + a sort by month).
     */
    suspend fun recompute(userId: UUID, today: LocalDate = todayLocal()) {
        val dates = snapshots.getDatesForUser(userId)
        val result = StreakCalculator.compute(dates, today)
        dao.upsert(
            StreakStateEntity(
                userId = userId,
                currentStreakMonths = result.currentStreakMonths,
                longestStreakMonths = result.longestStreakMonths,
                lastSnapshotMonth = result.lastSnapshotMonth,
            ),
        )
    }
}

private fun todayLocal(): LocalDate =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
