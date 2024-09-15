package com.tinaciousdesign.aboutapps.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

fun Context.openExternalBrowser(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    startActivity(intent)
}

fun Context.openAppPackage(packageName: String) {
    val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
    startActivity(launchIntent)
}

fun Context.openSettingsForAppPackage(packageName: String) {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
    }
    startActivity(intent)
}
