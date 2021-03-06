package com.easeplantz.easeplantz.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("status")
    var status: String? = null,

    @SerializedName("filename")
    var filename: String? = null,

    @SerializedName("model")
    var model: String,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("disease")
    var disease: String? = null,

    @SerializedName("prediction")
    var prediction: String? = null
)