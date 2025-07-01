package com.reringuy.sync.modules

import com.reringuy.sync.database.getDatabaseBuilder
import com.reringuy.sync.domain.SyncDatabase
import com.reringuy.sync.domain.getRoomDatabase
import com.reringuy.sync.domain.provideKtorfit
import com.reringuy.sync.domain.repositories.BasicDataRepository
import com.reringuy.sync.domain.services.createBasicDataService
import com.reringuy.sync.presentation.viewmodel.MainAppViewmodel
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.dsl.module

actual fun getMainModule() = module {
    single { getRoomDatabase(builder = getDatabaseBuilder()) }
    single { provideKtorfit() }
    single { get<Ktorfit>().createBasicDataService() }
    single { get<SyncDatabase>().basicDataDao() }
    single { BasicDataRepository(get(), get()) }
    factory { MainAppViewmodel(get()) }
}
