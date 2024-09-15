package com.tinaciousdesign.aboutapps.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tinaciousdesign.aboutapps.ui.theme.LocalCustomColourPalette

@Composable
fun MutedBackgroundButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    val colours =  LocalCustomColourPalette.current

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colours.buttonBackgroundMuted,
            contentColor = colours.buttonForegroundMuted,
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = modifier,
        content = content,
    )
}
