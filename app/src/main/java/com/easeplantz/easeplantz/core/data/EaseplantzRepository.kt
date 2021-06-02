package com.easeplantz.easeplantz.core.data

import com.easeplantz.easeplantz.core.data.source.local.LocalDataSource
import com.easeplantz.easeplantz.core.data.source.remote.RemoteDataSource
import com.easeplantz.easeplantz.core.data.source.remote.network.ApiResponse
import com.easeplantz.easeplantz.core.data.source.remote.response.PredictionResponse
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.repository.IEaseplantzRepository
import com.easeplantz.easeplantz.core.utils.AppExecutors
import com.easeplantz.easeplantz.core.utils.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EaseplantzRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ): IEaseplantzRepository {

    override fun getPrediction(model: String): Flowable<Resource<Prediction>>  =
        object : NetworkBoundResource<Prediction, PredictionResponse>(appExecutors) {
            override fun loadFromDB(): Flowable<Prediction> {
                return localDataSource.getPrediction(model).map {
                    DataMapper.mapPredictionEntitiesToDomain(it[0])
                }
            }

            override fun shouldFetch(data: Prediction?): Boolean = data == null

            override fun createCall(): Flowable<ApiResponse<PredictionResponse>> =
                remoteDataSource.getPrediction(model)

            override fun saveCallResult(data: PredictionResponse) {
                val prediction = DataMapper.mapPredictionResponsesToEntities(data)
                localDataSource.insertPrediction(prediction)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()
}