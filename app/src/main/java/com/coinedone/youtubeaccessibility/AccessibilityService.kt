package com.coinedone.youtubeaccessibility

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.util.Log
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import androidx.room.Room

import com.coinedone.youtubeaccessibility.LogDetailsClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


open class AccessibilityService : AccessibilityService() {
    private val TAG = "YouTubeAccessibilityService"
    private lateinit var repositoryClass: RepositoryClass

    val db = Room.databaseBuilder(
        applicationContext,
        LogDetailsDatabase::class.java, "Log-database"
    ).build()




    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        val source = event?.source
        val packageName = event?.packageName?.toString()
        val className = event?.className
        Log.i(TAG,"source"+source.toString())
        Log.i(TAG,"packageName"+packageName.toString())
        Log.i(TAG,"ClassName"+className.toString())
        Log.i(TAG,"Event"+event.toString())

        if (packageName == "com.google.android.youtube") {
            Log.i(TAG,"It has entered the android.youtube if statement")

                Log.i(TAG,"It has entered the android.youtube watchactivity  if statement")
                var videoTitle = source?.let { extractVideoTitle(it) }
                var channelName = source?.let { extractChannelNameFromAccessibilityNode(it) }
                var timestamp = System.currentTimeMillis().toString()
                channelName = channelName?.let { extractVideoTitle(it) }

               CoroutineScope(Dispatchers.IO).launch {
                    repositoryClass.insertLogs(LogDetailsClass("0",videoTitle.toString(),channelName.toString(), timestamp))
                }


        }
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
        Log.i(TAG, "onInterrupt method is called")
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        repositoryClass = RepositoryClass(application)
        Log.i(TAG, "onServiceConnected method is called")
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun extractVideoTitle(source: AccessibilityNodeInfo): String {
        // Implement this method to extract the video title
        val titleNode =
            source.findAccessibilityNodeInfosByViewId("com.google.android.youtube:id/title")
        return titleNode.firstOrNull()?.text?.toString() ?: ""
    }

    // Implement this method to extract channel name using accessibility
    private fun extractChannelNameFromAccessibilityNode(node: AccessibilityNodeInfo?): String {
        if (node != null) {
            val contentDescription = node.contentDescription
            if (contentDescription != null) {
                val contentDescriptionText = contentDescription.toString()
                val startIndex = contentDescriptionText.indexOf("Go to channel - ")
                if (startIndex != -1) {
                    return contentDescriptionText.substring(startIndex + "Go to channel - ".length)
                }
            }
        }
        return ""
    }

    fun extractVideoTitle(input: String): String {
        val title = input.substringBefore(" - ").trim()
        return title
    }


}
