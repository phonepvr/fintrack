package com.fintrack.domain.util

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class IndianCurrencyFormatterTest {

    @Test
    @DisplayName("Boundary: 0 renders without grouping")
    fun zero() {
        assertThat(formatIndianCurrency(BigDecimal("0"))).isEqualTo("₹0")
    }

    @Test
    @DisplayName("Boundary: 1 renders as ₹1")
    fun one() {
        assertThat(formatIndianCurrency(BigDecimal("1"))).isEqualTo("₹1")
    }

    @Test
    @DisplayName("Boundary: 999 renders without grouping")
    fun nineNineNine() {
        assertThat(formatIndianCurrency(BigDecimal("999"))).isEqualTo("₹999")
    }

    @Test
    @DisplayName("Boundary: 1,000 inserts the first comma")
    fun thousand() {
        assertThat(formatIndianCurrency(BigDecimal("1000"))).isEqualTo("₹1,000")
    }

    @Test
    @DisplayName("Boundary: 99,999 stays in rupee form")
    fun nineFiveZeros() {
        assertThat(formatIndianCurrency(BigDecimal("99999"))).isEqualTo("₹99,999")
    }

    @Test
    @DisplayName("Boundary: 1,00,000 switches to lakh form")
    fun oneLakh() {
        assertThat(formatIndianCurrency(BigDecimal("100000"))).isEqualTo("₹1.00 L")
    }

    @Test
    @DisplayName("Boundary: 10,00,000 stays in lakh form")
    fun tenLakh() {
        assertThat(formatIndianCurrency(BigDecimal("1000000"))).isEqualTo("₹10.00 L")
    }

    @Test
    @DisplayName("Boundary: 1,00,00,000 switches to crore form")
    fun oneCrore() {
        assertThat(formatIndianCurrency(BigDecimal("10000000"))).isEqualTo("₹1.00 Cr")
    }

    @Test
    @DisplayName("Round-half-up: 1,49,999 displays as ₹1.50 L")
    fun roundHalfUp() {
        assertThat(formatIndianCurrency(BigDecimal("149999"))).isEqualTo("₹1.50 L")
    }

    @Test
    @DisplayName("Negative amounts get a leading minus")
    fun negative() {
        assertThat(formatIndianCurrency(BigDecimal("-50000"))).isEqualTo("-₹50,000")
    }

    @Test
    @DisplayName("Signed currency formatter prepends + for non-negative")
    fun signedCurrency() {
        assertThat(formatSignedCurrency(BigDecimal("100000"))).isEqualTo("+₹1.00 L")
        assertThat(formatSignedCurrency(BigDecimal("-100000"))).isEqualTo("-₹1.00 L")
        assertThat(formatSignedCurrency(BigDecimal("0"))).isEqualTo("+₹0")
    }

    @Test
    @DisplayName("Percent formatter shows one decimal by default")
    fun percentDefault() {
        assertThat(formatPercent(BigDecimal("21.3"))).isEqualTo("21.3%")
        assertThat(formatPercent(BigDecimal("0"))).isEqualTo("0.0%")
        assertThat(formatPercent(BigDecimal("100.55"))).isEqualTo("100.6%")
    }

    @Test
    @DisplayName("Signed percent prefixes + on non-negative")
    fun signedPercent() {
        assertThat(formatSignedPercent(BigDecimal("5.2"))).isEqualTo("+5.2%")
        assertThat(formatSignedPercent(BigDecimal("-1.0"))).isEqualTo("-1.0%")
        assertThat(formatSignedPercent(BigDecimal("0"))).isEqualTo("+0.0%")
    }
}
