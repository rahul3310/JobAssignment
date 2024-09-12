package process.com.jobassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import process.com.jobassignment.datamodels.Job
import process.com.jobassignment.localdb.JobEntity
import process.com.jobassignment.repository.JobDetailsRepository
import process.com.jobassignment.repository.JobPaginationDataSource
import process.com.jobassignment.repository.LocalRoomRepository
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val repository: JobDetailsRepository,
    private val localRoomRepository: LocalRoomRepository
) : ViewModel() {


    private val _bookmarkedJobIds = MutableStateFlow<Set<Long>>(emptySet())


    init {
        viewModelScope.launch(Dispatchers.IO) {
            val bookmarkedJobs = localRoomRepository.getAllJobs().firstOrNull() ?: emptyList()
            _bookmarkedJobIds.value = bookmarkedJobs.map { it.jobId }.toSet()
        }
    }

    val jobsPagerData: Flow<PagingData<Job>> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
            ),
            pagingSourceFactory = {
                JobPaginationDataSource(repository, localRoomRepository)
            }
        ).flow


    fun bookMarkedClick(job: Job) {
        if (!_bookmarkedJobIds.value.contains(job.id?.toLong())) {
            addJobInDB(job)
        } else {
            job.id?.toLong()?.let { deleteJob(jobId = it) }
        }
    }

    private fun addJobInDB(job: Job) {
        viewModelScope.launch(Dispatchers.IO) {
            localRoomRepository.addJob(job.toJobDetails())
        }
    }

    private fun deleteJob(jobId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            localRoomRepository.deleteJob(jobId)
        }
    }
}