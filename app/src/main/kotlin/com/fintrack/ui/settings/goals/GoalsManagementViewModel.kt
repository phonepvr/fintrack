package com.fintrack.ui.settings.goals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.GoalEntity
import com.fintrack.data.repo.GoalRepository
import com.fintrack.domain.UserScope
import com.fintrack.domain.model.GoalType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import java.math.BigDecimal
import java.util.UUID
import javax.inject.Inject

data class GoalsUiState(
    val goals: List<GoalEntity> = emptyList(),
    val error: String? = null,
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class GoalsManagementViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val userScope: UserScope,
) : ViewModel() {

    private val errorState = MutableStateFlow<String?>(null)

    val state: StateFlow<GoalsUiState> = userScope.activeUserId
        .flatMapLatest { uid ->
            if (uid == null) flowOf(emptyList())
            else goalRepository.observeAll(uid)
        }
        .let { goals ->
            kotlinx.coroutines.flow.combine(goals, errorState) { g, err ->
                GoalsUiState(g, err)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), GoalsUiState())

    fun create(name: String, type: GoalType, target: BigDecimal, targetDate: LocalDate) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch {
            try {
                goalRepository.create(uid, name, type, target, targetDate)
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not create goal"
            }
        }
    }

    fun update(goal: GoalEntity) {
        viewModelScope.launch {
            try {
                goalRepository.update(goal)
            } catch (t: Throwable) {
                errorState.value = t.message ?: "Could not update goal"
            }
        }
    }

    fun archive(id: UUID) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch { goalRepository.archive(uid, id) }
    }

    fun delete(id: UUID) {
        val uid = userScope.activeUserId.value ?: return
        viewModelScope.launch { goalRepository.delete(uid, id) }
    }

    fun clearError() { errorState.value = null }
}
