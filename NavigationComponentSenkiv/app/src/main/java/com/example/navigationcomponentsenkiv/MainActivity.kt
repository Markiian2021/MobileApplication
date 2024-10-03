package com.example.navigationcomponentsenkiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.navigationcomponentsenkiv.ui.navigation.NavigationGraph
import com.example.navigationcomponentsenkiv.ui.theme.NavigationComponentSenkivTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationComponentSenkivTheme {
                NavigationGraph()
            }
        }
    }
}

