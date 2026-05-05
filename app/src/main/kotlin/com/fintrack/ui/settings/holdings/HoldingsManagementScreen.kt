package com.fintrack.ui.settings.holdings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.entities.SubBucketEntity
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.MilestoneRepository
import com.fintrack.data.repo.StreakRepository
import com.fintrack.data.repo.TaxonomyRepository
import com.fintrack.domain.UserScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

/**
 * Spec §5.8: three-level expandable Holdings list with reorder + activate/
 * deactivate at every level (AssetClass → SubBucket → Holding) plus inline
 * add-new at each level. Adding a new AssetClass surfaces a snackbar
 * directing the user to the Aim editor — the new class lands at 0% and
 * the sum-to-100 invariant blocks Save until they redistribute.
 */
data class HoldingsListUiState(
    val loading: Boolean = true,
    val sections: List<HoldingsSection> = emptyList(),
    val message: ManagementMessage? = null,
)

data class HoldingsSection(
    val assetClass: AssetClassEntity,
    val groups: List<HoldingsBucket>,
)

data class HoldingsBucket(
    val subBucket: SubBucketEntity,
    val holdings: List<HoldingEntity>,
)

sealed interface ManagementMessage {
    val text: String
    data class AssetClassAddedNeedsAim(override val text: String) : ManagementMessage
    data class Plain(override val text: String) : ManagementMessage
}

/**
 * Snapshot of "what's about to be deleted" surfaced to the confirm dialog.
 * `valueCount` is the number of HoldingValue rows referencing this holding
 * across every snapshot of every user — non-zero means the cascade will
 * also wipe historical values on those snapshots.
 */
data class DeleteHoldingPrompt(
    val holdingId: UUID,
    val name: String,
    val valueCount: Int,
)

