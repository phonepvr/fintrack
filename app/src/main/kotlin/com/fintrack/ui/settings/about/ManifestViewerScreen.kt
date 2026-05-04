package com.fintrack.ui.settings.about

import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * Runtime-verifiable manifest viewer: reads the installed APK's permission
 * list directly via `PackageManager.getPackageInfo(... PERMISSIONS)` and
 * renders it. No XML parsing — the OS itself is the source of truth.
 *
 * If anyone ever ships a build with a sneaky INTERNET permission, this
 * screen reveals it instantly; you don't have to trust the build script or
 * the source repo.
 */
@Composable
fun ManifestViewerRoute(onBack: () -> Unit) {
    val context = LocalContext.current
    val info = remember { readManifestInfo(context) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text("Installed permissions") },
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
                Text(
                    "Read directly from the installed APK at runtime via PackageManager. " +
                        "If a future build adds a network permission, it shows up here.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            item { InfoCard("Package", info.packageName) }
            item { InfoCard("Version", "${info.versionName} (${info.versionCode})") }
            item { PermissionsCard(info.requestedPermissions) }
        }
    }
}

private data class ManifestInfo(
    val packageName: String,
    val versionName: String,
    val versionCode: Long,
    val requestedPermissions: List<String>,
)

@Suppress("DEPRECATION")
private fun readManifestInfo(context: Context): ManifestInfo {
    val pm = context.packageManager
    val pkg = context.packageName
    val pi = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
        pm.getPackageInfo(pkg, PackageManager.PackageInfoFlags.of(PackageManager.GET_PERMISSIONS.toLong()))
    } else {
        pm.getPackageInfo(pkg, PackageManager.GET_PERMISSIONS)
    }
    val perms = pi.requestedPermissions?.toList().orEmpty().sorted()
    val versionCode = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
        pi.longVersionCode
    } else {
        pi.versionCode.toLong()
    }
    return ManifestInfo(
        packageName = pkg,
        versionName = pi.versionName.orEmpty(),
        versionCode = versionCode,
        requestedPermissions = perms,
    )
}

@Composable
private fun InfoCard(label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant)
            Spacer(Modifier.height(2.dp))
            Text(value, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
private fun PermissionsCard(permissions: List<String>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "Requested permissions (${permissions.size})",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(6.dp))
            if (permissions.isEmpty()) {
                Text(
                    "None — the app requests no permissions at all.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            } else {
                permissions.forEach { perm ->
                    Text(
                        "• $perm",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(vertical = 1.dp),
                    )
                }
            }
        }
    }
}
