package com.easeplantz.easeplantz.core.domain.usecase

import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.domain.model.Prediction
import io.reactivex.Flowable

interface EaseplantzUseCase {
    fun getPrediction(model: String): Flowable<Resource<Prediction>>
}