package com.fintrack.data.db.seed

import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.data.repo.HoldingValueDraft
import com.fintrack.domain.model.GoalType
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID

/**
 * Spec §3.4-3.6: two seed users with their snapshots, loans, and goals,
 * inserted in debug builds only. UUIDs deterministic so re-seeding is idempotent.
 *
 * Holding name → renamed in v3:
 *   "Equity (Direct)" → "Stocks (Direct)"
 *   "RD" — dropped (not in v3 catalog)
 */
object DevSeedData {

    val userAId: UUID = UUID.nameUUIDFromBytes("fintrack-seed-user:Mr. X".toByteArray())
    val userBId: UUID = UUID.nameUUIDFromBytes("fintrack-seed-user:Spouse".toByteArray())

    val userA = UserEntity(
        id = userAId,
        name = "Mr. X",
        colorHex = "#1976D2",
        createdAt = Instant.parse("2025-01-01T08:00:00Z"),
        isActive = true,
    )

    /** User A keeps the spec defaults: Market Linked 70 / Fixed Return 25 / Crypto 5. */
    val userAAimAllocations: List<AimAllocationEntity> = listOf(
        AimAllocationEntity(userAId, SeedData.MARKET_LINKED_ID, 70),
        AimAllocationEntity(userAId, SeedData.FIXED_RETURN_ID, 25),
        AimAllocationEntity(userAId, SeedData.CRYPTO_ID, 5),
    )

    val userB = UserEntity(
        id = userBId,
        name = "Spouse",
        colorHex = "#E91E63",
        createdAt = Instant.parse("2025-01-02T08:00:00Z"),
        isActive = true,
    )

    /** User B is more conservative: Market Linked 50 / Fixed Return 45 / Crypto 5. */
    val userBAimAllocations: List<AimAllocationEntity> = listOf(
        AimAllocationEntity(userBId, SeedData.MARKET_LINKED_ID, 50),
        AimAllocationEntity(userBId, SeedData.FIXED_RETURN_ID, 45),
        AimAllocationEntity(userBId, SeedData.CRYPTO_ID, 5),
    )

    data class SeedSnapshot(
        val date: LocalDate,
        val earningsInCr: BigDecimal,
        val rows: List<HoldingValueDraft>,
    )

    /** Loans are seeded as separate entities; each snapshot has matching LoanValue rows. */
    data class SeedLoan(
        val name: String,
        val originalAmount: BigDecimal,
        val takenDate: LocalDate,
        val monthlyEmi: BigDecimal,
    ) {
        val id: UUID = UUID.nameUUIDFromBytes("fintrack-seed-loan:$name".toByteArray())
        fun toEntity(userId: UUID, now: Instant): LoanEntity = LoanEntity(
            id = id, userId = userId, name = name, originalAmount = originalAmount,
            takenDate = takenDate, monthlyEmi = monthlyEmi, isActive = true,
            closedDate = null, createdAt = now, updatedAt = now,
        )
    }

    val seedLoansForUserA: List<SeedLoan> = listOf(
        SeedLoan("Home Loan – HDFC", BigDecimal("6000000"), LocalDate.parse("2022-07-01"), BigDecimal("52000")),
        SeedLoan("Car Loan – ICICI", BigDecimal("800000"), LocalDate.parse("2024-03-15"), BigDecimal("17500")),
    )

    /** Per-snapshot LoanValue outstanding amounts, indexed by snapshotDate then loanName. */
    val loanValuesForUserA: Map<LocalDate, Map<String, BigDecimal>> = mapOf(
        LocalDate.parse("2025-01-01") to mapOf(
            "Home Loan – HDFC" to BigDecimal("5480000"),
            "Car Loan – ICICI" to BigDecimal("690000"),
        ),
        LocalDate.parse("2025-05-01") to mapOf(
            "Home Loan – HDFC" to BigDecimal("5390000"),
            "Car Loan – ICICI" to BigDecimal("640000"),
        ),
        LocalDate.parse("2025-10-01") to mapOf(
            "Home Loan – HDFC" to BigDecimal("5280000"),
            "Car Loan – ICICI" to BigDecimal("580000"),
        ),
    )

