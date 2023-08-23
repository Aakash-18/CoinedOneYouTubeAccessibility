package com.coinedone.youtubeaccessibility

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext
/**
    Repository class for carrying out the dao process.
*/
class RepositoryClass(private var application: Application,
    ) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    var mDao: LogDetailsDao

    init {
        mDao = LogDetailsDatabase.getInstance(application).logDetailsDao()
    }

    fun getAllLogs() = mDao.getAllLogs()


    suspend fun insertLogs(log: LogDetailsClass){
        withContext(Dispatchers.IO) {
            mDao.insert(log)
        }
    }




}
