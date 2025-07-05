package com.reringuy.sync

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.work.PeriodicWorkRequestBuilder
import com.reringuy.sync.backgroundSync.BackgroundSyncWorker
import com.reringuy.sync.presentation.ui.AppWrapper
import java.time.Duration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        PeriodicWorkRequestBuilder<BackgroundSyncWorker>(repeatInterval = Duration.ofMinutes(15)).build()

        setContent {
            AppWrapper()
        }
    }
}
