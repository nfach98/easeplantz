package com.easeplantz.easeplantz.core.domain.repository

import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.domain.model.Prediction
import io.reactivex.Flowable

interface IEaseplantzRepository {
    fun getPrediction(model: String) : Flowable<Resource<Prediction>>
}