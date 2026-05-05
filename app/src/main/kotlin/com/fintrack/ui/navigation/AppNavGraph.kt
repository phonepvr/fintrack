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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fintrack.security.BiometricAuthenticator
import com.fintrack.ui.AppState
import com.fintrack.ui.AppViewModel
import com.fintrack.ui.home.HomeRoute
import com.fintrack.ui.lock.BiometricUnavailableScreen
import com.fintrack.ui.lock.LockRoute
import com.fintrack.ui.onboarding.CreateFirstProfileRoute
import com.fintrack.ui.onboarding.OnboardingPagerRoute
import com.fintrack.ui.picker.ProfilePickerRoute
import com.fintrack.ui.settings.about.AboutRoute
import com.fintrack.ui.settings.about.ManifestViewerRoute
import com.fintrack.ui.settings.aim.AimEditorRoute
import com.fintrack.ui.settings.backup.BackupRoute
import com.fintrack.ui.settings.excel.ExcelRoute
import com.fintrack.ui.settings.goals.GoalsManagementRoute
import com.fintrack.ui.settings.holdings.HoldingsManagementRoute
import com.fintrack.ui.settings.loans.LoansManagementRoute
import com.fintrack.ui.settings.users.ManageUsersRoute
import com.fintrack.ui.snapshots.detail.SnapshotDetailRoute
import com.fintrack.ui.snapshots.detail.SnapshotDetailViewModel
import com.fintrack.ui.snapshots.entry.SnapshotEntryRoute
import com.fintrack.ui.snapshots.entry.SnapshotEntryViewModel

/**
 * Top-level layout: AppViewModel state machine decides which gate is shown,
 * and inside `Ready` we run a NavController for sub-routes (home, snapshot
 * new/edit). Phases 3+ add routes to this NavHost without touching the gate.
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

        AppState.NeedsOnboarding -> OnboardingPagerRoute(
            onComplete = appViewModel::completeOnboarding,
        )

        AppState.NeedsFirstProfile -> CreateFirstProfileRoute(
            onProfileCreated = appViewModel::onProfileCreated,
        )

        AppState.ShowingPicker -> ProfilePickerRoute(
            onUserPicked = appViewModel::activateUser,
        )

        is AppState.Ready -> {
            AuthenticatedNavHost(appViewModel = appViewModel)
            // Pop the celebration sheet over the nav host whenever an
            // un-celebrated milestone exists for the active user.
            com.fintrack.ui.celebration.CelebrationSheetHost()
        }
    }
}

@Composable
private fun AuthenticatedNavHost(appViewModel: AppViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        composable(HOME_ROUTE) {
            HomeRoute(
                onSwitchUser = appViewModel::requestSwitchUser,
                onNewSnapshot = { navController.navigate(SNAPSHOT_NEW_ROUTE) },
                onSnapshotDetail = { id -> navController.navigate("snapshot/detail/$id") },
                onEditSnapshot = { id -> navController.navigate("snapshot/edit/$id") },
                onAimEditor = { navController.navigate("settings/aim") },
                onHoldings = { navController.navigate("settings/holdings") },
                onLoans = { navController.navigate("settings/loans") },
                onGoals = { navController.navigate("settings/goals") },
                onManageUsers = { navController.navigate("settings/users") },
                onBackup = { navController.navigate("settings/backup") },
                onExcel = { navController.navigate("settings/excel") },
                onAbout = { navController.navigate("settings/about") },
            )
        }
        composable("settings/aim") {
            AimEditorRoute(onDone = { navController.popBackStack() })
        }
        composable("settings/holdings") {
            HoldingsManagementRoute(
                onBack = { navController.popBackStack() },
                onAimEditor = { navController.navigate("settings/aim") },
            )
        }
        composable("settings/loans") {
            LoansManagementRoute(onBack = { navController.popBackStack() })
        }
        composable("settings/goals") {
            GoalsManagementRoute(onBack = { navController.popBackStack() })
        }
        composable("settings/users") {
            ManageUsersRoute(onBack = { navController.popBackStack() })
        }
        composable("settings/backup") {
            BackupRoute(onBack = { navController.popBackStack() })
        }
        composable("settings/excel") {
            ExcelRoute(onBack = { navController.popBackStack() })
        }
        composable("settings/about") {
            AboutRoute(
                onBack = { navController.popBackStack() },
                onViewManifest = { navController.navigate("settings/about/manifest") },
                onReplayOnboarding = { navController.navigate("onboarding/replay") },
            )
        }
        composable("settings/about/manifest") {
            ManifestViewerRoute(onBack = { navController.popBackStack() })
        }
        composable("onboarding/replay") {
            // Replay never touches the onboarding flag — it's a normal nav
            // round trip back to About per spec §2.2.
            OnboardingPagerRoute(
                onComplete = { navController.popBackStack() },
                isReplay = true,
                onDismissReplay = { navController.popBackStack() },
            )
        }
        composable(SNAPSHOT_NEW_ROUTE) {
            SnapshotEntryRoute(onDone = { navController.popBackStack() })
        }
        composable("snapshot/edit/{${SnapshotEntryViewModel.ARG_SNAPSHOT_ID}}") {
            SnapshotEntryRoute(onDone = { navController.popBackStack() })
        }
        composable("snapshot/detail/{${SnapshotDetailViewModel.ARG_SNAPSHOT_ID}}") { backStack ->
            val id = backStack.arguments?.getString(SnapshotDetailViewModel.ARG_SNAPSHOT_ID)
            SnapshotDetailRoute(
                onBack = { navController.popBackStack() },
                onEdit = {
                    if (id != null) {
                        navController.navigate("snapshot/edit/$id") {
                            popUpTo("snapshot/detail/$id") { inclusive = true }
                        }
                    }
                },
            )
        }
    }
}

private const val HOME_ROUTE = "home"
private const val SNAPSHOT_NEW_ROUTE = "snapshot/new"

@Composable
private fun Loading() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
