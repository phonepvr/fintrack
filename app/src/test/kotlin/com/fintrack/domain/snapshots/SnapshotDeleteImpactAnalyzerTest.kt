package com.fintrack.domain.snapshots

import com.fintrack.data.db.entities.SnapshotEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.util.UUID

class SnapshotDeleteImpactAnalyzerTest {

    private val userId = UUID.fromString("00000000-0000-0000-0000-000000000001")

    private fun snap(
        id: UUID = UUID.randomUUID(),
        date: String,
        createdAt: String = "2026-01-01T00:00:00Z",
    ): SnapshotEntity = SnapshotEntity(
        id = id,
        userId = userId,
        snapshotDate = LocalDate.parse(date),
        earningsInCr = BigDecimal.ZERO,
        notes = null,
        createdAt = Instant.parse(createdAt),
        updatedAt = Instant.parse(createdAt),
    )

    @Test
    @DisplayName("Latest snapshot in a single-snapshot month with active streak flags both warnings")
    fun latestAndBreaksStreak() {
        val target = snap(date = "2026-05-02")
        val all = listOf(
            snap(date = "2026-03-15"),
            snap(date = "2026-04-10"),
            target,
        )

        val out = SnapshotDeleteImpactAnalyzer.analyze(target.id, all, currentStreakMonths = 3)

        assertThat(out.isLatest).isTrue()
        assertThat(out.breaksActiveStreak).isTrue()
        assertThat(out.streakMonths).isEqualTo(3)
    }

    @Test
    @DisplayName("Middle snapshot with another in the same month flags neither warning")
    fun middleAndStreakSurvives() {
        val keep = snap(date = "2026-04-25")
        val target = snap(date = "2026-04-10")
        val all = listOf(
            snap(date = "2026-03-15"),
            target,
            keep,
            snap(date = "2026-05-02"),
        )

        val out = SnapshotDeleteImpactAnalyzer.analyze(target.id, all, currentStreakMonths = 3)

        assertThat(out.isLatest).isFalse()
        assertThat(out.breaksActiveStreak).isFalse()
        assertThat(out.streakMonths).isEqualTo(0)
    }

    @Test
    @DisplayName("No active streak suppresses the streak warning even when target is sole occupant of month")
    fun zeroStreakSuppressesStreakWarning() {
        val target = snap(date = "2026-05-02")
        val all = listOf(snap(date = "2026-01-15"), target)

        val out = SnapshotDeleteImpactAnalyzer.analyze(target.id, all, currentStreakMonths = 0)

        assertThat(out.isLatest).isTrue()
        assertThat(out.breaksActiveStreak).isFalse()
        assertThat(out.streakMonths).isEqualTo(0)
    }

    @Test
    @DisplayName("Latest determined by createdAt when two snapshots share the latest date")
    fun latestTieBreaksOnCreatedAt() {
        val older = snap(date = "2026-05-02", createdAt = "2026-05-02T08:00:00Z")
        val newer = snap(date = "2026-05-02", createdAt = "2026-05-02T09:00:00Z")
        val all = listOf(snap(date = "2026-04-10"), older, newer)

        val outNewer = SnapshotDeleteImpactAnalyzer.analyze(newer.id, all, currentStreakMonths = 2)
        val outOlder = SnapshotDeleteImpactAnalyzer.analyze(older.id, all, currentStreakMonths = 2)

        assertThat(outNewer.isLatest).isTrue()
        assertThat(outOlder.isLatest).isFalse()
        // Two snapshots share May, so deleting either one leaves the month covered.
        assertThat(outNewer.breaksActiveStreak).isFalse()
        assertThat(outOlder.breaksActiveStreak).isFalse()
    }

    @Test
    @DisplayName("Unknown snapshot id returns the empty default impact")
    fun unknownIdReturnsDefault() {
        val all = listOf(snap(date = "2026-04-10"), snap(date = "2026-05-02"))

        val out = SnapshotDeleteImpactAnalyzer.analyze(
            targetSnapshotId = UUID.randomUUID(),
            allSnapshots = all,
            currentStreakMonths = 5,
        )

        assertThat(out).isEqualTo(SnapshotDeleteImpact())
    }

    @Test
    @DisplayName("Earliest snapshot is not flagged as latest, and streak warning fires only when its month is unique")
    fun earliestSnapshot() {
        val earliest = snap(date = "2026-03-01")
        val all = listOf(
            earliest,
            snap(date = "2026-04-10"),
            snap(date = "2026-05-02"),
        )

        val out = SnapshotDeleteImpactAnalyzer.analyze(earliest.id, all, currentStreakMonths = 3)

        assertThat(out.isLatest).isFalse()
        assertThat(out.breaksActiveStreak).isTrue()
        assertThat(out.streakMonths).isEqualTo(3)
    }
}
