package com.easeplantz.easeplantz.data.main

import androidx.lifecycle.ViewModel
import com.easeplantz.easeplantz.data.Data
import com.easeplantz.easeplantz.data.MainEntity

class MainViewModel : ViewModel() {
    fun getMenus() : List<MainEntity> = Data.mainMenuData()
}