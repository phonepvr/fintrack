package com.fintrack.ui.celebration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.MilestoneEntity
import com.fintrack.data.repo.MilestoneRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.util.formatIndianCurrency
import com.fintrack.domain.util.formatted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Material 3 ModalBottomSheet celebration per spec §6.3. Observes the
 * active user's uncelebrated milestones and presents them one at a time.
 * Each "Got it" tap marks the row celebrated, advancing to the next.
 *
 * Silent backfill on first launch (AppViewModel.backfillEngagement) means
 * legacy milestones lit up via dev seed don't fire celebrations — they
 * arrive already-celebrated. New milestones earned during normal use
 * trigger the sheet.
 */
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CelebrationViewModel @Inject constructor(
    private val milestoneRepository: MilestoneRepository,
    private val userScope: UserScope,
) : ViewModel() {

    val pending: StateFlow<List<MilestoneEntity>> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(emptyList())
            else milestoneRepository.observeUncelebrated(uid)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun acknowledge(id: java.util.UUID) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch { milestoneRepository.markCelebrated(uid, id) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CelebrationSheetHost(viewModel: CelebrationViewModel = hiltViewModel()) {
    val pending by viewModel.pending.collectAsState()
    val current = pending.firstOrNull() ?: return

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    LaunchedEffect(current.id) { sheetState.show() }

    ModalBottomSheet(
        onDismissRequest = { viewModel.acknowledge(current.id) },
        sheetState = sheetState,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text("🎉", style = MaterialTheme.typography.headlineLarge)
            Text(
                "New milestone unlocked",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                current.type.displayName,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                "Achieved on ${current.achievedAtDate.formatted()}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            current.amountAtAchievement?.let {
                Text(
                    formatIndianCurrency(it),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = { viewModel.acknowledge(current.id) },
                modifier = Modifier.fillMaxWidth(),
            ) { Text("Got it") }
        }
    }
}
