package com.reringuy.sync.domain.services

import com.reringuy.sync.model.entity.BasicData
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import kotlinx.coroutines.flow.Flow


interface BasicDataService {
    @GET("all")
    fun getAllBasicData(): List<BasicData>

    @POST("random-data")
    fun generateRandomData(): Flow<BasicData>

    @POST("sync-data")
    fun syncBasicData(@Body basicData: BasicData): Flow<BasicData>
}