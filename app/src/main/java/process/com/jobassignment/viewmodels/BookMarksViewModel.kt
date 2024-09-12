package process.com.jobassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import process.com.jobassignment.localdb.JobEntity
import process.com.jobassignment.repository.LocalRoomRepository
import javax.inject.Inject


@HiltViewModel
class BookMarksViewModel @Inject constructor(
    private val repository: LocalRoomRepository
): ViewModel(){

    // A backing property for the list of jobs
    private val _jobList = MutableStateFlow<List<JobEntity>>(emptyList())
    val jobList = _jobList.asStateFlow()

    init {
        // Collect from the repository's flow and update the MutableStateFlow
        viewModelScope.launch {
            repository.getAllJobs().collect{
                _jobList.value = it
            }
        }
    }

}