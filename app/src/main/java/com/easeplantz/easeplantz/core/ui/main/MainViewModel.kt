package com.easeplantz.easeplantz.core.ui.main

import androidx.lifecycle.ViewModel
import com.easeplantz.easeplantz.core.utils.Data
import com.easeplantz.easeplantz.core.data.source.local.entity.MainEntity

class MainViewModel : ViewModel() {
    fun getMenus() : List<MainEntity> = Data.mainMenuData()
}