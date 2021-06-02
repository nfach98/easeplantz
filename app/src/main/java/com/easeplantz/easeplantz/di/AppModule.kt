package com.easeplantz.easeplantz.di

import com.easeplantz.easeplantz.core.domain.usecase.EaseplantzInteractor
import com.easeplantz.easeplantz.core.domain.usecase.EaseplantzUseCase
import com.easeplantz.easeplantz.ui.prediction.PredictionViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<EaseplantzUseCase> { EaseplantzInteractor(get()) }
}

val viewModelModule = module {
    viewModel { PredictionViewModel(get()) }
}