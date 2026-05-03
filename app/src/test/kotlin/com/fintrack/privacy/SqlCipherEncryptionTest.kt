package com.fintrack.privacy

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

/**
 * Privacy gate #4 from spec §6 (source-side coverage):
 *   The DB file on disk must fail to open as a normal SQLite database.
 *
 * Phase 1 verifies the wiring at the source level: [DatabaseModule] must (a)
 * load the SQLCipher native library, (b) build a SupportOpenHelperFactory
 * with a passphrase sourced from [KeystorePassphraseStore], and (c) hand
 * that factory to Room via openHelperFactory.
 *
 * Phase 6 adds an androidTest that creates a real DB on a device, then opens
 * the underlying file with stock android.database.sqlite.SQLiteDatabase and
 * asserts the file rejects the open. The runtime check requires SQLCipher's
 * Android JNI library, which is only loadable on Android (not desktop JVM
 * unit tests), hence the source-side check here.
 */
class SqlCipherEncryptionTest {

    private val databaseModuleSource: String by lazy {
        TestPaths.appFile("src/main/kotlin/com/fintrack/di/DatabaseModule.kt").readText()
    }

    @Test
    @DisplayName("DatabaseModule loads the SQLCipher native library")
    fun loadsSqlCipherNativeLib() {
        assertThat(databaseModuleSource).contains("System.loadLibrary(\"sqlcipher\")")
    }

    @Test
    @DisplayName("DatabaseModule wires Room through SupportOpenHelperFactory with a passphrase")
    fun roomUsesSupportOpenHelperFactory() {
        assertThat(databaseModuleSource).contains("SupportOpenHelperFactory(passphrase)")
        assertThat(databaseModuleSource).contains("openHelperFactory(factory)")
    }

    @Test
    @DisplayName("DatabaseModule sources the passphrase from KeystorePassphraseStore")
    fun passphraseFromKeystore() {
        assertThat(databaseModuleSource).contains("passphraseStore.getOrCreate()")
        assertThat(databaseModuleSource).contains("KeystorePassphraseStore")
    }

    @Test
    @DisplayName("KeystorePassphraseStore generates 256 random bits via SecureRandom")
    fun passphraseIs256BitSecureRandom() {
        val source = TestPaths.appFile(
            "src/main/kotlin/com/fintrack/security/KeystorePassphraseStore.kt",
        ).readText()
        assertThat(source).contains("SecureRandom")
        assertThat(source).contains("PASSPHRASE_BYTES = 32")
        assertThat(source).contains("EncryptedSharedPreferences")
        assertThat(source).contains("MasterKey")
    }
}
