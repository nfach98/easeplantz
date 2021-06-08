package com.easeplantz.easeplantz.core.data

import com.easeplantz.easeplantz.core.data.source.local.LocalDataSource
import com.easeplantz.easeplantz.core.data.source.local.entity.ResultEntity
import com.easeplantz.easeplantz.core.data.source.remote.RemoteDataSource
import com.easeplantz.easeplantz.core.data.source.remote.network.ApiResponse
import com.easeplantz.easeplantz.core.data.source.remote.response.PredictionResponse
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.model.Result
import com.easeplantz.easeplantz.core.domain.repository.IEaseplantzRepository
import com.easeplantz.easeplantz.core.utils.AppExecutors
import com.easeplantz.easeplantz.core.utils.Data
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

    override fun getPrediction(model: String, image: MultipartBody.Part?, shouldFetch: Boolean): Flowable<Resource<Prediction>>  =
        object : NetworkBoundResource<Prediction, PredictionResponse>(appExecutors) {
            override fun loadFromDB(): Flowable<Prediction> {
                return localDataSource.getPrediction(model).map { DataMapper.mapPredictionEntityToDomain(it) }
            }

            override fun shouldFetch(data: Prediction?): Boolean = shouldFetch

            override fun createCall(): Flowable<ApiResponse<PredictionResponse>> =
                remoteDataSource.getPrediction(model, image)

            override fun saveCallResult(data: PredictionResponse) {
                val prediction = DataMapper.mapPredictionResponseToEntity(data)
                localDataSource.insertPrediction(prediction)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun getResult(disease: String): Flowable<Result> {
        return localDataSource.getResult(disease).map {
            DataMapper.mapResultEntityToDomain(it)
        }
    }

    override fun insertResult(listResult: List<ResultEntity>) {
        appExecutors.diskIO().execute { localDataSource.insertResult(listResult) }
    }
}