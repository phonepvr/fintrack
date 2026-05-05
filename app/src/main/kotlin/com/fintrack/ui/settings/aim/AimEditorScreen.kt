package com.fintrack.ui.settings.aim

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.fintrack.ui.help.HelpIconButton
import com.fintrack.ui.help.HelpSheet
import com.fintrack.ui.help.HelpSheetContent
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.AimAllocationEntity
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.repo.AimAllocationRepository
import com.fintrack.data.repo.TaxonomyRepository
import com.fintrack.domain.UserScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

data class AimRowState(
    val assetClassId: UUID,
    val assetClassName: String,
    val percent: Int,
)

data class AimEditorUiState(
    val loading: Boolean = true,
    val rows: List<AimRowState> = emptyList(),
    val saving: Boolean = false,
    val saved: Boolean = false,
    val error: String? = null,
) {
    val sum: Int get() = rows.sumOf { it.percent }
    val canSave: Boolean get() = sum == 100 && !saving && rows.isNotEmpty()
}

/**
 * Pure helper for the "distribute remainder" affordance (spec §5.8).
 * Keeps the user's current non-zero percentages and splits the gap
 * (100 − sum) across rows currently at 0%. Falls back to spreading the
 * gap across all rows when no zero rows exist (or when sum already
 * exceeds 100, since a negative gap can't be 'filled' by zero rows).
 */
internal fun distributeRemainderTo(rows: List<AimRowState>): List<AimRowState> {
    if (rows.isEmpty()) return rows
    val gap = 100 - rows.sumOf { it.percent }
    if (gap == 0) return rows
    val zeroRows = rows.filter { it.percent == 0 }
    return if (gap > 0 && zeroRows.isNotEmpty()) {
        val per = gap / zeroRows.size
        val rem = gap - per * zeroRows.size
        var assigned = 0
        rows.map { r ->
            if (r.percent != 0) r else {
                val extra = if (assigned < rem) 1 else 0
                assigned++
                r.copy(percent = per + extra)
            }
        }
    } else {
        val per = gap / rows.size
        val rem = gap - per * rows.size
        rows.mapIndexed { idx, r ->
            val extra = if (idx == 0) rem else 0
            r.copy(percent = (r.percent + per + extra).coerceIn(0, 100))
        }
    }
}

@HiltViewModel
class AimEditorViewModel @Inject constructor(
    private val aimRepository: AimAllocationRepository,
    private val taxonomyRepository: TaxonomyRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val _state = MutableStateFlow(AimEditorUiState())
    val state: StateFlow<AimEditorUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val activeUser = userScope.activeUserId.value
            if (activeUser == null) {
                _state.update { it.copy(loading = false, error = "No active profile") }
                return@launch
            }
            val classes: List<AssetClassEntity> = taxonomyRepository.getActiveAssetClasses()
            val existing = aimRepository.getOrDefault(activeUser).associateBy { it.assetClassId }
            val rows = classes.map { ac ->
                AimRowState(
                    assetClassId = ac.id,
                    assetClassName = ac.name,
                    percent = existing[ac.id]?.aimPercent ?: 0,
                )
            }
            _state.update { it.copy(loading = false, rows = rows) }
        }
    }

    fun setPercent(assetClassId: UUID, value: Int) {
        val coerced = value.coerceIn(0, 100)
        _state.update { ui ->
            ui.copy(rows = ui.rows.map { if (it.assetClassId == assetClassId) it.copy(percent = coerced) else it })
        }
    }

    fun distributeEvenly() {
        _state.update { ui ->
            if (ui.rows.isEmpty()) ui
            else {
                val per = 100 / ui.rows.size
                val remainder = 100 - per * ui.rows.size
                val rows = ui.rows.mapIndexed { idx, r ->
                    r.copy(percent = if (idx == 0) per + remainder else per)
                }
                ui.copy(rows = rows)
            }
        }
    }

    fun distributeRemainder() {
        _state.update { ui -> ui.copy(rows = distributeRemainderTo(ui.rows)) }
    }

    fun save() {
        val s = _state.value
        if (!s.canSave) {
            _state.update { it.copy(error = "Aim percentages must sum to 100 (currently ${s.sum})") }
            return
        }
        val activeUser = userScope.activeUserId.value ?: return
        _state.update { it.copy(saving = true, error = null) }
        viewModelScope.launch {
            runCatching {
                aimRepository.replace(
                    userId = activeUser,
                    rows = s.rows.map { AimAllocationEntity(activeUser, it.assetClassId, it.percent) },
                )
            }.fold(
                onSuccess = { _state.update { it.copy(saving = false, saved = true) } },
                onFailure = { e -> _state.update { it.copy(saving = false, error = e.message ?: "Save failed") } },
            )
        }
    }

    fun clearError() = _state.update { it.copy(error = null) }
}

@Composable
fun AimEditorRoute(
    onDone: () -> Unit,
    onNavigateToAbout: (anchor: String) -> Unit = {},
    viewModel: AimEditorViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var showHelp by remember { mutableStateOf(false) }

    LaunchedEffect(state.saved) { if (state.saved) onDone() }
    LaunchedEffect(state.error) {
        state.error?.let {
            scope.launch { snackbarState.showSnackbar(it) }
            viewModel.clearError()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.statusBarsPadding(),
                title = { Text("Aim allocation") },
                navigationIcon = {
                    IconButton(onClick = onDone) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    HelpIconButton(onClick = { showHelp = true })
                    TextButton(onClick = viewModel::distributeRemainder) { Text("Fill gap") }
                    TextButton(onClick = viewModel::distributeEvenly) { Text("Even") }
                    TextButton(onClick = viewModel::save, enabled = state.canSave) {
                        Text(if (state.saving) "Saving…" else "Save")
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarState) },
    ) { padding ->
        if (state.loading) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Text(
                    "Set aim percentages for each active asset class. The Snapshot Detail's drift colours compare against these.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            item { SumPill(sum = state.sum) }
            items(state.rows, key = { it.assetClassId }) { row ->
                AimSlider(
                    label = row.assetClassName,
                    value = row.percent,
                    onChange = { viewModel.setPercent(row.assetClassId, it) },
                )
            }
        }
    }

    if (showHelp) {
        HelpSheet(
            sheet = HelpSheetContent.AIM_WHAT_IS,
            onDismiss = { showHelp = false },
            onLearnMore = onNavigateToAbout,
        )
    }
}

@Composable
private fun SumPill(sum: Int) {
    val ok = sum == 100
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (ok) MaterialTheme.colorScheme.primaryContainer
                             else MaterialTheme.colorScheme.errorContainer,
        ),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text("Total", fontWeight = FontWeight.SemiBold)
            Text(if (ok) "100% ✓" else "$sum% (must be 100)", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun AimSlider(label: String, value: Int, onChange: (Int) -> Unit) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(label, style = MaterialTheme.typography.bodyMedium)
            Text("$value%", fontWeight = FontWeight.SemiBold)
        }
        Spacer(Modifier.height(4.dp))
        Slider(
            value = value.toFloat(),
            onValueChange = { onChange(it.toInt()) },
            valueRange = 0f..100f,
            steps = 99,
            colors = SliderDefaults.colors(),
        )
    }
}
