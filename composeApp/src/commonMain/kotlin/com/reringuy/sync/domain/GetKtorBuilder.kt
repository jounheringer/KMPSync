package com.reringuy.sync.domain

import com.reringuy.sync.utils.BaseURL
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


expect fun getEngine(): HttpClientEngineFactory<*>

fun createHttpClient(): HttpClient = HttpClient(getEngine()) {
    install(ContentNegotiation) {
        json(
            json = Json {
                ignoreUnknownKeys = true
                isLenient = true
            }
        )
    }

    install(Logging) {
        level = LogLevel.ALL
    }
}