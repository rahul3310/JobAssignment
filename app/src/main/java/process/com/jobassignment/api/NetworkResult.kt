package process.com.jobassignment.api

import okhttp3.ResponseBody

sealed class NetworkResult<out T> {
    data class Success<out T>(val value: T) : NetworkResult<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : NetworkResult<Nothing>()

    data object Loading : NetworkResult<Nothing>()
}