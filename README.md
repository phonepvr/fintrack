# FinTrack

Android-only, **offline-first**, **privacy-focused** personal **wealth tracker**.
Multiple profiles share one device, each with their own isolated portfolio,
loans, goals, streaks, and milestones.

> Status: v3 ships through Phases A–K. Net Worth replaces "total portfolio"
> as the headline number; the four-class enum becomes a three-level taxonomy
> table; loans/streaks/milestones/goals are first-class; backups round-trip
> the full v3 schema; XLSX template + import is wired.

---

## Hard guarantees

- **Zero network access.** The manifest declares no `INTERNET`,
  `ACCESS_NETWORK_STATE`, `ACCESS_WIFI_STATE`, `CHANGE_NETWORK_STATE`, or
  `CHANGE_WIFI_STATE` permission. Adding any of them fails the privacy
  acceptance test in CI.
- **`network_security_config.xml`** declares `cleartextTrafficPermitted=false`
  with empty trust anchors, so even if someone slipped a hidden HTTP
  request through a third-party SDK it would fail TLS validation against
  zero anchors.
- **Encrypted at rest.** Room is opened through SQLCipher
  (`net.zetetic:sqlcipher-android` 4.6.1) using a 256-bit passphrase
  generated on first launch and stored in `EncryptedSharedPreferences`
  whose master key lives in the Android Keystore (hardware-backed where
  the device supports it). The plaintext passphrase never touches a normal
  SharedPreferences file.
- **Biometric on every cold launch.** No PIN fallback. After the
  configurable inactivity timeout (default 60 s), foregrounding the app
  re-locks until biometric succeeds again.
- **`FLAG_SECURE` always on.** Set on `MainActivity` before any Compose
  surface is rendered, so screenshots and recents thumbnails can never
  capture portfolio data. A defense-in-depth `PrivacyOverlay` masks the
  Compose tree on `ON_PAUSE` for OEMs that capture the recents thumbnail
  before honouring the flag.
- **R8 strips `android.util.Log`.** Release builds elide every `Log.v/d/i/
  w/e/wtf` call via `-assumenosideeffects`. A custom Detekt rule and a
  source-scanning unit test guard against any new `Log.*` reference in
  `app/src/main/`.
- **Money fields don't leak via the IME.** Every amount input goes through
  a `MoneyTextField` wrapper that disables autocorrect and uses the
  decimal numeric keyboard, so typed digits aren't surfaced as IME
  suggestions.
- **Clipboard auto-clears after 30 seconds** when the app puts amounts on
  the clipboard, with the `IS_SENSITIVE` hint on Android 13+.
- **About → View Manifest** reads
  `PackageManager.getPackageInfo(... PERMISSIONS)` at runtime so the user
  can verify the empty permission list themselves.
- **Profile separation is organisational, not cryptographic.** All profiles
  share the same database key. The About screen surfaces this explicitly.
- **Backups are user-driven and active-profile-scoped.** Auto Backup and
  device-to-device transfer are disabled. The Settings → Backup screen
  produces:
  - Encrypted JSON: AES-256-GCM with PBKDF2-HMAC-SHA256 (200 000
    iterations) keyed by your passphrase. Schema-version tagged; v3
    rejects v1/v2 backups with a clear error.
  - Plain CSV (zipped): same data, unencrypted, with `dd-MMM-yyyy` dates
    and a prominent warning in the UI.
- **XLSX template + import** runs entirely in-process via Apache POI
  5.2.5 with hardened R8 keep rules. No cloud round-trip; the test suite
  runs in a network-blocked environment.

---

## What v3 adds over v1/v2

- **Three-level taxonomy**: `AssetClass` → `SubBucket` → `Holding`. The
  Phase 1–6 enum (`MF_NPS / EQUITY / FIXED_RETURN / CRYPTO`) is gone;
  classes / sub-buckets / holdings are user-editable rows backed by
  Room, with reorder + activate/deactivate at every level.
- **Loans + Net Worth**: `Loan` and `LoanValue` entities; Net Worth
  (assets − liabilities) replaces "total portfolio" as the headline
  number on the snapshot card, the Journey tab, and the snapshot
  detail. The entry form has a LOANS section after Crypto with an
  inline "+ Add new loan" dialog.
- **Streaks**: calendar-month based with current-month grace, recompute
  after every snapshot mutation. 🔥 chip on the latest card; nudge
  banner past the 15th of an empty month.
- **Milestones**: 13 predefined types (first snapshot, ₹50L through
  ₹10Cr, doubled / tripled, debt-free, YoY 50%, 12 / 36 months tracked,
  back-from-break — repeatable). Detection runs after every save.
  Awards anchored to a snapshot older than 7 days arrive pre-celebrated
  (silent backfill on first launch / device restore); recent organic
  awards pop a Material 3 `ModalBottomSheet`. A "Wins" sub-tab on the
  Journey tab lists them on a vertical timeline.
