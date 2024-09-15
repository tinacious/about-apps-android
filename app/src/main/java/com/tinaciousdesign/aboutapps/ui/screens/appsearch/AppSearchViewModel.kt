package com.tinaciousdesign.aboutapps.ui.screens.appsearch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinaciousdesign.aboutapps.models.InstalledApp
import com.tinaciousdesign.aboutapps.models.matches
import com.tinaciousdesign.aboutapps.repositories.InstalledAppsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

interface AppSearchVM {
    val installedApps: StateFlow<List<InstalledApp>>
    val searchQuery: StateFlow<String>
    val isSearching: Flow<Boolean>

    fun onSearch(query: String)
}

@HiltViewModel
class AppSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    installedAppsRepository: InstalledAppsRepository,
) : AppSearchVM, ViewModel() {

    override val searchQuery: StateFlow<String> = savedStateHandle.getStateFlow("searchQuery", "")

    override val installedApps: StateFlow<List<InstalledApp>> =
        installedAppsRepository
            .installedApps
            .combine(searchQuery, ::Pair)
            .map { (list, query) ->
                list.matches(query)
                    .sortedBy { it.appName }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList(),
            )


    override val isSearching = searchQuery.map { it.isNotBlank() }

    override fun onSearch(query: String) {
        savedStateHandle["searchQuery"] = query
    }
}
