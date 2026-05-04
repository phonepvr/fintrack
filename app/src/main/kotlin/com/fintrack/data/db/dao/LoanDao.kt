package com.fintrack.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.fintrack.data.db.entities.LoanEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * Per-user reads ALL take userId. Privacy-test gate.
 */
@Dao
interface LoanDao {

    @Query("SELECT * FROM loans WHERE user_id = :userId ORDER BY taken_date DESC, name ASC")
    fun observeForUser(userId: UUID): Flow<List<LoanEntity>>

    @Query(
        """
        SELECT * FROM loans
        WHERE user_id = :userId AND is_active = 1
        ORDER BY taken_date DESC, name ASC
        """,
    )
    fun observeActiveForUser(userId: UUID): Flow<List<LoanEntity>>

    @Query("SELECT * FROM loans WHERE user_id = :userId AND is_active = 1")
    suspend fun getActiveForUser(userId: UUID): List<LoanEntity>

    @Query("SELECT * FROM loans WHERE user_id = :userId AND id = :loanId LIMIT 1")
    suspend fun getForUser(userId: UUID, loanId: UUID): LoanEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(row: LoanEntity)

    @Update
    suspend fun update(row: LoanEntity)

    @Query("DELETE FROM loans WHERE user_id = :userId AND id = :loanId")
    suspend fun deleteForUser(userId: UUID, loanId: UUID)
}
