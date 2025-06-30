
package com.reringuy.sync.domain

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.reringuy.sync.domain.dao.BasicDataDao
import com.reringuy.sync.model.entity.BasicData

@Database(
    entities = [
        BasicData::class
    ],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class SyncDatabase : RoomDatabase() {
    abstract fun basicDataDao(): BasicDataDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SyncDatabase> {
    override fun initialize(): SyncDatabase
}