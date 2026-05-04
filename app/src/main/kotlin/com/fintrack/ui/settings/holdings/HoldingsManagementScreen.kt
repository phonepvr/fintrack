package com.fintrack.ui.settings.holdings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.HoldingEntity
import com.fintrack.data.db.dao.HoldingDao
import com.fintrack.domain.model.AssetClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HoldingsManagementViewModel @Inject constructor(
    private val holdingDao: HoldingDao,
) : ViewModel() {

    val holdings: StateFlow<List<HoldingEntity>> = holdingDao.observeAll()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun addHolding(
        name: String,
        assetClass: AssetClass,
        trackInvested: Boolean,
        trackSip: Boolean,
        isBank: Boolean,
    ) {
        val current = holdings.value
        val nextOrder = (current.maxOfOrNull { it.displayOrder } ?: 0) + 10
        val entity = HoldingEntity(
            id = UUID.randomUUID(),
            name = name.trim(),
            assetClass = assetClass,
            trackInvested = trackInvested,
            trackSip = trackSip,
            isBankAccount = isBank,
            isActive = true,
            displayOrder = nextOrder,
        )
        viewModelScope.launch { holdingDao.insert(entity) }
    }

    fun renameHolding(id: UUID, newName: String, trackInvested: Boolean, trackSip: Boolean, isBank: Boolean) {
        val existing = holdings.value.firstOrNull { it.id == id } ?: return
        viewModelScope.launch {
            holdingDao.update(existing.copy(
                name = newName.trim(),
                trackInvested = trackInvested,
                trackSip = trackSip,
                isBankAccount = isBank,
            ))
        }
    }

    fun setActive(id: UUID, active: Boolean) {
        viewModelScope.launch { holdingDao.setActive(id, active) }
    }
}

@Composable
fun HoldingsManagementRoute(
    onBack: () -> Unit,
    viewModel: HoldingsManagementViewModel = hiltViewModel(),
) {
    val holdings by viewModel.holdings.collectAsState()
    var addOpen by rememberSaveable { mutableStateOf(false) }
    var editTarget by remember { mutableStateOf<HoldingEntity?>(null) }
    var sectionFilter by rememberSaveable { mutableStateOf<AssetClass?>(null) }

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
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { addOpen = true },
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text("Add holding") },
            )
        },
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                Text(
                    "Holdings are SHARED across all profiles. Adding or renaming a holding affects every user on this device.",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
            ) {
                FilterChip(
                    selected = sectionFilter == null,
                    onClick = { sectionFilter = null },
                    label = { Text("All") },
                )
                AssetClass.entries.forEach { ac ->
                    FilterChip(
                        selected = sectionFilter == ac,
                        onClick = { sectionFilter = ac },
                        label = { Text(ac.displayName) },
                    )
                }
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            val filtered = remember(holdings, sectionFilter) {
                if (sectionFilter == null) holdings else holdings.filter { it.assetClass == sectionFilter }
            }
            LazyColumn(
                contentPadding = PaddingValues(bottom = 96.dp),
            ) {
                items(filtered, key = { it.id }) { h ->
                    HoldingRow(
                        holding = h,
                        onEdit = { editTarget = h },
                        onToggleActive = { active -> viewModel.setActive(h.id, active) },
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
                }
            }
        }
    }

    if (addOpen) {
        HoldingEditorDialog(
            initial = null,
            onDismiss = { addOpen = false },
            onConfirm = { name, ac, trackInvested, trackSip, isBank ->
                viewModel.addHolding(name, ac, trackInvested, trackSip, isBank)
                addOpen = false
            },
        )
    }

    val edit = editTarget
    if (edit != null) {
        HoldingEditorDialog(
            initial = edit,
            onDismiss = { editTarget = null },
            onConfirm = { name, _, trackInvested, trackSip, isBank ->
                viewModel.renameHolding(edit.id, name, trackInvested, trackSip, isBank)
                editTarget = null
            },
        )
    }
}

@Composable
private fun HoldingRow(
    holding: HoldingEntity,
    onEdit: () -> Unit,
    onToggleActive: (Boolean) -> Unit,
) {
    var menuOpen by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(holding.name, style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (holding.isActive) FontWeight.Medium else FontWeight.Normal,
                color = if (holding.isActive) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.onSurfaceVariant)
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(top = 4.dp)) {
                AssistChip(onClick = {}, label = { Text(holding.assetClass.displayName,
                    style = MaterialTheme.typography.labelSmall) })
                if (holding.isBankAccount) {
                    AssistChip(onClick = {}, label = { Text("Bank",
                        style = MaterialTheme.typography.labelSmall) })
                }
                if (!holding.isActive) {
                    AssistChip(onClick = {}, label = { Text("Inactive",
                        style = MaterialTheme.typography.labelSmall) })
                }
            }
        }
        Switch(checked = holding.isActive, onCheckedChange = onToggleActive)
        IconButton(onClick = { menuOpen = true }) {
            Icon(Icons.Filled.Add,
                contentDescription = "More",
                modifier = Modifier.padding(0.dp))
        }
        DropdownMenu(expanded = menuOpen, onDismissRequest = { menuOpen = false }) {
            DropdownMenuItem(
                text = { Text("Edit") },
                onClick = { menuOpen = false; onEdit() },
            )
        }
    }
}

@Composable
private fun HoldingEditorDialog(
    initial: HoldingEntity?,
    onDismiss: () -> Unit,
    onConfirm: (String, AssetClass, Boolean, Boolean, Boolean) -> Unit,
) {
    val isEdit = initial != null
    var name by rememberSaveable(initial?.id?.toString()) { mutableStateOf(initial?.name.orEmpty()) }
    var assetClass by rememberSaveable(initial?.id?.toString()) {
        mutableStateOf(initial?.assetClass ?: AssetClass.MF_NPS)
    }
    var trackInvested by rememberSaveable(initial?.id?.toString()) {
        mutableStateOf(initial?.trackInvested ?: true)
    }
    var trackSip by rememberSaveable(initial?.id?.toString()) {
        mutableStateOf(initial?.trackSip ?: true)
    }
    var isBank by rememberSaveable(initial?.id?.toString()) {
        mutableStateOf(initial?.isBankAccount ?: false)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (isEdit) "Edit holding" else "Add holding") },
        text = {
            Column {
                OutlinedTextField(
                    value = name, onValueChange = { name = it },
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(Modifier.height(12.dp))
                Text("Asset class", style = MaterialTheme.typography.labelLarge)
                Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    AssetClass.entries.forEach { ac ->
                        FilterChip(
                            selected = assetClass == ac,
                            onClick = { if (!isEdit) assetClass = ac },
                            label = { Text(ac.displayName,
                                style = MaterialTheme.typography.labelSmall) },
                            enabled = !isEdit,
                        )
                    }
                }
                if (isEdit) {
                    Text(
                        "Asset class can't change after a holding is created — it would rewrite history for every user. Deactivate this holding and add a new one if needed.",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
                Spacer(Modifier.height(12.dp))
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Checkbox(checked = trackInvested, onCheckedChange = { trackInvested = it })
                    Text("Track invested amount")
                }
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Checkbox(checked = trackSip, onCheckedChange = { trackSip = it })
                    Text("Track SIP")
                }
                Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                    Checkbox(checked = isBank, onCheckedChange = { isBank = it })
                    Text("Bank account")
                }
            }
        },
        confirmButton = {
            Button(
                onClick = { onConfirm(name, assetClass, trackInvested, trackSip, isBank) },
                enabled = name.isNotBlank(),
            ) { Text(if (isEdit) "Save" else "Create") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
    )
}
