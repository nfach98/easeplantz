package com.easeplantz.easeplantz.core.data.source.local.room

import androidx.room.*
import com.easeplantz.easeplantz.core.data.source.local.entity.PredictionEntity
import com.easeplantz.easeplantz.core.data.source.local.entity.ResultEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface EaseplantzDao {
    @Query("SELECT * FROM prediction WHERE model = :model")
    fun getPrediction(model: String): Flowable<PredictionEntity>

    @Query("SELECT * FROM result WHERE disease = :disease LIMIT 1")
    fun getResult(disease: String): Flowable<ResultEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrediction(prediction: PredictionEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResults(listResult: List<ResultEntity>): Completable
}