package com.easeplantz.easeplantz.core.data

import androidx.lifecycle.LiveData
import com.easeplantz.easeplantz.core.data.source.remote.network.ApiResponse
import com.easeplantz.easeplantz.core.data.source.remote.response.PredictionResponse

interface EaseplantzDataSource {
    fun getPrediction(model: String) : LiveData<ApiResponse<PredictionResponse>>
}