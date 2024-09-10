package process.com.jobassignment.datamodels

import com.google.gson.annotations.SerializedName


data class JobDetailsResponse(
    @SerializedName("results")
    val results : List<Job>
)
