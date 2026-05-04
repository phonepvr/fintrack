package com.fintrack.ui.settings.aim

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AimEditorStateTest {

    @Test
    @DisplayName("Save is only allowed when the four percentages sum to exactly 100")
    fun saveGate() {
        val balanced = AimEditorUiState(
            loading = false,
            mfNps = 55, equity = 15, fixedReturn = 25, crypto = 5,
        )
        assertThat(balanced.sum).isEqualTo(100)
        assertThat(balanced.canSave).isTrue()

        val short = balanced.copy(crypto = 4)
        assertThat(short.sum).isEqualTo(99)
        assertThat(short.canSave).isFalse()

        val over = balanced.copy(mfNps = 60)
        assertThat(over.sum).isEqualTo(105)
        assertThat(over.canSave).isFalse()
    }

    @Test
    @DisplayName("Save is blocked while a previous save is in flight, even at 100%")
    fun savingBlocksSave() {
        val balanced = AimEditorUiState(
            loading = false,
            mfNps = 40, equity = 10, fixedReturn = 45, crypto = 5,
            saving = true,
        )
        assertThat(balanced.sum).isEqualTo(100)
        assertThat(balanced.canSave).isFalse()
    }
}
