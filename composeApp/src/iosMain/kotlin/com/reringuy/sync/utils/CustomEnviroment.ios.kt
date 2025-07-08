package com.reringuy.sync.utils

actual object CustomEnvironment {
    actual val baseUrl: String
        get() = "http://127.0.0.1:8000"
}