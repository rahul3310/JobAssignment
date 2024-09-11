package process.com.jobassignment.repository


import androidx.paging.PagingSource
import androidx.paging.PagingState
import process.com.jobassignment.api.NetworkResult
import process.com.jobassignment.datamodels.Job

class JobPaginationDataSource(
    private val repository: JobDetailsRepository,
    private val bookmarkedJobIds: Set<Int?>
) : PagingSource<Int, Job>() {
    override fun getRefreshKey(state: PagingState<Int, Job>): Int? {
        return state.anchorPosition?.apply {
            state.closestPageToPosition(this)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(this)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Job> {
        return try {
            val currentPage = params.key ?: 1
            val pageSize = params.loadSize
            when (val response = repository.getJobDetails(currentPage, pageSize)) {
                is NetworkResult.Success -> {
                    val jobList = response.value.body()?.results
                    val updatedJobs = jobList
                        ?.filter { job -> job.id != null }
                        ?.map { job ->
                            job.copy(isBookmarked = bookmarkedJobIds.contains(job.id!!))
                        }
                    LoadResult.Page(
                        data = updatedJobs ?: emptyList(),
                        prevKey = if (currentPage == 1) null else currentPage.minus(1),
                        nextKey = if (jobList?.isEmpty() == true) null else currentPage.plus(1),
                    )
                }

                else -> {
                    LoadResult.Error(Exception("Failed to load data"))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}