@HiltViewModel
class HoldingsManagementViewModel @Inject constructor(
    private val taxonomyRepository: TaxonomyRepository,
    private val holdingRepository: HoldingRepository,
    private val streakRepository: StreakRepository,
    private val milestoneRepository: MilestoneRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val message = MutableSharedFlow<ManagementMessage>(extraBufferCapacity = 4)
    private val _deletePrompt = MutableStateFlow<DeleteHoldingPrompt?>(null)
    val deletePrompt: StateFlow<DeleteHoldingPrompt?> = _deletePrompt.asStateFlow()

    val state: StateFlow<HoldingsListUiState> = combine(
        taxonomyRepository.observeAssetClasses(),
        taxonomyRepository.observeSubBuckets(),
        holdingRepository.observeAll(),
    ) { classes, buckets, holdings ->
        val sections = classes.map { ac ->
            val acBuckets = buckets
                .filter { it.assetClassId == ac.id }
                .sortedBy { it.displayOrder }
            val groups = acBuckets.map { sb ->
                HoldingsBucket(
                    subBucket = sb,
                    holdings = holdings
                        .filter { it.subBucketId == sb.id }
                        .sortedBy { it.displayOrder },
                )
            }
            HoldingsSection(assetClass = ac, groups = groups)
        }
        HoldingsListUiState(loading = false, sections = sections)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), HoldingsListUiState())

    val messages: kotlinx.coroutines.flow.SharedFlow<ManagementMessage> = message

    fun setHoldingActive(id: UUID, active: Boolean) =
        viewModelScope.launch { holdingRepository.setActive(id, active) }

    fun setSubBucketActive(id: UUID, active: Boolean) =
        viewModelScope.launch { taxonomyRepository.setSubBucketActive(id, active) }

    fun setAssetClassActive(id: UUID, active: Boolean) =
        viewModelScope.launch { taxonomyRepository.setAssetClassActive(id, active) }

    fun renameAssetClass(id: UUID, name: String) =
        viewModelScope.launch { taxonomyRepository.renameAssetClass(id, name) }

    fun renameSubBucket(id: UUID, name: String) =
        viewModelScope.launch { taxonomyRepository.renameSubBucket(id, name) }

    fun renameHolding(id: UUID, name: String) =
        viewModelScope.launch {
            val existing = state.value.sections
                .flatMap { it.groups }
                .flatMap { it.holdings }
                .firstOrNull { it.id == id } ?: return@launch
            holdingRepository.update(existing.copy(name = name.trim()))
        }

    fun moveAssetClass(id: UUID, delta: Int) = reorderInList(
        ids = state.value.sections.map { it.assetClass.id },
        target = id,
        delta = delta,
    ) { taxonomyRepository.reorderAssetClasses(it) }

    fun moveSubBucket(assetClassId: UUID, id: UUID, delta: Int) = reorderInList(
        ids = state.value.sections.firstOrNull { it.assetClass.id == assetClassId }
            ?.groups?.map { it.subBucket.id }.orEmpty(),
        target = id,
        delta = delta,
    ) { taxonomyRepository.reorderSubBuckets(assetClassId, it) }

    fun moveHolding(subBucketId: UUID, id: UUID, delta: Int) = reorderInList(
        ids = state.value.sections
            .flatMap { it.groups }
            .firstOrNull { it.subBucket.id == subBucketId }
            ?.holdings?.map { it.id }.orEmpty(),
        target = id,
        delta = delta,
    ) { holdingRepository.reorderWithinSubBucket(subBucketId, it) }

    fun createAssetClass(name: String) = viewModelScope.launch {
        val nextOrder = state.value.sections.maxOfOrNull { it.assetClass.displayOrder + 1 } ?: 0
        runCatching { taxonomyRepository.createAssetClass(name, nextOrder) }
            .onSuccess {
                message.tryEmit(
                    ManagementMessage.AssetClassAddedNeedsAim(
                        "Added \"$name\". Update Aim allocation so percentages sum to 100.",
                    ),
                )
            }
            .onFailure { message.tryEmit(ManagementMessage.Plain(it.message ?: "Could not add asset class")) }
    }

    fun createSubBucket(assetClassId: UUID, name: String) = viewModelScope.launch {
        val section = state.value.sections.firstOrNull { it.assetClass.id == assetClassId }
        val nextOrder = section?.groups?.maxOfOrNull { it.subBucket.displayOrder + 1 } ?: 0
        runCatching { taxonomyRepository.createSubBucket(assetClassId, name, nextOrder) }
            .onSuccess { message.tryEmit(ManagementMessage.Plain("Added \"$name\".")) }
            .onFailure { message.tryEmit(ManagementMessage.Plain(it.message ?: "Could not add sub-bucket")) }
    }

    fun createHolding(
        subBucketId: UUID,
        name: String,
        trackInvested: Boolean,
        trackSip: Boolean,
    ) = viewModelScope.launch {
        val bucket = state.value.sections
            .flatMap { it.groups }
            .firstOrNull { it.subBucket.id == subBucketId }
        val nextOrder = bucket?.holdings?.maxOfOrNull { it.displayOrder + 1 } ?: 0
        runCatching {
            holdingRepository.createHolding(subBucketId, name, trackInvested, trackSip, nextOrder)
        }
            .onSuccess { message.tryEmit(ManagementMessage.Plain("Added \"$name\".")) }
            .onFailure { message.tryEmit(ManagementMessage.Plain(it.message ?: "Could not add holding")) }
    }

    fun requestDeleteHolding(holdingId: UUID, name: String) = viewModelScope.launch {
        val count = holdingRepository.valueCountForHolding(holdingId)
        _deletePrompt.value = DeleteHoldingPrompt(holdingId, name, count)
    }

    fun dismissDeletePrompt() {
        _deletePrompt.value = null
    }

    fun confirmDeleteHolding() {
        val target = _deletePrompt.value ?: return
        _deletePrompt.value = null
        viewModelScope.launch {
            holdingRepository.deleteHolding(target.holdingId)
            // Recompute streak + milestones for the active user. Streak
            // is keyed off snapshot dates and won't change, but milestone
            // detection re-evaluates net-worth thresholds against the
            // newly-recomputed historical totals.
            userScope.activeUserId.value?.let { uid ->
                streakRepository.recompute(uid)
                milestoneRepository.detectAndPersist(uid)
            }
            message.tryEmit(ManagementMessage.Plain("Deleted \"${target.name}\"."))
        }
    }

    private fun reorderInList(
        ids: List<UUID>,
        target: UUID,
        delta: Int,
        commit: suspend (List<UUID>) -> Unit,
    ) = viewModelScope.launch {
        val idx = ids.indexOf(target)
        val newIdx = idx + delta
        if (idx < 0 || newIdx !in ids.indices) return@launch
        val mutable = ids.toMutableList()
        mutable.removeAt(idx)
        mutable.add(newIdx, target)
        commit(mutable)
    }
}

