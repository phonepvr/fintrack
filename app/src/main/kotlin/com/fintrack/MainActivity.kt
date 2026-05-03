package com.fintrack

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.compose.setContent
import androidx.fragment.app.FragmentActivity
import com.fintrack.security.BiometricAuthenticator
import com.fintrack.ui.navigation.FintrackApp as FintrackAppRoot
import com.fintrack.ui.theme.FintrackTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Single activity host.
 *
 * - Extends [FragmentActivity] (not ComponentActivity) because androidx
 *   BiometricPrompt needs a FragmentActivity to attach its dialog.
 * - Sets [WindowManager.LayoutParams.FLAG_SECURE] BEFORE setContent so that
 *   neither the IME, recents thumbnail, nor screenshot tooling can capture
 *   the window. This flag is asserted by [com.fintrack.privacy.FlagSecureTest].
 */
@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject lateinit var biometricAuthenticator: BiometricAuthenticator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE,
        )
        setContent {
            FintrackTheme {
                FintrackAppRoot(biometricAuthenticator = biometricAuthenticator)
            }
        }
    }
}
