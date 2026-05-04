package com.fintrack.security

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Per spec §1.5: any time the app puts text on the system clipboard it
 * must also schedule that text to be cleared after 30 seconds, so a
 * snapshot of money values doesn't sit in the clipboard indefinitely.
 *
 * The 30s window matches Android 13+'s system clipboard-clear default
 * for sensitive items, but we also enforce it explicitly for older
 * versions where the OS does not auto-clear.
 */
@Singleton
class ClipboardAutoClear @Inject constructor() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    /**
     * Copies [text] to the clipboard with the IS_SENSITIVE hint (Android
     * 13+) and schedules a clear after [CLEAR_AFTER_MS].
     */
    fun copy(context: Context, label: String, text: String) {
        val cm = ContextCompat.getSystemService(context, ClipboardManager::class.java) ?: return
        val clip = ClipData.newPlainText(label, text)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            clip.description.extras = android.os.PersistableBundle().apply {
                putBoolean("android.content.extra.IS_SENSITIVE", true)
            }
        }
        cm.setPrimaryClip(clip)

        scope.launch {
            delay(CLEAR_AFTER_MS)
            // Only clear if our text is still on the clipboard — don't
            // wipe whatever the user may have copied since.
            val current = cm.primaryClip?.getItemAt(0)?.text?.toString()
            if (current == text) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    cm.clearPrimaryClip()
                } else {
                    cm.setPrimaryClip(ClipData.newPlainText("", ""))
                }
            }
        }
    }

    companion object {
        const val CLEAR_AFTER_MS = 30_000L
    }
}
