package com.easeplantz.easeplantz.core.domain.model


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Prediction(
    var status: String? = null,
    var filename: String? = null,
    var model: String? = null,
    var url: String? = null,
    var disease: String? = null,
    var prediction: String? = null
) : Parcelable