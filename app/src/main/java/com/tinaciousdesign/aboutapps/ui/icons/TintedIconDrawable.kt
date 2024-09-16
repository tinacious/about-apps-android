package com.tinaciousdesign.aboutapps.ui.icons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TintedIconDrawable(
    @DrawableRes drawableId: Int,
    @StringRes contentDescriptionRes: Int,
    tint: Color = LocalContentColor.current,
    size: Dp = 24.dp
) {
    IconDrawable(
        drawableId = drawableId,
        contentDescriptionRes = contentDescriptionRes,
        size = size,
        tint = tint,
    )
}
