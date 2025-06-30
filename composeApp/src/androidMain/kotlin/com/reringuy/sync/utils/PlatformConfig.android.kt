package com.reringuy.sync.utils

import com.reringuy.sync.BuildConfig

actual object PlatformConfig {
    actual val isDebug: Boolean
        get() = BuildConfig.DEBUG
}