package com.easeplantz.easeplantz.core.data.source.local

import com.easeplantz.easeplantz.core.data.source.local.entity.PredictionEntity
import com.easeplantz.easeplantz.core.data.source.local.entity.ResultEntity
import com.easeplantz.easeplantz.core.data.source.local.room.EaseplantzDao
import io.reactivex.Flowable

class LocalDataSource constructor(private val dao: EaseplantzDao) {
    fun getPrediction(model: String): Flowable<List<PredictionEntity>> = dao.getPrediction(model)

    fun getResult(disease: String): Flowable<List<ResultEntity>> = dao.getResult(disease)

    fun insertPrediction(prediction: PredictionEntity) = dao.insertPrediction(prediction)
}