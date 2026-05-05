package com.fintrack.ui.settings.about

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.BuildConfig
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.domain.glossary.GlossaryContent

private const val ANCHOR_GLOSSARY_WEALTH = "glossary_wealth"
private const val ANCHOR_GLOSSARY_ASSETS = "glossary_assets"
private const val ANCHOR_GLOSSARY_ENGAGEMENT = "glossary_engagement"
private const val ANCHOR_GLOSSARY_LOANS = "glossary_loans"
private const val ANCHOR_PRIVACY = "privacy"

private const val ITEM_KEY_IDENTITY = "identity"
private const val ITEM_KEY_WELCOME = "welcome"
private const val ITEM_KEY_HOW_TO = "how_to"
private const val ITEM_KEY_GLOSSARY_WEALTH = "glossary_wealth"
private const val ITEM_KEY_GLOSSARY_ASSETS = "glossary_assets"
private const val ITEM_KEY_GLOSSARY_ENGAGEMENT = "glossary_engagement"
private const val ITEM_KEY_GLOSSARY_LOANS = "glossary_loans"
private const val ITEM_KEY_PRIVACY_PROMISE = "privacy_promise"
private const val ITEM_KEY_VIEW_MANIFEST = "view_manifest"
private const val ITEM_KEY_DISCLAIMER = "disclaimer"
private const val ITEM_KEY_PRIVACY_ENFORCEMENT = "privacy_enforcement"
private const val ITEM_KEY_APP_INFO = "app_info"

@Composable
fun AboutRoute(
    onBack: () -> Unit,
    onViewManifest: () -> Unit,
    onReplayOnboarding: () -> Unit,
    scrollToSection: String? = null,
    viewModel: AboutViewModel = hiltViewModel(),
) {
    val assetClasses by viewModel.assetClasses.collectAsState()

    val listState = rememberLazyListState()
    val expandedAnchors = remember { mutableStateMapOf<String, Boolean>() }

    val itemKeys = remember {
        listOf(
            ITEM_KEY_IDENTITY,
            ITEM_KEY_WELCOME,
            ITEM_KEY_HOW_TO,
            ITEM_KEY_GLOSSARY_WEALTH,
            ITEM_KEY_GLOSSARY_ASSETS,
            ITEM_KEY_GLOSSARY_ENGAGEMENT,
            ITEM_KEY_GLOSSARY_LOANS,
            ITEM_KEY_PRIVACY_PROMISE,
            ITEM_KEY_VIEW_MANIFEST,
            ITEM_KEY_DISCLAIMER,
            ITEM_KEY_PRIVACY_ENFORCEMENT,
            ITEM_KEY_APP_INFO,
        )
    }

    LaunchedEffect(scrollToSection) {
        val target = scrollToSection ?: return@LaunchedEffect
        val itemKey = anchorToItemKey(target) ?: return@LaunchedEffect
        val index = itemKeys.indexOf(itemKey).takeIf { it >= 0 } ?: return@LaunchedEffect
        // Auto-expand the matching glossary card so the deep-link lands on
        // open content, not a collapsed header.
        expandedAnchors[itemKey] = true
        listState.animateScrollToItem(index)
    }

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
            state = listState,
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item(ITEM_KEY_IDENTITY) { IdentityStripCard() }
            item(ITEM_KEY_WELCOME) { WelcomeCard(onReplayOnboarding) }
            item(ITEM_KEY_HOW_TO) { HowToUseCard() }
            item(ITEM_KEY_GLOSSARY_WEALTH) {
                ExpandableGlossaryCard(
                    anchor = ITEM_KEY_GLOSSARY_WEALTH,
                    title = "Wealth metrics",
                    entries = GlossaryContent.wealthMetrics,
                    expanded = expandedAnchors[ITEM_KEY_GLOSSARY_WEALTH] ?: false,
                    onToggle = { expandedAnchors[ITEM_KEY_GLOSSARY_WEALTH] =
                        !(expandedAnchors[ITEM_KEY_GLOSSARY_WEALTH] ?: false) },
                )
            }
            item(ITEM_KEY_GLOSSARY_ASSETS) {
                AssetClassesGlossaryCard(
                    assetClasses = assetClasses,
                    expanded = expandedAnchors[ITEM_KEY_GLOSSARY_ASSETS] ?: false,
                    onToggle = { expandedAnchors[ITEM_KEY_GLOSSARY_ASSETS] =
                        !(expandedAnchors[ITEM_KEY_GLOSSARY_ASSETS] ?: false) },
                )
            }
            item(ITEM_KEY_GLOSSARY_ENGAGEMENT) {
                ExpandableGlossaryCard(
                    anchor = ITEM_KEY_GLOSSARY_ENGAGEMENT,
                    title = "Streaks, milestones, goals",
                    entries = GlossaryContent.engagementTerms,
                    expanded = expandedAnchors[ITEM_KEY_GLOSSARY_ENGAGEMENT] ?: false,
                    onToggle = { expandedAnchors[ITEM_KEY_GLOSSARY_ENGAGEMENT] =
                        !(expandedAnchors[ITEM_KEY_GLOSSARY_ENGAGEMENT] ?: false) },
                )
            }
            item(ITEM_KEY_GLOSSARY_LOANS) {
                ExpandableGlossaryCard(
                    anchor = ITEM_KEY_GLOSSARY_LOANS,
                    title = "Loans",
                    entries = GlossaryContent.loans,
                    expanded = expandedAnchors[ITEM_KEY_GLOSSARY_LOANS] ?: false,
                    onToggle = { expandedAnchors[ITEM_KEY_GLOSSARY_LOANS] =
                        !(expandedAnchors[ITEM_KEY_GLOSSARY_LOANS] ?: false) },
                )
            }
            item(ITEM_KEY_PRIVACY_PROMISE) {
                Column {
                    OfflineBadge()
                    Spacer(Modifier.height(8.dp))
                    PrivacyPromiseCard()
                }
            }
            item(ITEM_KEY_VIEW_MANIFEST) { ViewManifestRow(onClick = onViewManifest) }
            item(ITEM_KEY_DISCLAIMER) { DisclaimerCard() }
            item(ITEM_KEY_PRIVACY_ENFORCEMENT) { PrivacyEnforcementCard() }
            item(ITEM_KEY_APP_INFO) { AppInfoCard() }
        }
    }
}

