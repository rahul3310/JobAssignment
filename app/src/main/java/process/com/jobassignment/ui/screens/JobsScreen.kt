package process.com.jobassignment.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import process.com.jobassignment.MainActivity
import process.com.jobassignment.R
import process.com.jobassignment.navigation.Routes
import process.com.jobassignment.ui.theme.PurpleGrey40
import process.com.jobassignment.ui.utils.PaginatedLazyColumn
import process.com.jobassignment.ui.utils.TopAppBarCustom
import process.com.jobassignment.viewmodels.JobDetailsViewModel


@Composable
fun JobsScreen(
    navController: NavHostController,
    jobDetailsViewModel: JobDetailsViewModel = hiltViewModel()
) {
    val activity = LocalContext.current as MainActivity


    Scaffold(
        topBar = {
            TopAppBarCustom(titleText = "Jobs",
                onBackClick = { activity.finishMethod() })
        }
    ) {
        PaginatedLazyColumn(
            modifier = Modifier.padding(it),
            items = jobDetailsViewModel.jobDetailsList.value.results,
            onLoadMore = { jobDetailsViewModel.updateCurrentPage(jobDetailsViewModel.currentPage.value + 1) },
            isLoading = jobDetailsViewModel.isLoading.value,
            isError = jobDetailsViewModel.isError.value,
            onRetry = { jobDetailsViewModel.updateCurrentPage(jobDetailsViewModel.currentPage.value) },
            isEmpty = jobDetailsViewModel.jobDetailsList.value.results.isEmpty(),
        ) { _, item ->
            JobDetailCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                jobTitle = item.title ?: "NA",
                jobLocation = item.jobLocationSlug ?: "NA",
                salaryText = item.primaryDetails?.salary ?: "NA"
            )
        }
    }

}

@Preview
@Composable
private fun JobDetailCard(
    modifier: Modifier = Modifier,
    jobTitle: String = "Android dev",
    jobLocation: String = "Noida",
    salaryText: String = "100000 - 15000"
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.elevatedCardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray.copy(0.5f)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        onClick = { /*TODO*/ },
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
                    painter = painterResource(id = R.drawable.ic_bookmark_border_24),
                    contentDescription = "bookmark",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.1f)
                        .size(24.dp)
                        .clickable { }
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
        }


    }
}