package com.tinaciousdesign.aboutapps.ui.screens.appsearch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tinaciousdesign.aboutapps.ui.components.AppSearchResults
import com.tinaciousdesign.aboutapps.ui.components.Divider
import com.tinaciousdesign.aboutapps.ui.components.SearchInputView
import com.tinaciousdesign.aboutapps.ui.components.SortControls
import com.tinaciousdesign.aboutapps.ui.components.SortDirection
import com.tinaciousdesign.aboutapps.ui.components.SortMode

@Composable
fun AppSearchScreen(
    viewModel: AppSearchViewModel
) {
    val installedApps by viewModel.installedApps.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val sortMode by viewModel.sortMode.collectAsStateWithLifecycle(SortMode.APP_NAME)
    val sortDirection by viewModel.sortDirection.collectAsStateWithLifecycle(SortDirection.ASCENDING)

    Column {
        SearchInputView(searchQuery) { newValue ->
            viewModel.onSearch(newValue)
        }

        SortControls(
            sortMode,
            sortDirection,
            viewModel::onSortModeChanged,
            viewModel::onSortDirectionChanged,
        )

        Divider(MaterialTheme.colorScheme.secondary)

        Box(modifier = Modifier.weight(1.0f)) {
            AppSearchResults(installedApps)
        }
    }
}
