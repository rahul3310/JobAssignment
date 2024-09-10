package process.com.jobassignment.api

import retrofit2.Response
import process.com.jobassignment.datamodels.JobDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("https://testapi.getlokalapp.com/common/jobs")
    suspend fun getJobDetails(
        @Query("page") page: Int,
    ): Response<JobDetailsResponse>
}