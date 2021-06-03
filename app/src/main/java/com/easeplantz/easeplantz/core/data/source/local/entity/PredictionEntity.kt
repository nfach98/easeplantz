package com.easeplantz.easeplantz.core.data.source.local.entity


import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prediction")
data class PredictionEntity(
    @ColumnInfo(name = "status")
    var status: String? = null,

    @ColumnInfo(name = "filename")
    var filename: String? = null,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "model")
    var model: String,

    @ColumnInfo(name = "url")
    var url: String? = null,

    @ColumnInfo(name = "disease")
    var disease: String? = null,

    @ColumnInfo(name = "prediction")
    var prediction: String? = null
)