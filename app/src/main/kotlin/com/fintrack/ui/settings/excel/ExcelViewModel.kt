package com.fintrack.ui.settings.excel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.xlsx.XlsxCodec
import com.fintrack.data.xlsx.XlsxConflictStrategy
import com.fintrack.data.xlsx.XlsxImportApplier
import com.fintrack.data.xlsx.XlsxImportSummary
import com.fintrack.data.xlsx.XlsxWorkbookData
import com.fintrack.domain.UserScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed interface ExcelStatus {
    data object Idle : ExcelStatus
    data object Working : ExcelStatus
    data class Preview(val data: XlsxWorkbookData) : ExcelStatus
    data class TemplateExported(val name: String) : ExcelStatus
    data class ImportSucceeded(val summary: XlsxImportSummary) : ExcelStatus
    data class Failed(val message: String) : ExcelStatus
}

data class ExcelUiState(
    val status: ExcelStatus = ExcelStatus.Idle,
    val strategy: XlsxConflictStrategy = XlsxConflictStrategy.SKIP_EXISTING,
)

@HiltViewModel
class ExcelViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val applier: XlsxImportApplier,
    private val userScope: UserScope,
) : ViewModel() {

    private val _state = MutableStateFlow(ExcelUiState())
    val state: StateFlow<ExcelUiState> = _state.asStateFlow()

    fun setStrategy(s: XlsxConflictStrategy) {
        _state.update { it.copy(strategy = s) }
    }

    fun reset() {
        _state.value = ExcelUiState()
    }

    fun exportTemplate(target: Uri) {
        _state.update { it.copy(status = ExcelStatus.Working) }
        viewModelScope.launch {
            val result = runCatching {
                withContext(Dispatchers.IO) {
                    context.contentResolver.openOutputStream(target).use { out ->
                        requireNotNull(out) { "Could not open output stream" }
                        XlsxCodec.writeEmptyTemplate(out)
                    }
                }
            }
            _state.update {
                it.copy(
                    status = result.fold(
                        onSuccess = { ExcelStatus.TemplateExported("fintrack-template.xlsx") },
                        onFailure = { e -> ExcelStatus.Failed(e.message ?: "Export failed") },
                    ),
                )
            }
        }
    }

    fun preview(source: Uri) {
        _state.update { it.copy(status = ExcelStatus.Working) }
        viewModelScope.launch {
            val result = runCatching {
                withContext(Dispatchers.IO) {
                    context.contentResolver.openInputStream(source).use { input ->
                        requireNotNull(input) { "Could not open input stream" }
                        XlsxCodec.read(input)
                    }
                }
            }
            _state.update {
                it.copy(
                    status = result.fold(
                        onSuccess = { ExcelStatus.Preview(it) },
                        onFailure = { e -> ExcelStatus.Failed(e.message ?: "Read failed") },
                    ),
                )
            }
        }
    }

    fun applyPreview() {
        val previewing = _state.value.status as? ExcelStatus.Preview ?: return
        val userId = userScope.activeUserId.value ?: run {
            _state.update { it.copy(status = ExcelStatus.Failed("No active profile")) }
            return
        }
        _state.update { it.copy(status = ExcelStatus.Working) }
        viewModelScope.launch {
            val result = runCatching {
                applier.apply(userId, previewing.data, _state.value.strategy)
            }
            _state.update {
                it.copy(
                    status = result.fold(
                        onSuccess = { ExcelStatus.ImportSucceeded(it) },
                        onFailure = { e -> ExcelStatus.Failed(e.message ?: "Import failed") },
                    ),
                )
            }
        }
    }
}
