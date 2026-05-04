package com.fintrack.domain.analytics

import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.LoanEntity
import com.fintrack.data.db.entities.LoanValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.db.entities.SubBucketEntity
import com.fintrack.data.db.seed.SeedData
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID

/**
 * Computed (never-persisted) view of a snapshot. v3 spec §3.4 — adds
 * liabilities/net-worth and switches the asset-class index from a Kotlin
 * enum to a DB-driven UUID.
 *
 * Money keeps full BigDecimal precision; percentages use scale 2 with
 * HALF_UP rounding for stable display.
 */
data class SnapshotAnalytics(
    val snapshotId: UUID,
    val date: LocalDate,
    val earningsInCr: BigDecimal,
    val notes: String?,

    // Asset-side totals (HoldingValue.current sums).
    val totalAssets: BigDecimal,
    val totalInvested: BigDecimal,
    val totalSip: BigDecimal,
    val investmentValue: BigDecimal,            // totalAssets minus FIXED_RETURN total

    // Liability + net worth.
    val totalLiabilities: BigDecimal,            // sum of LoanValue.outstanding for this snapshot
    val netWorth: BigDecimal,                    // totalAssets − totalLiabilities

    // Earnings ratio + Δ vs previous (compared on net worth).
    val percentOfEarnings: BigDecimal,
    val deltaAbsolute: BigDecimal?,              // netWorth − previousNetWorth
    val deltaPercent: BigDecimal?,

    // Allocation breakdown.
    val byAssetClass: Map<UUID, AssetClassSummary>,                // keyed by AssetClassEntity.id
    val rowsByAssetClass: Map<UUID, List<HoldingDetailRow>>,        // keyed by AssetClassEntity.id
    val rowsBySubBucket: Map<UUID, List<HoldingDetailRow>>,         // keyed by SubBucketEntity.id

    // Loans.
    val loanRows: List<LoanDetailRow>,
)

data class AssetClassSummary(
    val assetClassId: UUID,
    val assetClassName: String,
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
    val subBucketId: UUID,
    val subBucketName: String,
    val assetClassId: UUID,
    val assetClassName: String,
    val invested: BigDecimal?,
    val current: BigDecimal,
    val sip: BigDecimal?,
)

data class LoanDetailRow(
    val loanId: UUID,
    val name: String,
    val originalAmount: BigDecimal,
    val takenDate: LocalDate,
    val monthlyEmi: BigDecimal,
    val outstanding: BigDecimal,
)

object SnapshotAnalyticsCalculator {

    private val CRORE = BigDecimal("10000000")
    private val HUNDRED = BigDecimal("100")
    private const val PERCENT_SCALE = 2
    private const val INTERNAL_SCALE = 6

