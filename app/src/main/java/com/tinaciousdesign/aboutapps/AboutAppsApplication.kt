package com.tinaciousdesign.aboutapps

import android.app.Application
import com.tinaciousdesign.aboutapps.BuildConfig
import com.tinaciousdesign.aboutapps.logging.CrashReportingTree
import com.tinaciousdesign.aboutapps.logging.DebugConsoleLoggingTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AboutAppsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setUpLogging()
    }

    private fun setUpLogging() {
        val loggingTree = if (BuildConfig.DEBUG) DebugConsoleLoggingTree() else CrashReportingTree()
        Timber.plant(loggingTree)
    }
}
