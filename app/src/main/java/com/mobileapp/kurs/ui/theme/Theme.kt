package com.mobileapp.kurs.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = darkColorScheme(
    primary = Blue,
    background = LightBlue,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    error = Red
)

private val DarkColorScheme = lightColorScheme(
    primary = White,
    background = DarkBlue,
    onBackground = White,
    surface = Black,
    onSurface = White,
    error = Red
)

@Composable
fun KursTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
