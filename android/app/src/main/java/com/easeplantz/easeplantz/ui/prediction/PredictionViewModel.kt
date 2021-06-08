package com.easeplantz.easeplantz.ui.prediction

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.usecase.EaseplantzUseCase
import okhttp3.MultipartBody

class PredictionViewModel constructor(private val useCase: EaseplantzUseCase) : ViewModel() {
    fun getPrediction(model: String, image: MultipartBody.Part, shouldFetch: Boolean) : LiveData<Resource<Prediction>>{
        return LiveDataReactiveStreams.fromPublisher(useCase.getPrediction(model, image, shouldFetch))
    }
}