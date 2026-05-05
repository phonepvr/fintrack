package com.fintrack.data.xlsx

import androidx.room.withTransaction
import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.data.db.entities.LoanValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.domain.model.GoalType
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

enum class XlsxConflictStrategy {
    /** Existing snapshots for a given date stay; matching imported rows skipped. */
    SKIP_EXISTING,

    /** Existing snapshots for a given date are deleted (CASCADE drops values), then imported. */
    REPLACE_EXISTING,
}

data class XlsxImportSummary(
    val snapshotsCreated: Int,
    val snapshotsSkipped: Int,
    val snapshotsReplaced: Int,
    val holdingValuesCreated: Int,
    val loansCreated: Int,
    val loanValuesCreated: Int,
    val goalsCreated: Int,
    val warnings: List<String>,
)

/**
 * Persists a parsed [XlsxWorkbookData] into Room for [userId]. Snapshot
 * conflicts are resolved by [XlsxConflictStrategy]; everything else
 * (loans, goals) inserts under a uniqueness check by name.
 *
 * The whole import runs inside a single Room transaction so a partial
 * failure rolls back cleanly.
 */
@Singleton
class XlsxImportApplier @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val users get() = database.userDao()
    private val snapshots get() = database.snapshotDao()
    private val holdingValues get() = database.holdingValueDao()
    private val holdings get() = database.holdingDao()
    private val loans get() = database.loanDao()
    private val loanValues get() = database.loanValueDao()
    private val goals get() = database.goalDao()

    suspend fun apply(
        userId: UUID,
        data: XlsxWorkbookData,
        strategy: XlsxConflictStrategy,
    ): XlsxImportSummary {
        val warnings = mutableListOf<String>()
        var snapshotsCreated = 0
        var snapshotsSkipped = 0
        var snapshotsReplaced = 0
        var holdingValuesCreated = 0
        var loansCreated = 0
        var loanValuesCreated = 0
        var goalsCreated = 0

        users.getUser(userId) ?: error("Unknown user $userId")
        val holdingsByName = holdings.observeAll().first()
            .associateBy { it.name.lowercase() }

        val now = Clock.System.now()
        database.withTransaction {
            val existingByDate = snapshots.observeForUser(userId).first()
                .associateBy { it.snapshotDate }

            // Resolve loans first (they're referenced by name from LoanValues).
            val existingLoans = loans.observeForUser(userId).first()
                .associateBy { it.name.lowercase() }
            val loansByName = mutableMapOf<String, UUID>()
            existingLoans.forEach { (k, v) -> loansByName[k] = v.id }
            for (l in data.loans) {
                val key = l.name.lowercase()
                if (loansByName.containsKey(key)) continue
                val loanId = UUID.randomUUID()
                loans.insert(
                    LoanEntity(
                        id = loanId,
                        userId = userId,
                        name = l.name.trim(),
                        originalAmount = l.originalAmount,
                        takenDate = l.takenDate,
                        monthlyEmi = l.monthlyEmi,
                        isActive = true,
                        closedDate = null,
                        createdAt = now,
                        updatedAt = now,
                    ),
                )
                loansByName[key] = loanId
                loansCreated++
            }

            // Snapshots — drop or skip based on strategy.
            val newSnapshotIdsByDate = mutableMapOf<kotlinx.datetime.LocalDate, UUID>()
            for (s in data.snapshots) {
                val existing = existingByDate[s.date]
                if (existing != null) {
                    when (strategy) {
                        XlsxConflictStrategy.SKIP_EXISTING -> {
                            snapshotsSkipped++
                            newSnapshotIdsByDate[s.date] = existing.id
                            continue
                        }
                        XlsxConflictStrategy.REPLACE_EXISTING -> {
                            snapshots.deleteForUser(userId, existing.id)
                            snapshotsReplaced++
                        }
                    }
                }
                val id = UUID.randomUUID()
                snapshots.insert(
                    SnapshotEntity(
                        id = id,
                        userId = userId,
                        snapshotDate = s.date,
                        earningsInCr = s.earningsInCr,
                        notes = s.notes,
                        createdAt = now,
                        updatedAt = now,
                    ),
                )
                newSnapshotIdsByDate[s.date] = id
                if (existing == null) snapshotsCreated++
            }

            // Holding values — match the snapshot id (skipped or new) and the catalog row.
            for (hv in data.holdingValues) {
                val snapId = newSnapshotIdsByDate[hv.snapshotDate]
                if (snapId == null) {
                    warnings += "Skipping holding value: snapshot ${hv.snapshotDate} not present"
                    continue
                }
                val holding = holdingsByName[hv.holdingName.lowercase()]
                if (holding == null) {
                    warnings += "Unknown holding \"${hv.holdingName}\" — skipped"
                    continue
                }
                holdingValues.insert(
                    HoldingValueEntity(
                        id = UUID.randomUUID(),
                        snapshotId = snapId,
                        holdingId = holding.id,
                        invested = hv.invested,
                        current = hv.current,
                        sip = hv.sip,
                    ),
                )
                holdingValuesCreated++
            }

            // Loan values.
            for (lv in data.loanValues) {
                val snapId = newSnapshotIdsByDate[lv.snapshotDate]
                if (snapId == null) {
                    warnings += "Skipping loan value: snapshot ${lv.snapshotDate} not present"
                    continue
                }
                val loanId = loansByName[lv.loanName.lowercase()]
                if (loanId == null) {
                    warnings += "Unknown loan \"${lv.loanName}\" — skipped"
                    continue
                }
                loanValues.insert(
                    LoanValueEntity(
                        id = UUID.randomUUID(),
                        snapshotId = snapId,
                        loanId = loanId,
                        outstanding = lv.outstanding,
                    ),
                )
                loanValuesCreated++
            }

            // Goals — dedup by name within user.
            val existingGoals = goals.observeAllForUser(userId).first()
                .map { it.name.lowercase() }.toSet()
            for (g in data.goals) {
                if (g.name.lowercase() in existingGoals) continue
                val type = runCatching { GoalType.valueOf(g.goalType.uppercase()) }.getOrNull()
                if (type == null) {
                    warnings += "Goal \"${g.name}\": unknown type \"${g.goalType}\""
                    continue
                }
                goals.insert(
                    GoalEntity(
                        id = UUID.randomUUID(),
                        userId = userId,
                        name = g.name.trim(),
                        goalType = type,
                        targetNetWorth = if (type == GoalType.NET_WORTH) g.targetNetWorth else BigDecimal.ZERO,
                        targetDate = g.targetDate,
                        createdAt = now,
                    ),
                )
                goalsCreated++
            }

        }

        return XlsxImportSummary(
            snapshotsCreated = snapshotsCreated,
            snapshotsSkipped = snapshotsSkipped,
            snapshotsReplaced = snapshotsReplaced,
            holdingValuesCreated = holdingValuesCreated,
            loansCreated = loansCreated,
            loanValuesCreated = loanValuesCreated,
            goalsCreated = goalsCreated,
            warnings = warnings,
        )
    }
}
