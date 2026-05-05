package com.fintrack.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwitchAccount
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.R
import com.fintrack.data.repo.UserRepository
import com.fintrack.domain.UserScope
import com.fintrack.ui.home.overview.OverviewTab
import com.fintrack.ui.home.snapshots.SnapshotsTab
import com.fintrack.ui.settings.SettingsTab
import com.fintrack.ui.onboarding.parseHex
import java.util.UUID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class ActiveUserSummary(val name: String, val colorHex: String)

@HiltViewModel
class HomeViewModel @Inject constructor(
    userRepository: UserRepository,
    userScope: UserScope,
) : ViewModel() {

    val activeUser: StateFlow<ActiveUserSummary?> = combine(
        userRepository.observeActiveUsers(),
        userScope.activeUserId,
    ) { users, activeId ->
        val match = users.firstOrNull { it.id == activeId } ?: return@combine null
        ActiveUserSummary(name = match.name, colorHex = match.colorHex)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), null)
}

private enum class HomeTab(val labelRes: Int) {
    Snapshots(R.string.home_tab_snapshots),
    Journey(R.string.home_tab_journey),
    Settings(R.string.home_tab_settings),
}

@Composable
fun HomeRoute(
    onSwitchUser: () -> Unit,
    onNewSnapshot: () -> Unit,
    onSnapshotDetail: (UUID) -> Unit,
    onEditSnapshot: (UUID) -> Unit,
    onAimEditor: () -> Unit,
    onHoldings: () -> Unit,
    onLoans: () -> Unit,
    onManageUsers: () -> Unit,
    onBackup: () -> Unit,
    onAbout: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val activeUser by viewModel.activeUser.collectAsState()
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val user = activeUser
                        if (user != null) {
                            Box(
                                modifier = Modifier
                                    .size(14.dp)
                                    .clip(CircleShape)
                                    .background(parseHex(user.colorHex)),
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(user.name, style = MaterialTheme.typography.titleMedium)
                        } else {
                            Text(stringResource(R.string.app_name))
                        }
                    }
                },
                actions = {
                    IconButton(onClick = onSwitchUser) {
                        Icon(
                            imageVector = Icons.Filled.SwitchAccount,
                            contentDescription = stringResource(R.string.home_switch_user),
                        )
                    }
                },
            )
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                HomeTab.entries.forEachIndexed { index, tab ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(stringResource(tab.labelRes)) },
                    )
                }
            }
            when (HomeTab.entries[selectedTab]) {
                HomeTab.Snapshots -> SnapshotsTab(
                    onNewSnapshot = onNewSnapshot,
                    onSnapshotDetail = onSnapshotDetail,
                    onEditSnapshot = onEditSnapshot,
                )
                HomeTab.Journey -> OverviewTab(onSnapshotDetail = onSnapshotDetail)
                HomeTab.Settings -> SettingsTab(
                    onAimEditor = onAimEditor,
                    onHoldings = onHoldings,
                    onLoans = onLoans,
                    onManageUsers = onManageUsers,
                    onBackup = onBackup,
                    onAbout = onAbout,
                )
            }
        }
    }
}

@Composable
private fun EmptyPlaceholder(title: String, message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.size(8.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
    }
}
