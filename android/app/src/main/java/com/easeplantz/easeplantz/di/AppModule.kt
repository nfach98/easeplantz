package com.easeplantz.easeplantz.di

import com.easeplantz.easeplantz.core.domain.usecase.EaseplantzInteractor
import com.easeplantz.easeplantz.core.domain.usecase.EaseplantzUseCase
import com.easeplantz.easeplantz.ui.main.MainViewModel
import com.easeplantz.easeplantz.ui.prediction.PredictionViewModel
import com.easeplantz.easeplantz.ui.result.ResultViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<EaseplantzUseCase> { EaseplantzInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { PredictionViewModel(get()) }
    viewModel { ResultViewModel(get()) }
}