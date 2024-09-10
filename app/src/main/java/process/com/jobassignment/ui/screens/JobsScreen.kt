package process.com.jobassignment.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import process.com.jobassignment.viewmodels.JobDetailsViewModel


@Composable
fun JobsScreen(navController: NavHostController, jobDetailsViewModel: JobDetailsViewModel,) {
    Text(text = "Jobs Screen")
}