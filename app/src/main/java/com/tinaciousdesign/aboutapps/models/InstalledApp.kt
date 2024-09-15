package com.tinaciousdesign.aboutapps.models

import android.graphics.drawable.Drawable

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
        fun compareQuery(query: String): Comparator<InstalledApp> =
            Comparator { a, b ->
                val queryLowered = query.lowercase()

                val aExactMatch = a.appName.lowercase() == queryLowered ||
                    a.packageName.lowercase() == queryLowered
                val bExactMatch = b.appName.lowercase() == queryLowered ||
                    b.packageName.lowercase() == queryLowered

                if (aExactMatch && bExactMatch) {
                    return@Comparator 0
                }

                if (aExactMatch) -1 else 1
            }
    }
}

fun List<InstalledApp>.matches(query: String): List<InstalledApp> =
    this.filter { stock ->
        stock.appName.contains(query.trim(), ignoreCase = true) ||
            stock.packageName.contains(query.trim(), ignoreCase = true)
    }
