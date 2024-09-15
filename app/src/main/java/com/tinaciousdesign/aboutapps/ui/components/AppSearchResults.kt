package com.tinaciousdesign.aboutapps.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.tinaciousdesign.aboutapps.models.InstalledApp

@Composable
fun AppSearchResults(
    installedApps: List<InstalledApp>,
) {
    LazyColumn {
        itemsIndexed(installedApps) { idx, stock ->
            AppSearchResultListItem(stock)

            if (idx < installedApps.lastIndex) {
                Divider(MaterialTheme.colorScheme.surface)
            }
        }
    }
}
