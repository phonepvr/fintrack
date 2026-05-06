# FinTrack

A personal **wealth tracker** for Android — offline, private, and built for the Indian financial context.

Track your net worth across assets and loans, set and monitor financial goals, maintain a savings streak, and celebrate milestones — all without a single byte leaving your device.

---

## Features

- **Net Worth at a glance** — Every snapshot shows your total assets minus liabilities as the headline number. Drill into any snapshot to see a full breakdown by asset class and sub-bucket.
- **Loans** — Record each loan (home, car, personal, etc.) with its original amount, monthly EMI, and outstanding balance. Net Worth is always assets minus your real liabilities.
- **Goals** — Set a Net Worth target or a Debt-Free goal. FinTrack tracks your pace (Ahead / On Track / Behind / Achieved) against a straight-line projection and overlays the target on your wealth chart.
- **Streaks** — Every calendar month you record a snapshot keeps your streak alive. A flame chip appears on your latest snapshot card; a nudge appears after the 15th if the month is still empty.
- **Milestones** — 13 automatically detected milestones: first snapshot, every ₹50 L through ₹10 Cr, wealth doubled or tripled, debt-free, 50 % year-on-year growth, 12 and 36 months tracked, and coming-back-from-a-break. Recent milestones celebrate with a bottom sheet; older ones are silently recorded in your Wins timeline.
- **Journey tab** — A multi-line chart of your wealth, earnings, and investments over time; a Goals card; and a Wins sub-tab listing your milestone history.
- **XLSX import** — Download an empty Excel template, fill it in offline (great for migrating from a spreadsheet), and import it back. Conflicts (same snapshot date) give you a Skip or Replace choice per sheet.
- **Multiple profiles** — Family members or separate portfolios can each have their own isolated profile on one device.
- **Indian currency formatting** — Amounts display as ₹ with lakhs (L) and crores (Cr) throughout.

---

## Privacy & Security

FinTrack has **no internet access** — not now, not ever. The app manifest declares zero network permissions, and an automated test in the build pipeline verifies this on every release.

- **All data stays on your device.** There is no account, no server, no analytics, no crash reporting.
- **Your database is encrypted.** A unique 256-bit key is generated when you first launch the app and stored in your device's secure hardware (Android Keystore). No one can read the database file without unlocking your device.
- **Biometric lock.** The app requires fingerprint or face unlock every time you open it, and again after a period of inactivity (default: 60 seconds).
- **Screenshot protection.** The app is marked secure so your portfolio data never appears in the recents screen or in screenshots taken by other apps.
- **Backups are yours.** You can export an encrypted JSON backup (password-protected, AES-256) or a plain CSV zip. These are saved wherever you choose on your device — no cloud upload.
- **Profile separation is organisational, not cryptographic.** Multiple profiles share the same database key. The About screen in the app explains this directly.

---

## Install

Download the latest APK from the [Releases page](../../releases/latest) and install it on any Android 8.0+ device with a biometric sensor enrolled.

> **Android may warn you about installing from an unknown source.** This is expected for apps distributed outside the Play Store. You can review the app's declared permissions in *About → View Manifest* after installation — the list is empty of any network or sensitive permissions.

---

## Build from source

Requires JDK 17 and Android SDK 35.

```bash
./gradlew :app:assembleDebug    # build debug APK
./gradlew :app:installDebug     # build and install on a connected device
./gradlew :app:test             # run unit tests
```

CI runs on every push via GitHub Actions and publishes the debug APK to the Releases page automatically.

---

## License

Personal project. Not for redistribution without the author's permission.
