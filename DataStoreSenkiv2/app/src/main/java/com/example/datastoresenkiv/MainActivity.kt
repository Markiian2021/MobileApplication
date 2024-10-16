package com.example.datastoresenkiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.datastoresenkiv.ui.navigation.NavigationGraph
import com.example.datastoresenkiv.ui.theme.DataStoreSenkivTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreSenkivTheme {
                NavigationGraph()
            }
        }
    }
}