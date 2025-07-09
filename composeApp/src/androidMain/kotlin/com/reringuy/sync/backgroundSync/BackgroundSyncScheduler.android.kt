package com.reringuy.sync.backgroundSync

import co.touchlab.kermit.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

actual class BackgroundSyncScheduler(private val useCase: BackgroundSyncUseCase) {
    actual suspend fun scheduleBackgroundSync() {
        Logger.i("miau.2") { "Miau au au" }
        useCase.syncLocalData()
    }
}

internal class BackgroundSyncSchedulerWrapper() : KoinComponent {
    private val scheduler: BackgroundSyncScheduler by inject<BackgroundSyncScheduler>()

    fun triggerSync() {
        CoroutineScope(Dispatchers.IO).launch {
            Logger.i("miau.1") { "Miau au" }
            scheduler.scheduleBackgroundSync()
        }
    }
}