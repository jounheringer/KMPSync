package com.reringuy.sync.backgroundSync

import com.reringuy.sync.domain.dao.BasicDataDao
import com.reringuy.sync.domain.services.BasicDataService

class BackgroundSyncUseCase(
    private val service: BasicDataService,
    private val dao: BasicDataDao
) {
    suspend fun syncLocalData() {
        try {
            val dataList = service.getAllBasicData()
            dao.upsertAllBasicData(dataList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}