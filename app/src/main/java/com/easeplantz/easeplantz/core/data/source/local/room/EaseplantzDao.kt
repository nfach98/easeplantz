package com.easeplantz.easeplantz.core.data.source.local.room

import androidx.room.*
import com.easeplantz.easeplantz.core.data.source.local.entity.PredictionEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface EaseplantzDao {
    @Query("SELECT * FROM prediction WHERE model = :model")
    fun getPrediction(model: String): Flowable<List<PredictionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrediction(prediction: PredictionEntity): Completable
}