package process.com.jobassignment.ui.utils

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import process.com.jobassignment.datamodels.Job
import process.com.jobassignment.localdb.JobEntity
import process.com.jobassignment.navigation.Routes

fun navigateToJobDetailsScreen(navController: NavController, job: Job) {
    val bundle = Bundle()
    bundle.putParcelable("jobDetails", job.toJobDetails())
    navController.currentBackStackEntry?.savedStateHandle?.set(Routes.JobDetailsScreen.routes, bundle)
    navController.navigate(Routes.JobDetailsScreen.routes) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

fun navigateToJobDetailsScreen(navController: NavController, job: JobEntity) {
    val bundle = Bundle()
    bundle.putParcelable("jobDetails", job)
    navController.currentBackStackEntry?.savedStateHandle?.set(Routes.JobDetailsScreen.routes, bundle)
    navController.navigate(Routes.JobDetailsScreen.routes) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}