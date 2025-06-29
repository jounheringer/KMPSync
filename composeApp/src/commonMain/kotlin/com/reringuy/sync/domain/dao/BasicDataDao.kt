package com.reringuy.sync.domain.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.reringuy.sync.model.entity.BasicData
import kotlinx.coroutines.flow.Flow
import kotlin.time.ExperimentalTime

@Dao
@ExperimentalTime
interface BasicDataDao {
    @Query("SELECT * FROM basicdata")
    fun getAllBasicData(): Flow<List<BasicData>>

    @Upsert
    suspend fun upsertBasicData(basicData: BasicData)

    @Query("DELETE FROM basicdata WHERE id = :id")
    suspend fun deleteBasicData(id: Int)
}