package com.reringuy.sync.domain.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.reringuy.sync.model.entity.BasicData

@Dao
interface BasicDataDao {
    @Query("SELECT * FROM basicdata")
    suspend fun getAllBasicData(): List<BasicData>

    @Upsert
    suspend fun upsertBasicData(basicData: BasicData): Long

    @Upsert
    suspend fun upsertAllBasicData(basicDatas: List<BasicData>)

    @Query("DELETE FROM basicdata WHERE id = :id")
    suspend fun deleteBasicData(id: Int)

    @Query("DELETE FROM basicdata")
    suspend fun deleteAllBasicData()
}