- **Goals**: Net Worth target or Debt-free, both per user. Pace is
  computed against the straight-line track and bucketed
  AHEAD / ON_TRACK / BEHIND / MISSED / ACHIEVED. The Journey tab shows
  a Goals card between the headline cards and the chart; tapping a
  goal overlays a horizontal target line on the chart.
- **`dd-MMM-yyyy` everywhere in the UI** (snapshots list/detail/entry,
  Journey history table, line-chart axes, loan rows). JSON backups
  stay ISO-8601; CSV exports use `dd-MMM-yyyy` and import lenient.
- **XLSX (Apache POI)**: download an empty template, fill offline,
  re-import via SAF. Preview screen shows per-sheet counts plus a
  Skip / Replace radio for snapshot-date conflicts. Holdings/loans/
  goals are matched by name; unknown names produce warnings rather
  than aborting the import.
- **Settings is now seven sub-screens** (Aim, Holdings, Loans, Goals,
  Manage users, Backup, Excel template, About) plus the active-profile
  card and inactivity / picker preferences.

---

## Build requirements

- JDK 17 or newer (the project compiles against bytecode 17). Apache POI
  5.2.x is the last line that supports Java 17; 5.3+ requires Java 21.
- Android SDK 35 with build-tools 35.0.0 and platform-tools.
  Set `ANDROID_HOME` (or `ANDROID_SDK_ROOT`) before invoking Gradle.
- Internet access to Google Maven and Maven Central for first-run
  dependency resolution. Subsequent builds run offline once the Gradle
  cache is populated.
- A device or emulator with a biometric enrolled, running Android 8.0
  (API 26) or newer, for installation.

## Quick start

```bash
./gradlew :app:installDebug      # debug APK signed with the auto-generated debug keystore
./gradlew :app:assembleDebug     # build the APK without installing
./gradlew :app:test              # all unit tests including the privacy gate + analytics + streak / milestone / goal calculators + XLSX round-trip
./gradlew detekt                 # Kotlin lint + the no-Log.* rule
```

Or build via the included GitHub Actions workflow — `.github/workflows/build.yml`
publishes the debug APK as a workflow artifact (`fintrack-debug-apk`) on
every push and writes a diagnostic snapshot back to the branch on
failure (`.ci_diag/last-run.md`).

## How privacy is enforced

The privacy guarantees above are checked by automated tests under
`app/src/test/kotlin/com/fintrack/privacy/`:

| Test | What it asserts |
|---|---|
| `ManifestPermissionsTest` | The manifest declares no networking permissions, blocks cleartext via `network_security_config`, and disables Auto Backup via the `no_backup` XML rules. |
| `DependencyGraphTest` | The Gradle catalog and app build script reference no networking / analytics SDKs (okhttp, retrofit, ktor-client, volley, org.apache.http, segment, amplitude, firebase, crashlytics, play-services-measurement). |
| `SnapshotDaoUserScopedTest` | Reflection-based: every read method on `SnapshotDao`, `HoldingValueDao`, `LoanDao`, `LoanValueDao`, `MilestoneDao`, `GoalDao`, `StreakStateDao` declares a `userId` parameter. Adding a new method that omits it fails the build. |
| `LogPatternTest` | Source-scans `app/src/main/` and fails on any `Log.[vdiwe]/wtf` or `println`. |
| `MoneyTypesTest` | The data and domain layers contain no `Double` or `Float` fields. |
| `BackupCodecsTest` | `CryptoBox` round-trips, rejects tampered ciphertext, and rejects the wrong passphrase. CSV codec round-trips quoted fields, commas, and embedded newlines. |
| `XlsxCodecTest` | XLSX template generator + importer round-trip across all five sheets. |
| `StreakCalculatorTest`, `MilestoneDetectorTest`, `GoalCalculatorTest`, `SnapshotAnalyticsTest`, `SeedDataTest`, `DateFormatterTest`, `IndianCurrencyFormatterTest`, `AimEditorStateTest` | Domain math + locked spec defaults. |

Run the privacy gate alone with:

```bash
./gradlew :app:test --tests 'com.fintrack.privacy.*' --tests 'com.fintrack.data.backup.*' --tests 'com.fintrack.data.xlsx.*'
```

## Project layout

Single Gradle module (`:app`).

