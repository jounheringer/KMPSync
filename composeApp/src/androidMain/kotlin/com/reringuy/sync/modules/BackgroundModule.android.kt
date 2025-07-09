package com.reringuy.sync.modules

import com.reringuy.sync.backgroundSync.BackgroundSyncScheduler
import com.reringuy.sync.backgroundSync.BackgroundSyncUseCase
import com.reringuy.sync.domain.SyncDatabase
import com.reringuy.sync.domain.createHttpClient
import com.reringuy.sync.domain.services.BasicDataService
import io.ktor.client.HttpClient
import org.koin.dsl.module

actual fun backgroundModule() = module {
    single<HttpClient> { createHttpClient() }
    single { BasicDataService(get()) }
    single { get<SyncDatabase>().basicDataDao() }
    factory { BackgroundSyncUseCase(get(), get()) }
    factory { BackgroundSyncScheduler(get()) }
}