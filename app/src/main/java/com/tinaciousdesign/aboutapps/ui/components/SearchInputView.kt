package com.tinaciousdesign.aboutapps.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.tinaciousdesign.aboutapps.R
import com.tinaciousdesign.aboutapps.ui.TestTags
import com.tinaciousdesign.aboutapps.ui.icons.TintedIconDrawable

@Composable
fun SearchInputView(currentValue: String, onSearch: (String) -> Unit) {
    val context = LocalContext.current

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(
                top = 20.dp,
                bottom = 8.dp,
                start = 14.dp,
                end = 14.dp,
            )
    ) {
        OutlinedTextField(
            value = currentValue,
            onValueChange = onSearch,
            placeholder = {
                Text(context.getString(R.string.search_field_placeholder))
            },
            leadingIcon = {
                TintedIconDrawable(R.drawable.ic_search, R.string.search_icon_content_description)
            },
            modifier = Modifier
                .weight(1.0f)
                .padding(end = 14.dp)
                .testTag(TestTags.searchField)
        )

        MutedBackgroundButton({
            onSearch("")
        }) {
            Text(context.getString(R.string.search_clear_button))
        }
    }
}
