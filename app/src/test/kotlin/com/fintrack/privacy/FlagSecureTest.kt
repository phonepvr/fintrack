package com.fintrack.privacy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Privacy gate #5 from spec §6 (source-side coverage):
 *   FLAG_SECURE is set on the main activity.
 *
 * Phase 1 verifies the wiring at the source level: [MainActivity] must call
 * window.setFlags with FLAG_SECURE BEFORE setContent so that no Compose
 * surface is ever rendered in a non-secure window.
 *
 * Phase 6 adds an androidTest that launches MainActivity with ActivityScenario
 * and asserts (window.attributes.flags and FLAG_SECURE) != 0 at runtime.
 */
class FlagSecureTest {

    private val mainActivitySource: String by lazy {
        TestPaths.appFile("src/main/kotlin/com/fintrack/MainActivity.kt").readText()
    }

    @Test
    @DisplayName("MainActivity sets FLAG_SECURE on its window")
    fun flagSecureIsSet() {
        assertThat(mainActivitySource).contains("WindowManager.LayoutParams.FLAG_SECURE")
        assertThat(mainActivitySource).contains("window.setFlags(")
    }

    @Test
    @DisplayName("FLAG_SECURE is set BEFORE setContent so no frame is ever rendered insecurely")
    fun flagBeforeSetContent() {
        // Match the call site (`setContent {`), not the import (`androidx.activity.compose.setContent`).
        val setFlagsIndex = mainActivitySource.indexOf("window.setFlags(")
        val setContentCallIndex = mainActivitySource.indexOf("setContent {")
        assertThat(setFlagsIndex).isGreaterThan(-1)
        assertThat(setContentCallIndex).isGreaterThan(-1)
        assertThat(setFlagsIndex).isLessThan(setContentCallIndex)
    }

    @Test
    @DisplayName("MainActivity extends FragmentActivity (required by BiometricPrompt)")
    fun extendsFragmentActivity() {
        assertThat(mainActivitySource).contains(": FragmentActivity()")
    }
}
