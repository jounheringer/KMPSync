package com.reringuy.sync.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class BasicData(
    @PrimaryKey(autoGenerate = true)
    var uid: Int? = null,
    var id: Int? = null,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    val synced: Boolean = false
)