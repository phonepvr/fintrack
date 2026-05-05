package com.fintrack.ui.settings.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fintrack.data.db.entities.AssetClassEntity
import com.fintrack.data.db.entities.SubBucketEntity
import com.fintrack.data.repo.TaxonomyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class AboutUiState(
    val assetClasses: List<AssetClassEntity> = emptyList(),
    val subBuckets: List<SubBucketEntity> = emptyList(),
)

/**
 * Streams the live taxonomy so the About glossary's asset-class names
 * reflect any user renames or additions. Static explanations live in
 * `domain/glossary/GlossaryContent.kt` and are paired with these names
 * by the screen.
 */
@HiltViewModel
class AboutViewModel @Inject constructor(
    taxonomyRepository: TaxonomyRepository,
) : ViewModel() {

    val assetClasses: StateFlow<List<AssetClassEntity>> = taxonomyRepository
        .observeAssetClasses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    val subBuckets: StateFlow<List<SubBucketEntity>> = taxonomyRepository
        .observeSubBuckets()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
}
