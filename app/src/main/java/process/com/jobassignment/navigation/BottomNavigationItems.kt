package process.com.jobassignment.navigation

import process.com.jobassignment.R

sealed class BottomNavigationItems(
    val route: String,
    val title: String,
    val icon: Int,
    val selectedIcon: Int
) {
    data object Jobs : BottomNavigationItems(
        route = "jobs_screen",
        title = "Jobs",
        icon = R.drawable.baseline_home_24,
        selectedIcon = R.drawable.baseline_home_24
    )

    data object Bookmarks : BottomNavigationItems(
        route = "bookmarks_screen",
        title = "BookMarks",
        icon = R.drawable.baseline_bookmark_border_24,
        selectedIcon = R.drawable.baseline_bookmark_border_24
    )
}