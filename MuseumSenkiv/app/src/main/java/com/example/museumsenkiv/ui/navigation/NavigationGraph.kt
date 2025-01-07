package com.example.museumsenkiv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.museumsenkiv.ui.screens.ArtDetailsScreen
import com.example.museumsenkiv.ui.screens.ArtListScreen
import com.example.museumsenkiv.ui.screens.DepartmentScreen
import com.example.museumsenkiv.ui.screens.MuseumViewModel
import com.example.museumsenkiv.ui.screens.ThemeViewModel

const val SCREEN_DEPARTMENT_SELECTION = "departmentScreen"
const val SCREEN_ART_LIST_SCREEN = "artListScreen"
const val SCREEN_ART_DETAILS_SCREEN = "artDetailsScreen"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: MuseumViewModel,
    themeViewModel: ThemeViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_DEPARTMENT_SELECTION
    ) {

        composable(route = SCREEN_DEPARTMENT_SELECTION) {
            DepartmentScreen(
                viewModel = viewModel,
                onDepartmentClick = { departmentId ->
                    navController.navigate("$SCREEN_ART_LIST_SCREEN/$departmentId")
                },
                navController = navController,
                themeViewModel = themeViewModel
            )
        }

        composable(
            route = "$SCREEN_ART_LIST_SCREEN/{departmentId}",
            arguments = listOf(navArgument("departmentId") { type = NavType.IntType })
        ) { backStackEntry ->
            val departmentId = backStackEntry.arguments?.getInt("departmentId") ?: 0
            ArtListScreen(
                viewModel = viewModel,
                departmentId = departmentId,
                onArtClick = { objectID ->
                    navController.navigate("$SCREEN_ART_DETAILS_SCREEN/$objectID")
                },
                themeViewModel = themeViewModel
            )
        }

        composable(
            route = "$SCREEN_ART_DETAILS_SCREEN/{objectID}",
            arguments = listOf(navArgument("objectID") { type = NavType.IntType })
        ) { backStackEntry ->
            val objectID = backStackEntry.arguments?.getInt("objectID") ?: 0
            ArtDetailsScreen(
                viewModel = viewModel,
                objectID = objectID,
                themeViewModel = themeViewModel
            )
        }

    }
}