private fun anchorToItemKey(anchor: String): String? = when (anchor) {
    ANCHOR_GLOSSARY_WEALTH -> ITEM_KEY_GLOSSARY_WEALTH
    ANCHOR_GLOSSARY_ASSETS -> ITEM_KEY_GLOSSARY_ASSETS
    ANCHOR_GLOSSARY_ENGAGEMENT -> ITEM_KEY_GLOSSARY_ENGAGEMENT
    ANCHOR_GLOSSARY_LOANS -> ITEM_KEY_GLOSSARY_LOANS
    ANCHOR_PRIVACY -> ITEM_KEY_PRIVACY_PROMISE
    else -> null
}

// ---------------------------------------------------------------- v3.1 sections

@Composable
private fun IdentityStripCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Filled.ExpandLess, // placeholder; app icon would render here in production
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            Spacer(Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("FinTrack", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold)
                Text(
                    "Version ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun WelcomeCard(onReplay: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "A private, offline wealth portfolio tracker. Update your holdings monthly, " +
                    "see your net worth grow, and keep your financial life on your device — " +
                    "not in the cloud.",
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(Modifier.height(12.dp))
            OutlinedButton(onClick = onReplay, modifier = Modifier.fillMaxWidth()) {
                Text("Replay onboarding")
            }
        }
    }
}

