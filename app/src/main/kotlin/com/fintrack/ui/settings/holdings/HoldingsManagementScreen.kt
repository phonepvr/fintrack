package com.fintrack.ui.settings.holdings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.dao.HoldingDao
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.SubBucketEntity
import com.fintrack.data.repo.TaxonomyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * Phase A scope: read-only listing grouped by SubBucket with active toggles.
 * Phase D rewrites this to the three-level expandable list per spec §5.8 with
 * add/edit/reorder at every level + new-asset-class rebalancing flow.
 */
data class HoldingsListUiState(
    val loading: Boolean = true,
    val sections: List<HoldingsSection> = emptyList(),
)

data class HoldingsSection(
    val assetClass: AssetClassEntity,
    val groups: List<HoldingsBucket>,
)

data class HoldingsBucket(
    val subBucket: SubBucketEntity,
    val holdings: List<HoldingEntity>,
)

@HiltViewModel
class HoldingsManagementViewModel @Inject constructor(
    private val holdingDao: HoldingDao,
    private val taxonomyRepository: TaxonomyRepository,
) : ViewModel() {

    val state: StateFlow<HoldingsListUiState> = combine(
        taxonomyRepository.observeAssetClasses(),
        taxonomyRepository.observeSubBuckets(),
        holdingDao.observeAll(),
    ) { classes, buckets, holdings ->
        val sections = classes.map { ac ->
            val acBuckets = buckets.filter { it.assetClassId == ac.id }
            val groups = acBuckets.map { sb ->
                HoldingsBucket(
                    subBucket = sb,
                    holdings = holdings.filter { it.subBucketId == sb.id },
                )
            }
            HoldingsSection(assetClass = ac, groups = groups)
        }
        HoldingsListUiState(loading = false, sections = sections)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HoldingsListUiState())

    fun setActive(holdingId: UUID, active: Boolean) {
        viewModelScope.launch { holdingDao.setActive(holdingId, active) }
    }
}

@Composable
fun HoldingsManagementRoute(
    onBack: () -> Unit,
    viewModel: HoldingsManagementViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text("Holdings") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    "Holdings are SHARED across all profiles. Adding, renaming, or " +
                        "deactivating a holding affects every user on this device.",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
                Text(
                    "Phase A: read-only listing with activate / deactivate. " +
                        "Three-level add / edit / reorder lands in Phase D.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            LazyColumn(contentPadding = PaddingValues(bottom = 24.dp)) {
                state.sections.forEach { section ->
                    item(key = "section-${section.assetClass.id}") {
                        SectionHeader(section.assetClass.name)
                    }
                    section.groups.forEach { group ->
                        item(key = "bucket-${group.subBucket.id}") {
                            BucketHeader(group.subBucket.name)
                        }
                        items(group.holdings, key = { it.id }) { h ->
                            HoldingRow(
                                holding = h,
                                onToggleActive = { active -> viewModel.setActive(h.id, active) },
                            )
                            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(name: String) {
    Text(
        name,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 4.dp),
    )
}

@Composable
private fun BucketHeader(name: String) {
    Text(
        name,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(start = 24.dp, top = 8.dp, bottom = 4.dp),
    )
}

@Composable
private fun HoldingRow(
    holding: HoldingEntity,
    onToggleActive: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                holding.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (holding.isActive) FontWeight.Medium else FontWeight.Normal,
                color = if (holding.isActive) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(top = 4.dp),
            ) {
                if (holding.trackInvested) {
                    AssistChip(onClick = {}, label = {
                        Text("Invested", style = MaterialTheme.typography.labelSmall)
                    })
                }
                if (holding.trackSip) {
                    AssistChip(onClick = {}, label = {
                        Text("SIP", style = MaterialTheme.typography.labelSmall)
                    })
                }
                if (!holding.isActive) {
                    AssistChip(onClick = {}, label = {
                        Text("Inactive", style = MaterialTheme.typography.labelSmall)
                    })
                }
            }
        }
        Switch(checked = holding.isActive, onCheckedChange = onToggleActive)
    }
}

