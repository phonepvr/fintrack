package com.fintrack.privacy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Privacy gate #1 from spec §6:
 *   The merged AndroidManifest.xml must NOT declare INTERNET, ACCESS_NETWORK_STATE,
 *   or ACCESS_WIFI_STATE permissions. The build fails if any are added.
 *
 * This test parses the source manifest directly (rather than the merged
 * manifest in build/intermediates) so it runs without needing a full
 * Android build to have completed first. Phase 6 adds an additional
 * androidTest that re-checks the merged manifest at runtime, but the
 * source-side check catches regressions much faster in CI.
 */
class ManifestPermissionsTest {

    private val manifest: String by lazy {
        TestPaths.appFile("src/main/AndroidManifest.xml").readText()
    }

    @Test
    @DisplayName("AndroidManifest must not declare android.permission.INTERNET")
    fun noInternetPermission() {
        assertThat(manifest).doesNotContain("android.permission.INTERNET")
    }

    @Test
    @DisplayName("AndroidManifest must not declare ACCESS_NETWORK_STATE")
    fun noNetworkStatePermission() {
        assertThat(manifest).doesNotContain("android.permission.ACCESS_NETWORK_STATE")
    }

    @Test
    @DisplayName("AndroidManifest must not declare ACCESS_WIFI_STATE")
    fun noWifiStatePermission() {
        assertThat(manifest).doesNotContain("android.permission.ACCESS_WIFI_STATE")
    }

    @Test
    @DisplayName("AndroidManifest must explicitly forbid cleartext traffic")
    fun cleartextDisabled() {
        assertThat(manifest).contains("android:usesCleartextTraffic=\"false\"")
    }

    @Test
    @DisplayName("AndroidManifest must disable Auto Backup")
    fun autoBackupDisabled() {
        assertThat(manifest).contains("android:allowBackup=\"false\"")
    }
}
