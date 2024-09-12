package process.com.jobassignment.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import process.com.jobassignment.MainActivity
import process.com.jobassignment.ui.screens.BookmarksScreen
import process.com.jobassignment.ui.screens.JobDetailsScreen
import process.com.jobassignment.ui.screens.JobsScreen
import process.com.jobassignment.viewmodels.JobDetailsViewModel

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.JobsScreen.routes) {

        composable(Routes.JobsScreen.routes) {
            JobsScreen(navController)
        }
        composable(Routes.BookmarksScreen.routes) {
            BookmarksScreen(navController)

        }

        composable(Routes.JobDetailsScreen.routes) {
            val bundle = navController.previousBackStackEntry?.savedStateHandle?.get<Bundle>(Routes.JobDetailsScreen.routes)
            JobDetailsScreen(navController,bundle)

        }
    }
}