@Composable
fun HoldingsManagementRoute(
    onBack: () -> Unit,
    onAimEditor: () -> Unit,
    viewModel: HoldingsManagementViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(viewModel) {
        viewModel.messages.collect { msg ->
            when (msg) {
                is ManagementMessage.AssetClassAddedNeedsAim -> {
                    val result = snackbarState.showSnackbar(
                        message = msg.text,
                        actionLabel = "Open Aim",
                    )
                    if (result == SnackbarResult.ActionPerformed) onAimEditor()
                }
                is ManagementMessage.Plain -> {
                    snackbarState.showSnackbar(msg.text)
                }
            }
        }
    }

    val expanded = remember { mutableStateMapOf<UUID, Boolean>() }
    var addAssetClass by remember { mutableStateOf(false) }
    var addSubBucketFor by remember { mutableStateOf<UUID?>(null) }
    var addHoldingFor by remember { mutableStateOf<UUID?>(null) }
    var renameTarget by remember { mutableStateOf<RenameTarget?>(null) }

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
                actions = {
                    TextButton(onClick = { addAssetClass = true }) { Text("+ Class") }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item("warning") {
                Text(
                    "Holdings are SHARED across all profiles. Changes affect every user on this device.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(horizontal = 4.dp),
                )
            }
            items(state.sections, key = { it.assetClass.id }) { section ->
                AssetClassCard(
                    section = section,
                    expanded = expanded[section.assetClass.id] ?: true,
                    onToggleExpanded = {
                        expanded[section.assetClass.id] = !(expanded[section.assetClass.id] ?: true)
                    },
                    onMoveUp = { viewModel.moveAssetClass(section.assetClass.id, -1) },
                    onMoveDown = { viewModel.moveAssetClass(section.assetClass.id, +1) },
                    onToggleActive = { viewModel.setAssetClassActive(section.assetClass.id, it) },
                    onRename = {
                        renameTarget = RenameTarget.AssetClass(section.assetClass.id, section.assetClass.name)
                    },
                    onAddSubBucket = { addSubBucketFor = section.assetClass.id },
                    onSubBucketMoveUp = { sbId -> viewModel.moveSubBucket(section.assetClass.id, sbId, -1) },
                    onSubBucketMoveDown = { sbId -> viewModel.moveSubBucket(section.assetClass.id, sbId, +1) },
                    onSubBucketToggleActive = viewModel::setSubBucketActive,
                    onSubBucketRename = { sb ->
                        renameTarget = RenameTarget.SubBucket(sb.id, sb.name)
                    },
                    onSubBucketAddHolding = { sbId -> addHoldingFor = sbId },
                    onHoldingMoveUp = { sbId, hId -> viewModel.moveHolding(sbId, hId, -1) },
                    onHoldingMoveDown = { sbId, hId -> viewModel.moveHolding(sbId, hId, +1) },
                    onHoldingToggleActive = viewModel::setHoldingActive,
                    onHoldingRename = { h ->
                        renameTarget = RenameTarget.Holding(h.id, h.name)
                    },
                    onHoldingDelete = { h ->
                        viewModel.requestDeleteHolding(h.id, h.name)
                    },
                )
            }
        }
    }

    if (addAssetClass) {
        SimpleNameDialog(
            title = "Add asset class",
            onSubmit = { name ->
                viewModel.createAssetClass(name)
                addAssetClass = false
            },
            onDismiss = { addAssetClass = false },
        )
    }
    addSubBucketFor?.let { acId ->
        SimpleNameDialog(
            title = "Add sub-bucket",
            onSubmit = { name ->
                viewModel.createSubBucket(acId, name)
                addSubBucketFor = null
            },
            onDismiss = { addSubBucketFor = null },
        )
    }
    addHoldingFor?.let { sbId ->
        AddHoldingDialog(
            onSubmit = { name, ti, ts ->
                viewModel.createHolding(sbId, name, ti, ts)
                addHoldingFor = null
            },
            onDismiss = { addHoldingFor = null },
        )
    }
    renameTarget?.let { target ->
        SimpleNameDialog(
            title = "Rename",
            initial = target.currentName,
            onSubmit = { name ->
                when (target) {
                    is RenameTarget.AssetClass -> viewModel.renameAssetClass(target.id, name)
                    is RenameTarget.SubBucket -> viewModel.renameSubBucket(target.id, name)
                    is RenameTarget.Holding -> viewModel.renameHolding(target.id, name)
                }
                renameTarget = null
            },
            onDismiss = { renameTarget = null },
        )
    }

    val deletePrompt by viewModel.deletePrompt.collectAsState()
    deletePrompt?.let { prompt ->
        DeleteHoldingDialog(
            prompt = prompt,
            onConfirm = viewModel::confirmDeleteHolding,
            onDismiss = viewModel::dismissDeletePrompt,
        )
    }
}

