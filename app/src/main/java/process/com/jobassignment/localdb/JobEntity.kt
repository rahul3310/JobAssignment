package process.com.jobassignment.localdb

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "job_details")
data class JobEntity(
    @PrimaryKey(autoGenerate = false)
    val jobId: Long,
    val jobTitle: String?,
    val jobLocation: String?,
    val salary: String?,
    val phoneNumber: String?
) : Parcelable
