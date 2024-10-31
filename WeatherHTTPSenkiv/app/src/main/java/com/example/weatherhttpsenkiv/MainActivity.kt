package com.example.weatherhttpsenkiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weatherhttpsenkiv.ui.navigation.NavigationGraph
import com.example.weatherhttpsenkiv.ui.theme.WeatherHTTPSenkivTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherHTTPSenkivTheme {
                NavigationGraph()
            }
        }
    }
}
