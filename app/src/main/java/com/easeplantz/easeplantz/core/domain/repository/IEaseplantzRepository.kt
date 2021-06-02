package com.easeplantz.easeplantz.core.domain.repository

import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.domain.model.Prediction
import io.reactivex.Flowable
import okhttp3.MultipartBody

interface IEaseplantzRepository {
    fun getPrediction(model: String, image: MultipartBody.Part) : Flowable<Resource<Prediction>>
}