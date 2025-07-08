package com.reringuy.sync.utils

import com.reringuy.sync.BuildConfig

actual object CustomEnvironment {
    actual val baseUrl: String
        get() = BuildConfig.BASE_URL
}