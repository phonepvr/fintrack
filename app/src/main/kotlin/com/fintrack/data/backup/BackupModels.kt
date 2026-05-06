package com.fintrack.data.backup

import kotlinx.serialization.Serializable

/**
 * v3 on-disk shape. The `version` field is checked on import — anything
 * other than V3 is rejected with an explicit error per the user's decision
 * (no best-effort migration from v2).
 *
 * BigDecimal/UUID/Instant/LocalDate are encoded as their toString() form,
 * which matches what Room already stores via Converters.
 *
 * v3 changes vs v2:
 * - Schema version bumped to 3 (skipping 2 to keep file/version unambiguous).
 * - New top-level: assetClasses, subBuckets, loans, loanValues, goals,
 *   milestones, streakState.
 * - HoldingDto: assetClass enum string → assetClassId + subBucketId UUIDs.
 * - HoldingDto: dropped isBankAccount (the "Bank" sub-bucket is the new home).
 * - aimAllocations replaces UserSettingsDto's four fixed columns.
 */
@Serializable
data class BackupPayload(
    val version: Int = SCHEMA_VERSION,
    val exportedAt: String,
    val activeUserId: String,
    val users: List<UserDto>,
    val assetClasses: List<AssetClassDto>,
    val subBuckets: List<SubBucketDto>,
    val holdings: List<HoldingDto>,
    val aimAllocations: List<AimAllocationDto>,
    val snapshots: List<SnapshotDto>,
    val holdingValues: List<HoldingValueDto>,
    val loans: List<LoanDto>,
    val loanValues: List<LoanValueDto>,
    val goals: List<GoalDto>,
    val milestones: List<MilestoneDto>,
    val streakState: StreakStateDto? = null,
) {
    companion object {
        const val SCHEMA_VERSION = 3
    }
}

@Serializable
data class UserDto(
    val id: String,
    val name: String,
    val colorHex: String,
    val createdAt: String,
    val isActive: Boolean,
)

@Serializable
data class AssetClassDto(
    val id: String,
    val name: String,
    val displayOrder: Int,
    val isSeeded: Boolean,
    val isActive: Boolean,
    val createdAt: String,
)

@Serializable
data class SubBucketDto(
    val id: String,
    val assetClassId: String,
    val name: String,
    val displayOrder: Int,
    val isSeeded: Boolean,
    val isActive: Boolean,
    val createdAt: String,
)

@Serializable
data class HoldingDto(
    val id: String,
    val subBucketId: String,
    val name: String,
    val trackInvested: Boolean,
    val trackSip: Boolean,
    val isActive: Boolean,
    val displayOrder: Int,
)

@Serializable
data class AimAllocationDto(
    val userId: String,
    val assetClassId: String,
    val aimPercent: Int,
)

@Serializable
data class SnapshotDto(
    val id: String,
    val userId: String,
    val snapshotDate: String,
    val earningsInCr: String,
    val notes: String? = null,
    val createdAt: String,
    val updatedAt: String,
)

@Serializable
data class HoldingValueDto(
    val id: String,
    val snapshotId: String,
    val holdingId: String,
    val invested: String? = null,
    val current: String,
    val sip: String? = null,
)

@Serializable
data class LoanDto(
    val id: String,
    val userId: String,
    val name: String,
    val originalAmount: String,
    val takenDate: String,
    val monthlyEmi: String,
    val isActive: Boolean,
    val closedDate: String? = null,
    val createdAt: String,
    val updatedAt: String,
)

@Serializable
data class LoanValueDto(
    val id: String,
    val snapshotId: String,
    val loanId: String,
    val outstanding: String,
)

@Serializable
data class GoalDto(
    val id: String,
    val userId: String,
    val name: String,
    val goalType: String,
    val targetNetWorth: String,
    val targetDate: String,
    val createdAt: String,
    val achievedAt: String? = null,
    val isArchived: Boolean,
    /**
     * Liabilities snapshot taken when the DEBT_FREE goal was created.
     * Empty string from pre-v3.4 backups → binary progress fallback on restore.
     */
    val startingLiabilities: String = "0",
)

@Serializable
data class MilestoneDto(
    val id: String,
    val userId: String,
    val type: String,
    val achievedAtSnapshotId: String,
    val achievedAtDate: String,
    val amountAtAchievement: String? = null,
    val isCelebrated: Boolean,
    val createdAt: String,
)

@Serializable
data class StreakStateDto(
    val userId: String,
    val currentStreakMonths: Int,
    val longestStreakMonths: Int,
    val lastSnapshotMonth: String? = null,
)
