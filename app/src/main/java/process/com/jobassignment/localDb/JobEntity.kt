
package process.com.jobassignment.localDb
/*
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey



@Entity(tableName = "jobs")
data class Job(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val title: String?,
    val type: Int?,
    @Embedded
    val primaryDetails: PrimaryDetails?,
    @Embedded
    val feeDetails: ContentV3?,  // Assuming fee details are part of ContentV3
    @Ignore
    val jobTags: List<JobTag> = emptyList(), // Ignored as it's stored in another table
    val jobType: Int?,
    val jobCategoryId: Int?,
    val qualification: Int?,
    val experience: Int?,
    val shiftTiming: Int?,
    val jobRoleId: Int?,
    val salaryMax: Int?,
    val salaryMin: Int?,
    val cityLocation: Int?,
    val locality: Int?,
    val premiumTill: String?,
    val content: String?,
    val companyName: String?,
    val advertiser: Int?,
    val buttonText: String?,
    val customLink: String?,
    val amount: String?,
    val views: Int?,
    val shares: Int?,
    val fbShares: Int?,
    val isBookmarked: Boolean?,
    val isApplied: Boolean?,
    val isOwner: Boolean?,
    val updatedOn: String?,
    val whatsappNo: String?,
    @Embedded
    val contactPreference: ContactPreference?,
    val createdOn: String?,
    val isPremium: Boolean?,
    @Ignore
    val creatives: List<Creative> = emptyList(), // Ignored as it's stored in another table
    @Ignore
    val videos: List<Any> = emptyList(), // Ignored as not defined
    @Ignore
    val locations: List<Location> = emptyList(), // Ignored, stored separately
    @Ignore
    val tags: List<Any> = emptyList(), // Ignored
    val status: Int?,
    val expireOn: String?,
    val jobHours: String?,
    val openingsCount: Int?,
    val jobRole: String?,
    val otherDetails: String?,
    val jobCategory: String?,
    val numApplications: Int?,
    val enableLeadCollection: Boolean?,
    val isJobSeekerProfileMandatory: Boolean?,
    val jobLocationSlug: String?,
    val feesText: String?,
    val questionBankId: Int?,
    val screeningRetry: Int?,
    val shouldShowLastContacted: Boolean?,
    val feesCharged: Int?
)

@Entity(tableName = "job_tags")
data class JobTag(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val jobId: Int, // Foreign key to Job
    val value: String?,
    val bgColor: String?,
    val textColor: String?
)


data class PrimaryDetails(
    val place: String?,
    val salary: String?,
    val jobType: String?,
    val experience: String?,
    val feesCharged: String?,
    val qualification: String?
)


data class ContactPreference(
    val preference: Int?,
    val whatsappLink: String?,
    val preferredCallStartTime: String?,
    val preferredCallEndTime: String?
)

@Entity(tableName = "creatives")
data class Creative(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val jobId: Int, // Foreign key to Job
    val file: String?,
    val thumbUrl: String?,
    val creativeType: Int?
)

@Entity(tableName = "locations")
data class Location(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val locale: String?,
    val state: Int?
)

@Entity(tableName = "content_v3")
data class ContentV3(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Ignore
    val v3: List<V3Detail> = emptyList() // Ignored, stored in another table
)

@Entity(tableName = "v3_details")
data class V3Detail(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val contentV3Id: Int, // Foreign key to ContentV3
    val fieldKey: String?,
    val fieldName: String?,
    val fieldValue: String?
)


*/
