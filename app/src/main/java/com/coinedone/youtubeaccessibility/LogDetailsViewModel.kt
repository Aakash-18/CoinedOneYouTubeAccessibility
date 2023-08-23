package com.coinedone.youtubeaccessibility

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

/**
    Android View Model for collecting data from  accessibilty service and from the repository
 */

class LogDetailsViewModel(application: Application) : AndroidViewModel(application) {
    var allLogData: LiveData<List<LogDetailsClass>>? = null
    var logRepository: RepositoryClass

    init {
        logRepository = RepositoryClass(application)
        allLogData = logRepository?.getAllLogs()

    }

    internal fun getAllCategoryList(): LiveData<List<LogDetailsClass>>? {
        return allLogData
    }

}