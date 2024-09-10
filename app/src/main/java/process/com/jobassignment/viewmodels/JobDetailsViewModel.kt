package process.com.jobassignment.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import process.com.jobassignment.api.NetworkResult
import process.com.jobassignment.datamodels.JobDetailsResponse
import process.com.jobassignment.repository.JobDetailsRepository
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class JobDetailsViewModel @Inject constructor(
    private val repository: JobDetailsRepository
) : ViewModel() {
    val isLoading = mutableStateOf(false)
    val isError = mutableStateOf(false)
    private val viewModelScope = CoroutineScope(context = Dispatchers.IO)
    val jobDetailsList = mutableStateOf<JobDetailsResponse>(JobDetailsResponse(results = ArrayList()))

    private val _currentPage = MutableStateFlow(1)
    val currentPage = _currentPage

    fun updateCurrentPage(newPage:Int){
        _currentPage.value = newPage
    }

    init {
        fetchJobData()
    }
    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun fetchJobData() {
        viewModelScope.launch {
            _currentPage
                .debounce(1000)
                .distinctUntilChanged()
                .flatMapLatest { page ->
                    flow {
                        val result = repository.getJobDetails(page)
                        emit(result)
                    }
                }
                .flowOn(Dispatchers.IO)
                .collect { response ->
                    handlePagedResponse(response)
                }
        }

    }

    private fun handlePagedResponse(
        result: NetworkResult<Response<JobDetailsResponse>>
    ) {
        when (result) {
            is NetworkResult.Loading -> {
               isLoading.value = true
            }

            is NetworkResult.Success -> {
                isLoading.value = false
                if (result.value.isSuccessful) {
                    result.value.body()?.let {
                        jobDetailsList.value = it
                    }
                }
            }

            is NetworkResult.Failure -> {
                isError.value = true
                isLoading.value = false
                NetworkResult.Failure(
                    result.isNetworkError,
                    result.errorCode,
                    result.errorBody
                )

            }

        }
    }


}