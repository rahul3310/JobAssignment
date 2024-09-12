package process.com.jobassignment.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import process.com.jobassignment.localdb.JobDao
import process.com.jobassignment.localdb.JobEntity
import javax.inject.Inject

class LocalRoomRepository @Inject constructor(
    private val jobDao: JobDao
) {


    suspend fun addJob(newJob: JobEntity) = jobDao.addJob(newJob)

    fun getAllJobs() = jobDao.getAllJobs()

    suspend fun deleteJob(jobId: Long) = jobDao.deleteJobById(jobId)


}