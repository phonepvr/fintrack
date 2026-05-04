package com.fintrack.ui.snapshots.entry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.repo.HoldingValueDraft
import com.fintrack.data.repo.HoldingRepository
import com.fintrack.data.repo.SnapshotRepository
import com.fintrack.data.repo.TaxonomyRepository
import com.fintrack.domain.UserScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import java.util.UUID
import javax.inject.Inject

data class HoldingFieldsState(
    val holdingId: UUID,
    val name: String,
    val subBucketId: UUID,
    val subBucketName: String,
    val assetClassId: UUID,
    val assetClassName: String,
    val trackInvested: Boolean,
    val trackSip: Boolean,
    val invested: String = "",
    val current: String = "",
    val sip: String = "",
)

data class SnapshotEntryUiState(
    val loading: Boolean = true,
    val editing: Boolean = false,
    val date: LocalDate = todayLocal(),
    val earnings: String = "",
    val notes: String = "",
    val rows: List<HoldingFieldsState> = emptyList(),
    val saving: Boolean = false,
    val error: String? = null,
    val savedSnapshotId: UUID? = null,
)

@HiltViewModel
class SnapshotEntryViewModel @Inject constructor(
    private val snapshotRepository: SnapshotRepository,
    private val holdingRepository: HoldingRepository,
    private val taxonomyRepository: TaxonomyRepository,
    private val userScope: UserScope,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val snapshotId: UUID? =
        savedStateHandle.get<String>(ARG_SNAPSHOT_ID)?.takeUnless { it.isBlank() }?.let(UUID::fromString)

    private val _state = MutableStateFlow(SnapshotEntryUiState(editing = snapshotId != null))
    val state: StateFlow<SnapshotEntryUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch { hydrate() }
    }

    private suspend fun hydrate() {
        val activeUser = userScope.activeUserId.value
        if (activeUser == null) {
            _state.update { it.copy(loading = false, error = "No active profile") }
            return
        }
        val holdings = holdingRepository.observeActive().first()
        val subBuckets = taxonomyRepository.getActiveSubBuckets().associateBy { it.id }
        val assetClasses = taxonomyRepository.getActiveAssetClasses().associateBy { it.id }
        val rowsTemplate = holdings.mapNotNull { h ->
            val sb = subBuckets[h.subBucketId] ?: return@mapNotNull null
            val ac = assetClasses[sb.assetClassId] ?: return@mapNotNull null
            HoldingFieldsState(
                holdingId = h.id,
                name = h.name,
                subBucketId = sb.id,
                subBucketName = sb.name,
                assetClassId = ac.id,
                assetClassName = ac.name,
                trackInvested = h.trackInvested,
                trackSip = h.trackSip,
            )
        }
        if (snapshotId == null) {
            _state.update { it.copy(loading = false, rows = rowsTemplate) }
            return
        }
        val snapshot = snapshotRepository.getForUser(activeUser, snapshotId)
        if (snapshot == null) {
            _state.update { it.copy(loading = false, error = "Snapshot not found") }
            return
        }
        val values = snapshotRepository.getValuesForSnapshot(activeUser, snapshotId)
            .associateBy { it.holdingId }
        val populatedRows = rowsTemplate.map { row ->
            val v = values[row.holdingId]
            if (v == null) row else row.copy(
                invested = v.invested?.toPlainString().orEmpty(),
                current = v.current.toPlainString(),
                sip = v.sip?.toPlainString().orEmpty(),
            )
        }
        _state.update {
            it.copy(
                loading = false,
                editing = true,
                date = snapshot.snapshotDate,
                earnings = snapshot.earningsInCr.toPlainString(),
                notes = snapshot.notes.orEmpty(),
                rows = populatedRows,
            )
        }
    }

    fun setDate(date: LocalDate) = _state.update { it.copy(date = date) }
    fun setEarnings(text: String) = _state.update { it.copy(earnings = text.filterNumeric()) }
    fun setNotes(text: String) = _state.update { it.copy(notes = text) }

    fun setRowInvested(holdingId: UUID, text: String) =
        updateRow(holdingId) { it.copy(invested = text.filterNumeric()) }

    fun setRowCurrent(holdingId: UUID, text: String) =
        updateRow(holdingId) { it.copy(current = text.filterNumeric()) }

    fun setRowSip(holdingId: UUID, text: String) =
        updateRow(holdingId) { it.copy(sip = text.filterNumeric()) }

    fun save() {
        val current = _state.value
        val earnings = current.earnings.parseAmountOrNull()
        if (earnings == null || earnings.signum() < 0) {
            _state.update { it.copy(error = "Earnings (in cr) is required and must be ≥ 0") }
            return
        }
        val drafts = current.rows.mapNotNull { row -> row.toDraftOrNull() }
        if (drafts.isEmpty()) {
            _state.update { it.copy(error = "Enter at least one holding's current value") }
            return
        }
        val validation = drafts.firstNotNullOfOrNull { it.validationError() }
        if (validation != null) {
            _state.update { it.copy(error = validation) }
            return
        }
        val activeUser = userScope.activeUserId.value
        if (activeUser == null) {
            _state.update { it.copy(error = "No active profile") }
            return
        }
        _state.update { it.copy(saving = true, error = null) }
        viewModelScope.launch {
            try {
                val resultId = if (snapshotId == null) {
                    snapshotRepository.createSnapshot(
                        userId = activeUser,
                        date = current.date,
                        earningsInCr = earnings,
                        notes = current.notes.takeIf { it.isNotBlank() },
                        holdingValues = drafts,
                    )
                } else {
                    snapshotRepository.updateSnapshot(
                        userId = activeUser,
                        snapshotId = snapshotId,
                        date = current.date,
                        earningsInCr = earnings,
                        notes = current.notes.takeIf { it.isNotBlank() },
                        holdingValues = drafts,
                    )
                    snapshotId
                }
                _state.update { it.copy(saving = false, savedSnapshotId = resultId) }
            } catch (t: Throwable) {
                _state.update { it.copy(saving = false, error = t.message ?: "Save failed") }
            }
        }
    }

    fun clearError() = _state.update { it.copy(error = null) }

    private fun updateRow(holdingId: UUID, transform: (HoldingFieldsState) -> HoldingFieldsState) {
        _state.update { ui ->
            ui.copy(rows = ui.rows.map { if (it.holdingId == holdingId) transform(it) else it })
        }
    }

    companion object {
        const val ARG_SNAPSHOT_ID = "snapshotId"
    }
}

internal fun todayLocal(): LocalDate =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

private fun HoldingFieldsState.toDraftOrNull(): HoldingValueDraft? {
    val current = current.parseAmountOrNull() ?: return null
    val invested = if (trackInvested) invested.parseAmountOrNull() else null
    val sip = if (trackSip) sip.parseAmountOrNull() else null
    return HoldingValueDraft(
        holdingId = holdingId,
        invested = invested,
        current = current,
        sip = sip,
    )
}

private fun HoldingValueDraft.validationError(): String? {
    if (current.signum() < 0) return "Current values must be ≥ 0"
    if (invested != null && invested.signum() < 0) return "Invested values must be ≥ 0"
    if (sip != null && sip.signum() < 0) return "SIP values must be ≥ 0"
    return null
}
