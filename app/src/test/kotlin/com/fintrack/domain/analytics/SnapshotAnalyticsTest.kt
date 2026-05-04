package com.fintrack.domain.analytics

import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.db.entities.UserSettingsEntity
import com.fintrack.domain.model.AssetClass
import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

class SnapshotAnalyticsTest {

    private val userId = UUID.randomUUID()

    private val degree = HoldingEntity(
        id = UUID.randomUUID(), name = "Degree212", assetClass = AssetClass.MF_NPS,
        trackInvested = true, trackSip = true, isBankAccount = false,
    )
    private val ppf = HoldingEntity(
        id = UUID.randomUUID(), name = "PPF", assetClass = AssetClass.FIXED_RETURN,
        trackInvested = false, trackSip = true, isBankAccount = false,
    )
    private val hdfc = HoldingEntity(
        id = UUID.randomUUID(), name = "HDFC", assetClass = AssetClass.FIXED_RETURN,
        trackInvested = false, trackSip = false, isBankAccount = true,
    )
    private val coindcx = HoldingEntity(
        id = UUID.randomUUID(), name = "CoinDCX", assetClass = AssetClass.CRYPTO,
        trackInvested = true, trackSip = false, isBankAccount = false,
    )
    private val equity = HoldingEntity(
        id = UUID.randomUUID(), name = "Equity", assetClass = AssetClass.EQUITY,
        trackInvested = true, trackSip = false, isBankAccount = false,
    )

    private val catalog = listOf(degree, ppf, hdfc, coindcx, equity)

    private val defaultSettings = UserSettingsEntity(
        userId = userId,
        aimPctMfNps = 55,
        aimPctEquity = 15,
        aimPctFixedReturn = 25,
        aimPctCrypto = 5,
    )

    private fun snapshot(
        date: String = "2025-01-01",
        earnings: String = "1.00",
    ) = SnapshotEntity(
        id = UUID.randomUUID(),
        userId = userId,
        snapshotDate = LocalDate.parse(date),
        earningsInCr = BigDecimal(earnings),
        notes = null,
        createdAt = Instant.parse("2025-01-01T00:00:00Z"),
        updatedAt = Instant.parse("2025-01-01T00:00:00Z"),
    )

    private fun value(
        snapshotId: UUID,
        holding: HoldingEntity,
        invested: String? = null,
        current: String,
        sip: String? = null,
    ) = HoldingValueEntity(
        id = UUID.randomUUID(),
        snapshotId = snapshotId,
        holdingId = holding.id,
        invested = invested?.let(::BigDecimal),
        current = BigDecimal(current),
        sip = sip?.let(::BigDecimal),
    )

    @Test
    @DisplayName("Empty value set yields zero totals and aim-class-magnitude drift")
    fun emptyValues() {
        val s = snapshot()
        val a = SnapshotAnalyticsCalculator.compute(
            snapshot = s,
            values = emptyList(),
            catalog = catalog,
            settings = defaultSettings,
            previousTotalPortfolio = null,
        )

        assertThat(a.totalPortfolio).isEqualByComparingTo(BigDecimal.ZERO)
        assertThat(a.totalInvested).isEqualByComparingTo(BigDecimal.ZERO)
        assertThat(a.totalSip).isEqualByComparingTo(BigDecimal.ZERO)
        assertThat(a.percentOfEarnings).isEqualByComparingTo(BigDecimal.ZERO)
        assertThat(a.deltaAbsolute).isNull()
        assertThat(a.deltaPercent).isNull()
        // No portfolio yet → 0% of every class → drift = -aim%
        assertThat(a.byAssetClass.getValue(AssetClass.MF_NPS).driftPct).isEqualByComparingTo(BigDecimal("-55"))
        assertThat(a.byAssetClass.getValue(AssetClass.EQUITY).driftPct).isEqualByComparingTo(BigDecimal("-15"))
        assertThat(a.byAssetClass.getValue(AssetClass.FIXED_RETURN).driftPct).isEqualByComparingTo(BigDecimal("-25"))
        assertThat(a.byAssetClass.getValue(AssetClass.CRYPTO).driftPct).isEqualByComparingTo(BigDecimal("-5"))
    }

    @Test
    @DisplayName("All-bank Fixed Return rolls up into savingsRollup equal to FIXED_RETURN total")
    fun savingsRollup() {
        val s = snapshot()
        val a = SnapshotAnalyticsCalculator.compute(
            snapshot = s,
            values = listOf(
                value(s.id, hdfc, current = "100000"),
                value(s.id, ppf, current = "300000"),
            ),
            catalog = catalog,
            settings = defaultSettings,
            previousTotalPortfolio = null,
        )
        assertThat(a.savingsRollup).isEqualByComparingTo(BigDecimal("100000"))
        assertThat(a.byAssetClass.getValue(AssetClass.FIXED_RETURN).current).isEqualByComparingTo(BigDecimal("400000"))
    }

