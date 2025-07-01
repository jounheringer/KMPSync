package com.reringuy.sync.domain.services

import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.utils.ApiResponse
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import kotlinx.coroutines.flow.Flow


interface BasicDataService {
    @GET("all")
    suspend fun getAllBasicData(): ApiResponse<List<BasicData>>

    @POST("random-data")
    suspend fun generateRandomData(): ApiResponse<Flow<BasicData>>

    @POST("sync-data")
    suspend fun syncBasicData(@Body basicData: BasicData): ApiResponse<Flow<BasicData>>
}