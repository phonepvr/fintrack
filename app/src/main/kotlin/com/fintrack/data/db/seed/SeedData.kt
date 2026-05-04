package com.fintrack.data.db.seed

import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.GlobalSettingsEntity
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.SubBucketEntity
import kotlinx.datetime.Instant
import java.util.UUID

/**
 * Seed catalog applied on first DB create. Three levels:
 *   AssetClass → SubBucket → Holding
 *
 * UUIDs are deterministic (UUID.nameUUIDFromBytes) so re-seeding is idempotent
 * across rebuilds and so backups can refer to seeded rows by id without
 * reconciling on name.
 *
 * Default per-user aim percentages (Market Linked 70 / Fixed Return 25 / Crypto 5)
 * are inserted by UserRepository.createUser when a profile is created.
 */
object SeedData {

    private val SEED_INSTANT = Instant.fromEpochMilliseconds(0L)

    // -------------------------------------------------------------- AssetClass

    private fun acId(name: String): UUID =
        UUID.nameUUIDFromBytes("fintrack-assetclass:$name".toByteArray())

    val MARKET_LINKED_ID = acId("Market Linked")
    val FIXED_RETURN_ID = acId("Fixed Return")
    val CRYPTO_ID = acId("Crypto")

    val defaultAssetClasses: List<AssetClassEntity> = listOf(
        AssetClassEntity(MARKET_LINKED_ID, "Market Linked", 1, isSeeded = true, createdAt = SEED_INSTANT),
        AssetClassEntity(FIXED_RETURN_ID, "Fixed Return", 2, isSeeded = true, createdAt = SEED_INSTANT),
        AssetClassEntity(CRYPTO_ID, "Crypto", 3, isSeeded = true, createdAt = SEED_INSTANT),
    )

    /** Default aim percentages used when seeding a new user. */
    val defaultAimPercentByAssetClass: Map<UUID, Int> = mapOf(
        MARKET_LINKED_ID to 70,
        FIXED_RETURN_ID to 25,
        CRYPTO_ID to 5,
    )

    // ---------------------------------------------------------------- SubBucket

    private fun sbId(assetClassName: String, subName: String): UUID =
        UUID.nameUUIDFromBytes("fintrack-subbucket:$assetClassName/$subName".toByteArray())

    val MF_ID = sbId("Market Linked", "MF")
    val NPS_ID = sbId("Market Linked", "NPS")
    val STOCKS_ID = sbId("Market Linked", "Stocks")
    val PF_ID = sbId("Fixed Return", "PF")
    val PPF_ID = sbId("Fixed Return", "PPF")
    val FD_ID = sbId("Fixed Return", "FD")
    val WINT_WEALTH_ID = sbId("Fixed Return", "Wint Wealth")
    val BANK_ID = sbId("Fixed Return", "Bank")
    val CRYPTO_SB_ID = sbId("Crypto", "Crypto")

    val defaultSubBuckets: List<SubBucketEntity> = listOf(
        SubBucketEntity(MF_ID, MARKET_LINKED_ID, "MF", 1, isSeeded = true, createdAt = SEED_INSTANT),
        SubBucketEntity(NPS_ID, MARKET_LINKED_ID, "NPS", 2, isSeeded = true, createdAt = SEED_INSTANT),
        SubBucketEntity(STOCKS_ID, MARKET_LINKED_ID, "Stocks", 3, isSeeded = true, createdAt = SEED_INSTANT),
        SubBucketEntity(PF_ID, FIXED_RETURN_ID, "PF", 1, isSeeded = true, createdAt = SEED_INSTANT),
        SubBucketEntity(PPF_ID, FIXED_RETURN_ID, "PPF", 2, isSeeded = true, createdAt = SEED_INSTANT),
        SubBucketEntity(FD_ID, FIXED_RETURN_ID, "FD", 3, isSeeded = true, createdAt = SEED_INSTANT),
        SubBucketEntity(WINT_WEALTH_ID, FIXED_RETURN_ID, "Wint Wealth", 4, isSeeded = true, createdAt = SEED_INSTANT),
        SubBucketEntity(BANK_ID, FIXED_RETURN_ID, "Bank", 5, isSeeded = true, createdAt = SEED_INSTANT),
        SubBucketEntity(CRYPTO_SB_ID, CRYPTO_ID, "Crypto", 1, isSeeded = true, createdAt = SEED_INSTANT),
    )

    // ----------------------------------------------------------------- Holdings

    private fun seed(
        name: String,
        subBucketId: UUID,
        trackInvested: Boolean,
        trackSip: Boolean,
    ): HoldingEntity = HoldingEntity(
        id = UUID.nameUUIDFromBytes("fintrack-holding:$name".toByteArray()),
        subBucketId = subBucketId,
        name = name,
        trackInvested = trackInvested,
        trackSip = trackSip,
        isActive = true,
        displayOrder = 0,
    )

    /** Spec §3.2 default catalog (14 rows). Display order is row index × 10. */
    val defaultHoldings: List<HoldingEntity> = listOf(
        // Market Linked / MF
        seed("Degree212", MF_ID, trackInvested = true, trackSip = true),
        seed("Kuvera", MF_ID, trackInvested = true, trackSip = true),
        seed("Tata Capital", MF_ID, trackInvested = true, trackSip = true),
        // Market Linked / NPS
        seed("NPS", NPS_ID, trackInvested = true, trackSip = true),
        // Market Linked / Stocks
        seed("Stocks (Direct)", STOCKS_ID, trackInvested = true, trackSip = true),
        // Fixed Return / PF
        seed("EPF", PF_ID, trackInvested = false, trackSip = true),
        // Fixed Return / PPF
        seed("PPF", PPF_ID, trackInvested = false, trackSip = true),
        // Fixed Return / FD
        seed("FD", FD_ID, trackInvested = false, trackSip = false),
        // Fixed Return / Wint Wealth
        seed("Wint Wealth", WINT_WEALTH_ID, trackInvested = false, trackSip = true),
        // Fixed Return / Bank
        seed("HDFC", BANK_ID, trackInvested = false, trackSip = false),
        seed("Kotak", BANK_ID, trackInvested = false, trackSip = false),
        seed("AU", BANK_ID, trackInvested = false, trackSip = false),
        seed("ICICI", BANK_ID, trackInvested = false, trackSip = false),
        // Crypto
        seed("CoinDCX", CRYPTO_SB_ID, trackInvested = true, trackSip = false),
    ).mapIndexed { index, holding -> holding.copy(displayOrder = index * 10) }

    // ----------------------------------------------------------- Global settings

    val defaultGlobalSettings: GlobalSettingsEntity = GlobalSettingsEntity(
        inactivityLockSeconds = 60,
        defaultCurrencySymbol = "₹",
        activeUserId = null,
        alwaysShowProfilePicker = false,
    )
}
