package process.com.jobassignment.repository

import process.com.jobassignment.localDb.JobDatabase
import javax.inject.Inject

class LocalDataRepository
@Inject
constructor(private val jobDatabase: JobDatabase){


}