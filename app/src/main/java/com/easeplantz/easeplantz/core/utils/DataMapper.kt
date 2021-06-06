package com.easeplantz.easeplantz.core.utils

import com.easeplantz.easeplantz.core.data.source.local.entity.PredictionEntity
import com.easeplantz.easeplantz.core.data.source.local.entity.ResultEntity
import com.easeplantz.easeplantz.core.data.source.remote.response.PredictionResponse
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.domain.model.Result

object DataMapper {
    fun mapPredictionResponsesToEntities(input: PredictionResponse): PredictionEntity =
        PredictionEntity(
            status = input.status,
            filename = input.filename,
            model = input.model,
            url = input.url,
            disease = input.disease,
            prediction = input.prediction,
        )

    fun mapPredictionEntitiesToDomain(input: PredictionEntity): Prediction =
        Prediction(
            status = input.status,
            filename = input.filename,
            model = input.model,
            url = input.url,
            disease = input.disease,
            prediction = input.prediction,
        )

    fun mapResultEntitiesToDomain(input: ResultEntity): Result =
        Result(
            id = input.id,
            disease = input.disease,
            description = input.description,
        )
}