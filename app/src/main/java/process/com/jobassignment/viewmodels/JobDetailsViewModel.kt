package process.com.jobassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import process.com.jobassignment.datamodels.Job
import process.com.jobassignment.repository.JobDetailsRepository
import process.com.jobassignment.repository.JobPaginationDataSource
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val repository: JobDetailsRepository
) : ViewModel() {

    // Maintain a set of bookmarked job IDs
    private val _bookmarkedJobIds = MutableStateFlow<Set<Int?>>(emptySet())

    @OptIn(ExperimentalCoroutinesApi::class)
    val jobsPagerData: Flow<PagingData<Job>> =
        _bookmarkedJobIds.flatMapLatest {sets->
            Pager(
                config = PagingConfig(
                    pageSize = 20,
                ),
                pagingSourceFactory = {
                    JobPaginationDataSource(repository, sets)
                }
            ).flow
                .cachedIn(viewModelScope)

        }

    fun bookMarkedClick(job: Job) {
        val updatedBookmarkedJobIds = if (_bookmarkedJobIds.value.contains(job.id)) {
            _bookmarkedJobIds.value - job.id
        } else {
            _bookmarkedJobIds.value + job.id
        }
        _bookmarkedJobIds.value = updatedBookmarkedJobIds

        // Optionally, save bookmark status to repository or database if needed
    }
}