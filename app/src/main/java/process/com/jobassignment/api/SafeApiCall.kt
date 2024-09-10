package process.com.jobassignment.api

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.HttpException
import java.net.SocketTimeoutException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): NetworkResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            NetworkResult.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            Log.e("throwable", "safeApiCall: $throwable")
            when (throwable) {
                is HttpException -> {
                    NetworkResult.Failure(
                        false,
                        throwable.code(),
                        throwable.response()?.errorBody()
                    )
                }

                is SocketTimeoutException -> {
                    NetworkResult.Failure(
                        false,
                        408,
                        errorBody = "Unable to reach Server!".toResponseBody()
                    )
                }

                else -> {
                    NetworkResult.Failure(true, null, null)
                }
            }
        }
    }

}