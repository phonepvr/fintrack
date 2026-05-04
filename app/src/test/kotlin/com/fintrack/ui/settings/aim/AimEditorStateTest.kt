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
}
