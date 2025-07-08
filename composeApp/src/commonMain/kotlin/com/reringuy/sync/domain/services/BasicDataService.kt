package com.reringuy.sync.domain.services

import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.utils.CustomEnvironment
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType

class BasicDataService(private val client: HttpClient) {
    suspend fun getAllBasicData(): List<BasicData> {
        val response: HttpResponse = client.get("${CustomEnvironment.baseUrl}/all")
        return response.body()
    }

    suspend fun syncBasicData(data: List<BasicData>): List<BasicData> {
        val response: HttpResponse = client.post("${CustomEnvironment.baseUrl}/sync-data") {
            contentType(ContentType.Application.Json)
            setBody(data)
        }
        return response.body()
    }
}