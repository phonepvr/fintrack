package com.fintrack.ui.picker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.UserEntity
import com.fintrack.data.repo.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ProfilePickerViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    val users: StateFlow<List<UserEntity>> = userRepository.observeActiveUsers()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun createUser(name: String, colorHex: String, onCreated: (UUID) -> Unit) {
        viewModelScope.launch {
            val user = userRepository.createUser(name = name.trim(), colorHex = colorHex, makeActive = false)
            onCreated(user.id)
        }
    }

    fun renameUser(id: UUID, name: String, colorHex: String) {
        viewModelScope.launch { userRepository.renameUser(id, name, colorHex) }
    }

    fun deleteUser(id: UUID) {
        viewModelScope.launch { userRepository.deleteUser(id) }
    }
}
