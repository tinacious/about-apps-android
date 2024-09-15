package com.tinaciousdesign.aboutapps.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

data class CustomPalette(
    val textColor: Color = Color.Unspecified,
    val textColorMuted: Color = Color.Unspecified,
    val buttonForegroundMuted: Color = Color.Unspecified,
    val buttonBackgroundMuted: Color = Color.Unspecified,
)

private val DarkColorScheme = darkColorScheme(
    background = BrandColours.Greys.grey_50,
    onBackground = BrandColours.Greys.white,
    surface = BrandColours.Greys.grey_100,
    onSurface = BrandColours.Greys.white,
    surfaceTint = BrandColours.Greys.grey_300,
    primary = BrandColours.pink,
    secondary = BrandColours.blue,
    tertiary = BrandColours.purple
)

private val LightColorScheme = lightColorScheme(
    background = BrandColours.Greys.white,
    onBackground = BrandColours.Greys.black,
    surface = BrandColours.Greys.grey_450,
    surfaceVariant = BrandColours.Greys.grey_400,
    onSurface = BrandColours.Greys.black,
    surfaceTint = BrandColours.Greys.grey_300,
    primary = BrandColours.pink,
    secondary = BrandColours.blue,
    tertiary = BrandColours.purple,
)

private val OnLightPalette = CustomPalette(
    textColor = BrandColours.Greys.black,
    textColorMuted = BrandColours.Greys.grey_200,
    buttonForegroundMuted = BrandColours.Greys.white,
    buttonBackgroundMuted = BrandColours.Greys.grey_200,
)

private val OnDarkPalette = CustomPalette(
    textColor = BrandColours.Greys.white,
    textColorMuted = BrandColours.Greys.grey_300,
    buttonForegroundMuted = BrandColours.Greys.black,
    buttonBackgroundMuted = BrandColours.Greys.grey_300,
)

val LocalCustomColourPalette = staticCompositionLocalOf { CustomPalette() }


@Composable
fun AboutAppsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val customColorsPalette = if (darkTheme) OnDarkPalette else OnLightPalette

    CompositionLocalProvider(
        LocalCustomColourPalette provides customColorsPalette
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}
