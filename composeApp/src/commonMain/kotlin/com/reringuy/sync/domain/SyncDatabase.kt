
package com.reringuy.sync.domain

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.reringuy.sync.converters.InstantConverter
import com.reringuy.sync.domain.dao.BasicDataDao
import com.reringuy.sync.model.entity.BasicData
import kotlin.time.ExperimentalTime

@ExperimentalTime
@Database(
    entities = [
        BasicData::class
    ],
    version = 1
)
@TypeConverters(InstantConverter::class)
abstract class SyncDatabase : RoomDatabase() {
    abstract fun basicDataDao(): BasicDataDao
}

@ExperimentalTime
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SyncDatabase> {
    override fun initialize(): SyncDatabase
}