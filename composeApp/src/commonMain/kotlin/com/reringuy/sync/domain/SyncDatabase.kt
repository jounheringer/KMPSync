
package com.reringuy.sync.domain

import androidx.room.AutoMigration
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.reringuy.sync.domain.dao.BasicDataDao
import com.reringuy.sync.model.entity.BasicData

@Database(
    entities = [BasicData::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3, spec = SyncDatabaseMigrations.Companion.VThree::class)
    ]
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class SyncDatabase : RoomDatabase() {
    abstract fun basicDataDao(): BasicDataDao
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<SyncDatabase> {
    override fun initialize(): SyncDatabase
}