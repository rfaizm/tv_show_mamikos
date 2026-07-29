package com.rachmanm.tvshow.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val AppLightColors = lightColorScheme(
    primary = Primary,
    onPrimary = AppSurface,
    primaryContainer = PrimaryVariant,
    secondary = Secondary,
    background = AppBackground,
    onBackground = OnSurface,
    surface = AppSurface,
    onSurface = OnSurface,
    surfaceVariant = PosterPlaceholder,
    onSurfaceVariant = OnSurfaceVariant
)

@Composable
fun TvShowsTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = AppLightColors,
        typography = Typography,
        content = content
    )
}