    /** Convenience overload taking only the [SeedData.FIXED_RETURN_ID] as the FR class. */
    fun compute(
        snapshot: SnapshotEntity,
        values: List<HoldingValueEntity>,
        catalog: List<HoldingEntity>,
        subBuckets: List<SubBucketEntity>,
        assetClasses: List<AssetClassEntity>,
        aimAllocations: List<AimAllocationEntity>,
        loans: List<LoanEntity>,
        loanValues: List<LoanValueEntity>,
        previousNetWorth: BigDecimal?,
    ): SnapshotAnalytics {
        val subBucketById = subBuckets.associateBy { it.id }
        val assetClassById = assetClasses.associateBy { it.id }
        val holdingById: Map<UUID, HoldingEntity> = catalog.associateBy { it.id }

        val rows: List<HoldingDetailRow> = values.mapNotNull { v ->
            val h = holdingById[v.holdingId] ?: return@mapNotNull null
            val sb = subBucketById[h.subBucketId] ?: return@mapNotNull null
            val ac = assetClassById[sb.assetClassId] ?: return@mapNotNull null
            HoldingDetailRow(
                holdingId = h.id,
                name = h.name,
                subBucketId = sb.id,
                subBucketName = sb.name,
                assetClassId = ac.id,
                assetClassName = ac.name,
                invested = v.invested,
                current = v.current,
                sip = v.sip,
            )
        }

        val totalAssets = rows.fold(BigDecimal.ZERO) { acc, r -> acc + r.current }
        val totalInvested = rows.fold(BigDecimal.ZERO) { acc, r -> acc + (r.invested ?: BigDecimal.ZERO) }
        val totalSip = rows.fold(BigDecimal.ZERO) { acc, r -> acc + (r.sip ?: BigDecimal.ZERO) }

        val rowsByAssetClass: Map<UUID, List<HoldingDetailRow>> =
            assetClasses.associate { it.id to rows.filter { r -> r.assetClassId == it.id } }
        val rowsBySubBucket: Map<UUID, List<HoldingDetailRow>> =
            subBuckets.associate { it.id to rows.filter { r -> r.subBucketId == it.id } }

        val aimByClass: Map<UUID, Int> = aimAllocations.associate { it.assetClassId to it.aimPercent }

        val byAssetClass: Map<UUID, AssetClassSummary> = assetClasses.associate { ac ->
            val classRows = rowsByAssetClass.getValue(ac.id)
            val classCurrent = classRows.fold(BigDecimal.ZERO) { acc, r -> acc + r.current }
            val classInvested = classRows.fold(BigDecimal.ZERO) { acc, r -> acc + (r.invested ?: BigDecimal.ZERO) }
            val classSip = classRows.fold(BigDecimal.ZERO) { acc, r -> acc + (r.sip ?: BigDecimal.ZERO) }
            val currentPct = if (totalAssets.signum() == 0) {
                BigDecimal.ZERO.setScale(PERCENT_SCALE)
            } else {
                classCurrent.divide(totalAssets, INTERNAL_SCALE, RoundingMode.HALF_UP)
                    .multiply(HUNDRED)
                    .setScale(PERCENT_SCALE, RoundingMode.HALF_UP)
            }
            val aim = aimByClass[ac.id] ?: 0
            val drift = currentPct.subtract(BigDecimal(aim))
                .setScale(PERCENT_SCALE, RoundingMode.HALF_UP)
            ac.id to AssetClassSummary(
                assetClassId = ac.id,
                assetClassName = ac.name,
                invested = classInvested,
                current = classCurrent,
                sip = classSip,
                currentPct = currentPct,
                aimPct = aim,
                driftPct = drift,
            )
        }

        // Investment value = total assets minus the Fixed Return class total.
        // The seeded Fixed Return class has a stable id; if the user has deactivated
        // or replaced it, this falls back to "all classes whose name starts with 'Fixed'"
        // which is good enough for analytics until the user reorganises explicitly.
        val fixedReturnTotal = byAssetClass[SeedData.FIXED_RETURN_ID]?.current
            ?: assetClasses.firstOrNull { it.name.startsWith("Fixed", ignoreCase = true) }
                ?.let { byAssetClass[it.id]?.current }
            ?: BigDecimal.ZERO
        val investmentValue = totalAssets.subtract(fixedReturnTotal)

        // Liabilities + net worth.
        val loansById = loans.associateBy { it.id }
        val totalLiabilities = loanValues.fold(BigDecimal.ZERO) { acc, lv -> acc + lv.outstanding }
        val netWorth = totalAssets.subtract(totalLiabilities)

        val percentOfEarnings = if (snapshot.earningsInCr.signum() == 0) {
            BigDecimal.ZERO.setScale(PERCENT_SCALE)
        } else {
            netWorth
                .divide(snapshot.earningsInCr.multiply(CRORE), INTERNAL_SCALE, RoundingMode.HALF_UP)
                .multiply(HUNDRED)
                .setScale(PERCENT_SCALE, RoundingMode.HALF_UP)
        }

        val deltaAbsolute = previousNetWorth?.let { netWorth.subtract(it) }
        val deltaPercent = previousNetWorth?.takeIf { it.signum() != 0 }?.let { prev ->
            netWorth.subtract(prev)
                .divide(prev, INTERNAL_SCALE, RoundingMode.HALF_UP)
                .multiply(HUNDRED)
                .setScale(PERCENT_SCALE, RoundingMode.HALF_UP)
        }

        val loanRows = loanValues.mapNotNull { lv ->
            val l = loansById[lv.loanId] ?: return@mapNotNull null
            LoanDetailRow(
                loanId = l.id,
                name = l.name,
                originalAmount = l.originalAmount,
                takenDate = l.takenDate,
                monthlyEmi = l.monthlyEmi,
                outstanding = lv.outstanding,
            )
        }.sortedBy { it.name }

        return SnapshotAnalytics(
            snapshotId = snapshot.id,
            date = snapshot.snapshotDate,
            earningsInCr = snapshot.earningsInCr,
            notes = snapshot.notes,
            totalAssets = totalAssets,
            totalInvested = totalInvested,
            totalSip = totalSip,
            investmentValue = investmentValue,
            totalLiabilities = totalLiabilities,
            netWorth = netWorth,
            percentOfEarnings = percentOfEarnings,
            deltaAbsolute = deltaAbsolute,
            deltaPercent = deltaPercent,
            byAssetClass = byAssetClass,
            rowsByAssetClass = rowsByAssetClass,
            rowsBySubBucket = rowsBySubBucket,
            loanRows = loanRows,
        )
    }
}
