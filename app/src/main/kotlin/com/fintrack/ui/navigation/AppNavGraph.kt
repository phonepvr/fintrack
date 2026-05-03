package com.fintrack.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintrack.security.BiometricAuthenticator
import com.fintrack.ui.AppState
import com.fintrack.ui.AppViewModel
import com.fintrack.ui.home.HomeRoute
import com.fintrack.ui.lock.BiometricUnavailableScreen
import com.fintrack.ui.lock.LockRoute
import com.fintrack.ui.onboarding.CreateFirstProfileRoute

/**
 * Phase 1 has no NavController-driven navigation. The state machine in
 * [AppViewModel] decides what to render. Phase 2 will add a NavHost when the
 * snapshot list / detail / entry screens land.
 */
@Composable
fun FintrackApp(
    biometricAuthenticator: BiometricAuthenticator,
    appViewModel: AppViewModel = hiltViewModel(),
) {
    val state by appViewModel.state.collectAsState()

    when (state) {
        AppState.Loading -> Loading()

        AppState.Locked -> LockRoute(
            biometric = biometricAuthenticator,
            onUnlocked = appViewModel::onUnlocked,
            onBiometricUnavailable = appViewModel::onBiometricUnavailable,
        )

        AppState.BiometricUnavailable -> BiometricUnavailableScreen(
            onRetry = appViewModel::onBiometricAvailable,
        )

        AppState.NeedsFirstProfile -> CreateFirstProfileRoute(
            onProfileCreated = appViewModel::onProfileCreated,
        )

        is AppState.Ready -> HomeRoute(
            onSwitchUser = {
                // Phase 1: no profile picker. Phase 2 wires this up.
            },
        )
    }
}

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
