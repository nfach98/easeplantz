package com.easeplantz.easeplantz.core.utils

import com.easeplantz.easeplantz.core.data.source.local.entity.PredictionEntity
import com.easeplantz.easeplantz.core.data.source.local.entity.ResultEntity
import com.easeplantz.easeplantz.core.data.source.remote.response.PredictionResponse
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.model.Result

object DataMapper {
    fun mapPredictionResponseToEntity(input: PredictionResponse): PredictionEntity =
        PredictionEntity(
            status = input.status,
            filename = input.filename,
            model = input.model,
            url = input.url,
            disease = input.disease,
            prediction = input.prediction,
        )

    fun mapPredictionEntitiesToDomain(input: List<PredictionEntity>): List<Prediction> =
        input.map {
            Prediction(
                status = it.status,
                filename = it.filename,
                model = it.model,
                url = it.url,
                disease = it.disease,
                prediction = it.prediction,
            )
        }

    fun mapPredictionEntityToDomain(input: PredictionEntity): Prediction =
        Prediction(
            status = input.status,
            filename = input.filename,
            model = input.model,
            url = input.url,
            disease = input.disease,
            prediction = input.prediction,
        )

    fun mapResultEntitiesToDomain(input: List<ResultEntity>): List<Result> =
        input.map {
            Result(
                id = it.id,
                disease = it.disease,
                description = it.description,
            )
        }

    fun mapResultEntityToDomain(input: ResultEntity): Result =
        Result(
            id = input.id,
            disease = input.disease,
            description = input.description,
        )
}