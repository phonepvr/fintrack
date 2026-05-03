# FinTrack

Android-only, offline-first, privacy-focused, multi-user personal portfolio tracker.

> **Phase 1 only**: This commit ships the foundation — biometric lock, encrypted Room
> database, first-profile onboarding, and the empty home shell. Snapshot entry,
> overview/charts, settings, and backup/import land in Phases 2–6.

## Hard guarantees

- **Zero network access.** The manifest declares no `INTERNET`,
  `ACCESS_NETWORK_STATE`, or `ACCESS_WIFI_STATE` permission. Any addition
  fails `app/src/test/.../privacy/ManifestPermissionsTest`.
- **Encrypted at rest.** Room is opened through SQLCipher
  (`net.zetetic:sqlcipher-android`) using a 256-bit passphrase generated on
  first launch and stored in `EncryptedSharedPreferences` whose master key
  lives in the Android Keystore. The plaintext passphrase never touches a
  regular SharedPreferences file.
- **Biometric on every cold launch.** No PIN fallback. After the configured
  inactivity timeout (default 60 s), foregrounding the app re-locks until
  biometric succeeds again.
- **`FLAG_SECURE` always on.** Set on `MainActivity` before any Compose
  surface is rendered, so screenshots and recents thumbnails can never
  capture portfolio data.
- **Profile picker is organisational, not cryptographic.** All profiles share
  the same database key. The About screen surfaces this disclaimer.

## Build requirements

- JDK 17 or newer (the project compiles against bytecode 17).
- Android SDK 35 with build-tools 35.0.0 and platform-tools.
  Set `ANDROID_HOME` (or `ANDROID_SDK_ROOT`) before invoking Gradle.
- Internet access to Google Maven and Maven Central for first-run dependency
  resolution. Subsequent builds run offline once the Gradle cache is populated.
- A device or emulator with biometric enrolled, running Android 8.0 (API 26)
  or newer.

This sandbox is missing both the Android SDK and Google Maven access, so
**all build/test verification must run on the developer's local machine**.
The plan and source were authored without an executed build; expect minor
fix-ups on first `./gradlew assembleDebug`.

## Building

```bash
./gradlew :app:assembleDebug                # debug APK
./gradlew :app:assembleRelease              # release APK (unsigned by default)
./gradlew :app:installDebug                 # install on attached device
```

The `release` build type has `isMinifyEnabled = true` and applies
`proguard-rules.pro`; signing config is intentionally omitted from version
control.

## Verification gate (run on every change)

```bash
./gradlew :app:test --tests '*privacy*'     # five privacy acceptance tests
./gradlew :app:test                         # full unit test suite
./gradlew detekt                            # lint-style checks (no `!!`, etc.)
./gradlew :app:lint                         # AGP lint
./gradlew :app:connectedDebugAndroidTest    # FLAG_SECURE + biometric runtime tests (Phase 6)
```

The privacy acceptance tests in `app/src/test/kotlin/com/fintrack/privacy/`:

| Test | Spec §6 line | What it asserts |
|---|---|---|
| `ManifestPermissionsTest` | 1 | No `INTERNET`/`ACCESS_NETWORK_STATE`/`ACCESS_WIFI_STATE`; no cleartext; auto-backup off. |
| `DependencyGraphTest`     | 2 | Version catalog and app build script reference no networking/analytics SDKs. |
| `SnapshotDaoUserScopedTest` | 6 | All read methods on `SnapshotDao`/`HoldingValueDao` declare a `userId` parameter. |
| `SqlCipherEncryptionTest` | 3 | `DatabaseModule` wires `SupportOpenHelperFactory` with a Keystore-sourced passphrase and loads `libsqlcipher`. |
| `FlagSecureTest`          | 4 | `MainActivity` sets `FLAG_SECURE` on its window before `setContent`. |
| `MoneyTypesTest`          | §8 | No `Double`/`Float` fields in the data or domain layers. |

Tests 4 and 5 are source-level checks today; Phase 6 adds the runtime
`androidTest` equivalents that open a real DB file with stock SQLite and
launch `MainActivity` to inspect window flags at runtime.

## Phase 1 scope

What you can do today after `installDebug`:

1. Cold launch → biometric prompt (or biometric-unavailable screen if none enrolled).
2. After biometric success → "Create your first profile" form (name + colour).
3. Submit → land on the empty home shell with three tabs (Snapshots, Overview,
   Settings), top bar showing the new user's name + colour dot, and a
   "Switch user" affordance (no-op until Phase 2).

Backgrounding the app for longer than 60 s and returning re-prompts biometric.

## Phase 2 scope (next)

- Profile picker, switch-user wiring, multi-user creation in Settings.
- Snapshot list (per active user) with empty state.
- New / edit / duplicate / delete snapshots with the live running-total chip.
- Debug-only seeding of the spec §5 sample data (User A and User B).

## Module layout

Single Gradle module (`:app`) for v1. Module split is deferred until Phase 4
when the chart subsystem lands.

```
app/src/main/kotlin/com/fintrack/
  FintrackApp.kt                 @HiltAndroidApp + ProcessLifecycleObserver wiring
  MainActivity.kt                FLAG_SECURE host
  di/                            Hilt modules (DatabaseModule, …)
  security/                      KeystorePassphraseStore, BiometricAuthenticator,
                                 InactivityTracker
  data/db/                       Entities, DAOs, type converters, FintrackDatabase, seed
  data/repo/                     User, GlobalSettings, Holding repositories
  domain/                        UserScope, AssetClass
  ui/                            Theme, lock, onboarding, home, navigation
```

## License

Not specified. Internal project for personal use; do not redistribute without
the author's permission.
