package process.com.jobassignment.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import process.com.jobassignment.ui.screens.BookmarksScreen
import process.com.jobassignment.ui.screens.JobsScreen
import process.com.jobassignment.viewmodels.JobDetailsViewModel

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.JobsScreen.routes) {
        composable(Routes.JobsScreen.routes) {backEntry->
            val jobDetailsViewModel: JobDetailsViewModel = hiltViewModel(backEntry)
            JobsScreen(navController, jobDetailsViewModel)
        }
        composable(Routes.BookmarksScreen.routes) {
            BookmarksScreen(navController)

        }
    }
}