package com.fintrack.security

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BiometricAuthenticator @Inject constructor() {

    enum class Availability { Available, NoneEnrolled, HardwareUnavailable, Unsupported }

    enum class AuthOutcome { Succeeded, Failed, Cancelled, ErrorNoBiometric, ErrorOther }

    fun availability(activity: FragmentActivity): Availability {
        val manager = BiometricManager.from(activity)
        return when (manager.canAuthenticate(BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> Availability.Available
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> Availability.NoneEnrolled
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> Availability.HardwareUnavailable
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> Availability.Unsupported
            else -> Availability.Unsupported
        }
    }

    /**
     * Shows the platform biometric prompt. The PIN/password fallback is
     * intentionally NOT enabled — spec is firm on biometric-only access.
     */
    fun authenticate(
        activity: FragmentActivity,
        title: String,
        subtitle: String,
        negativeButtonText: String,
        onResult: (AuthOutcome) -> Unit,
    ) {
        if (availability(activity) != Availability.Available) {
            onResult(AuthOutcome.ErrorNoBiometric)
            return
        }
        val executor = androidx.core.content.ContextCompat.getMainExecutor(activity)
        val prompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    onResult(AuthOutcome.Succeeded)
                }

                override fun onAuthenticationFailed() {
                    // Wrong fingerprint / face — keep the prompt up (system handles retries).
                    onResult(AuthOutcome.Failed)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    val outcome = when (errorCode) {
                        BiometricPrompt.ERROR_USER_CANCELED,
                        BiometricPrompt.ERROR_NEGATIVE_BUTTON,
                        BiometricPrompt.ERROR_CANCELED -> AuthOutcome.Cancelled

                        BiometricPrompt.ERROR_NO_BIOMETRICS,
                        BiometricPrompt.ERROR_HW_NOT_PRESENT,
                        BiometricPrompt.ERROR_HW_UNAVAILABLE -> AuthOutcome.ErrorNoBiometric

                        else -> AuthOutcome.ErrorOther
                    }
                    onResult(outcome)
                }
            },
        )
        val info = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setSubtitle(subtitle)
            .setNegativeButtonText(negativeButtonText)
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .setConfirmationRequired(false)
            .build()
        prompt.authenticate(info)
    }
}
