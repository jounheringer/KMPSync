package com.reringuy.sync.domain

import com.reringuy.sync.domain.converters.ApiResponseConverterFactory
import com.reringuy.sync.utils.BaseURL
import de.jensklingenberg.ktorfit.Ktorfit
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun provideKtorfit(): Ktorfit {
    val client = HttpClient(Darwin) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
    return Ktorfit.Builder()
        .baseUrl(BaseURL.BASE_URL)
        .httpClient(client)
        .converterFactories(ApiResponseConverterFactory())
        .build()
}