package process.com.jobassignment.navigation

sealed class Routes(val routes: String) {
    data object JobsScreen : Routes("jobs_screen")
    data object BookmarksScreen : Routes("bookmarks_screen")
    data object JobDetailsScreen : Routes("job_details_screen")
}