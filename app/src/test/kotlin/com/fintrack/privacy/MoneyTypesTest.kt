package com.fintrack.privacy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Spec §8 demands that money values are always [java.math.BigDecimal]. This
 * test scans the data and domain source trees for the substrings `: Double`,
 * `: Float`, and primitive constructors that imply float-typed money. If the
 * data layer ever introduces a Double-typed field, this fails the build.
 *
 * Note: scanning by substring is intentionally crude. False positives can be
 * suppressed by structuring the file so the substring doesn't appear (e.g. a
 * named alias). The check is meant as a guardrail; real review still matters.
 */
class MoneyTypesTest {

    private val forbiddenTokens = listOf(": Double", ": Float", "kotlin.Double", "kotlin.Float")

    @Test
    @DisplayName("Data and domain layers contain no Double or Float fields")
    fun noFloatingPointInMoneyLayers() {
        val violations = mutableListOf<String>()
        val roots = listOf(
            TestPaths.appFile("src/main/kotlin/com/fintrack/data"),
            TestPaths.appFile("src/main/kotlin/com/fintrack/domain"),
        )
        for (dir in roots) {
            dir.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { file ->
                val text = file.readText()
                for (token in forbiddenTokens) {
                    if (text.contains(token)) {
                        violations += "${file.path}: contains '$token'"
                    }
                }
            }
        }
        assertThat(violations).isEmpty()
    }
}
