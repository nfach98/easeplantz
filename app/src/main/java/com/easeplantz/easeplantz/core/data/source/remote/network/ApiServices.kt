package com.easeplantz.easeplantz.core.data.source.remote.network

import com.easeplantz.easeplantz.core.data.source.remote.response.PredictResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiServices {
    @POST("upload")
    fun getPrediction(
        @Query("model") model: String?,
        @Part image: MultipartBody.Part
    ): Call<PredictResponse>
}