package com.reringuy.sync.backgroundSync

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class BackgroundSyncScheduler(private val useCase: BackgroundSyncUseCase) {
    actual suspend fun scheduleBackgroundSync() {
        useCase.syncLocalData()
    }
}

@Suppress("unused")
class BackgroundSyncSchedulerWrapper() : KoinComponent {
    private val scheduler: BackgroundSyncScheduler by inject<BackgroundSyncScheduler>()

    fun triggerSync() {
        CoroutineScope(Dispatchers.IO).launch {
            scheduler.scheduleBackgroundSync()
        }
    }
}