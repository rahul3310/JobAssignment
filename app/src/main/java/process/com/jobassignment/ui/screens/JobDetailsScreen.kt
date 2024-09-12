package process.com.jobassignment.ui.screens

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import process.com.jobassignment.localdb.JobEntity
import process.com.jobassignment.ui.utils.TopAppBarCustom

@Composable
fun JobDetailsScreen(
    navController: NavController,
    bundle: Bundle?
) {
    val jobDetails = remember { mutableStateOf<JobEntity?>(null) }
    LaunchedEffect(Unit) {
        if (bundle != null && bundle.containsKey("jobDetails")) {
            jobDetails.value = bundle.getParcelable("jobDetails")
        }
    }
    Scaffold(
        topBar = {
            TopAppBarCustom(titleText = "Job Details",
                onBackClick = {
                    navController.popBackStack()
                })
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 16.dp)
        ) {

            Text(
                text = jobDetails.value?.jobTitle?: "NA",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 24.dp)
            )

            Text(
                text = jobDetails.value?.jobLocation?: "NA",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black.copy(0.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )

            Text(
                text = "Job Salary",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            )


            Text(
                text = jobDetails.value?.salary?: "NA",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black.copy(0.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .background(Color.Green.copy(0.3f), RoundedCornerShape(2.dp))
                    .padding(horizontal = 8.dp, vertical = 6.dp)
            )


            Text(
                text = "For any query contact",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)

            )
            Text(
                text = jobDetails.value?.phoneNumber?: "NA",
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black.copy(0.5f),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )


        }

    }
}
