package com.fintrack.ui.settings.aim

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.util.UUID

class AimEditorStateTest {

    private val ml = UUID.randomUUID()
    private val fr = UUID.randomUUID()
    private val cr = UUID.randomUUID()

    private fun row(id: UUID, name: String, percent: Int) = AimRowState(id, name, percent)

    @Test
    @DisplayName("Save is only allowed when the percentages sum to exactly 100")
    fun saveGate() {
        val balanced = AimEditorUiState(
            loading = false,
            rows = listOf(
                row(ml, "Market Linked", 70),
                row(fr, "Fixed Return", 25),
                row(cr, "Crypto", 5),
            ),
        )
        assertThat(balanced.sum).isEqualTo(100)
        assertThat(balanced.canSave).isTrue()

        val short = balanced.copy(rows = balanced.rows.dropLast(1) + row(cr, "Crypto", 4))
        assertThat(short.sum).isEqualTo(99)
        assertThat(short.canSave).isFalse()

        val over = balanced.copy(rows = balanced.rows.dropLast(1) + row(cr, "Crypto", 10))
        assertThat(over.sum).isEqualTo(105)
        assertThat(over.canSave).isFalse()
    }

    @Test
    @DisplayName("Save is blocked while a previous save is in flight, even at 100%")
    fun savingBlocksSave() {
        val balanced = AimEditorUiState(
            loading = false,
            rows = listOf(
                row(ml, "Market Linked", 50),
                row(fr, "Fixed Return", 45),
                row(cr, "Crypto", 5),
            ),
            saving = true,
        )
        assertThat(balanced.sum).isEqualTo(100)
        assertThat(balanced.canSave).isFalse()
    }

    @Test
    @DisplayName("Save is blocked when there are no rows")
    fun emptyRowsBlocksSave() {
        val empty = AimEditorUiState(loading = false, rows = emptyList())
        assertThat(empty.canSave).isFalse()
    }

    @Test
    @DisplayName("distributeRemainder fills a positive gap into 0% rows")
    fun distributeRemainderFillsZeros() {
        val rows = listOf(
            row(ml, "Market Linked", 70),
            row(fr, "Fixed Return", 25),
            row(cr, "Crypto", 0),
        )
        val out = distributeRemainderTo(rows)
        assertThat(out.sumOf { it.percent }).isEqualTo(100)
        assertThat(out.first { it.assetClassId == ml }.percent).isEqualTo(70)
        assertThat(out.first { it.assetClassId == fr }.percent).isEqualTo(25)
        assertThat(out.first { it.assetClassId == cr }.percent).isEqualTo(5)
    }

    @Test
    @DisplayName("distributeRemainder splits the gap evenly across multiple zero rows")
    fun distributeRemainderSplitsAcrossZeros() {
        val a = UUID.randomUUID(); val b = UUID.randomUUID(); val c = UUID.randomUUID()
        val rows = listOf(
            row(ml, "ML", 70),
            row(a, "Z1", 0),
            row(b, "Z2", 0),
            row(c, "Z3", 0),
        )
        val out = distributeRemainderTo(rows)
        assertThat(out.sumOf { it.percent }).isEqualTo(100)
        // 30 split across 3 rows → 10 each.
        assertThat(out.filter { it.percent == 10 }).hasSize(3)
    }

    @Test
    @DisplayName("distributeRemainder is a no-op when sum is already 100")
    fun distributeRemainderNoop() {
        val rows = listOf(
            row(ml, "ML", 70),
            row(fr, "FR", 25),
            row(cr, "CR", 5),
        )
        assertThat(distributeRemainderTo(rows)).isEqualTo(rows)
    }

    @Test
    @DisplayName("distributeRemainder handles overshoot by spreading the negative gap")
    fun distributeRemainderHandlesOvershoot() {
        val rows = listOf(
            row(ml, "ML", 70),
            row(fr, "FR", 30),
            row(cr, "CR", 5),
        )
        val out = distributeRemainderTo(rows)
        assertThat(out.sumOf { it.percent }).isEqualTo(100)
    }
}
