package com.fintrack.domain.milestones

import com.fintrack.domain.model.MilestoneType
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import java.math.BigDecimal
import java.util.UUID

/**
 * Pure milestone detection per spec §6.3. Caller passes the snapshot
 * history (chronological) plus the prior set of awarded milestones.
 * Returns the new awards that should be persisted.
 *
 * Detection is idempotent — re-running on the same input returns no new
 * awards once the corresponding milestones already exist.
 *
 * BACK_FROM_BREAK is the only repeatable type; it can fire multiple
 * times per user, anchored to the snapshot that ended the break.
 */
data class SnapshotPoint(
    val snapshotId: UUID,
    val date: LocalDate,
    val netWorth: BigDecimal,
    val totalLiabilities: BigDecimal,
)

data class MilestoneAward(
    val type: MilestoneType,
    val snapshotId: UUID,
    val date: LocalDate,
    val amount: BigDecimal? = null,
)

object MilestoneDetector {

    /**
     * @param history snapshots in chronological order (earliest first).
     * @param existing prior awards by type — used to enforce "once" for
     *   non-repeatable types and to count BACK_FROM_BREAK occurrences.
     */
    fun detect(
        history: List<SnapshotPoint>,
        existing: List<AwardedMilestone>,
    ): List<MilestoneAward> {
        if (history.isEmpty()) return emptyList()
        val out = mutableListOf<MilestoneAward>()
        val onceTaken = existing.filter { !it.type.isRepeatable }.map { it.type }.toMutableSet()
        val repeatableSnapshotIds = existing.filter { it.type.isRepeatable }
            .map { it.snapshotId }
            .toMutableSet()

        // FIRST_SNAPSHOT — earliest snapshot.
        if (MilestoneType.FIRST_SNAPSHOT !in onceTaken) {
            val s = history.first()
            out += MilestoneAward(MilestoneType.FIRST_SNAPSHOT, s.snapshotId, s.date)
            onceTaken += MilestoneType.FIRST_SNAPSHOT
        }

        // Net-worth thresholds (in rupees) — first snapshot to cross each line.
        val netWorthBands = listOf(
            MilestoneType.NET_WORTH_50L to BigDecimal("5000000"),
            MilestoneType.NET_WORTH_1CR to BigDecimal("10000000"),
            MilestoneType.NET_WORTH_2CR to BigDecimal("20000000"),
            MilestoneType.NET_WORTH_5CR to BigDecimal("50000000"),
            MilestoneType.NET_WORTH_10CR to BigDecimal("100000000"),
        )
        for ((type, threshold) in netWorthBands) {
            if (type in onceTaken) continue
            val crossing = history.firstOrNull { it.netWorth >= threshold } ?: continue
            out += MilestoneAward(type, crossing.snapshotId, crossing.date, crossing.netWorth)
            onceTaken += type
        }

        // Months tracked (calendar-month distinct).
        val months = history.map { YearMonthLite(it.date.year, it.date.monthNumber) }.toSortedSet()
        val monthsList = months.toList()
        if (MilestoneType.TWELVE_MONTHS_TRACKED !in onceTaken && monthsList.size >= 12) {
            // Anchor to the first snapshot in the 12th distinct month.
            val anchorMonth = monthsList[11]
            val anchor = history.first {
                YearMonthLite(it.date.year, it.date.monthNumber) == anchorMonth
            }
            out += MilestoneAward(MilestoneType.TWELVE_MONTHS_TRACKED, anchor.snapshotId, anchor.date)
            onceTaken += MilestoneType.TWELVE_MONTHS_TRACKED
        }
        if (MilestoneType.THIRTY_SIX_MONTHS_TRACKED !in onceTaken && monthsList.size >= 36) {
            val anchorMonth = monthsList[35]
            val anchor = history.first {
                YearMonthLite(it.date.year, it.date.monthNumber) == anchorMonth
            }
            out += MilestoneAward(MilestoneType.THIRTY_SIX_MONTHS_TRACKED, anchor.snapshotId, anchor.date)
            onceTaken += MilestoneType.THIRTY_SIX_MONTHS_TRACKED
        }

        // Doubled / tripled vs the earliest snapshot's positive net worth.
        val baseline = history.first().netWorth
        if (baseline.signum() > 0) {
            val multiples = listOf(
                MilestoneType.DOUBLED_NET_WORTH to BigDecimal("2"),
                MilestoneType.TRIPLED_NET_WORTH to BigDecimal("3"),
            )
            for ((type, mult) in multiples) {
                if (type in onceTaken) continue
                val target = baseline.multiply(mult)
                val crossing = history.drop(1).firstOrNull { it.netWorth >= target } ?: continue
                out += MilestoneAward(type, crossing.snapshotId, crossing.date, crossing.netWorth)
                onceTaken += type
            }
        }

        // YoY 50%: any snapshot whose net worth ≥ 1.5× a snapshot from ~12
        // months earlier (within ±31 days). Earliest-firing wins.
        if (MilestoneType.YOY_GROWTH_50_PCT !in onceTaken) {
            for (i in history.indices) {
                val current = history[i]
                val target = current.date.minus(DatePeriod(years = 1))
                val anchor = history.subList(0, i).firstOrNull { prev ->
                    val diff = kotlin.math.abs(prev.date.toEpochDays() - target.toEpochDays())
                    diff <= 31 && prev.netWorth.signum() > 0
                }
                if (anchor != null && current.netWorth >= anchor.netWorth.multiply(BigDecimal("1.5"))) {
                    out += MilestoneAward(
                        MilestoneType.YOY_GROWTH_50_PCT, current.snapshotId, current.date, current.netWorth,
                    )
                    onceTaken += MilestoneType.YOY_GROWTH_50_PCT
                    break
                }
            }
        }

        // DEBT_FREE: first snapshot with 0 liabilities AND a prior snapshot
        // had positive liabilities. Stops repeated firings if liabilities
        // re-appear and disappear by enforcing once-only.
        if (MilestoneType.DEBT_FREE !in onceTaken) {
            var sawDebt = false
            for (s in history) {
                if (s.totalLiabilities.signum() > 0) sawDebt = true
                else if (sawDebt && s.totalLiabilities.signum() == 0) {
                    out += MilestoneAward(MilestoneType.DEBT_FREE, s.snapshotId, s.date)
                    onceTaken += MilestoneType.DEBT_FREE
                    break
                }
            }
        }

        // BACK_FROM_BREAK: a snapshot that follows a gap of ≥ 2 calendar
        // months from the previous snapshot. Each qualifying snapshot
        // earns a separate award (subject to existing-set dedup).
        for (i in 1 until history.size) {
            val prev = history[i - 1]
            val cur = history[i]
            if (cur.snapshotId in repeatableSnapshotIds) continue
            val prevMonth = YearMonthLite(prev.date.year, prev.date.monthNumber)
            val curMonth = YearMonthLite(cur.date.year, cur.date.monthNumber)
            val monthsGap = (curMonth.year - prevMonth.year) * 12 + (curMonth.month - prevMonth.month)
            if (monthsGap >= 2) {
                out += MilestoneAward(
                    MilestoneType.BACK_FROM_BREAK, cur.snapshotId, cur.date,
                )
                repeatableSnapshotIds += cur.snapshotId
            }
        }

        return out
    }
}

/** A previously-awarded milestone. Snapshot id matters for repeatables. */
data class AwardedMilestone(val type: MilestoneType, val snapshotId: UUID)

private data class YearMonthLite(val year: Int, val month: Int) : Comparable<YearMonthLite> {
    override fun compareTo(other: YearMonthLite): Int =
        if (year != other.year) year.compareTo(other.year) else month.compareTo(other.month)
}
