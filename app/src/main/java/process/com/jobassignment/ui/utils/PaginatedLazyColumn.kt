package process.com.jobassignment.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any> PaginatedLazyColumn(
    modifier: Modifier = Modifier,
    pagingData: LazyPagingItems<T>,
    noMoreContent: @Composable (() -> Unit) = { },
    refreshingContent: @Composable (() -> Unit) = { },
    errorContent: @Composable ((error: Throwable, retry: (() -> Unit)?) -> Unit) = { error, retry ->
        DefaultErrorContent(retry, error.message)
    },
    pagingItemContent: @Composable (index: Int, value: T?) -> Unit,
) {
    PagingListContainer(
        pagingData = pagingData,
        refreshingContent = refreshingContent,
        listContent = {
            LazyColumn(modifier) {
                items(pagingData.itemCount) { index ->
                    pagingItemContent(index, pagingData[index])
                }
                item {
                    Spacer(Modifier.height(8.dp))
                }
                itemPaging(pagingData, noMoreContent, errorContent)
            }
        }
    )
}

@Composable
private fun <T : Any> PagingListContainer(
    pagingData: LazyPagingItems<T>,
    refreshingContent: @Composable (() -> Unit),
    listContent: @Composable () -> Unit,
) {
    if (pagingData.loadState.refresh is LoadState.Loading) {
        refreshingContent()
    } else if (pagingData.loadState.refresh is LoadState.NotLoading && pagingData.itemCount == 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No Job available.",
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = 0.5f)
            )
        }
    } else {
        listContent()
    }
}

private fun <T : Any> LazyListScope.itemPaging(
    pagingData: LazyPagingItems<T>,
    noMoreContent: @Composable (() -> Unit),
    errorContent: @Composable ((error: Throwable, retry: (() -> Unit)?) -> Unit),
) {
    when (pagingData.loadState.append) {
        is LoadState.Loading -> item {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is LoadState.Error -> item {
            errorContent((pagingData.loadState.append as LoadState.Error).error) {
                pagingData.retry()
            }
        }

        is LoadState.NotLoading ->
            if (pagingData.loadState.append.endOfPaginationReached && noMoreContent != null) {
                item { noMoreContent() }
            }
    }
}

@Composable
private fun DefaultErrorContent(retry: (() -> Unit)?, errorText: String? = "NetWork Error!") {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Text(
            text = errorText ?: "Some Error!",
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            maxLines = Int.MAX_VALUE
        )
        Box(
            modifier = Modifier
                .wrapContentWidth()
                .padding(top = 12.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color.Green)
                .border(
                    width = 1.dp,
                    color = Color.Green,
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable {
                    retry?.invoke()
                }
        ) {
            Text(
                text = "Retry",
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(top = 4.dp, bottom = 4.dp, start = 24.dp, end = 24.dp)
                    .align(Alignment.Center),
                textAlign = TextAlign.Center


            )
        }
    }
}


