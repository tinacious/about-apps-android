package com.tinaciousdesign.aboutapps.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Divider(
    color: Color,
) {
    Box(
        modifier = Modifier
            .background(color)
            .height(1.dp)
            .fillMaxWidth()
    )
}
