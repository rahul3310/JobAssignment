package process.com.jobassignment.localDb
import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface JobDao {

    // Job Methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job): Long

    @Query("SELECT * FROM jobs WHERE id = :jobId")
    suspend fun getJobById(jobId: Int): Job

    @Query("SELECT * FROM jobs")
    suspend fun getAllJobs(): List<Job>

    @Delete
    suspend fun deleteJob(job: Job)

    // JobTag Methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJobTag(jobTag: JobTag)

    @Query("SELECT * FROM job_tags WHERE jobId = :jobId")
    suspend fun getTagsForJob(jobId: Int): List<JobTag>

    @Delete
    suspend fun deleteJobTag(jobTag: JobTag)

    // Creative Methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCreative(creative: Creative)

    @Query("SELECT * FROM creatives WHERE jobId = :jobId")
    suspend fun getCreativesForJob(jobId: Int): List<Creative>

    @Delete
    suspend fun deleteCreative(creative: Creative)

    // Location Methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: Location)

    @Query("SELECT * FROM locations")
    suspend fun getAllLocations(): List<Location>

    @Delete
    suspend fun deleteLocation(location: Location)

    // ContentV3 Methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContentV3(contentV3: ContentV3): Long

    @Query("SELECT * FROM content_v3 WHERE id = :contentV3Id")
    suspend fun getContentV3ById(contentV3Id: Int): ContentV3

    @Delete
    suspend fun deleteContentV3(contentV3: ContentV3)

    // V3Detail Methods
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertV3Detail(v3Detail: V3Detail)

    @Query("SELECT * FROM v3_details WHERE contentV3Id = :contentV3Id")
    suspend fun getV3DetailsForContent(contentV3Id: Int): List<V3Detail>

    @Delete
    suspend fun deleteV3Detail(v3Detail: V3Detail)
}




