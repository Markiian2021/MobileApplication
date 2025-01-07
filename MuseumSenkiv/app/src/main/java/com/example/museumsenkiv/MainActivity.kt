package com.example.museumsenkiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.rememberNavController
import com.example.museumsenkiv.ui.navigation.NavigationGraph
import com.example.museumsenkiv.ui.screens.AppTheme
import com.example.museumsenkiv.ui.screens.MuseumViewModel
import com.example.museumsenkiv.ui.screens.ThemeViewModel
import com.example.museumsenkiv.ui.theme.MuseumSenkivTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MuseumSenkivTheme {
                val themeViewModel: ThemeViewModel = getViewModel()
                val isDarkMode by themeViewModel.isDarkMode.collectAsState()

                AppTheme(isDarkMode = isDarkMode) {
                    val navController = rememberNavController()
                    val museumViewModel: MuseumViewModel = getViewModel()
                    NavigationGraph(
                        navController = navController,
                        viewModel = museumViewModel,
                        themeViewModel = themeViewModel
                    )
                }
            }
        }
    }
}