package com.tinaciousdesign.aboutapps.ui.screens.appsearch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinaciousdesign.aboutapps.models.InstalledApp
import com.tinaciousdesign.aboutapps.models.filterByQuery
import com.tinaciousdesign.aboutapps.repositories.InstalledAppsRepository
import com.tinaciousdesign.aboutapps.ui.components.SortDirection
import com.tinaciousdesign.aboutapps.ui.components.SortMode
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
    val sortMode: StateFlow<SortMode>
    val sortDirection: StateFlow<SortDirection>
    val isSearching: Flow<Boolean>

    fun onSearch(query: String)
    fun onSortModeChanged(sortMode: SortMode)
    fun onSortDirectionChanged(sortDirection: SortDirection)
}

@HiltViewModel
class AppSearchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    installedAppsRepository: InstalledAppsRepository,
) : AppSearchVM, ViewModel() {

    override val searchQuery: StateFlow<String> = savedStateHandle.getStateFlow("searchQuery", "")

    override val sortMode: StateFlow<SortMode> = savedStateHandle.getStateFlow("sortMode", SortMode.APP_NAME)

    override val sortDirection: StateFlow<SortDirection> = savedStateHandle
        .getStateFlow("sortDirection", SortDirection.ASCENDING)

    override val installedApps: StateFlow<List<InstalledApp>> =
        combine(
            sortMode,
            searchQuery,
            installedAppsRepository.installedApps,
            sortDirection
        ) { mode, query, appsList, direction ->
            DisplayCriteria(
                sortMode = mode,
                installedApps = appsList,
                searchQuery = query,
                sortDirection = direction,
            )
        }
            .map { data ->
                data.installedApps.filterByQuery(data.searchQuery)
                    .sortedWith(InstalledApp.compareBySortModeAndDirection(data.sortMode, data.sortDirection))
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

    override fun onSortModeChanged(sortMode: SortMode) {
        savedStateHandle["sortMode"] = sortMode
    }

    override fun onSortDirectionChanged(sortDirection: SortDirection) {
        savedStateHandle["sortDirection"] = sortDirection
    }
}

private data class DisplayCriteria(
    val searchQuery: String,
    val sortDirection: SortDirection,
    val sortMode: SortMode,
    val installedApps: List<InstalledApp>,
)
