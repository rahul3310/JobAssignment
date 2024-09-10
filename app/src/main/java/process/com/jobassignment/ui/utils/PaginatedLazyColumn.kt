package process.com.jobassignment.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import process.com.jobassignment.datamodels.Job

@Composable
fun PaginatedLazyColumn(
    items: List<Job>,
    onLoadMore: () -> Unit,
    isLoading: Boolean,
    isError: Boolean,
    isEmpty: Boolean,
    onRetry: () -> Unit,
) {
    val scrollState = rememberLazyListState()

    when {
        isLoading && items.isEmpty() ==true -> {
            // Loading state when no items are loaded
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        isError && items.isEmpty() == true -> {
            // Error state when no items are loaded
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Something went wrong.", color = Color.Red)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { onRetry() }) {
                        Text(text = "Retry")
                    }
                }
            }
        }

        isEmpty -> {
            // Empty state
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No items available.")
            }
        }

        else -> {
            // Main content with pagination
            LazyColumn(
                state = scrollState,
                contentPadding = PaddingValues(16.dp)
            ) {
                itemsIndexed(items){index, item ->
                    jobCard(item)
//                    Text(text = item.title?:"NA")
                }

                // Show a loading indicator at the bottom if loading more data
                if (isLoading && items?.isNotEmpty()==true) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                // Error message at the bottom when there is an error during pagination
                if (isError && items?.isNotEmpty()==true) {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(text = "Failed to load more data.", color = Color.Red)
                                Spacer(modifier = Modifier.height(8.dp))
                                Button(onClick = { onRetry() }) {
                                    Text(text = "Retry")
                                }
                            }
                        }
                    }
                }
            }

            // Trigger loading more items when scrolling to the bottom
            LaunchedEffect(scrollState) {
                snapshotFlow { scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                    .collect { lastVisibleItemIndex ->
                        if (lastVisibleItemIndex == items.size?.minus(1) && !isLoading) {
                            onLoadMore()
                        }
                    }
            }
        }
    }
}


@Composable
fun jobCard(job: Job){
    OutlinedCard(
        modifier = Modifier.padding(8.dp),
        shape = RectangleShape,
        elevation = CardDefaults.cardElevation(8.dp),
        border = BorderStroke(2.dp , color = Color.Gray)
    ) {

        Row {
            Text(
                modifier = Modifier.weight(1f),
                text = "Job Title",
                fontWeight = FontWeight.Black,
                fontSize = 16.sp
            )

            Text(
                modifier = Modifier.weight(1f),
                text = job.title.toString(),
                fontSize = 14.sp
            )
        }

    }
}

