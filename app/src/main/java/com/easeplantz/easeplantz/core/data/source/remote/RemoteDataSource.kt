package com.easeplantz.easeplantz.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.easeplantz.easeplantz.core.data.source.remote.network.ApiResponse
import com.easeplantz.easeplantz.core.data.source.remote.network.ApiServices
import com.easeplantz.easeplantz.core.data.source.remote.response.PredictionResponse
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource constructor(private val apiServices: ApiServices) {
    @SuppressLint("CheckResult")
    fun getPrediction(model: String): Flowable<ApiResponse<PredictionResponse>> {
        val resultData = PublishSubject.create<ApiResponse<PredictionResponse>>()

        val client = apiServices.getPrediction(model)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                resultData.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}