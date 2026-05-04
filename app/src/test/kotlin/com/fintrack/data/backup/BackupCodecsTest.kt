package com.fintrack.data.backup

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class BackupCodecsTest {

    @Test
    @DisplayName("CryptoBox round-trip recovers the original plaintext")
    fun cryptoRoundTrip() {
        val pass = "correct horse battery staple".toCharArray()
        val plaintext = "the quick brown fox jumped over the lazy dog".toByteArray()
        val out = ByteArrayOutputStream()
        CryptoBox.encrypt(pass.copyOf(), plaintext, out)
        val recovered = CryptoBox.decrypt(pass.copyOf(), ByteArrayInputStream(out.toByteArray()))
        assertThat(recovered).isEqualTo(plaintext)
    }

    @Test
    @DisplayName("CryptoBox decrypt throws on tampered ciphertext")
    fun cryptoTampered() {
        val pass = "secret".toCharArray()
        val plaintext = "Hello".toByteArray()
        val out = ByteArrayOutputStream()
        CryptoBox.encrypt(pass.copyOf(), plaintext, out)
        val tampered = out.toByteArray()
        // Flip one byte deep in the ciphertext.
        tampered[tampered.size - 5] = tampered[tampered.size - 5].inc()
        assertThrows<Exception> {
            CryptoBox.decrypt(pass.copyOf(), ByteArrayInputStream(tampered))
        }
    }

    @Test
    @DisplayName("CryptoBox decrypt throws with the wrong passphrase")
    fun cryptoWrongPassphrase() {
        val pass = "right".toCharArray()
        val out = ByteArrayOutputStream()
        CryptoBox.encrypt(pass.copyOf(), "payload".toByteArray(), out)
        assertThrows<Exception> {
            CryptoBox.decrypt("wrong".toCharArray(), ByteArrayInputStream(out.toByteArray()))
        }
    }

    @Test
    @DisplayName("CSV codec round-trips simple rows")
    fun csvSimple() {
        val rows = listOf(
            listOf("a", "b", "c"),
            listOf("1", "2", "3"),
        )
        val text = rows.joinToString("\n") { CsvCodec.encodeRow(it) } + "\n"
        val parsed = CsvCodec.parse(text)
        assertThat(parsed).isEqualTo(rows)
    }

    @Test
    @DisplayName("CSV codec preserves commas, quotes, and newlines inside quoted fields")
    fun csvQuoting() {
        val rows = listOf(
            listOf("name with, comma", "value \"quoted\"", "line1\nline2"),
        )
        val text = rows.joinToString("\n") { CsvCodec.encodeRow(it) } + "\n"
        val parsed = CsvCodec.parse(text)
        assertThat(parsed).isEqualTo(rows)
    }
}
