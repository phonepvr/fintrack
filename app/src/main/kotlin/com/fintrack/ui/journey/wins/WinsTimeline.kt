package com.fintrack.ui.journey.wins

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
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
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WinsTimelineViewModel @Inject constructor(
    private val milestoneRepository: MilestoneRepository,
    private val userScope: UserScope,
) : ViewModel() {

    val milestones: StateFlow<List<MilestoneEntity>> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(emptyList())
            else milestoneRepository.observeForUser(uid).map { list ->
                list.sortedByDescending { it.achievedAtDate }
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}

@Composable
fun WinsTimeline(viewModel: WinsTimelineViewModel = hiltViewModel()) {
    val items by viewModel.milestones.collectAsState()
    if (items.isEmpty()) {
        EmptyWins()
        return
    }
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(0.dp),
    ) {
        items(items, key = { it.id }) { m ->
            TimelineRow(milestone = m, isFirst = m === items.first(), isLast = m === items.last())
        }
    }
}

@Composable
private fun TimelineRow(milestone: MilestoneEntity, isFirst: Boolean, isLast: Boolean) {
    Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
        TimelineGutter(isFirst = isFirst, isLast = isLast)
        Spacer(Modifier.width(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    milestone.type.displayName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    milestone.achievedAtDate.formatted(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                milestone.amountAtAchievement?.let {
                    Spacer(Modifier.size(4.dp))
                    Text(
                        formatIndianCurrency(it),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                    )
                }
            }
        }
    }
}

@Composable
private fun TimelineGutter(isFirst: Boolean, isLast: Boolean) {
    val dotColor = MaterialTheme.colorScheme.primary
    val lineColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
    Box(
        modifier = Modifier
            .width(24.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopCenter,
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val cx = size.width / 2f
            val dotY = 18.dp.toPx()
            if (!isFirst) {
                drawLine(lineColor, Offset(cx, 0f), Offset(cx, dotY), strokeWidth = 2f)
            }
            if (!isLast) {
                drawLine(lineColor, Offset(cx, dotY), Offset(cx, size.height), strokeWidth = 2f)
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 12.dp)
                .size(12.dp)
                .clip(CircleShape)
                .background(dotColor),
        )
    }
}

@Composable
private fun EmptyWins() {
    Box(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "No wins yet",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.height(6.dp))
            Text(
                "Take a snapshot to start unlocking milestones.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

