package com.reringuy.sync.converters

import androidx.room.TypeConverter
import kotlin.time.Instant
import kotlin.time.ExperimentalTime

@ExperimentalTime
class InstantConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Instant? {
        return value?.let { Instant.fromEpochMilliseconds(it) }
    }

    @TypeConverter
    fun dateToTimestamp(instant: Instant?): Long? {
        return instant?.toEpochMilliseconds()
    }
}