package process.com.jobassignment.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import process.com.jobassignment.api.NetworkResult
import process.com.jobassignment.datamodels.JobDetailsResponse
import process.com.jobassignment.repository.JobDetailsRepository
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val repository: JobDetailsRepository
) : ViewModel() {
    private val viewModelScope = CoroutineScope(context = Dispatchers.IO)
    private val jobDetailsList = mutableStateOf<JobDetailsResponse?>(null)

    fun getJobDetails(page: Int) {
        viewModelScope.launch {
            when (val result = repository.getJobDetails(page)) {
                is NetworkResult.Loading -> {

                }

                is NetworkResult.Success -> {
                    if (result.value.isSuccessful) {
                        result.value?.let {
                            jobDetailsList.value = it.body()
                        }
                    }
                }

                is NetworkResult.Failure -> {
                    NetworkResult.Failure(
                        result.isNetworkError,
                        result.errorCode,
                        result.errorBody
                    )

                }

            }
        }

    }

}