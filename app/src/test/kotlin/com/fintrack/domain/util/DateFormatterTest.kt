package com.fintrack.domain.util

import com.google.common.truth.Truth.assertThat
import kotlinx.datetime.LocalDate
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DateFormatterTest {

    @Test
    @DisplayName("Renders dd-MMM-yyyy with US-English month abbreviations")
    fun rendersUsMonths() {
        assertThat(LocalDate(2026, 5, 5).formatted()).isEqualTo("05-May-2026")
        assertThat(LocalDate(2026, 1, 1).formatted()).isEqualTo("01-Jan-2026")
        assertThat(LocalDate(2026, 12, 31).formatted()).isEqualTo("31-Dec-2026")
    }

    @Test
    @DisplayName("Pads single-digit days with a leading zero")
    fun padsDay() {
        assertThat(LocalDate(2026, 3, 9).formatted()).isEqualTo("09-Mar-2026")
    }
}
