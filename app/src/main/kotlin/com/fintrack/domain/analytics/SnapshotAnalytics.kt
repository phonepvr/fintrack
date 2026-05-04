package com.fintrack.domain.analytics

import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.db.entities.UserSettingsEntity
import com.fintrack.domain.model.AssetClass
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID

/**
 * Computed (never-persisted) view of a snapshot. Spec §3.4.
 *
 * Money keeps full BigDecimal precision; percentages use scale 2 with HALF_UP
 * rounding for stable display.
 */
data class SnapshotAnalytics(
    val snapshotId: UUID,
    val date: LocalDate,
    val earningsInCr: BigDecimal,
    val notes: String?,
    val totalPortfolio: BigDecimal,
    val totalInvested: BigDecimal,
    val totalSip: BigDecimal,
    val savingsRollup: BigDecimal,
    val investmentValue: BigDecimal,
    val percentOfEarnings: BigDecimal,
    val deltaAbsolute: BigDecimal?,
    val deltaPercent: BigDecimal?,
    val byAssetClass: Map<AssetClass, AssetClassSummary>,
    val rowsByAssetClass: Map<AssetClass, List<HoldingDetailRow>>,
)

data class AssetClassSummary(
    val assetClass: AssetClass,
    val invested: BigDecimal,
    val current: BigDecimal,
    val sip: BigDecimal,
    val currentPct: BigDecimal,
    val aimPct: Int,
    val driftPct: BigDecimal,
) {
    val driftBand: DriftBand
        get() = DriftBand.forDrift(driftPct)
}

enum class DriftBand { WITHIN, WARN, OFF;

    companion object {
        fun forDrift(drift: BigDecimal): DriftBand {
            val abs = drift.abs()
            return when {
                abs <= TWO -> WITHIN
                abs <= FIVE -> WARN
                else -> OFF
            }
        }

        private val TWO = BigDecimal("2")
        private val FIVE = BigDecimal("5")
    }
}

data class HoldingDetailRow(
    val holdingId: UUID,
    val name: String,
    val assetClass: AssetClass,
    val isBank: Boolean,
    val invested: BigDecimal?,
    val current: BigDecimal,
    val sip: BigDecimal?,
)

object SnapshotAnalyticsCalculator {

    private val CRORE = BigDecimal("10000000")
    private val HUNDRED = BigDecimal("100")
    private const val PERCENT_SCALE = 2
    private const val INTERNAL_SCALE = 6

    /**
     * @param previousTotalPortfolio total of the chronologically prior snapshot for the same
     *        user (or null when this is the first one). Used to compute Δ.
     */
    fun compute(
        snapshot: SnapshotEntity,
        values: List<HoldingValueEntity>,
        catalog: List<HoldingEntity>,
        settings: UserSettingsEntity,
        previousTotalPortfolio: BigDecimal?,
    ): SnapshotAnalytics {
        val holdingById: Map<UUID, HoldingEntity> = catalog.associateBy { it.id }
        val rows: List<HoldingDetailRow> = values.mapNotNull { v ->
            val h = holdingById[v.holdingId] ?: return@mapNotNull null
            HoldingDetailRow(
                holdingId = h.id,
                name = h.name,
                assetClass = h.assetClass,
                isBank = h.isBankAccount,
                invested = v.invested,
                current = v.current,
                sip = v.sip,
            )
        }

        val totalCurrent = rows.fold(BigDecimal.ZERO) { acc, r -> acc + r.current }
        val totalInvested = rows.fold(BigDecimal.ZERO) { acc, r -> acc + (r.invested ?: BigDecimal.ZERO) }
        val totalSip = rows.fold(BigDecimal.ZERO) { acc, r -> acc + (r.sip ?: BigDecimal.ZERO) }

        val rowsByClass: Map<AssetClass, List<HoldingDetailRow>> = AssetClass.entries.associateWith { ac ->
            rows.filter { it.assetClass == ac }
        }

        val aimMap: Map<AssetClass, Int> = mapOf(
            AssetClass.MF_NPS to settings.aimPctMfNps,
            AssetClass.EQUITY to settings.aimPctEquity,
            AssetClass.FIXED_RETURN to settings.aimPctFixedReturn,
            AssetClass.CRYPTO to settings.aimPctCrypto,
        )

        val byAssetClass: Map<AssetClass, AssetClassSummary> = AssetClass.entries.associateWith { ac ->
            val classRows = rowsByClass.getValue(ac)
            val classCurrent = classRows.fold(BigDecimal.ZERO) { acc, r -> acc + r.current }
            val classInvested = classRows.fold(BigDecimal.ZERO) { acc, r -> acc + (r.invested ?: BigDecimal.ZERO) }
            val classSip = classRows.fold(BigDecimal.ZERO) { acc, r -> acc + (r.sip ?: BigDecimal.ZERO) }
            val currentPct = if (totalCurrent.signum() == 0) {
                BigDecimal.ZERO.setScale(PERCENT_SCALE)
            } else {
                classCurrent.divide(totalCurrent, INTERNAL_SCALE, RoundingMode.HALF_UP)
                    .multiply(HUNDRED)
                    .setScale(PERCENT_SCALE, RoundingMode.HALF_UP)
            }
            val aim = aimMap.getValue(ac)
            val drift = currentPct.subtract(BigDecimal(aim))
                .setScale(PERCENT_SCALE, RoundingMode.HALF_UP)
            AssetClassSummary(
                assetClass = ac,
                invested = classInvested,
                current = classCurrent,
                sip = classSip,
                currentPct = currentPct,
                aimPct = aim,
                driftPct = drift,
            )
        }

        val savingsRollup = rows.filter { it.isBank }
            .fold(BigDecimal.ZERO) { acc, r -> acc + r.current }
        val investmentValue = byAssetClass.getValue(AssetClass.MF_NPS).current +
            byAssetClass.getValue(AssetClass.EQUITY).current +
            byAssetClass.getValue(AssetClass.CRYPTO).current

        val percentOfEarnings = if (snapshot.earningsInCr.signum() == 0) {
            BigDecimal.ZERO.setScale(PERCENT_SCALE)
        } else {
            totalCurrent
                .divide(snapshot.earningsInCr.multiply(CRORE), INTERNAL_SCALE, RoundingMode.HALF_UP)
                .multiply(HUNDRED)
                .setScale(PERCENT_SCALE, RoundingMode.HALF_UP)
        }

        val deltaAbsolute = previousTotalPortfolio?.let { totalCurrent.subtract(it) }
        val deltaPercent = previousTotalPortfolio?.takeIf { it.signum() != 0 }?.let { prev ->
            totalCurrent.subtract(prev)
                .divide(prev, INTERNAL_SCALE, RoundingMode.HALF_UP)
                .multiply(HUNDRED)
                .setScale(PERCENT_SCALE, RoundingMode.HALF_UP)
        }

        return SnapshotAnalytics(
            snapshotId = snapshot.id,
            date = snapshot.snapshotDate,
            earningsInCr = snapshot.earningsInCr,
            notes = snapshot.notes,
            totalPortfolio = totalCurrent,
            totalInvested = totalInvested,
            totalSip = totalSip,
            savingsRollup = savingsRollup,
            investmentValue = investmentValue,
            percentOfEarnings = percentOfEarnings,
            deltaAbsolute = deltaAbsolute,
            deltaPercent = deltaPercent,
            byAssetClass = byAssetClass,
            rowsByAssetClass = rowsByClass,
        )
    }
}
