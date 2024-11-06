package com.example.spacexsenkiv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.spacexsenkiv.ui.screens.LaunchItem
import com.example.spacexsenkiv.ui.screens.LaunchList
import com.example.spacexsenkiv.ui.screens.NewsScreen

const val SCREEN_LAUNCH_LIST_SCREEN = "launch_list"
const val SCREEN_NEWS_SCREEN = "news_screen"
const val SCREEN_LAUNCH_ITEM_SCREEN = "launch_item"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_LAUNCH_LIST_SCREEN
    ) {
        composable(
            route = SCREEN_LAUNCH_LIST_SCREEN
        ) {
            LaunchList(
                onLaunchNews = { navController.navigate(SCREEN_NEWS_SCREEN) },
                onLaunchItem = { launch ->
                    navController.navigate("$SCREEN_LAUNCH_ITEM_SCREEN/${launch.id}")
                }
            )
        }
        composable(
            route = SCREEN_NEWS_SCREEN
        ) {
           NewsScreen()
        }
        composable(
            route = "$SCREEN_LAUNCH_ITEM_SCREEN/{launchId}",
            arguments = listOf(navArgument("launchId") { type = NavType.StringType })
        ) { backStackEntry ->
            val launchId = backStackEntry.arguments?.getString("launchId")
            LaunchItem(launchId = launchId)
        }
    }
}

