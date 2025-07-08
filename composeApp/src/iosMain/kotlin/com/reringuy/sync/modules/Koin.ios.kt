package com.reringuy.sync.modules

import org.koin.core.context.startKoin

@Suppress("unused")
actual fun startKoin() {
    startKoin {
        modules(
            getMainModule()
        )
    }
}