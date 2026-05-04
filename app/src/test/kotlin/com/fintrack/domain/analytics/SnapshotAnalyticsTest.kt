package com.fintrack.domain.analytics

import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.data.db.entities.LoanValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.db.entities.SubBucketEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

class SnapshotAnalyticsTest {

    private val userId = UUID.randomUUID()
    private val seedInstant = Instant.parse("2025-01-01T00:00:00Z")

    // Three asset classes (Market Linked, Fixed Return, Crypto) — matches v3 spec.
    private val acMarketLinked = AssetClassEntity(
        id = UUID.randomUUID(), name = "Market Linked", displayOrder = 1,
        isSeeded = true, createdAt = seedInstant,
    )
    private val acFixedReturn = AssetClassEntity(
        id = UUID.randomUUID(), name = "Fixed Return", displayOrder = 2,
        isSeeded = true, createdAt = seedInstant,
    )
    private val acCrypto = AssetClassEntity(
        id = UUID.randomUUID(), name = "Crypto", displayOrder = 3,
        isSeeded = true, createdAt = seedInstant,
    )
    private val assetClasses = listOf(acMarketLinked, acFixedReturn, acCrypto)

    // Sub-buckets covering the holdings used in tests below.
    private val sbMf = sb("MF", acMarketLinked.id, 1)
    private val sbStocks = sb("Stocks", acMarketLinked.id, 3)
    private val sbPpf = sb("PPF", acFixedReturn.id, 2)
    private val sbBank = sb("Bank", acFixedReturn.id, 5)
    private val sbCrypto = sb("Crypto", acCrypto.id, 1)
    private val subBuckets = listOf(sbMf, sbStocks, sbPpf, sbBank, sbCrypto)

    private val degree = HoldingEntity(
        id = UUID.randomUUID(), name = "Degree212", subBucketId = sbMf.id,
        trackInvested = true, trackSip = true,
    )
    private val ppf = HoldingEntity(
        id = UUID.randomUUID(), name = "PPF", subBucketId = sbPpf.id,
        trackInvested = false, trackSip = true,
    )
    private val hdfc = HoldingEntity(
        id = UUID.randomUUID(), name = "HDFC", subBucketId = sbBank.id,
        trackInvested = false, trackSip = false,
    )
    private val coindcx = HoldingEntity(
        id = UUID.randomUUID(), name = "CoinDCX", subBucketId = sbCrypto.id,
        trackInvested = true, trackSip = false,
    )
    private val stocks = HoldingEntity(
        id = UUID.randomUUID(), name = "Stocks (Direct)", subBucketId = sbStocks.id,
        trackInvested = true, trackSip = false,
    )
    private val catalog = listOf(degree, ppf, hdfc, coindcx, stocks)

    /** v3 default aim: Market Linked 70 / Fixed Return 25 / Crypto 5. */
    private val defaultAim = listOf(
        AimAllocationEntity(userId, acMarketLinked.id, 70),
        AimAllocationEntity(userId, acFixedReturn.id, 25),
        AimAllocationEntity(userId, acCrypto.id, 5),
    )

    private fun sb(name: String, acId: UUID, order: Int) = SubBucketEntity(
        id = UUID.randomUUID(), assetClassId = acId, name = name,
        displayOrder = order, isSeeded = true, isActive = true, createdAt = seedInstant,
    )

