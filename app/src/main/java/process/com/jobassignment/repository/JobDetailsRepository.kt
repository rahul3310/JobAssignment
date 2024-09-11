package process.com.jobassignment.repository


import process.com.jobassignment.api.ApiServices
import process.com.jobassignment.api.safeApiCall
import javax.inject.Inject

class JobDetailsRepository @Inject constructor(
    private val apiServices: ApiServices
) {
    suspend fun getJobDetails(page: Int,pageSize:Int) = safeApiCall {
        apiServices.getJobDetails(page, pageSize)
    }
}