package com.tinaciousdesign.aboutapps.repositories

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.tinaciousdesign.aboutapps.models.InstalledApp
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject


interface InstalledAppsRepository {
    val installedApps: Flow<List<InstalledApp>>
    fun getInstalledApps(): List<InstalledApp>
}

class InstalledAppsRepositoryImpl @Inject constructor(
    @ApplicationContext private val application: Context,
) : InstalledAppsRepository {
    override val installedApps: Flow<List<InstalledApp>>
        get() = flow {
            val installedApps = getInstalledApps()
            emit(installedApps)
        }

    override fun getInstalledApps(): List<InstalledApp> {
        val packageManager = application.packageManager
        val packages = packageManager.getInstalledPackages(PackageManager.GET_META_DATA)

        return packages
            .filter { packageInfo ->
                (packageInfo.applicationInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0
            }
            .map { packageInfo ->
                val icon = packageManager.getApplicationIcon(packageInfo.applicationInfo)
                val appName = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString()
                val fileSize = File(packageInfo.applicationInfo.sourceDir).length()

                InstalledApp(
                    appName = appName,
                    packageName = packageInfo.packageName,
                    icon = icon,
                    installedAt = packageInfo.firstInstallTime,
                    lastUpdatedAt = packageInfo.lastUpdateTime,
                    versionName = packageInfo.versionName,
                    version = packageInfo.longVersionCode,
                    size = fileSize,
                )
            }
    }
}
