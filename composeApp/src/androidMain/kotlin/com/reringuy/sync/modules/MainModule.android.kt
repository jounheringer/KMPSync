package com.reringuy.sync.modules

import com.reringuy.sync.database.getDatabaseBuilder
import com.reringuy.sync.domain.getRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun getMainModule() = module {
    single { getRoomDatabase(builder = getDatabaseBuilder(ctx = androidContext()) ) }
}