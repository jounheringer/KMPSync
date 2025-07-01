package com.reringuy.sync.domain.repositories

import com.reringuy.sync.domain.dao.BasicDataDao
import com.reringuy.sync.domain.services.BasicDataService
import com.reringuy.sync.model.entity.BasicData
import com.reringuy.sync.utils.ApiResponse
import com.reringuy.sync.utils.OperationHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasicDataRepository(
    private val service: BasicDataService,
    private val dao: BasicDataDao,
) {
    fun getAllBasicData(): Flow<List<BasicData>> = flow {
        emit(dao.getAllBasicData())

        try {
            val dataList = service.getAllBasicData()
            dao.upsertAllBasicData(dataList)
            emit(dataList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun saveLocalData(basicData: BasicData): Flow<OperationHandler<BasicData>> = flow {
        try {
            val auxId = dao.upsertBasicData(basicData)
            emit(OperationHandler.Success(basicData.apply { uid = auxId.toInt() }))
        } catch (e: Exception) {
            emit(OperationHandler.Failure(e.message ?: "Unknown error"))
        }
    }

    fun syncData(basicDatas: List<BasicData>): Flow<OperationHandler<List<BasicData>>> = flow {
        try {
            val syncedData = service.syncBasicData(basicDatas)
            dao.upsertAllBasicData(syncedData)
            emit(OperationHandler.Success(dao.getAllBasicData()))
        } catch (e: Exception) {
            emit(OperationHandler.Failure(e.message ?: "Unknown error"))
        }
    }
}