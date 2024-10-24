package com.example.datastoreviewmodelsenkiv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.datastoreviewmodelsenkiv.ui.navigation.NavigationGraph
import com.example.datastoreviewmodelsenkiv.ui.theme.DataStoreViewModelSenkivTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreViewModelSenkivTheme {
                NavigationGraph()
            }
        }
    }
}
