package com.example.museumsenkiv.ui.screens

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.museumsenkiv.ui.theme.DarkColorPalette
import com.example.museumsenkiv.ui.theme.LightColorPalette

@Composable
fun AppTheme(isDarkMode: Boolean, content: @Composable () -> Unit) {

    val colors = if (isDarkMode) DarkColorPalette else LightColorPalette

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}

