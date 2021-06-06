package com.easeplantz.easeplantz.core.data.source.local

import com.easeplantz.easeplantz.core.data.source.local.entity.PredictionEntity
import com.easeplantz.easeplantz.core.data.source.local.entity.ResultEntity
import com.easeplantz.easeplantz.core.data.source.local.room.EaseplantzDao
import io.reactivex.Flowable

class LocalDataSource constructor(private val dao: EaseplantzDao) {
    fun getPrediction(model: String): Flowable<PredictionEntity> = dao.getPrediction(model)

    fun getResult(id: Int): Flowable<ResultEntity> = dao.getResult(id)

    fun insertPrediction(prediction: PredictionEntity) = dao.insertPrediction(prediction)
}