@Composable
private fun HowToUseCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "How to use this app",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(8.dp))
            HowToStep(
                "1",
                "Add a snapshot every month",
                "Tap + on the Snapshots tab. Pick today's date. Enter the current value of " +
                    "each holding and the outstanding balance of any loans. Save.",
            )
            HowToStep(
                "2",
                "Keep your earnings updated",
                "Each snapshot asks for \"Earnings (in cr)\" — your total lifetime earnings. " +
                    "Update this every snapshot so % of earnings stays accurate.",
            )
            HowToStep(
                "3",
                "Customise what you track",
                "Settings → Holdings lets you add new platforms, banks, or whole asset classes. " +
                    "Settings → Loans lets you add and close loans as your liabilities change.",
            )
            HowToStep(
                "4",
                "Set goals and watch progress",
                "Settings → Goals lets you set net-worth targets with a deadline. The app " +
                    "shows whether you're on pace and projects when you'll hit the target.",
            )
            HowToStep(
                "5",
                "Bulk import with Excel",
                "Settings → Excel template gives you a workbook pre-filled with your catalog. " +
                    "Fill it in offline, then upload it back. Useful for entering historical data.",
            )
            HowToStep(
                "6",
                "Backup before you reinstall",
                "Settings → Backup → Export encrypted JSON saves a password-protected file you " +
                    "can store anywhere. Reimport on a new device or after a reinstall.",
            )
        }
    }
}

@Composable
private fun HowToStep(number: String, title: String, body: String) {
    Row(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(
            number,
            modifier = Modifier.size(20.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(Modifier.size(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
            Text(
                body,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp),
            )
        }
    }
}

@Composable
private fun ExpandableGlossaryCard(
    anchor: String,
    title: String,
    entries: List<GlossaryContent.Entry>,
    expanded: Boolean,
    onToggle: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onToggle),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(Modifier.height(8.dp))
                    entries.forEachIndexed { index, e ->
                        if (index > 0) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 6.dp),
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                            )
                        }
                        GlossaryEntryRow(e)
                    }
                    @Suppress("UNUSED_EXPRESSION")
                    anchor // anchor referenced to silence the unused-parameter warning
                }
            }
        }
    }
}

@Composable
private fun GlossaryEntryRow(entry: GlossaryContent.Entry) {
    Column(modifier = Modifier.padding(vertical = 2.dp)) {
        Text(entry.title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Medium)
        Text(
            entry.body,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 2.dp),
        )
    }
}

@Composable
private fun AssetClassesGlossaryCard(
    assetClasses: List<AssetClassEntity>,
    expanded: Boolean,
    onToggle: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onToggle),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text(
                    "Asset classes",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(1f),
                )
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(Modifier.height(8.dp))
                    val visible = assetClasses.filter { it.isActive }
                    visible.forEachIndexed { index, ac ->
                        if (index > 0) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 6.dp),
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
                            )
                        }
                        val entry = GlossaryContent.forAssetClassId(ac.id)
                            ?: GlossaryContent.genericAssetClassFallback
                        // Use the LIVE name from the DB (handles renames) but
                        // pair with the canonical explanation looked up by id.
                        GlossaryEntryRow(GlossaryContent.Entry(ac.name, entry.body))
                    }
                    if (visible.isEmpty()) {
                        Text(
                            "No active asset classes — Settings → Holdings to add some.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AppInfoCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "App information",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(8.dp))
            InfoRow("Version", "${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})")
            InfoRow("Build", BuildConfig.BUILD_TYPE)
            InfoRow("License", "Personal use — see README")
            Spacer(Modifier.height(8.dp))
            Text(
                "Built with Jetpack Compose, Room, SQLCipher, Apache POI, kotlinx-serialization, " +
                    "kotlinx-datetime, Hilt, and Material 3. All open-source — see each library's " +
                    "license for details.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "Built with love by #PankLak ❤",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
            )
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
        Text(label, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
        Text(
            value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

// ---------------------------------------------------------- v3 panels (preserved)

@Composable
internal fun OfflineBadge() {
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
internal fun PrivacyPromiseCard() {
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
internal fun ViewManifestRow(onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
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
internal fun DisclaimerCard() {
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
internal fun PrivacyEnforcementCard() {
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
