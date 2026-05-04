package com.fintrack.data.repo

import com.fintrack.data.db.FintrackDatabase
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.data.db.entities.LoanValueEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Loans and per-snapshot LoanValue rows for the active user. All reads take
 * userId; LoanValueDao read methods JOIN on snapshots.user_id.
 */
@Singleton
class LoanRepository @Inject constructor(
    private val database: FintrackDatabase,
) {
    private val loans get() = database.loanDao()
    private val values get() = database.loanValueDao()

    fun observeLoansForUser(userId: UUID): Flow<List<LoanEntity>> = loans.observeForUser(userId)
    fun observeActiveLoansForUser(userId: UUID): Flow<List<LoanEntity>> = loans.observeActiveForUser(userId)
    suspend fun getActiveLoansForUser(userId: UUID): List<LoanEntity> = loans.getActiveForUser(userId)

    fun observeValuesForSnapshot(userId: UUID, snapshotId: UUID): Flow<List<LoanValueEntity>> =
        values.observeForSnapshot(userId, snapshotId)

    suspend fun getValuesForSnapshot(userId: UUID, snapshotId: UUID): List<LoanValueEntity> =
        values.getForSnapshot(userId, snapshotId)

    fun observeAllValuesForUser(userId: UUID): Flow<List<LoanValueEntity>> =
        values.observeAllForUser(userId)

    suspend fun createLoan(
        userId: UUID,
        name: String,
        originalAmount: BigDecimal,
        takenDate: LocalDate,
        monthlyEmi: BigDecimal,
    ): UUID {
        val now = Clock.System.now()
        val entity = LoanEntity(
            id = UUID.randomUUID(),
            userId = userId,
            name = name.trim(),
            originalAmount = originalAmount,
            takenDate = takenDate,
            monthlyEmi = monthlyEmi,
            isActive = true,
            closedDate = null,
            createdAt = now,
            updatedAt = now,
        )
        loans.insert(entity)
        return entity.id
    }

    suspend fun updateLoan(loan: LoanEntity) {
        loans.update(loan.copy(updatedAt = Clock.System.now()))
    }

    suspend fun markClosed(userId: UUID, loanId: UUID, closedDate: LocalDate) {
        val existing = loans.getForUser(userId, loanId) ?: return
        loans.update(existing.copy(
            isActive = false,
            closedDate = closedDate,
            updatedAt = Clock.System.now(),
        ))
    }

    suspend fun deleteLoan(userId: UUID, loanId: UUID) {
        loans.deleteForUser(userId, loanId)
    }
}
