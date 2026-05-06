package com.fintrack.ui.snapshots.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.HoldingValueEntity
import com.fintrack.data.db.entities.LoanValueEntity
import com.fintrack.data.db.entities.SnapshotEntity
import com.fintrack.data.repo.AimAllocationRepository
import com.fintrack.data.repo.GoalRepository
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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
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
    private val goalRepository: GoalRepository,
    private val userScope: UserScope,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val snapshotId: UUID = savedStateHandle.get<String>(ARG_SNAPSHOT_ID)
        ?.let(UUID::fromString)
        ?: error("snapshot id missing from nav args")

    private val _state = MutableStateFlow(SnapshotDetailUiState())
    val state: StateFlow<SnapshotDetailUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch { observeAndHydrate() }
    }

    /**
     * Re-emit a fresh [SnapshotAnalytics] whenever the underlying snapshot,
     * its holding values, or its loan values change. Fixes the stale-cache
     * bug where Detail → Edit → save → back-pop returned to a UiState that
     * pre-dated the edit (notes typed during edit didn't appear). The Detail
     * VM is retained on the back stack, so a one-shot `init` hydrate would
     * never re-run; the Flow-based observer below does.
     */
    private suspend fun observeAndHydrate() {
        val activeUser = userScope.activeUserId.value
        if (activeUser == null) {
            _state.update { it.copy(loading = false, error = "No active profile") }
            return
        }
        combine(
            snapshotRepository.observeForUser(activeUser)
                .map { snaps -> snaps.firstOrNull { it.id == snapshotId } },
            snapshotRepository.observeAllValuesForUser(activeUser)
                .map { all -> all.filter { it.snapshotId == snapshotId } },
            loanRepository.observeAllValuesForUser(activeUser)
                .map { all -> all.filter { it.snapshotId == snapshotId } },
        ) { snapshot, values, loanValues ->
            Triple(snapshot, values, loanValues)
        }.distinctUntilChanged().collectLatest { (snapshot, values, loanValues) ->
            if (snapshot == null) {
                _state.update { it.copy(loading = false, error = "Snapshot not found") }
                return@collectLatest
            }
            val analytics = computeAnalytics(activeUser, snapshot, values, loanValues)
            val deleteImpact = computeDeleteImpact(activeUser)
            _state.update {
                it.copy(
                    loading = false,
                    analytics = analytics,
                    error = null,
                    deleteImpact = deleteImpact,
                )
            }
        }
    }

    private suspend fun computeAnalytics(
        activeUser: UUID,
        snapshot: SnapshotEntity,
        values: List<HoldingValueEntity>,
        loanValues: List<LoanValueEntity>,
    ): SnapshotAnalytics {
        val catalog = holdingRepository.observeAll().first()
        val subBuckets = taxonomyRepository.observeSubBuckets().first()
        val assetClasses = taxonomyRepository.observeAssetClasses().first()
        val aim = aimRepository.getOrDefault(activeUser)
        val loans = loanRepository.observeLoansForUser(activeUser).first()

        val previousSnapshot = snapshotRepository.previousForUser(activeUser, snapshot.snapshotDate)
        val previousNetWorth: BigDecimal? = previousSnapshot?.let { prev ->
            val prevAssets = snapshotRepository.getValuesForSnapshot(activeUser, prev.id)
                .fold(BigDecimal.ZERO) { acc, v -> acc + v.current }
            val prevLiabilities = loanRepository.getValuesForSnapshot(activeUser, prev.id)
                .fold(BigDecimal.ZERO) { acc, v -> acc + v.outstanding }
            prevAssets.subtract(prevLiabilities)
        }

        return SnapshotAnalyticsCalculator.compute(
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
    }

    private suspend fun computeDeleteImpact(activeUser: UUID): SnapshotDeleteImpact {
        val allSnapshots = snapshotRepository.observeForUser(activeUser).first()
        val streakState = streakRepository.getForUser(activeUser)
        return SnapshotDeleteImpactAnalyzer.analyze(
            targetSnapshotId = snapshotId,
            allSnapshots = allSnapshots,
            currentStreakMonths = streakState?.currentStreakMonths ?: 0,
        )
    }

    fun delete(onDone: () -> Unit) {
        val userId = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            snapshotRepository.deleteSnapshot(userId, snapshotId)
            streakRepository.recompute(userId)
            milestoneRepository.detectAndPersist(userId)
            goalRepository.detectAndPersistAchievements(userId)
            onDone()
        }
    }

    companion object {
        const val ARG_SNAPSHOT_ID = "snapshotId"
    }
}
