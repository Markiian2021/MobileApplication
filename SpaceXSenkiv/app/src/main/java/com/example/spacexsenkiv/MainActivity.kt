package com.example.spacexsenkiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.spacexsenkiv.ui.navigation.NavigationGraph
import com.example.spacexsenkiv.ui.theme.SpaceXSenkivTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXSenkivTheme {
                NavigationGraph()
            }
        }
    }
}
