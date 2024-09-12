package process.com.jobassignment.localdb

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [JobEntity::class], version = 1)
abstract class JobRoomDataBase : RoomDatabase() {
    abstract fun jobDetailsDao(): JobDao
}