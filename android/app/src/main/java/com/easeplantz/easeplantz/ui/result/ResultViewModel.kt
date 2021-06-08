package com.easeplantz.easeplantz.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.model.Result
import com.easeplantz.easeplantz.core.domain.usecase.EaseplantzUseCase
import okhttp3.MultipartBody

class ResultViewModel constructor(private val useCase: EaseplantzUseCase) : ViewModel() {
    fun getResult(disease: String) : LiveData<Result>{
        return LiveDataReactiveStreams.fromPublisher(useCase.getResult(disease))
    }
}