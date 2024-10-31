package com.example.weatherhttpsenkiv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherhttpsenkiv.ui.screens.WeatherDayScreen
import com.example.weatherhttpsenkiv.ui.screens.WeatherScreen

const val SCREEN_WEATHER_SCREEN = "weatherList"
const val SCREEN_WEATHER_DAY_SCREEN = "weatherDayScreen"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_WEATHER_SCREEN
    ) {
        composable(
            route = SCREEN_WEATHER_SCREEN
        ) {
            WeatherScreen(
                onWeatherScreen = { dateInMillis ->
                    navController.navigate("$SCREEN_WEATHER_DAY_SCREEN/$dateInMillis")
                }
            )
        }

        composable(
            route = "$SCREEN_WEATHER_DAY_SCREEN/{dateInMillis}"
        ) { backStackEntry ->
            val dateInMillis = backStackEntry.arguments?.getString("dateInMillis")?.toLongOrNull() ?: 0L
            WeatherDayScreen(dateInMillis = dateInMillis)
        }
    }
}