```
app/src/main/kotlin/com/fintrack/
  FintrackApp.kt                 @HiltAndroidApp; ProcessLifecycleObserver wiring + debug seed
  MainActivity.kt                FLAG_SECURE host, FragmentActivity for BiometricPrompt
  di/DatabaseModule.kt           Room ⇄ SQLCipher SupportOpenHelperFactory wiring
  security/                      KeystorePassphraseStore, BiometricAuthenticator, InactivityTracker,
                                 MoneyTextField, PrivacyOverlay, ClipboardAutoClear
  data/db/                       Entities (13), DAOs (13), type converters, FintrackDatabase, seed catalog
  data/repo/                     User, GlobalSettings, Holding, Snapshot, Aim, Taxonomy, Loan, Goal,
                                 Streak, Milestone repositories
  data/backup/                   BackupRepository (orchestrator), BackupModels (v3), CryptoBox, CsvCodec
  data/xlsx/                     XlsxCodec (POI write+read), XlsxImportApplier
  domain/UserScope.kt            Active-user holder; repositories take userId from here
  domain/analytics/              SnapshotAnalyticsCalculator (totals, drift, net worth, …)
  domain/streaks/                StreakCalculator (pure)
  domain/milestones/             MilestoneDetector (pure)
  domain/goals/                  GoalCalculator (pure)
  domain/util/                   formatIndianCurrency · DateFormatter (dd-MMM-yyyy)
  ui/lock/                       Lock + biometric-unavailable screens
  ui/onboarding/                 Create-first-profile flow
  ui/picker/                     Profile picker (auto-skip-when-single)
  ui/home/                       Top-bar + tabs (Snapshots / Journey / Settings)
  ui/home/snapshots/             Snapshot card with Net Worth headline + 2×2 grid + 🔥 chip + nudge banner
  ui/home/overview/              Journey tab: Charts sub-tab (3-line default chart, Goals card, history table) + Wins sub-tab
  ui/journey/wins/               Vertical milestone timeline with gutter dots
  ui/journey/goals/              Journey Goals card + chart overlay logic
  ui/celebration/                Material 3 ModalBottomSheet for milestone unlocks
  ui/snapshots/entry/            Three-level entry form + LOANS section + Assets·Liabilities·Net Worth chips
  ui/snapshots/detail/           Snapshot detail (allocation table, sub-bucket sub-sections, Loans card, footer)
  ui/settings/                   Aim editor (slider + distribute-remainder), Holdings (3-level expandable),
                                 Loans (CRUD), Goals (CRUD), Excel (template + import), Manage users,
                                 Backup, About (with View Manifest)
  ui/common/CommonDatePickerSheet.kt
  ui/navigation/AppNavGraph.kt   State-driven gate + NavController for sub-routes
```

## Phased deliverables (v3)

| # | Phase | Headline |
|---|---|---|
| A | Privacy hardening + clean schema reset | network_security_config, R8 Log strip, View Manifest, three-level taxonomy + loans/streaks/milestones/goals entities. |
| B | Date format + snapshot card + entry redesign | `dd-MMM-yyyy` everywhere; Net Worth headline + 2×2 grid; three-level grouped entry form. |
| C | Loans UI | Entry-form Loans section, Settings → Loans (CRUD), snapshot detail Loans card. |
| D | Three-level Holdings + dynamic Aim editor | Expandable tree with rename/reorder/activate at every level; "Fill gap" affordance on Aim editor. |
| E | My Journey So Far rewrite | Three-line default chart (Wealth / Earning / Investment); Charts + Wins sub-tabs; expanded history table. |
| F | Streaks | Calendar-month logic with current-month grace; chip + nudge banner. |
| G | Milestones | 13 types, detector with idempotent re-runs, ModalBottomSheet celebration, Wins timeline. |
| H | Goals | NET_WORTH + DEBT_FREE goal types, pace bands, Settings CRUD, Journey card + chart overlay. |
| I | XLSX template + import | Apache POI 5.2.5 + R8 keep rules; SAF download/import; preview with Skip/Replace radio. |
| J | CSV/JSON export updates | All v3 entities round-trip; `dd-MMM-yyyy` for CSV; v2 backup rejection. |
| K | Polish | Empty/error states, README, version bump to 3.0.0, full test pass. |

## Signed release build

The release build type is configured but unsigned by default. To produce
a signed APK without committing keys:

1. Generate a keystore once:
   ```bash
   keytool -genkey -v -keystore fintrack-release.jks -alias fintrack \
     -keyalg RSA -keysize 4096 -validity 10000
   ```
   Keep the keystore file outside the repository (`.gitignore` already
   excludes `*.jks` and `keystore.properties`).

2. Create `keystore.properties` next to `app/build.gradle.kts` with:
   ```properties
   storeFile=/abs/path/to/fintrack-release.jks
   storePassword=...
   keyAlias=fintrack
   keyPassword=...
   ```
   This file is `.gitignore`d.

3. Build:
   ```bash
   ./gradlew :app:assembleRelease
   ```
   When `keystore.properties` exists, the release build is signed with that
   key. When it doesn't, the release build is left unsigned (the debug
   build is always signed by the standard auto-generated debug keystore).

The `keystore.properties` shape is documented inline in
`app/build.gradle.kts`.

## Out of scope (v3)

- Per-profile authentication (PIN, password, distinct biometric).
- Cross-user comparisons or household roll-ups.
- Tax-aware reporting (LTCG / STCG).
- Instrument-level holdings inside a platform (e.g. individual MF
  schemes inside a Degree212 portfolio).
- Cloud sync of any kind.
- Widgets, notifications, background work.
- Light/dark theme toggle (uses system default).

## License

Internal project for personal use. Not for redistribution without the
author's permission.
