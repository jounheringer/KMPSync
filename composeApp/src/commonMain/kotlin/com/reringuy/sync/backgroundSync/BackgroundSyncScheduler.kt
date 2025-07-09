package com.reringuy.sync.backgroundSync

expect class BackgroundSyncScheduler {
    suspend fun scheduleBackgroundSync()
}
