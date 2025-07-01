package com.reringuy.sync.domain

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

actual fun getEngine(): HttpClientEngineFactory<*> = OkHttp