    fun snapshotsForUserA(byName: Map<String, UUID>): List<SeedSnapshot> = listOf(
        SeedSnapshot(
            date = LocalDate.parse("2025-01-01"),
            earningsInCr = BigDecimal("4.00"),
            rows = listOf(
                row(byName, "Degree212", invested = "3000000", current = "3250000", sip = "50000"),
                row(byName, "Kuvera", invested = "1500000", current = "1620000", sip = "15000"),
                row(byName, "Tata Capital", invested = "200000", current = "210000", sip = "0"),
                row(byName, "NPS", invested = "300000", current = "340000", sip = "35000"),
                row(byName, "Stocks (Direct)", invested = "1000000", current = "1100000", sip = "0"),
                row(byName, "EPF", invested = null, current = "200000", sip = "10000"),
                row(byName, "PPF", invested = null, current = "400000", sip = "5000"),
                row(byName, "FD", invested = null, current = "0", sip = null),
                row(byName, "Wint Wealth", invested = null, current = "700000", sip = "8000"),
                row(byName, "HDFC", invested = null, current = "100000", sip = null),
                row(byName, "Kotak", invested = null, current = "150000", sip = null),
                row(byName, "AU", invested = null, current = "400000", sip = null),
                row(byName, "ICICI", invested = null, current = "150000", sip = null),
                row(byName, "CoinDCX", invested = "100000", current = "110000", sip = "0"),
            ),
        ),
        SeedSnapshot(
            date = LocalDate.parse("2025-05-01"),
            earningsInCr = BigDecimal("4.18"),
            rows = listOf(
                row(byName, "Degree212", invested = "3200000", current = "3650000", sip = "50000"),
                row(byName, "Kuvera", invested = "1560000", current = "1780000", sip = "15000"),
                row(byName, "Tata Capital", invested = "200000", current = "225000", sip = "0"),
                row(byName, "NPS", invested = "440000", current = "500000", sip = "35000"),
                row(byName, "Stocks (Direct)", invested = "1000000", current = "1220000", sip = "0"),
                row(byName, "EPF", invested = null, current = "240000", sip = "10000"),
                row(byName, "PPF", invested = null, current = "420000", sip = "5000"),
                row(byName, "FD", invested = null, current = "0", sip = null),
                row(byName, "Wint Wealth", invested = null, current = "730000", sip = "8000"),
                row(byName, "HDFC", invested = null, current = "120000", sip = null),
                row(byName, "Kotak", invested = null, current = "160000", sip = null),
                row(byName, "AU", invested = null, current = "450000", sip = null),
                row(byName, "ICICI", invested = null, current = "170000", sip = null),
                row(byName, "CoinDCX", invested = "100000", current = "125000", sip = "0"),
            ),
        ),
        SeedSnapshot(
            date = LocalDate.parse("2025-10-01"),
            earningsInCr = BigDecimal("4.36"),
            rows = listOf(
                row(byName, "Degree212", invested = "3400000", current = "4020000", sip = "50000"),
                row(byName, "Kuvera", invested = "1620000", current = "1950000", sip = "15000"),
                row(byName, "Tata Capital", invested = "200000", current = "240000", sip = "0"),
                row(byName, "NPS", invested = "580000", current = "660000", sip = "35000"),
                row(byName, "Stocks (Direct)", invested = "1000000", current = "1350000", sip = "0"),
                row(byName, "EPF", invested = null, current = "290000", sip = "10000"),
                row(byName, "PPF", invested = null, current = "450000", sip = "5000"),
                row(byName, "FD", invested = null, current = "0", sip = null),
                row(byName, "Wint Wealth", invested = null, current = "800000", sip = "8000"),
                row(byName, "HDFC", invested = null, current = "150000", sip = null),
                row(byName, "Kotak", invested = null, current = "180000", sip = null),
                row(byName, "AU", invested = null, current = "500000", sip = null),
                row(byName, "ICICI", invested = null, current = "200000", sip = null),
                row(byName, "CoinDCX", invested = "100000", current = "140000", sip = "0"),
            ),
        ),
    )

    fun snapshotsForUserB(byName: Map<String, UUID>): List<SeedSnapshot> = listOf(
        SeedSnapshot(
            date = LocalDate.parse("2025-03-01"),
            earningsInCr = BigDecimal("1.20"),
            rows = listOf(
                row(byName, "Kuvera", invested = "500000", current = "540000", sip = "10000"),
                row(byName, "NPS", invested = "150000", current = "165000", sip = "15000"),
                row(byName, "PPF", invested = null, current = "300000", sip = "12500"),
                row(byName, "HDFC", invested = null, current = "80000", sip = null),
                row(byName, "ICICI", invested = null, current = "60000", sip = null),
            ),
        ),
        SeedSnapshot(
            date = LocalDate.parse("2025-09-01"),
            earningsInCr = BigDecimal("1.30"),
            rows = listOf(
                row(byName, "Kuvera", invested = "560000", current = "650000", sip = "10000"),
                row(byName, "NPS", invested = "240000", current = "260000", sip = "15000"),
                row(byName, "PPF", invested = null, current = "350000", sip = "12500"),
                row(byName, "HDFC", invested = null, current = "90000", sip = null),
                row(byName, "ICICI", invested = null, current = "70000", sip = null),
            ),
        ),
    )

    fun goalsForUserA(now: Instant): List<GoalEntity> = listOf(
        GoalEntity(
            id = UUID.nameUUIDFromBytes("fintrack-seed-goal:Mr. X/First Crore".toByteArray()),
            userId = userAId,
            name = "First Crore (Net Worth)",
            goalType = GoalType.NET_WORTH,
            targetNetWorth = BigDecimal("10000000"),
            targetDate = LocalDate.parse("2027-12-01"),
            createdAt = now,
        ),
        GoalEntity(
            id = UUID.nameUUIDFromBytes("fintrack-seed-goal:Mr. X/Debt-free".toByteArray()),
            userId = userAId,
            name = "Debt-free",
            goalType = GoalType.DEBT_FREE,
            targetNetWorth = BigDecimal.ZERO,
            targetDate = LocalDate.parse("2030-07-01"),
            createdAt = now,
        ),
    )

    private fun row(
        byName: Map<String, UUID>,
        holdingName: String,
        invested: String?,
        current: String,
        sip: String?,
    ): HoldingValueDraft {
        val id = byName[holdingName]
            ?: error("Seed references missing holding: $holdingName")
        return HoldingValueDraft(
            holdingId = id,
            invested = invested?.let(::BigDecimal),
            current = BigDecimal(current),
            sip = sip?.let(::BigDecimal),
        )
    }
}
