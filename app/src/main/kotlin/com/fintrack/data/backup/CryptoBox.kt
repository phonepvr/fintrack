package com.fintrack.data.backup

import java.io.ByteArrayInputStream
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * Encrypted-JSON backup format (versioned, all little-endian where needed):
 *
 *   bytes  field
 *   4      magic           "FNTK"
 *   4      format version  Int (BE via DataInput)
 *   4      iter count      Int (≥ 200_000 enforced on decode)
 *   2      salt length     Short
 *   N      salt            random bytes
 *   2      nonce length    Short
 *   M      nonce (IV)      random bytes
 *   4      payload length  Int
 *   K      ciphertext      AES-256/GCM with 128-bit auth tag (length includes tag)
 *
 * The passphrase is NEVER persisted — the user types it in the SAF dialog
 * each time. Key derivation: PBKDF2-HMAC-SHA256.
 */
internal object CryptoBox {

    private const val MAGIC = "FNTK"
    private const val FORMAT_VERSION = 1
    private const val DEFAULT_ITERATIONS = 200_000
    private const val MIN_ACCEPTED_ITERATIONS = 100_000
    private const val KEY_BITS = 256
    private const val GCM_TAG_BITS = 128
    private const val SALT_BYTES = 16
    private const val NONCE_BYTES = 12

    fun encrypt(passphrase: CharArray, plaintext: ByteArray, out: OutputStream) {
        val random = SecureRandom()
        val salt = ByteArray(SALT_BYTES).also(random::nextBytes)
        val nonce = ByteArray(NONCE_BYTES).also(random::nextBytes)
        val key = deriveKey(passphrase, salt, DEFAULT_ITERATIONS)
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"), GCMParameterSpec(GCM_TAG_BITS, nonce))
        val ct = cipher.doFinal(plaintext)
        DataOutputStream(out).use { d ->
            d.writeBytes(MAGIC)
            d.writeInt(FORMAT_VERSION)
            d.writeInt(DEFAULT_ITERATIONS)
            d.writeShort(salt.size)
            d.write(salt)
            d.writeShort(nonce.size)
            d.write(nonce)
            d.writeInt(ct.size)
            d.write(ct)
        }
    }

    fun decrypt(passphrase: CharArray, input: InputStream): ByteArray {
        val bytes = input.readBytes()
        DataInputStream(ByteArrayInputStream(bytes)).use { d ->
            val magic = ByteArray(4).also { d.readFully(it) }
            require(String(magic, Charsets.US_ASCII) == MAGIC) { "Not a FinTrack backup file" }
            val version = d.readInt()
            require(version == FORMAT_VERSION) { "Unsupported backup version $version" }
            val iterations = d.readInt()
            require(iterations >= MIN_ACCEPTED_ITERATIONS) { "Backup parameters too weak" }
            val saltLen = d.readShort().toInt() and 0xFFFF
            val salt = ByteArray(saltLen).also { d.readFully(it) }
            val nonceLen = d.readShort().toInt() and 0xFFFF
            val nonce = ByteArray(nonceLen).also { d.readFully(it) }
            val ctLen = d.readInt()
            val ct = ByteArray(ctLen).also { d.readFully(it) }

            val key = deriveKey(passphrase, salt, iterations)
            val cipher = Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "AES"), GCMParameterSpec(GCM_TAG_BITS, nonce))
            return cipher.doFinal(ct)
        }
    }

    private fun deriveKey(passphrase: CharArray, salt: ByteArray, iterations: Int): ByteArray {
        val spec = PBEKeySpec(passphrase, salt, iterations, KEY_BITS)
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        return factory.generateSecret(spec).encoded.also {
            spec.clearPassword()
        }
    }
}
