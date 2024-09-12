package process.com.jobassignment.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import process.com.jobassignment.R
import process.com.jobassignment.extensionfunctions.toast
import process.com.jobassignment.navigation.Routes
import process.com.jobassignment.ui.utils.PaginatedLazyColumn
import process.com.jobassignment.ui.utils.TopAppBarCustom
import process.com.jobassignment.ui.utils.navigateToJobDetailsScreen
import process.com.jobassignment.viewmodels.BookMarksViewModel

@Composable
fun BookmarksScreen(
    navController: NavHostController,
    bookMarksViewModel: BookMarksViewModel = hiltViewModel()
) {
    val saveJob = bookMarksViewModel.jobList.collectAsState()
    Scaffold(
        topBar = {
            TopAppBarCustom(titleText = "Bookmarks",
                onBackClick = {
                    navController.popBackStack(Routes.JobsScreen.routes, false)
                })
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            if (saveJob.value.isNotEmpty()) {
                itemsIndexed(saveJob.value) { _, job ->
                    JobDetailCardBookmark(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                        jobTitle = job.jobTitle ?: "NA",
                        jobLocation = job.jobLocation ?: "NA",
                        salaryText = job.salary ?: "NA",
                        phoneNumber = job.phoneNumber ?: "NA",
                        onJobCardClick = {
                            navigateToJobDetailsScreen(navController, job)
                        },
                    )
                }
            } else {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No Job available.",
                            fontSize = 14.sp,
                            color = Color.Black.copy(alpha = 0.5f),
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(12.dp))
            }

        }
    }
}

@Composable
private fun JobDetailCardBookmark(
    modifier: Modifier = Modifier,
    jobTitle: String,
    jobLocation: String,
    salaryText: String,
    phoneNumber: String,
    onJobCardClick: () -> Unit,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray.copy(0.5f)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = { onJobCardClick() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = jobTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp)
            )

            //Location
            Text(
                text = jobLocation,
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.padding(top = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            //salary
            Text(
                text = salaryText,
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .background(Color.Green.copy(0.3f), RoundedCornerShape(2.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp),
            )

            //phone number
            Text(
                text = phoneNumber,
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}