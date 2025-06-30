package com.reringuy.sync.domain.services

import com.reringuy.sync.model.entity.BasicData
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST


interface BasicDataService {
    @GET("basic-data/all")
    fun getAllBasicData(): List<BasicData>

    @POST("basic-data/sync")
    suspend fun syncBasicData(@Body basicData: BasicData)
}