package com.tinaciousdesign.aboutapps.models

import android.graphics.drawable.Drawable
import com.tinaciousdesign.aboutapps.ui.components.SortDirection
import com.tinaciousdesign.aboutapps.ui.components.SortMode

data class InstalledApp(
    val packageName: String,
    val appName: String,
    val icon: Drawable,
    val size: Long,
    val installedAt: Long,
    val lastUpdatedAt: Long,
    val version: Long,
    val versionName: String?,
) {
    companion object {
        fun compareBySortModeAndDirection(sortMode: SortMode, sortDirection: SortDirection): Comparator<InstalledApp> =
            Comparator { a, b ->
                val first = if (sortDirection == SortDirection.ASCENDING) a else b
                val second = if (sortDirection == SortDirection.ASCENDING) b else a

                when (sortMode) {
                    SortMode.APP_NAME -> first.appName.compareTo(second.appName)
                    SortMode.PACKAGE_NAME -> first.packageName.compareTo(second.packageName)
                    SortMode.INSTALLED_AT -> first.installedAt.compareTo(second.installedAt)
                    SortMode.LAST_UPDATED_AT -> first.lastUpdatedAt.compareTo(second.lastUpdatedAt)
                    SortMode.FILE_SIZE -> first.size.compareTo(second.size)
                }
            }
    }
}

fun List<InstalledApp>.filterByQuery(query: String): List<InstalledApp> =
    this.filter { stock ->
        stock.appName.contains(query.trim(), ignoreCase = true) ||
            stock.packageName.contains(query.trim(), ignoreCase = true)
    }
