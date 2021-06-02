package com.easeplantz.easeplantz.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.easeplantz.easeplantz.core.data.source.local.entity.PredictionEntity

@Database(entities = [PredictionEntity::class], version = 1, exportSchema = false)
abstract class EaseplantzDatabase : RoomDatabase() {

    abstract fun easeplantzDao(): EaseplantzDao
}