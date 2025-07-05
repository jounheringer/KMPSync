package com.reringuy.sync.backgroundSync

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

actual fun performBackgroundSync() {
    println("MEGA MIAU")
}

class BackgroundSyncWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        println("MEGA MIAU2")
        performBackgroundSync()

        return Result.success()
    }

}