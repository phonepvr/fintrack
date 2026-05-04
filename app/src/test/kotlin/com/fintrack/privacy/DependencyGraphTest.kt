package com.fintrack.privacy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Privacy gate #2 from spec §6:
 *   No dependency in the resolved Gradle graph imports `okhttp`, `retrofit`,
 *   `firebase`, `crashlytics`, `analytics`, or `play-services-measurement`.
 *
 * This source-side check parses the version catalog and the app build script
 * for forbidden coordinate substrings. It catches direct declarations
 * deterministically. A complementary task that walks the resolved
 * runtimeClasspath at build time would catch transitive pulls — that's
 * registered as `verifyNoForbiddenDeps` in app/build.gradle.kts (added in
 * Phase 6 once we have all production deps locked).
 */
class DependencyGraphTest {

    private val forbidden = listOf(
        "okhttp",
        "okhttp3",
        "retrofit",
        "retrofit2",
        "ktor-client",
        "volley",
        "org.apache.http",
        "firebase",
        "crashlytics",
        "analytics",
        "play-services-measurement",
        "com.facebook",
        "appsflyer",
        "amplitude",
        "mixpanel",
        "branch",
        "segment",
    )

    private val versionCatalog: String by lazy {
        TestPaths.rootFile("gradle/libs.versions.toml").readText()
    }

    private val appBuild: String by lazy {
        TestPaths.appFile("build.gradle.kts").readText()
    }

    @Test
    @DisplayName("Version catalog contains no networking / analytics SDKs")
    fun catalogIsClean() {
        forbidden.forEach { needle ->
            assertThat(versionCatalog.lowercase()).doesNotContain(needle.lowercase())
        }
    }

    @Test
    @DisplayName("App build.gradle.kts contains no networking / analytics SDKs")
    fun appBuildIsClean() {
        forbidden.forEach { needle ->
            assertThat(appBuild.lowercase()).doesNotContain(needle.lowercase())
        }
    }
}
