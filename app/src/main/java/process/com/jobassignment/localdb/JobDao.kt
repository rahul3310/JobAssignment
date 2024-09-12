package process.com.jobassignment.localdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJob(job: JobEntity)

    @Query("SELECT * FROM job_details")
    fun getAllJobs(): Flow<List<JobEntity>>

    @Query("DELETE FROM job_details WHERE jobId = :id")
    suspend fun deleteJobById(id: Long)
}