package com.fintrack.data.backup

import kotlinx.serialization.Serializable

/**
 * On-disk shape of an encrypted JSON backup. The format is versioned so
 * future migrations can stay backwards compatible.
 *
 * BigDecimal/UUID/Instant/LocalDate are encoded as their toString() form,
 * which is what Room already stores via Converters.
 */
@Serializable
data class BackupPayload(
    val version: Int = 1,
    val exportedAt: String,
    val activeUserId: String,
    val users: List<UserDto>,
    val userSettings: List<UserSettingsDto>,
    val holdings: List<HoldingDto>,
    val snapshots: List<SnapshotDto>,
    val holdingValues: List<HoldingValueDto>,
)

@Serializable
data class UserDto(
    val id: String,
    val name: String,
    val colorHex: String,
    val createdAt: String,
    val isActive: Boolean,
)

@Serializable
data class UserSettingsDto(
    val userId: String,
    val aimPctMfNps: Int,
    val aimPctEquity: Int,
    val aimPctFixedReturn: Int,
    val aimPctCrypto: Int,
)

@Serializable
data class HoldingDto(
    val id: String,
    val name: String,
    val assetClass: String,
    val trackInvested: Boolean,
    val trackSip: Boolean,
    val isBankAccount: Boolean,
    val isActive: Boolean,
    val displayOrder: Int,
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
