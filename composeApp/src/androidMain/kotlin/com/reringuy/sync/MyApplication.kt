package com.reringuy.sync

import android.app.Application
import com.reringuy.sync.modules.getMainModule
import io.kotzilla.sdk.analytics.koin.analytics
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(
                getMainModule()
            )
            analytics()
        }
    }
}