package com.fintrack.privacy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File

/**
 * Privacy guardrail: the production source must not contain any `Log.*` /
 * `println` call that interpolates sensitive identifiers (snapshot, holding,
 * loan, user, earnings, networth, total, current, invested, sip, outstanding,
 * passphrase). R8 strips Log.* in release per proguard-rules.pro, but this
 * test catches the regression at PR time.
 *
 * Approach: scan main source for `Log.[dive](...$<sensitive>...)` and
 * `println(...$<sensitive>...)` patterns. The check is intentionally syntactic
 * — it does not resolve types — so a false positive can only happen if you're
 * writing the literal substring `${snapshot...}` inside a `Log.*` or
 * `println` call. That's exactly what we want to forbid.
 */
class LogPatternTest {

    private val sensitiveTokens = listOf(
        "snapshot", "holding", "loan", "user", "earnings",
        "networth", "passphrase", "passphraseStore",
        "current", "invested", "outstanding",
    )

    private val mainRoot = TestPaths.appFile("src/main/kotlin/com/fintrack")

    @Test
    @DisplayName("No Log.[dive]/println in main source interpolates sensitive identifiers")
    fun forbiddenLogPatterns() {
        val violations = mutableListOf<String>()
        mainRoot.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { file ->
            file.useLines { lines ->
                lines.forEachIndexed { idx, line ->
                    val isLog = line.contains(LOG_REGEX)
                    val isPrintln = line.contains(PRINTLN_REGEX)
                    if (!isLog && !isPrintln) return@forEachIndexed
                    val lower = line.lowercase()
                    for (token in sensitiveTokens) {
                        // The danger pattern is `${...token...}` inside the call. A bare
                        // word reference (e.g. a function parameter named `user`) is fine
                        // unless it's interpolated into a string. So we require the line
                        // to also contain a "${" sequence.
                        if (lower.contains("\${") && lower.contains(token)) {
                            violations += "${file.relativeTo(mainRoot)}:${idx + 1}  $line"
                            break
                        }
                    }
                }
            }
        }
        assertThat(violations).isEmpty()
    }

    @Test
    @DisplayName("No Log.* in main source at all (we strip them in release; let's not write them)")
    fun noLogCallsAtAll() {
        val violations = mutableListOf<String>()
        mainRoot.walkTopDown().filter { it.isFile && it.extension == "kt" }.forEach { file ->
            file.useLines { lines ->
                lines.forEachIndexed { idx, line ->
                    if (line.contains(LOG_REGEX)) {
                        violations += "${file.relativeTo(mainRoot)}:${idx + 1}  $line"
                    }
                }
            }
        }
        assertThat(violations).isEmpty()
    }

    companion object {
        private val LOG_REGEX = Regex("""\bLog\.[dievw]\(""")
        private val PRINTLN_REGEX = Regex("""\bprintln\(""")
    }
}
