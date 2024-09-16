package com.tinaciousdesign.aboutapps.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tinaciousdesign.aboutapps.R
import com.tinaciousdesign.aboutapps.ui.icons.TintedIconDrawable
import com.tinaciousdesign.aboutapps.ui.theme.LocalCustomColourPalette

enum class SortMode {
    APP_NAME,
    PACKAGE_NAME,
    INSTALLED_AT,
    LAST_UPDATED_AT,
    FILE_SIZE,
}

enum class SortDirection {
    ASCENDING,
    DESCENDING,
}

@Composable
fun SortControls(
    sortMode: SortMode,
    sortDirection: SortDirection,
    onSortModeChanged: (SortMode) -> Unit,
    onSortDirectionChanged: (SortDirection) -> Unit,
) {
    val context = LocalContext.current

    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableIntStateOf(0) }

    val sortOptions = listOf<Pair<Int, SortMode>>(
        Pair(R.string.app_search_sort_mode_option_app_name, SortMode.APP_NAME),
        Pair(R.string.app_search_sort_mode_option_package_name, SortMode.PACKAGE_NAME),
        Pair(R.string.app_search_sort_mode_option_installed_at, SortMode.INSTALLED_AT),
        Pair(R.string.app_search_sort_mode_option_last_modified_at, SortMode.LAST_UPDATED_AT),
        Pair(R.string.app_search_sort_mode_option_filesize, SortMode.FILE_SIZE),
    )

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                start = 14.dp,
                end = 14.dp,
            )
            .fillMaxWidth()
    ) {

        // region Sort Order

        Box {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable {
                        isDropDownExpanded.value = true
                    }
            ) {
                Text(context.getString(R.string.app_search_sort_mode_label) + ": ")
                Text(
                    text = context.getString(sortOptions[itemPosition.value].first),
                    fontWeight = FontWeight.Medium,
                )
            }

            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                sortOptions.forEachIndexed { idx, (stringRes, mode) ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = context.getString(stringRes),
                                fontWeight = if (sortMode == mode) FontWeight.Medium else FontWeight.Normal,
                                color = if (sortMode == mode) MaterialTheme.colorScheme.secondary else Color.Unspecified,
                            )
                        },
                        onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.value = idx

                            onSortModeChanged(mode)
                        }
                    )
                }
            }
        }

        // endregion Sort Order


        // region Sort direction

        Box {
            if (sortDirection == SortDirection.ASCENDING) {
                UnstyledButton(

                    onClick = {
                        onSortDirectionChanged(SortDirection.DESCENDING)
                    },
                ) {
                    Row {
                        TintedIconDrawable(
                            R.drawable.ic_ascending,
                            R.string.app_search_sort_direction_asc,
                            LocalCustomColourPalette.current.textColor,
                        )
                        Text(
                            text = context.getString(R.string.app_search_sort_direction_asc),
                            color = LocalCustomColourPalette.current.textColor,
                            modifier = Modifier
                                .padding(start = 4.dp)
                        )
                    }

                }
            } else {
                UnstyledButton(onClick = {
                    onSortDirectionChanged(SortDirection.ASCENDING)
                }) {
                    Row {
                        TintedIconDrawable(
                            R.drawable.ic_descending,
                            R.string.app_search_sort_direction_desc,
                            LocalCustomColourPalette.current.textColor,
                        )
                        Text(
                            text = context.getString(R.string.app_search_sort_direction_desc),
                            color = LocalCustomColourPalette.current.textColor,
                            modifier = Modifier
                                .padding(start = 4.dp)
                        )
                    }
                }
            }
        }

        // endregion Sort direction
    }
}
