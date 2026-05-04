package com.fintrack.ui.snapshots.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.data.repo.UserSettingsRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.analytics.SnapshotAnalytics
import com.fintrack.domain.analytics.SnapshotAnalyticsCalculator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject

data class SnapshotDetailUiState(
    val loading: Boolean = true,
    val analytics: SnapshotAnalytics? = null,
    val error: String? = null,
)

@HiltViewModel
class SnapshotDetailViewModel @Inject constructor(
    private val snapshotRepository: SnapshotRepository,
    private val holdingRepository: HoldingRepository,
    private val userSettingsRepository: UserSettingsRepository,
    private val userScope: UserScope,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val snapshotId: UUID = savedStateHandle.get<String>(ARG_SNAPSHOT_ID)
        ?.let(UUID::fromString)
        ?: error("snapshot id missing from nav args")

    private val _state = MutableStateFlow(SnapshotDetailUiState())
    val state: StateFlow<SnapshotDetailUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch { hydrate() }
    }

    private suspend fun hydrate() {
        val activeUser = userScope.activeUserId.value
        if (activeUser == null) {
            _state.update { it.copy(loading = false, error = "No active profile") }
            return
        }
        val snapshot = snapshotRepository.getForUser(activeUser, snapshotId)
        if (snapshot == null) {
            _state.update { it.copy(loading = false, error = "Snapshot not found") }
            return
        }
        val values = snapshotRepository.getValuesForSnapshot(activeUser, snapshotId)
        val catalog = holdingRepository.observeAll().first()
        val settings = userSettingsRepository.getOrDefault(activeUser)

        val previousSnapshot = snapshotRepository.previousForUser(activeUser, snapshot.snapshotDate)
        val previousTotal: BigDecimal? = previousSnapshot?.let { prev ->
            snapshotRepository.getValuesForSnapshot(activeUser, prev.id)
                .fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
        }

        val analytics = SnapshotAnalyticsCalculator.compute(
            snapshot = snapshot,
            values = values,
            catalog = catalog,
            settings = settings,
            previousTotalPortfolio = previousTotal,
        )
        _state.update { it.copy(loading = false, analytics = analytics, error = null) }
    }

    companion object {
        const val ARG_SNAPSHOT_ID = "snapshotId"
    }
}
