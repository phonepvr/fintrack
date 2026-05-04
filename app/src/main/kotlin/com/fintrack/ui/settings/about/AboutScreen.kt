package com.fintrack.ui.settings.about

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.fintrack.BuildConfig

@Composable
fun AboutRoute(
    onBack: () -> Unit,
    onViewManifest: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text("About") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item {
                Text("FinTrack", style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold)
                Text("Version ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            item { OfflineBadge() }
            item { PrivacyPromiseCard() }
            item { ViewManifestRow(onClick = onViewManifest) }
            item { DisclaimerCard() }
            item { PrivacyEnforcementCard() }
        }
    }
}

@Composable
private fun OfflineBadge() {
    AssistChip(
        onClick = {},
        label = { Text("Offline-only · no network access", fontWeight = FontWeight.SemiBold) },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            labelColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
    )
}

@Composable
private fun PrivacyPromiseCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "What this app will never do",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(8.dp))
            BulletLine("Connect to the internet (INTERNET permission absent from manifest).")
            BulletLine("Send analytics, crash reports, or telemetry (no such SDKs in the build).")
            BulletLine("Back up your data to Google or any cloud (allowBackup = false).")
            BulletLine("Show in screenshots or app-switcher previews (FLAG_SECURE).")
            BulletLine("Store your data unencrypted (SQLCipher with hardware-backed key).")
            BulletLine("Track you across profiles (each profile is logically isolated).")
        }
    }
}

@Composable
private fun ViewManifestRow(onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text("View installed permissions", style = MaterialTheme.typography.titleMedium)
                Text(
                    "Read directly from the running APK at runtime. The strongest trust signal " +
                        "we can offer — verifiable from a screenshot.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}

@Composable
private fun DisclaimerCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Multi-profile separation is organisational, not cryptographic",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onErrorContainer,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "All profiles share a single device-encrypted database. Anyone with access to " +
                    "this device's biometric can view every profile. If you need separate users " +
                    "to have separate access, use distinct Android user accounts on the device.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onErrorContainer,
            )
        }
    }
}

@Composable
private fun PrivacyEnforcementCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("How privacy is enforced", style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(8.dp))
            BulletLine(
                "AndroidManifest declares no INTERNET, ACCESS_NETWORK_STATE, ACCESS_WIFI_STATE, " +
                    "CHANGE_NETWORK_STATE, or CHANGE_WIFI_STATE permissions.",
            )
            BulletLine(
                "network_security_config forbids cleartext traffic and supplies an empty trust anchor — " +
                    "any outbound TLS handshake would fail.",
            )
            BulletLine(
                "Database is opened via SQLCipher with a 256-bit passphrase generated on first launch " +
                    "and stored in EncryptedSharedPreferences whose master key lives in the Android Keystore.",
            )
            BulletLine("FLAG_SECURE is set on the activity, blocking screenshots and recents thumbnails.")
            BulletLine(
                "Auto Backup and device-to-device transfer are explicitly disabled — only the in-app " +
                    "Backup screen produces exports.",
            )
            BulletLine(
                "Encrypted JSON backups use AES-256-GCM with PBKDF2-HMAC-SHA256 " +
                    "(≥200 000 iterations) keyed by your passphrase.",
            )
            BulletLine(
                "Release builds strip android.util.Log calls via R8 — even if a developer slips a " +
                    "Log.d into source, no log lines reach a release APK.",
            )
        }
    }
}

@Composable
private fun BulletLine(text: String) {
    Text(
        "• $text",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.padding(vertical = 2.dp),
    )
}
