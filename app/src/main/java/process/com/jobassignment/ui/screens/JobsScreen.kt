package process.com.jobassignment.ui.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import process.com.jobassignment.MainActivity
import process.com.jobassignment.R
import process.com.jobassignment.extensionfunctions.toast
import process.com.jobassignment.navigation.Routes
import process.com.jobassignment.ui.utils.PaginatedLazyColumn
import process.com.jobassignment.ui.utils.TopAppBarCustom
import process.com.jobassignment.ui.utils.navigateToJobDetailsScreen
import process.com.jobassignment.viewmodels.JobDetailsViewModel


@Composable
fun JobsScreen(
    navController: NavHostController,
    jobDetailsViewModel: JobDetailsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val activity = context as MainActivity
    val jobsPagingData = jobDetailsViewModel.jobsPagerData.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBarCustom(titleText = "Jobs",
                onBackClick = {
                    activity.finish()
                })
        }
    ) {
        PaginatedLazyColumn(
            modifier = Modifier.padding(it),
            pagingData = jobsPagingData
        ) { _, job ->
            JobDetailCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                jobTitle = job?.title ?: "NA",
                jobLocation = job?.jobLocationSlug ?: "NA",
                salaryText = job?.primaryDetails?.salary ?: "NA",
                phoneNumber = job?.customLink ?: "NA",
                isBookmarked = job?.isBookmarked ?: false,
                onJobCardClick = {
                    if (job != null) {
                        navigateToJobDetailsScreen(navController,job)
                    }
                },
                onBookMarkClick = {
                    job?.let { item ->
                        jobDetailsViewModel.bookMarkedClick(item)
                    }
                    jobsPagingData.refresh()
                }
            )
        }
    }

}


@Composable
private fun JobDetailCard(
    modifier: Modifier = Modifier,
    jobTitle: String,
    jobLocation: String,
    salaryText: String,
    phoneNumber: String,
    isBookmarked: Boolean,
    onJobCardClick: () -> Unit,
    onBookMarkClick: () -> Unit
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
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = jobTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.95f)
                        .padding(end = 24.dp)
                )
                Icon(
                    painter = painterResource(id = if (isBookmarked) R.drawable.ic_bookmark_saved_24 else R.drawable.ic_bookmark_border_24),
                    contentDescription = "bookmark",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f)
                        .size(24.dp)
                        .clickable {
                            onBookMarkClick()
                        }
                )

            }

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