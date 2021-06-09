package com.easeplantz.easeplantz.ui.main

import androidx.lifecycle.ViewModel
import com.easeplantz.easeplantz.core.utils.Data
import com.easeplantz.easeplantz.core.data.source.local.entity.MainEntity
import com.easeplantz.easeplantz.core.data.source.local.entity.ResultEntity
import com.easeplantz.easeplantz.core.domain.usecase.EaseplantzUseCase

class MainViewModel constructor(private val useCase: EaseplantzUseCase) : ViewModel() {
//    fun getMenus() : List<MainEntity> = Data(context).mainMenuData()

//    fun insertResult(){
//        useCase.insertResult(Data.mainResultData())
//    }
}