    private fun snapshot(date: String = "2025-01-01", earnings: String = "1.00") = SnapshotEntity(
        id = UUID.randomUUID(),
        userId = userId,
        snapshotDate = LocalDate.parse(date),
        earningsInCr = BigDecimal(earnings),
        notes = null,
        createdAt = seedInstant,
        updatedAt = seedInstant,
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

    private fun compute(
        snapshot: SnapshotEntity,
        values: List<HoldingValueEntity>,
        loans: List<LoanEntity> = emptyList(),
        loanValues: List<LoanValueEntity> = emptyList(),
        previousNetWorth: BigDecimal? = null,
    ) = SnapshotAnalyticsCalculator.compute(
        snapshot = snapshot,
        values = values,
        catalog = catalog,
        subBuckets = subBuckets,
        assetClasses = assetClasses,
        aimAllocations = defaultAim,
        loans = loans,
        loanValues = loanValues,
        previousNetWorth = previousNetWorth,
    )

    @Test
    @DisplayName("Empty value set yields zero totals and aim-class-magnitude drift")
    fun emptyValues() {
        val s = snapshot()
        val a = compute(s, emptyList())

        assertThat(a.totalAssets).isEqualToIgnoringScale(BigDecimal.ZERO)
        assertThat(a.totalLiabilities).isEqualToIgnoringScale(BigDecimal.ZERO)
        assertThat(a.netWorth).isEqualToIgnoringScale(BigDecimal.ZERO)
        assertThat(a.totalInvested).isEqualToIgnoringScale(BigDecimal.ZERO)
        assertThat(a.percentOfEarnings).isEqualToIgnoringScale(BigDecimal.ZERO)
        assertThat(a.deltaAbsolute).isNull()

        // No portfolio yet → 0% of every class → drift = -aim%
        assertThat(a.byAssetClass.getValue(acMarketLinked.id).driftPct)
            .isEqualToIgnoringScale(BigDecimal("-70"))
        assertThat(a.byAssetClass.getValue(acFixedReturn.id).driftPct)
            .isEqualToIgnoringScale(BigDecimal("-25"))
        assertThat(a.byAssetClass.getValue(acCrypto.id).driftPct)
            .isEqualToIgnoringScale(BigDecimal("-5"))
    }

    @Test
    @DisplayName("Investment value = totalAssets minus Fixed Return total")
    fun investmentValue() {
        val s = snapshot()
        val a = compute(
            s,
            values = listOf(
                value(s.id, degree, invested = "100000", current = "120000", sip = "5000"),
                value(s.id, stocks, invested = "50000", current = "60000"),
                value(s.id, coindcx, invested = "10000", current = "11000"),
                value(s.id, hdfc, current = "9000"),
            ),
        )
        // 120k + 60k + 11k + 9k = 200k assets; FR = 9k; Investment = 191k
        assertThat(a.totalAssets).isEqualToIgnoringScale(BigDecimal("200000"))
        assertThat(a.investmentValue).isEqualToIgnoringScale(BigDecimal("191000"))
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
    @DisplayName("Net worth subtracts liabilities from assets")
    fun netWorthMath() {
        val s = snapshot(earnings = "4.00")
        val homeLoan = LoanEntity(
            id = UUID.randomUUID(), userId = userId, name = "Home",
            originalAmount = BigDecimal("6000000"),
            takenDate = LocalDate.parse("2022-01-01"),
            monthlyEmi = BigDecimal("50000"),
            isActive = true, closedDate = null,
            createdAt = seedInstant, updatedAt = seedInstant,
        )
        val a = compute(
            s,
            values = listOf(value(s.id, stocks, invested = "1000000", current = "1100000")),
            loans = listOf(homeLoan),
            loanValues = listOf(LoanValueEntity(
                id = UUID.randomUUID(), snapshotId = s.id, loanId = homeLoan.id,
                outstanding = BigDecimal("5000000"),
            )),
        )
        assertThat(a.totalAssets).isEqualToIgnoringScale(BigDecimal("1100000"))
        assertThat(a.totalLiabilities).isEqualToIgnoringScale(BigDecimal("5000000"))
        assertThat(a.netWorth).isEqualToIgnoringScale(BigDecimal("-3900000"))
    }

    @Test
    @DisplayName("Δ vs previous compares net worth, not asset totals")
    fun deltaUsesNetWorth() {
        val s = snapshot()
        val a = compute(
            s,
            values = listOf(value(s.id, stocks, invested = "1000000", current = "1100000")),
            previousNetWorth = BigDecimal("1000000"),
        )
        val absDelta = requireNotNull(a.deltaAbsolute)
        val pctDelta = requireNotNull(a.deltaPercent)
        assertThat(absDelta).isEqualToIgnoringScale(BigDecimal("100000"))
        assertThat(pctDelta).isEqualToIgnoringScale(BigDecimal("10"))
    }

    @Test
    @DisplayName("Δ vs previous is null on the first snapshot")
    fun deltaNullForFirstSnapshot() {
        val s = snapshot()
        val a = compute(s, listOf(value(s.id, stocks, invested = "1000", current = "1000")))
        assertThat(a.deltaAbsolute).isNull()
        assertThat(a.deltaPercent).isNull()
    }

    @Test
    @DisplayName("% of earnings = (netWorth ÷ (earningsInCr × 1Cr)) × 100")
    fun percentOfEarnings() {
        val s = snapshot(earnings = "4.00")
        val a = compute(
            s,
            values = listOf(value(s.id, stocks, invested = "0", current = "8530000")),
        )
        assertThat(a.percentOfEarnings).isEqualToIgnoringScale(BigDecimal("21.33"))
    }

    @Test
    @DisplayName("HoldingDetailRow carries asset-class and sub-bucket names for display")
    fun rowsHaveTaxonomyMetadata() {
        val s = snapshot()
        val a = compute(
            s,
            values = listOf(value(s.id, hdfc, current = "100000")),
        )
        val row = a.rowsByAssetClass.getValue(acFixedReturn.id).single()
        assertThat(row.subBucketName).isEqualTo("Bank")
        assertThat(row.assetClassName).isEqualTo("Fixed Return")
    }
}
