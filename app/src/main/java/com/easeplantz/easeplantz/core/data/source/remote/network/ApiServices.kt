package com.easeplantz.easeplantz.core.data.source.remote.network

import com.easeplantz.easeplantz.core.data.source.remote.response.PredictionResponse
import io.reactivex.Flowable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiServices {
    @Multipart
    @POST("upload")
    fun getPrediction(
        @Query("model") model: String,
        @Part image: MultipartBody.Part
    ): Flowable<PredictionResponse>
}