package com.example.datastoreviewmodelsenkiv.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.datastoreviewmodelsenkiv.ui.screens.subjectsDetails.SubjectsDetailsScreen
import com.example.datastoreviewmodelsenkiv.ui.screens.subjectsList.SubjectsListScreen

const val SCREEN_SUBJECT_LIST = "SubjectsList"
const val SCREEN_SUBJECT_DETAILS = "SubjectsDetails"

@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost (
        modifier = modifier,
        navController = navController,
        startDestination = SCREEN_SUBJECT_LIST
    ) {
        composable(
            route = SCREEN_SUBJECT_LIST,
        ) {
            SubjectsListScreen (
                onDetailScreen = { id ->
                    navController.navigate("$SCREEN_SUBJECT_DETAILS/$id")
                }
            )
        }

        composable(
            route = "$SCREEN_SUBJECT_DETAILS/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.Companion.IntType
                    nullable = false
                },
            )
        ) { backStack ->
            SubjectsDetailsScreen(
                id = backStack.arguments?.getInt("id") ?: -1
            )
        }
    }
}