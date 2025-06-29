package com.reringuy.sync.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reringuy.sync.domain.SyncDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<SyncDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<SyncDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}