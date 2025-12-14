package com.example.jbg_reclamos.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,
    background = LightBackground,
    surface = LightSurface,
    error = RedError,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = LightText,
    onSurface = LightText,
    onError = Color.White
)

private val DarkColorScheme = darkColorScheme(
    primary = BluePrimary,
    secondary = BlueSecondary,
    background = DarkBackground,
    surface = DarkSurface,
    error = RedError,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = DarkText,
    onSurface = DarkText,
    onError = Color.White
)

@Composable
fun JBG_RECLAMOSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // desactivamos Material You
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = androidx.compose.ui.platform.LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
