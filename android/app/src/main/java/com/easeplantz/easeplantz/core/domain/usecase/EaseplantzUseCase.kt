package com.easeplantz.easeplantz.core.domain.usecase

import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.data.source.local.entity.ResultEntity
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.model.Result
import io.reactivex.Flowable
import okhttp3.MultipartBody

interface EaseplantzUseCase {
    fun getPrediction(model: String, image: MultipartBody.Part?, shouldFetch: Boolean): Flowable<Resource<Prediction>>

    fun getResult(disease: String) : Flowable<Result>

    fun insertResult(listResult: List<ResultEntity>)
}