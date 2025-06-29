package com.reringuy.sync

import com.reringuy.sync.modules.getMainModule
import org.koin.core.context.startKoin

@Suppress("UNUSED")
fun initKoin() {
    startKoin {
        modules(
            getMainModule()
        )
    }
}