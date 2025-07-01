package com.reringuy.sync.domain

import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin

actual fun getEngine(): HttpClientEngineFactory<*> = Darwin