package com.easeplantz.easeplantz.core.domain.repository

import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.model.Result
import io.reactivex.Flowable
import okhttp3.MultipartBody

interface IEaseplantzRepository {
    fun getPrediction(model: String, image: MultipartBody.Part?, shouldFetch: Boolean) : Flowable<Resource<List<Prediction>>>

    fun getResult(disease: String) : Flowable<List<Result>>
}