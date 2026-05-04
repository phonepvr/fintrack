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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.UserSettingsEntity
import com.fintrack.data.repo.UserSettingsRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.model.AssetClass
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AimEditorUiState(
    val loading: Boolean = true,
    val mfNps: Int = 55,
    val equity: Int = 15,
    val fixedReturn: Int = 25,
    val crypto: Int = 5,
    val saving: Boolean = false,
    val saved: Boolean = false,
    val error: String? = null,
) {
    val sum: Int get() = mfNps + equity + fixedReturn + crypto
    val canSave: Boolean get() = sum == 100 && !saving
}

@HiltViewModel
class AimEditorViewModel @Inject constructor(
    private val userSettingsRepository: UserSettingsRepository,
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
            val s = userSettingsRepository.getOrDefault(activeUser)
            _state.update {
                it.copy(
                    loading = false,
                    mfNps = s.aimPctMfNps,
                    equity = s.aimPctEquity,
                    fixedReturn = s.aimPctFixedReturn,
                    crypto = s.aimPctCrypto,
                )
            }
        }
    }

    fun setMfNps(v: Int) = _state.update { it.copy(mfNps = v.coerceIn(0, 100)) }
    fun setEquity(v: Int) = _state.update { it.copy(equity = v.coerceIn(0, 100)) }
    fun setFixedReturn(v: Int) = _state.update { it.copy(fixedReturn = v.coerceIn(0, 100)) }
    fun setCrypto(v: Int) = _state.update { it.copy(crypto = v.coerceIn(0, 100)) }

    fun save() {
        val s = _state.value
        if (!s.canSave) {
            _state.update { it.copy(error = "Aim percentages must sum to exactly 100 (currently ${s.sum})") }
            return
        }
        val activeUser = userScope.activeUserId.value ?: return
        _state.update { it.copy(saving = true, error = null) }
        viewModelScope.launch {
            runCatching {
                userSettingsRepository.update(UserSettingsEntity(
                    userId = activeUser,
                    aimPctMfNps = s.mfNps,
                    aimPctEquity = s.equity,
                    aimPctFixedReturn = s.fixedReturn,
                    aimPctCrypto = s.crypto,
                ))
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
    viewModel: AimEditorViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val snackbarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

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
                    "Set per-class aim percentages for the active profile. The Snapshot Detail's drift colours compare against these.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            item { SumPill(sum = state.sum) }
            item {
                AimSlider("MF + NPS (Moderate)", state.mfNps, viewModel::setMfNps)
            }
            item {
                AimSlider("Equity (Aggressive)", state.equity, viewModel::setEquity)
            }
            item {
                AimSlider("Fixed Return (Safe)", state.fixedReturn, viewModel::setFixedReturn)
            }
            item {
                AimSlider("Crypto (Very Aggressive)", state.crypto, viewModel::setCrypto)
            }
            item {
                Text(
                    "Defaults: MF + NPS ${AssetClass.MF_NPS.defaultAimPct}, " +
                        "Equity ${AssetClass.EQUITY.defaultAimPct}, " +
                        "Fixed ${AssetClass.FIXED_RETURN.defaultAimPct}, " +
                        "Crypto ${AssetClass.CRYPTO.defaultAimPct}.",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun SumPill(sum: Int) {
    val ok = sum == 100
    Card(
        colors = androidx.compose.material3.CardDefaults.cardColors(
            containerColor = if (ok) MaterialTheme.colorScheme.primaryContainer
                             else MaterialTheme.colorScheme.errorContainer,
        ),
    ) {
        Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total", fontWeight = FontWeight.SemiBold)
            Text(if (ok) "100% ✓" else "$sum% (must be 100)", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun AimSlider(label: String, value: Int, onChange: (Int) -> Unit) {
    Column {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
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
