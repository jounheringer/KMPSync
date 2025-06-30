package com.reringuy.sync.modules

import com.reringuy.sync.database.getDatabaseBuilder
import com.reringuy.sync.domain.getRoomDatabase
import com.reringuy.sync.domain.repositories.BasicDataRepository
import com.reringuy.sync.domain.services.createBasicDataService
import com.reringuy.sync.presentation.viewmodel.MainAppViewmodel
import com.reringuy.sync.utils.BaseURL
import de.jensklingenberg.ktorfit.Ktorfit
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun getMainModule() = module {
    single { getRoomDatabase(builder = getDatabaseBuilder(ctx = androidContext())) }
    single { provideKtorfit() }
    single { get<Ktorfit>().createBasicDataService() }
    single { BasicDataRepository(get()) }
    factory { MainAppViewmodel(get()) }

}

fun provideKtorfit() = Ktorfit.Builder().baseUrl(BaseURL.BASE_URL).build()
