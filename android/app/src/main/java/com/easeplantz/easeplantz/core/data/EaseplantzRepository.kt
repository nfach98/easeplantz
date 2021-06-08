package com.easeplantz.easeplantz.core.data

import com.easeplantz.easeplantz.core.data.source.local.LocalDataSource
import com.easeplantz.easeplantz.core.data.source.remote.RemoteDataSource
import com.easeplantz.easeplantz.core.data.source.remote.network.ApiResponse
import com.easeplantz.easeplantz.core.data.source.remote.response.PredictionResponse
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.model.Result
import com.easeplantz.easeplantz.core.domain.repository.IEaseplantzRepository
import com.easeplantz.easeplantz.core.utils.AppExecutors
import com.easeplantz.easeplantz.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MultipartBody

class EaseplantzRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ): IEaseplantzRepository {

    override fun getPrediction(model: String, image: MultipartBody.Part?, shouldFetch: Boolean): Flowable<Resource<List<Prediction>>>  =
        object : NetworkBoundResource<List<Prediction>, PredictionResponse>(appExecutors) {
            override fun loadFromDB(): Flowable<List<Prediction>> {
                return localDataSource.getPrediction(model).map { DataMapper.mapPredictionEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Prediction>?): Boolean = shouldFetch

            override fun createCall(): Flowable<ApiResponse<PredictionResponse>> =
                remoteDataSource.getPrediction(model, image)

            override fun saveCallResult(data: PredictionResponse) {
                val prediction = DataMapper.mapPredictionResponsesToEntities(data)
                localDataSource.insertPrediction(prediction)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getResult(id: Int): Flowable<List<Result>> {
        return localDataSource.getResult(id).map {
            DataMapper.mapResultEntitiesToDomain(it)
        }
    }
}