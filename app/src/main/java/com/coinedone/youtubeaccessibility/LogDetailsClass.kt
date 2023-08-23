package com.coinedone.youtubeaccessibility

import android.content.Context
import android.provider.ContactsContract.Data
import android.provider.MediaStore.Video
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase


/**
    Log Details class has entity where the members are stored
    Dao where all functions are declared - one to insert data and one to collect all data and display it in fragemnt
    DataBase class for the database creation
*/
@Entity(tableName = "logDetails")
data class LogDetailsClass(@PrimaryKey
                           val id: String, val title: String, val channelName: String, val currentTimeStamp:String)


@Dao
interface LogDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: LogDetailsClass)

    @Query("SELECT * FROM logDetails")
    fun getAllLogs(): LiveData<List<LogDetailsClass>>
}

@Database(entities = [LogDetailsClass::class], version = 1, exportSchema = false)
abstract class LogDetailsDatabase : RoomDatabase() {
    abstract fun logDetailsDao(): LogDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: LogDetailsDatabase?= null

        fun getInstance(context: Context): LogDetailsDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) =
            databaseBuilder(
                context.applicationContext,
                LogDetailsDatabase::class.java, "Sample.db").allowMainThreadQueries()
                .build()
    }
}
