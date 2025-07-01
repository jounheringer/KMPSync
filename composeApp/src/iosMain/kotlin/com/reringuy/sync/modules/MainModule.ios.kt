package com.reringuy.sync.modules

import com.reringuy.sync.database.getDatabaseBuilder
import com.reringuy.sync.domain.SyncDatabase
import com.reringuy.sync.domain.createHttpClient
import com.reringuy.sync.domain.getRoomDatabase
import com.reringuy.sync.domain.repositories.BasicDataRepository
import com.reringuy.sync.domain.services.BasicDataService
import com.reringuy.sync.presentation.viewmodel.MainAppViewmodel
import io.ktor.client.HttpClient
import org.koin.dsl.module

actual fun getMainModule() = module {
    single { getRoomDatabase(builder = getDatabaseBuilder()) }
    single<HttpClient> { createHttpClient() }
    single { BasicDataService(get()) }
    single { get<SyncDatabase>().basicDataDao() }
    single { BasicDataRepository(get(), get()) }
    factory { MainAppViewmodel(get()) }
}