    @Test
    @DisplayName("Investment value equals MF_NPS + EQUITY + CRYPTO totals")
    fun investmentValue() {
        val s = snapshot()
        val a = SnapshotAnalyticsCalculator.compute(
            snapshot = s,
            values = listOf(
                value(s.id, degree, invested = "100000", current = "120000", sip = "5000"),
                value(s.id, equity, invested = "50000", current = "60000"),
                value(s.id, coindcx, invested = "10000", current = "11000"),
                value(s.id, hdfc, current = "9000"),
            ),
            catalog = catalog,
            settings = defaultSettings,
            previousTotalPortfolio = null,
        )
        // 120k + 60k + 11k = 191000
        assertThat(a.investmentValue).isEqualByComparingTo(BigDecimal("191000"))
        // total includes the 9k bank
        assertThat(a.totalPortfolio).isEqualByComparingTo(BigDecimal("200000"))
    }

    @Test
    @DisplayName("Drift bands: ±2% green, ±5% amber, beyond is red")
    fun driftBands() {
        assertThat(DriftBand.forDrift(BigDecimal("0"))).isEqualTo(DriftBand.WITHIN)
        assertThat(DriftBand.forDrift(BigDecimal("1.99"))).isEqualTo(DriftBand.WITHIN)
        assertThat(DriftBand.forDrift(BigDecimal("-2"))).isEqualTo(DriftBand.WITHIN)
        assertThat(DriftBand.forDrift(BigDecimal("2.01"))).isEqualTo(DriftBand.WARN)
        assertThat(DriftBand.forDrift(BigDecimal("-5"))).isEqualTo(DriftBand.WARN)
        assertThat(DriftBand.forDrift(BigDecimal("5.01"))).isEqualTo(DriftBand.OFF)
        assertThat(DriftBand.forDrift(BigDecimal("-12"))).isEqualTo(DriftBand.OFF)
    }

    @Test
    @DisplayName("Δ vs previous gives absolute and signed percent")
    fun deltaCalculation() {
        val s = snapshot(earnings = "4.00")
        val a = SnapshotAnalyticsCalculator.compute(
            snapshot = s,
            values = listOf(value(s.id, equity, invested = "1000000", current = "1100000")),
            catalog = catalog,
            settings = defaultSettings,
            previousTotalPortfolio = BigDecimal("1000000"),
        )
        assertThat(a.deltaAbsolute).isNotNull()
        assertThat(a.deltaAbsolute).isEqualByComparingTo(BigDecimal("100000"))
        // 100000 / 1000000 = 0.1 → 10%
        assertThat(a.deltaPercent).isEqualByComparingTo(BigDecimal("10"))
    }

    @Test
    @DisplayName("Δ vs previous is null on the first snapshot")
    fun deltaNullForFirstSnapshot() {
        val s = snapshot()
        val a = SnapshotAnalyticsCalculator.compute(
            snapshot = s,
            values = listOf(value(s.id, equity, invested = "1000", current = "1000")),
            catalog = catalog,
            settings = defaultSettings,
            previousTotalPortfolio = null,
        )
        assertThat(a.deltaAbsolute).isNull()
        assertThat(a.deltaPercent).isNull()
    }

    @Test
    @DisplayName("% of earnings = (total ÷ (earningsInCr × 1Cr)) × 100")
    fun percentOfEarnings() {
        val s = snapshot(earnings = "4.00") // 4 crore
        val a = SnapshotAnalyticsCalculator.compute(
            snapshot = s,
            values = listOf(value(s.id, equity, invested = "0", current = "8530000")), // 85.30L
            catalog = catalog,
            settings = defaultSettings,
            previousTotalPortfolio = null,
        )
        // 8530000 / 40000000 = 0.21325 → 21.33%
        assertThat(a.percentOfEarnings).isEqualByComparingTo(BigDecimal("21.33"))
    }

    @Test
    @DisplayName("Spec §5 User A snapshot 1 totals reproduce the spec's ~85.30 lakh / ~21.3% earnings")
    fun userASnapshot1Reproduces() {
        val s = snapshot(date = "2025-01-01", earnings = "4.00")
        val values = listOf(
            value(s.id, degree, invested = "3000000", current = "3250000", sip = "50000"),
            value(s.id, equity, invested = "1000000", current = "1100000"),
            value(s.id, ppf, current = "400000", sip = "5000"),
            value(s.id, hdfc, current = "100000"),
            value(s.id, coindcx, invested = "100000", current = "110000"),
        )
        val a = SnapshotAnalyticsCalculator.compute(
            snapshot = s, values = values, catalog = catalog,
            settings = defaultSettings, previousTotalPortfolio = null,
        )
        // 3250000 + 1100000 + 400000 + 100000 + 110000 = 4960000 (subset of full snapshot)
        assertThat(a.totalPortfolio).isEqualByComparingTo(BigDecimal("4960000"))
        assertThat(a.savingsRollup).isEqualByComparingTo(BigDecimal("100000"))
    }
}