@Composable
private fun DeleteHoldingDialog(
    prompt: DeleteHoldingPrompt,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete \"${prompt.name}\"?") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                if (prompt.valueCount == 0) {
                    Text(
                        "This holding has no snapshot history. It will be " +
                            "removed from the catalog.",
                    )
                } else {
                    Text(
                        "This holding appears in ${prompt.valueCount} historical " +
                            "snapshot " +
                            if (prompt.valueCount == 1) "value." else "values.",
                    )
                    Text(
                        "Deleting it will also remove those values. Net worth on " +
                            "the affected snapshots will recalculate without this " +
                            "holding. This cannot be undone.",
                        color = MaterialTheme.colorScheme.error,
                    )
                }
                Text(
                    "The holdings catalog is shared across all profiles on this " +
                        "device — every profile will lose this holding.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        },
        confirmButton = {
            TextButton(onClick = onConfirm) { Text("Delete") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        },
    )
}

private sealed interface RenameTarget {
    val currentName: String
    data class AssetClass(val id: UUID, override val currentName: String) : RenameTarget
    data class SubBucket(val id: UUID, override val currentName: String) : RenameTarget
    data class Holding(val id: UUID, override val currentName: String) : RenameTarget
}

@Composable
private fun AssetClassCard(
    section: HoldingsSection,
    expanded: Boolean,
    onToggleExpanded: () -> Unit,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onToggleActive: (Boolean) -> Unit,
    onRename: () -> Unit,
    onAddSubBucket: () -> Unit,
    onSubBucketMoveUp: (UUID) -> Unit,
    onSubBucketMoveDown: (UUID) -> Unit,
    onSubBucketToggleActive: (UUID, Boolean) -> Unit,
    onSubBucketRename: (SubBucketEntity) -> Unit,
    onSubBucketAddHolding: (UUID) -> Unit,
    onHoldingMoveUp: (UUID, UUID) -> Unit,
    onHoldingMoveDown: (UUID, UUID) -> Unit,
    onHoldingToggleActive: (UUID, Boolean) -> Unit,
    onHoldingRename: (HoldingEntity) -> Unit,
    onHoldingDelete: (HoldingEntity) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = onToggleExpanded) {
                    Icon(
                        if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.KeyboardArrowDown,
                        contentDescription = if (expanded) "Collapse" else "Expand",
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        section.assetClass.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = if (section.assetClass.isActive) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                    Text(
                        "${section.groups.size} sub-bucket(s)",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                ReorderButtons(onUp = onMoveUp, onDown = onMoveDown)
                IconButton(onClick = onRename) {
                    Icon(Icons.Filled.Edit, contentDescription = "Rename")
                }
                Switch(checked = section.assetClass.isActive, onCheckedChange = onToggleActive)
            }
            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(start = 12.dp, top = 4.dp, bottom = 4.dp)) {
                    section.groups.forEach { bucket ->
                        SubBucketBlock(
                            bucket = bucket,
                            onMoveUp = { onSubBucketMoveUp(bucket.subBucket.id) },
                            onMoveDown = { onSubBucketMoveDown(bucket.subBucket.id) },
                            onToggleActive = { onSubBucketToggleActive(bucket.subBucket.id, it) },
                            onRename = { onSubBucketRename(bucket.subBucket) },
                            onAddHolding = { onSubBucketAddHolding(bucket.subBucket.id) },
                            onHoldingMoveUp = { hId -> onHoldingMoveUp(bucket.subBucket.id, hId) },
                            onHoldingMoveDown = { hId -> onHoldingMoveDown(bucket.subBucket.id, hId) },
                            onHoldingToggleActive = onHoldingToggleActive,
                            onHoldingRename = onHoldingRename,
                            onHoldingDelete = onHoldingDelete,
                        )
                        HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                    }
                    TextButton(onClick = onAddSubBucket) {
                        Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.size(4.dp))
                        Text("Add sub-bucket")
                    }
                }
            }
        }
    }
}

