package com.tinaciousdesign.aboutapps.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.tinaciousdesign.aboutapps.R
import com.tinaciousdesign.aboutapps.models.InstalledApp
import com.tinaciousdesign.aboutapps.ui.theme.LocalCustomColourPalette
import com.tinaciousdesign.aboutapps.utils.formattedDateFromMillis
import com.tinaciousdesign.aboutapps.utils.formattedFileSize
import com.tinaciousdesign.aboutapps.utils.openAppPackage
import com.tinaciousdesign.aboutapps.utils.openSettingsForAppPackage

@Composable
fun AppSearchResultListItem(
    installedApp: InstalledApp,
) {
    val context = LocalContext.current
    val colours =  LocalCustomColourPalette.current

    Row(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 12.dp,
            )
    ) {
        Image(
            painter = rememberAsyncImagePainter(installedApp.icon),
            contentDescription = null,
            modifier = Modifier
                .width(60.dp)
                .height(60.dp)
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
        ) {
            Text(
                text = installedApp.appName,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Text(
                text = installedApp.packageName,
                color = colours.textColorMuted,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(bottom = 10.dp)
            )

            Row {
                Text(
                    text = context.getString(R.string.app_search_result_item_label_version) + ":",
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = "${installedApp.version}",
                    color = colours.textColorMuted,
                )
            }

            Row {
                Text(
                    text = context.getString(R.string.app_search_result_item_label_version_name) + ":",
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = installedApp.versionName ?: context.getString(R.string.app_search_result_item_no_version_name),
                    color = colours.textColorMuted,
                )
            }

            Text(
                text = formattedFileSize(installedApp.size),
                modifier = Modifier
                    .padding(top = 10.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
            ) {
                Text(
                    text = context.getString(R.string.app_search_result_item_label_installed_at) + ":",
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = formattedDateFromMillis(installedApp.installedAt),
                    color = colours.textColorMuted,
                )
            }

            Row {
                Text(
                    text = context.getString(R.string.app_search_result_item_label_last_updated_at) + ":",
                    modifier = Modifier
                        .padding(end = 5.dp)
                )
                Text(
                    text = formattedDateFromMillis(installedApp.lastUpdatedAt),
                    color = colours.textColorMuted,
                )
            }

            ButtonRow(
                installedApp,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun ButtonRow(
    installedApp: InstalledApp,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
    ) {
        MutedBackgroundButton(
            onClick = {
                context.openSettingsForAppPackage(installedApp.packageName)
            },
            modifier = Modifier
                .padding(end = 10.dp)
        ) {
            Text(context.getString(R.string.app_search_result_item_button_settings))
        }

        MutedBackgroundButton(
            onClick = {
                context.openAppPackage(installedApp.packageName)
            },
        ) {
            Text(context.getString(R.string.app_search_result_item_button_open))
        }
    }
}
