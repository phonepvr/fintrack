package com.fintrack.data.db.seed

import com.fintrack.data.db.entities.GlobalSettingsEntity
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.domain.model.AssetClass
import java.util.UUID

/**
 * Static seed catalog applied on first DB create (and idempotently on every
 * launch in case a row was somehow lost). Holdings are GLOBAL — adding to this
 * list does not retroactively show up for existing installs unless a migration
 * inserts the new rows.
 */
object SeedData {

    val defaultGlobalSettings: GlobalSettingsEntity = GlobalSettingsEntity(
        inactivityLockSeconds = 60,
        defaultCurrencySymbol = "₹",
        activeUserId = null,
        alwaysShowProfilePicker = false,
    )

    /**
     * Spec §3.2 default catalog. Display order is row index × 10 so we can
     * insert custom entries between later without renumbering.
     */
    val defaultHoldings: List<HoldingEntity> = listOf(
        // MF + NPS
        seed("Degree212", AssetClass.MF_NPS, trackInvested = true, trackSip = true),
        seed("Kuvera", AssetClass.MF_NPS, trackInvested = true, trackSip = true),
        seed("Tata Capital", AssetClass.MF_NPS, trackInvested = true, trackSip = true),
        seed("NPS", AssetClass.MF_NPS, trackInvested = true, trackSip = true),
        // Equity
        seed("Equity (Direct)", AssetClass.EQUITY, trackInvested = true, trackSip = true),
        // Fixed Return (non-bank)
        seed("PPF", AssetClass.FIXED_RETURN, trackInvested = false, trackSip = true),
        seed("RD", AssetClass.FIXED_RETURN, trackInvested = false, trackSip = true),
        seed("Wint Wealth", AssetClass.FIXED_RETURN, trackInvested = false, trackSip = true),
        // Fixed Return (bank accounts)
        seed("HDFC", AssetClass.FIXED_RETURN, isBank = true),
        seed("Kotak", AssetClass.FIXED_RETURN, isBank = true),
        seed("AU", AssetClass.FIXED_RETURN, isBank = true),
        seed("ICICI", AssetClass.FIXED_RETURN, isBank = true),
        // Crypto
        seed("CoinDCX", AssetClass.CRYPTO, trackInvested = true, trackSip = false),
    ).mapIndexed { index, holding ->
        holding.copy(displayOrder = index * 10)
    }

    private fun seed(
        name: String,
        assetClass: AssetClass,
        trackInvested: Boolean = false,
        trackSip: Boolean = false,
        isBank: Boolean = false,
    ): HoldingEntity = HoldingEntity(
        // Stable UUIDs derived from the name keep first-launch reproducible across
        // installs and make backup/import reconciliation easier.
        id = UUID.nameUUIDFromBytes("fintrack-holding:$name".toByteArray()),
        name = name,
        assetClass = assetClass,
        trackInvested = trackInvested,
        trackSip = trackSip,
        isBankAccount = isBank,
        isActive = true,
        displayOrder = 0,
    )
}
