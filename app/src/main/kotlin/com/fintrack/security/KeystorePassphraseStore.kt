package com.fintrack.security

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.SecureRandom
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Generates and persists the SQLCipher database passphrase.
 *
 * - The passphrase is 32 cryptographically random bytes (256 bits).
 * - Storage: [EncryptedSharedPreferences] backed by a [MasterKey] that lives
 *   in the Android Keystore (hardware-backed where the device supports it).
 * - The plaintext passphrase NEVER touches a regular SharedPreferences file
 *   or any disk path under our control; the Keystore-encrypted XML is the
 *   only at-rest copy.
 *
 * Lifecycle: [getOrCreate] is idempotent. The first call generates and writes;
 * every subsequent call returns the same bytes. If the EncryptedSharedPreferences
 * file is wiped (factory reset, app data clear), a new passphrase is generated
 * — at which point the existing fintrack.db will be unreadable. That's the
 * intended behaviour: data is irrecoverable without the Keystore-protected key.
 */
@Singleton
class KeystorePassphraseStore @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    private val prefs: SharedPreferences by lazy { buildEncryptedPrefs() }

    @Synchronized
    fun getOrCreate(): ByteArray {
        prefs.getString(KEY_PASSPHRASE_B64, null)?.let { encoded ->
            return Base64.decode(encoded, Base64.NO_WRAP)
        }
        val fresh = ByteArray(PASSPHRASE_BYTES).also { SecureRandom().nextBytes(it) }
        prefs.edit()
            .putString(KEY_PASSPHRASE_B64, Base64.encodeToString(fresh, Base64.NO_WRAP))
            .commit() // commit, not apply — we MUST persist before opening the DB
        return fresh
    }

    private fun buildEncryptedPrefs(): SharedPreferences {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .setUserAuthenticationRequired(false)
            .build()
        return EncryptedSharedPreferences.create(
            context,
            PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
        )
    }

    companion object {
        private const val PREFS_NAME = "fintrack_secure_prefs"
        private const val KEY_PASSPHRASE_B64 = "db_passphrase_v1"
        private const val PASSPHRASE_BYTES = 32 // 256-bit
    }
}
