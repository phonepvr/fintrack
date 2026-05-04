package com.fintrack.ui.snapshots.entry

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class AmountParsingTest {

    @Test
    @DisplayName("filterNumeric strips letters but keeps digits and one decimal point")
    fun filterNumericBasics() {
        assertThat("123abc456".filterNumeric()).isEqualTo("123456")
        assertThat("1.23".filterNumeric()).isEqualTo("1.23")
        assertThat("1.2.3".filterNumeric()).isEqualTo("1.23") // second dot dropped
        assertThat("..1".filterNumeric()).isEqualTo("1")     // leading dots dropped
        assertThat("Rs ₹1,000".filterNumeric()).isEqualTo("1000")
    }

    @Test
    @DisplayName("parseAmountOrNull returns null on blank, BigDecimal otherwise")
    fun parseAmountOrNull() {
        assertThat("".parseAmountOrNull()).isNull()
        assertThat("   ".parseAmountOrNull()).isNull()
        assertThat("not a number".parseAmountOrNull()).isNull()
        val parsed = "100000".parseAmountOrNull()
        assertThat(parsed).isNotNull()
        assertThat(parsed).isEqualTo(BigDecimal("100000"))
    }

    @Test
    @DisplayName("parseAmountOrZero returns ZERO for unparseable input")
    fun parseAmountOrZero() {
        assertThat("".parseAmountOrZero()).isEqualTo(BigDecimal.ZERO)
        assertThat("xx".parseAmountOrZero()).isEqualTo(BigDecimal.ZERO)
        assertThat("12.5".parseAmountOrZero()).isEqualTo(BigDecimal("12.5"))
    }
}
