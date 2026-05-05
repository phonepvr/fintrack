package com.fintrack.ui.snapshots.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.AimAllocationRepository
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.LoanRepository
import com.fintrack.data.repo.MilestoneRepository
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.data.repo.StreakRepository
import com.fintrack.data.repo.TaxonomyRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.analytics.SnapshotAnalytics
import com.fintrack.domain.analytics.SnapshotAnalyticsCalculator
import com.fintrack.domain.snapshots.SnapshotDeleteImpact
import com.fintrack.domain.snapshots.SnapshotDeleteImpactAnalyzer
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
    val deleteImpact: SnapshotDeleteImpact = SnapshotDeleteImpact(),
)

@HiltViewModel
class SnapshotDetailViewModel @Inject constructor(
    private val snapshotRepository: SnapshotRepository,
    private val holdingRepository: HoldingRepository,
    private val taxonomyRepository: TaxonomyRepository,
    private val aimRepository: AimAllocationRepository,
    private val loanRepository: LoanRepository,
    private val streakRepository: StreakRepository,
    private val milestoneRepository: MilestoneRepository,
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
        val subBuckets = taxonomyRepository.observeSubBuckets().first()
        val assetClasses = taxonomyRepository.observeAssetClasses().first()
        val aim = aimRepository.getOrDefault(activeUser)
        val loans = loanRepository.observeLoansForUser(activeUser).first()
        val loanValues = loanRepository.getValuesForSnapshot(activeUser, snapshotId)

        val previousSnapshot = snapshotRepository.previousForUser(activeUser, snapshot.snapshotDate)
        val previousNetWorth: BigDecimal? = previousSnapshot?.let { prev ->
            val prevAssets = snapshotRepository.getValuesForSnapshot(activeUser, prev.id)
                .fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
            val prevLiabilities = loanRepository.getValuesForSnapshot(activeUser, prev.id)
                .fold(BigDecimal.ZERO) { acc, v -> acc + v.outstanding }
            prevAssets.subtract(prevLiabilities)
        }

        val analytics = SnapshotAnalyticsCalculator.compute(
            snapshot = snapshot,
            values = values,
            catalog = catalog,
            subBuckets = subBuckets,
            assetClasses = assetClasses,
            aimAllocations = aim,
            loans = loans,
            loanValues = loanValues,
            previousNetWorth = previousNetWorth,
        )

        val allSnapshots = snapshotRepository.observeForUser(activeUser).first()
        val streakState = streakRepository.getForUser(activeUser)
        val deleteImpact = SnapshotDeleteImpactAnalyzer.analyze(
            targetSnapshotId = snapshotId,
            allSnapshots = allSnapshots,
            currentStreakMonths = streakState?.currentStreakMonths ?: 0,
        )

        _state.update {
            it.copy(
                loading = false,
                analytics = analytics,
                error = null,
                deleteImpact = deleteImpact,
            )
        }
    }

    fun delete(onDone: () -> Unit) {
        val userId = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            snapshotRepository.deleteSnapshot(userId, snapshotId)
            streakRepository.recompute(userId)
            milestoneRepository.detectAndPersist(userId)
            onDone()
        }
    }

    companion object {
        const val ARG_SNAPSHOT_ID = "snapshotId"
    }
}