@Composable
private fun SubBucketBlock(
    bucket: HoldingsBucket,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onToggleActive: (Boolean) -> Unit,
    onRename: () -> Unit,
    onAddHolding: () -> Unit,
    onHoldingMoveUp: (UUID) -> Unit,
    onHoldingMoveDown: (UUID) -> Unit,
    onHoldingToggleActive: (UUID, Boolean) -> Unit,
    onHoldingRename: (HoldingEntity) -> Unit,
    onHoldingDelete: (HoldingEntity) -> Unit,
) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
            Spacer(Modifier.size(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    bucket.subBucket.name,
                    style = MaterialTheme.typography.titleSmall,
                    color = if (bucket.subBucket.isActive) MaterialTheme.colorScheme.onSurface
                    else MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Text(
                    "${bucket.holdings.size} holding(s)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            ReorderButtons(onUp = onMoveUp, onDown = onMoveDown)
            IconButton(onClick = onRename) {
                Icon(Icons.Filled.Edit, contentDescription = "Rename")
            }
            Switch(checked = bucket.subBucket.isActive, onCheckedChange = onToggleActive)
        }
        Column(modifier = Modifier.padding(start = 12.dp)) {
            bucket.holdings.forEach { h ->
                HoldingRow(
                    holding = h,
                    onMoveUp = { onHoldingMoveUp(h.id) },
                    onMoveDown = { onHoldingMoveDown(h.id) },
                    onToggleActive = { onHoldingToggleActive(h.id, it) },
                    onRename = { onHoldingRename(h) },
                    onDelete = { onHoldingDelete(h) },
                )
            }
            TextButton(onClick = onAddHolding) {
                Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.size(4.dp))
                Text("Add holding")
            }
        }
    }
}

@Composable
private fun HoldingRow(
    holding: HoldingEntity,
    onMoveUp: () -> Unit,
    onMoveDown: () -> Unit,
    onToggleActive: (Boolean) -> Unit,
    onRename: () -> Unit,
    onDelete: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                holding.name,
                style = MaterialTheme.typography.bodyMedium,
                color = if (holding.isActive) MaterialTheme.colorScheme.onSurface
                else MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                if (holding.trackInvested) {
                    AssistChip(
                        onClick = {},
                        label = { Text("Invested", style = MaterialTheme.typography.labelSmall) },
                    )
                }
                if (holding.trackSip) {
                    AssistChip(
                        onClick = {},
                        label = { Text("SIP", style = MaterialTheme.typography.labelSmall) },
                    )
                }
            }
        }
        ReorderButtons(onUp = onMoveUp, onDown = onMoveDown)
        IconButton(onClick = onRename) {
            Icon(Icons.Filled.Edit, contentDescription = "Rename", modifier = Modifier.size(18.dp))
        }
        IconButton(onClick = onDelete) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier.size(18.dp),
                tint = MaterialTheme.colorScheme.error,
            )
        }
        Switch(checked = holding.isActive, onCheckedChange = onToggleActive)
    }
}

@Composable
private fun ReorderButtons(onUp: () -> Unit, onDown: () -> Unit) {
    Column {
        IconButton(onClick = onUp, modifier = Modifier.size(24.dp)) {
            Icon(
                Icons.Filled.KeyboardArrowUp,
                contentDescription = "Move up",
                modifier = Modifier.size(16.dp),
            )
        }
        IconButton(onClick = onDown, modifier = Modifier.size(24.dp)) {
            Icon(
                Icons.Filled.KeyboardArrowDown,
                contentDescription = "Move down",
                modifier = Modifier.size(16.dp),
            )
        }
    }
}

@Composable
private fun SimpleNameDialog(
    title: String,
    initial: String = "",
    onSubmit: (String) -> Unit,
    onDismiss: () -> Unit,
) {
    var name by remember { mutableStateOf(initial) }
    val canSubmit by remember { derivedStateOf { name.trim().isNotEmpty() } }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(title) },
        text = {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        confirmButton = {
            TextButton(enabled = canSubmit, onClick = { onSubmit(name.trim()) }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}

@Composable
private fun AddHoldingDialog(
    onSubmit: (name: String, trackInvested: Boolean, trackSip: Boolean) -> Unit,
    onDismiss: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var trackInvested by remember { mutableStateOf(false) }
    var trackSip by remember { mutableStateOf(false) }
    val canSubmit by remember { derivedStateOf { name.trim().isNotEmpty() } }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Add holding") },
        text = {
            Column {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = trackInvested, onCheckedChange = { trackInvested = it })
                    Text("Track invested amount")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = trackSip, onCheckedChange = { trackSip = it })
                    Text("Track monthly SIP")
                }
            }
        },
        confirmButton = {
            TextButton(enabled = canSubmit, onClick = {
                onSubmit(name.trim(), trackInvested, trackSip)
            }) { Text("Save") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}
