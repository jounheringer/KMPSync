package com.reringuy.sync.domain.services

import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.utils.BaseURL
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse

class BasicDataService(private val client: HttpClient) {
    suspend fun getAllBasicData(): List<BasicData> {
        val response: HttpResponse = client.get("${BaseURL.BASE_URL}/all")
        return response.body()
    }

    suspend fun syncBasicData(data: List<BasicData>): List<BasicData> {
        val response: HttpResponse = client.post("${BaseURL.BASE_URL}/sync-data") {
            setBody(data)
        }
        return response.body()
    }

    suspend fun generateRandomData(): BasicData {
        val response: HttpResponse = client.get("${BaseURL.BASE_URL}/random-data")
        return response.body()
    }
}