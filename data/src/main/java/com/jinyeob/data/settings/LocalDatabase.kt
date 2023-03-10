package com.jinyeob.data.settings

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [
    ],
    exportSchema = false
)

internal abstract class LocalDatabase : RoomDatabase()
