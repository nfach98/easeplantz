package com.easeplantz.easeplantz.core.domain.usecase

import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.model.Result
import com.easeplantz.easeplantz.core.domain.repository.IEaseplantzRepository
import io.reactivex.Flowable
import okhttp3.MultipartBody

class EaseplantzInteractor(private val repository: IEaseplantzRepository) : EaseplantzUseCase {
    override fun getPrediction(model: String, image: MultipartBody.Part?, shouldFetch: Boolean): Flowable<Resource<List<Prediction>>> =
        repository.getPrediction(model, image, shouldFetch)

    override fun getResult(disease: String): Flowable<List<Result>> = repository.getResult(disease)
}