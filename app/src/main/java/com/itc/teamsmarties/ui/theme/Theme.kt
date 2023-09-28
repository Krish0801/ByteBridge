package com.itc.teamsmarties.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = ThemeColors.Night.primary,
    onPrimary = ThemeColors.Night.onPrimary,
    secondary = Color.Blue,
    surface = ThemeColors.Night.surface,
    background = ThemeColors.Night.background,
    onSecondary = ThemeColors.Night.cardColor
)

val LightColorScheme = lightColorScheme(
    primary = ThemeColors.Day.primary,
    onPrimary = ThemeColors.Day.onPrimary,
    secondary = Color.Blue,
    surface = ThemeColors.Day.surface,
    background = ThemeColors.Day.background,
    onSecondary = ThemeColors.Day.cardColor
)

@Composable
fun TeamSmartiesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme)
        DarkColorScheme
    else
        LightColorScheme

    val view = LocalView.current
    val statusBarColor = if (darkTheme) DarkColorScheme.primary else LightColorScheme.primary
    val navigationBarColor = if (darkTheme) DarkColorScheme.primary else LightColorScheme.primary

    SideEffect {
        val window = (view.context as Activity).window
        window?.statusBarColor = statusBarColor.toArgb()
        window?.navigationBarColor = navigationBarColor.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
