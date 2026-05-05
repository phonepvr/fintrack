package com.fintrack.domain.milestones

import com.fintrack.domain.model.MilestoneType
import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

class MilestoneDetectorTest {

    private fun sp(date: String, netWorth: String, liabilities: String = "0") = SnapshotPoint(
        snapshotId = UUID.randomUUID(),
        date = LocalDate.parse(date),
        netWorth = BigDecimal(netWorth),
        totalLiabilities = BigDecimal(liabilities),
    )

    @Test
    @DisplayName("Empty history yields no awards")
    fun empty() {
        assertThat(MilestoneDetector.detect(emptyList(), emptyList())).isEmpty()
    }

    @Test
    @DisplayName("First snapshot triggers FIRST_SNAPSHOT")
    fun firstSnapshot() {
        val out = MilestoneDetector.detect(
            history = listOf(sp("2025-01-15", "100000")),
            existing = emptyList(),
        )
        val types = out.map { it.type }
        assertThat(types).contains(MilestoneType.FIRST_SNAPSHOT)
    }

    @Test
    @DisplayName("Net worth thresholds award the first crossing snapshot only")
    fun netWorthBands() {
        val crossOne = sp("2025-02-15", "5500000")     // crosses 50L
        val crossTwo = sp("2025-03-15", "12000000")    // crosses 1Cr
        val out = MilestoneDetector.detect(
            history = listOf(sp("2025-01-15", "1000000"), crossOne, crossTwo),
            existing = emptyList(),
        )
        val byType = out.groupBy { it.type }
        assertThat(byType[MilestoneType.NET_WORTH_50L]?.single()?.snapshotId)
            .isEqualTo(crossOne.snapshotId)
        assertThat(byType[MilestoneType.NET_WORTH_1CR]?.single()?.snapshotId)
            .isEqualTo(crossTwo.snapshotId)
    }

    @Test
    @DisplayName("Detection is idempotent for non-repeatable types")
    fun idempotentNonRepeatable() {
        val first = sp("2025-01-15", "100000")
        val firstRun = MilestoneDetector.detect(listOf(first), emptyList())
        val existing = firstRun.map { AwardedMilestone(it.type, it.snapshotId) }
        val secondRun = MilestoneDetector.detect(listOf(first), existing)
        // No re-issue of FIRST_SNAPSHOT.
        assertThat(secondRun.map { it.type }).doesNotContain(MilestoneType.FIRST_SNAPSHOT)
    }

    @Test
    @DisplayName("Doubled / tripled fire against the earliest baseline")
    fun doublingTripling() {
        val s0 = sp("2025-01-01", "1000000")
        val s1 = sp("2025-06-01", "1500000")
        val s2 = sp("2025-12-01", "2200000")  // crosses 2× baseline
        val s3 = sp("2026-06-01", "3500000")  // crosses 3× baseline
        val out = MilestoneDetector.detect(listOf(s0, s1, s2, s3), emptyList())
        val byType = out.groupBy { it.type }
        assertThat(byType[MilestoneType.DOUBLED_NET_WORTH]?.single()?.snapshotId)
            .isEqualTo(s2.snapshotId)
        assertThat(byType[MilestoneType.TRIPLED_NET_WORTH]?.single()?.snapshotId)
            .isEqualTo(s3.snapshotId)
    }

    @Test
    @DisplayName("Twelve months tracked anchors at the 12th distinct month")
    fun twelveMonthsTracked() {
        val months = (1..12).map { m ->
            sp("%04d-%02d-01".format(2025, m), "1000000")
        }
        val out = MilestoneDetector.detect(months, emptyList())
        val award = out.firstOrNull { it.type == MilestoneType.TWELVE_MONTHS_TRACKED }
        assertThat(award).isNotNull()
        assertThat(award!!.date).isEqualTo(LocalDate.parse("2025-12-01"))
    }

    @Test
    @DisplayName("DEBT_FREE awards once liabilities transition from positive to zero")
    fun debtFree() {
        val s0 = sp("2025-01-01", "1000000", liabilities = "200000")
        val s1 = sp("2025-06-01", "1500000", liabilities = "100000")
        val s2 = sp("2025-12-01", "2000000", liabilities = "0")
        val out = MilestoneDetector.detect(listOf(s0, s1, s2), emptyList())
        val award = out.first { it.type == MilestoneType.DEBT_FREE }
        assertThat(award.snapshotId).isEqualTo(s2.snapshotId)
    }

    @Test
    @DisplayName("DEBT_FREE does not fire if there was never any debt")
    fun debtFreeRequiresPriorDebt() {
        val out = MilestoneDetector.detect(
            history = listOf(
                sp("2025-01-01", "1000000"),
                sp("2025-06-01", "1500000"),
            ),
            existing = emptyList(),
        )
        assertThat(out.map { it.type }).doesNotContain(MilestoneType.DEBT_FREE)
    }

    @Test
    @DisplayName("YoY 50% fires when a snapshot is ≥ 1.5× a snapshot ~12 months earlier")
    fun yoyGrowth() {
        val s0 = sp("2025-01-15", "1000000")
        val s1 = sp("2026-01-12", "1600000") // +60% in ~12 months
        val out = MilestoneDetector.detect(listOf(s0, s1), emptyList())
        assertThat(out.firstOrNull { it.type == MilestoneType.YOY_GROWTH_50_PCT }?.snapshotId)
            .isEqualTo(s1.snapshotId)
    }

    @Test
    @DisplayName("BACK_FROM_BREAK is repeatable — fires after every ≥ 2-month gap")
    fun backFromBreakRepeats() {
        val s0 = sp("2025-01-15", "100000")
        val s1 = sp("2025-04-15", "120000")  // 3-month gap
        val s2 = sp("2025-05-12", "130000")  // continuous
        val s3 = sp("2025-09-10", "140000")  // 4-month gap
        val out = MilestoneDetector.detect(listOf(s0, s1, s2, s3), emptyList())
        val ids = out.filter { it.type == MilestoneType.BACK_FROM_BREAK }.map { it.snapshotId }
        assertThat(ids).containsExactly(s1.snapshotId, s3.snapshotId)
    }

    @Test
    @DisplayName("BACK_FROM_BREAK respects existing awards (no double-issue per snapshot)")
    fun backFromBreakDedup() {
        val s0 = sp("2025-01-15", "100000")
        val s1 = sp("2025-04-15", "120000")  // 3-month gap
        val existing = listOf(AwardedMilestone(MilestoneType.BACK_FROM_BREAK, s1.snapshotId))
        val out = MilestoneDetector.detect(listOf(s0, s1), existing)
        assertThat(out.filter { it.type == MilestoneType.BACK_FROM_BREAK }).isEmpty()
    }
}
