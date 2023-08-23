package com.coinedone.youtubeaccessibility

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var repositoryClass: RepositoryClass



    private lateinit var database: LogDetailsDatabase
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enabling the accessibility service for users to get notified

        if (!isAccessibilityServiceEnabled()) {
            requestAccessibilityPermission();
        }

        // Fragment is called in mainactivity

        var fragment = YoutubeLogFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.list_youtube, fragment)
            .commit()
    }


    fun isAccessibilityServiceEnabled(): Boolean{

        // Check if your Accessibility Service is enabled
        return false
    }

    fun requestAccessibilityPermission()
    {
        val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }
}