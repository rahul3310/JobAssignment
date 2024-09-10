package process.com.jobassignment.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import process.com.jobassignment.ui.utils.PaginatedLazyColumn
import process.com.jobassignment.viewmodels.JobDetailsViewModel


@Composable
fun JobsScreen(navController: NavHostController, jobDetailsViewModel: JobDetailsViewModel,) {

    PaginatedLazyColumn(
        items = jobDetailsViewModel.jobDetailsList.value.results,
        onLoadMore = { jobDetailsViewModel.updateCurrentPage(jobDetailsViewModel.currentPage.value+1) },
        isLoading = jobDetailsViewModel.isLoading.value,
        isError = jobDetailsViewModel.isError.value,
        onRetry = {  jobDetailsViewModel.updateCurrentPage(jobDetailsViewModel.currentPage.value)},
        isEmpty = jobDetailsViewModel.jobDetailsList.value.results.isEmpty(),
    )
}