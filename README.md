# FinTrack

Android-only, **offline-first**, **privacy-focused** personal portfolio tracker.
Multiple profiles share one device, each with their own isolated portfolio.

> Status: Phases 1–6 complete (foundation, snapshots, detail, overview, full
> settings + bidirectional backup, polish). Ready to install on a personal
> device.

---

## Hard guarantees

- **Zero network access.** The manifest declares no `INTERNET`,
  `ACCESS_NETWORK_STATE`, or `ACCESS_WIFI_STATE` permission. Adding any of
  them fails the privacy acceptance test in CI.
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
  capture portfolio data.
- **Profile separation is organisational, not cryptographic.** All profiles
  share the same database key. The About screen surfaces this explicitly.
- **Backups are user-driven and active-profile-scoped.** Auto Backup and
  device-to-device transfer are disabled. The Settings → Backup screen
  produces:
  - Encrypted JSON: AES-256-GCM with PBKDF2-HMAC-SHA256 (200 000 iterations)
    keyed by your passphrase.
  - Plain CSV (zipped): same data, unencrypted, with a prominent warning
    in the UI.

---

## Build requirements

- JDK 17 or newer (the project compiles against bytecode 17).
- Android SDK 35 with build-tools 35.0.0 and platform-tools.
  Set `ANDROID_HOME` (or `ANDROID_SDK_ROOT`) before invoking Gradle.
- Internet access to Google Maven and Maven Central for first-run
  dependency resolution. Subsequent builds run offline once the Gradle
  cache is populated.
- A device or emulator with a biometric enrolled, running Android 8.0
  (API 26) or newer, for installation.

## Quick start

```bash
./gradlew :app:installDebug                 # debug APK signed with the auto-generated debug keystore
./gradlew :app:assembleDebug                # build the APK without installing
./gradlew :app:test                         # all unit tests including the privacy gate
./gradlew detekt                            # Kotlin lint
```

Or build via the included GitHub Actions workflow — `.github/workflows/build.yml`
publishes the debug APK as a workflow artifact (`fintrack-debug-apk`).

## How privacy is enforced

The privacy guarantees above are checked by automated tests under
`app/src/test/kotlin/com/fintrack/privacy/`:

| Test | What it asserts |
|---|---|
| `ManifestPermissionsTest` | The manifest declares no networking permissions, blocks cleartext, and disables Auto Backup. |
| `DependencyGraphTest` | The Gradle catalog and app build script reference no networking / analytics SDKs (okhttp / retrofit / firebase / crashlytics / analytics / play-services-measurement). |
| `SnapshotDaoUserScopedTest` | Reflection-based: every read method on `SnapshotDao` and `HoldingValueDao` declares a `userId` parameter. Adding a new method that omits it fails the build. |
| `SqlCipherEncryptionTest` | `DatabaseModule` wires `SupportOpenHelperFactory` with a Keystore-sourced passphrase and loads `libsqlcipher`. |
| `FlagSecureTest` | `MainActivity` sets `FLAG_SECURE` on its window before `setContent`. |
| `MoneyTypesTest` | The data and domain layers contain no `Double` or `Float` fields. |
| `BackupCodecsTest` | `CryptoBox` round-trips, rejects tampered ciphertext, and rejects the wrong passphrase. CSV codec round-trips quoted fields, commas, and embedded newlines. |

Run the gate with:

```bash
./gradlew :app:test --tests 'com.fintrack.privacy.*'  --tests 'com.fintrack.data.backup.*'
```

The full test suite (privacy + analytics math + Indian currency formatter +
backup codecs + DAO scope) currently runs **48 unit tests**.

## Project layout

Single Gradle module (`:app`).

```
app/src/main/kotlin/com/fintrack/
  FintrackApp.kt                 @HiltAndroidApp; ProcessLifecycleObserver wiring + debug seed
  MainActivity.kt                FLAG_SECURE host, FragmentActivity for BiometricPrompt
  di/DatabaseModule.kt           Room ⇄ SQLCipher SupportOpenHelperFactory wiring
  security/                      KeystorePassphraseStore, BiometricAuthenticator, InactivityTracker
  data/db/                       Entities, DAOs, type converters, FintrackDatabase, seed catalog
  data/repo/                     User, GlobalSettings, Holding, Snapshot, UserSettings repositories
  data/backup/                   BackupRepository (orchestrator), BackupModels, CryptoBox, CsvCodec
  domain/UserScope.kt            Active-user holder; repositories take userId from here
  domain/analytics/              SnapshotAnalyticsCalculator (totals, drift, savings rollup, …)
  domain/util/                   Indian currency formatter
  ui/lock/                       Lock + biometric-unavailable screens
  ui/onboarding/                 Create-first-profile flow
  ui/picker/                     Profile picker (auto-skip-when-single)
  ui/home/                       Top-bar + tabs (Snapshots / Overview / Settings)
  ui/home/snapshots/             Snapshots tab list + ViewModel
  ui/home/overview/              Overview tab: headline, period filters, Canvas chart, history table
  ui/snapshots/entry/            Snapshot entry / edit form with running-total chip
  ui/snapshots/detail/           Snapshot detail (allocation table, breakdowns, footer chips)
  ui/settings/                   Settings tab + sub-screens (aim, holdings, users, backup, about)
  ui/navigation/AppNavGraph.kt   State-driven gate + NavController for sub-routes
```

## Phased deliverables (spec §7)

1. **Phase 1** — Foundation: Gradle/Hilt/Compose setup, Room with SQLCipher,
   biometric lock, FLAG_SECURE, first-profile onboarding, privacy
   acceptance tests.
2. **Phase 2** — Snapshot CRUD, profile picker with auto-skip, switch-user,
   debug-only seed users (Mr. X + Spouse from spec §5).
3. **Phase 3** — Snapshot Detail (allocation table with drift colour bands,
   breakdown cards, footer chips with Δ vs previous).
4. **Phase 4** — Overview tab (headline cards, Canvas trend chart with 4
   views × 4 period filters, sortable history table).
5. **Phase 5** — Full Settings: aim % editor (per user, sum-to-100), holdings
   management (global), inactivity timeout, manage users, encrypted JSON
   export/import, plain CSV-zip export/import, About with the
   organisational-separation disclaimer.
6. **Phase 6** — Polish: empty/error/loading states across screens, README,
   signed release build scaffold, tightened Detekt rules.

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

The `keystore.properties` shape is documented inline in `app/build.gradle.kts`.

## Out of scope (v1)

Per spec §9 — kept out of v1 deliberately:

- Per-profile authentication (PIN, password, distinct biometric).
- Cross-user comparisons or household roll-ups.
- Goal tracking (e.g. retirement corpus targets).
- Tax-aware reporting (LTCG / STCG).
- Instrument-level holdings inside a platform (e.g. individual MF schemes inside Degree212).
- Cloud sync of any kind.
- Widgets, notifications, background work.
- Light/dark theme toggle (uses system default).

## License

Internal project for personal use. Not for redistribution without the
author's permission.
