package com.reringuy.sync.domain

import androidx.room.DeleteColumn
import androidx.room.migration.AutoMigrationSpec

class SyncDatabaseMigrations {
    companion object {
        @DeleteColumn(
            tableName = "BasicData",
            columnName = "first_name"
        )
        @DeleteColumn(
            tableName = "BasicData",
            columnName = "last_name"
        )
        class VThree : AutoMigrationSpec {
        }

    }
}