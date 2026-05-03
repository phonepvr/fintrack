package com.fintrack.ui.lock

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.fintrack.R
import com.fintrack.security.BiometricAuthenticator

@Composable
fun LockRoute(
    biometric: BiometricAuthenticator,
    onUnlocked: () -> Unit,
    onBiometricUnavailable: () -> Unit,
) {
    val context = LocalContext.current
    val activity = context.findFragmentActivity()
        ?: error("LockRoute must be hosted by a FragmentActivity for BiometricPrompt.")
    var promptShown by remember { mutableStateOf(false) }
    val title = stringResource(R.string.biometric_prompt_title)
    val subtitle = stringResource(R.string.biometric_prompt_subtitle)
    val negative = stringResource(R.string.biometric_prompt_negative)

    LaunchedEffect(Unit) {
        when (biometric.availability(activity)) {
            BiometricAuthenticator.Availability.Available -> {
                if (!promptShown) {
                    promptShown = true
                    biometric.authenticate(activity, title, subtitle, negative) { outcome ->
                        when (outcome) {
                            BiometricAuthenticator.AuthOutcome.Succeeded -> onUnlocked()
                            BiometricAuthenticator.AuthOutcome.ErrorNoBiometric -> onBiometricUnavailable()
                            else -> promptShown = false
                        }
                    }
                }
            }
            else -> onBiometricUnavailable()
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = stringResource(R.string.lock_title),
                style = MaterialTheme.typography.headlineMedium,
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.lock_subtitle),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(24.dp))
            Button(onClick = {
                if (!promptShown) {
                    promptShown = true
                    biometric.authenticate(activity, title, subtitle, negative) { outcome ->
                        when (outcome) {
                            BiometricAuthenticator.AuthOutcome.Succeeded -> onUnlocked()
                            BiometricAuthenticator.AuthOutcome.ErrorNoBiometric -> onBiometricUnavailable()
                            else -> promptShown = false
                        }
                    }
                }
            }) {
                Text(stringResource(R.string.lock_unlock))
            }
        }
    }
}

private tailrec fun Context.findFragmentActivity(): FragmentActivity? = when (this) {
    is FragmentActivity -> this
    is ContextWrapper -> baseContext.findFragmentActivity()
    else -> null
}
