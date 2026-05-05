package com.fintrack.data.xlsx

import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.math.BigDecimal

class XlsxCodecTest {

    @Test
    @DisplayName("Round-trip preserves snapshots, holding values, loans, loan values, and goals")
    fun roundTrip() {
        val original = XlsxWorkbookData(
            snapshots = listOf(
                XlsxSnapshotRow(LocalDate.parse("2026-01-15"), BigDecimal("4.00"), "first"),
                XlsxSnapshotRow(LocalDate.parse("2026-02-15"), BigDecimal("4.10"), null),
            ),
            holdingValues = listOf(
                XlsxHoldingValueRow(
                    snapshotDate = LocalDate.parse("2026-01-15"),
                    holdingName = "Degree212",
                    invested = BigDecimal("100000"),
                    current = BigDecimal("105000"),
                    sip = BigDecimal("5000"),
                ),
                XlsxHoldingValueRow(
                    snapshotDate = LocalDate.parse("2026-02-15"),
                    holdingName = "HDFC",
                    invested = null,
                    current = BigDecimal("9500"),
                    sip = null,
                ),
            ),
            loans = listOf(
                XlsxLoanRow(
                    name = "Home",
                    originalAmount = BigDecimal("6000000"),
                    takenDate = LocalDate.parse("2022-01-01"),
                    monthlyEmi = BigDecimal("50000"),
                ),
            ),
            loanValues = listOf(
                XlsxLoanValueRow(
                    snapshotDate = LocalDate.parse("2026-01-15"),
                    loanName = "Home",
                    outstanding = BigDecimal("5500000"),
                ),
            ),
            goals = listOf(
                XlsxGoalRow(
                    name = "First Crore",
                    goalType = "NET_WORTH",
                    targetNetWorth = BigDecimal("10000000"),
                    targetDate = LocalDate.parse("2027-12-31"),
                ),
                XlsxGoalRow(
                    name = "Debt-free",
                    goalType = "DEBT_FREE",
                    targetNetWorth = BigDecimal.ZERO,
                    targetDate = LocalDate.parse("2030-06-30"),
                ),
            ),
        )

        val out = ByteArrayOutputStream()
        XlsxCodec.write(original, out)
        val read = XlsxCodec.read(ByteArrayInputStream(out.toByteArray()))

        assertThat(read.snapshots).hasSize(2)
        assertThat(read.snapshots[0].date).isEqualTo(LocalDate.parse("2026-01-15"))
        assertThat(read.snapshots[0].earningsInCr).isEqualTo(BigDecimal("4.00"))
        assertThat(read.snapshots[0].notes).isEqualTo("first")
        assertThat(read.snapshots[1].notes).isNull()

        assertThat(read.holdingValues).hasSize(2)
        assertThat(read.holdingValues[0].holdingName).isEqualTo("Degree212")
        assertThat(read.holdingValues[0].invested).isEqualTo(BigDecimal("100000"))
        assertThat(read.holdingValues[0].sip).isEqualTo(BigDecimal("5000"))
        assertThat(read.holdingValues[1].invested).isNull()
        assertThat(read.holdingValues[1].sip).isNull()

        assertThat(read.loans).hasSize(1)
        assertThat(read.loans[0].name).isEqualTo("Home")
        assertThat(read.loans[0].monthlyEmi).isEqualTo(BigDecimal("50000"))

        assertThat(read.loanValues).hasSize(1)
        assertThat(read.loanValues[0].loanName).isEqualTo("Home")

        assertThat(read.goals).hasSize(2)
        assertThat(read.goals[0].goalType).isEqualTo("NET_WORTH")
        assertThat(read.goals[1].goalType).isEqualTo("DEBT_FREE")
    }

    @Test
    @DisplayName("Empty template writes 6 sheets and reads back all-empty data")
    fun emptyTemplate() {
        val out = ByteArrayOutputStream()
        XlsxCodec.writeEmptyTemplate(out)
        val read = XlsxCodec.read(ByteArrayInputStream(out.toByteArray()))
        assertThat(read.snapshots).isEmpty()
        assertThat(read.holdingValues).isEmpty()
        assertThat(read.loans).isEmpty()
        assertThat(read.loanValues).isEmpty()
        assertThat(read.goals).isEmpty()
    }
}
