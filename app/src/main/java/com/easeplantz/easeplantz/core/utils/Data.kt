package com.easeplantz.easeplantz.core.utils

import com.easeplantz.easeplantz.R
import com.easeplantz.easeplantz.core.data.source.local.entity.MainEntity

object Data {
    fun mainMenuData(): ArrayList<MainEntity> {
        val menus = ArrayList<MainEntity>()

        menus.add(
            MainEntity(
                id = "1",
                title = "Corn Diseases",
                image = R.drawable.image_corn
            )
        )

        menus.add(
            MainEntity(
                id = "2",
                title = "Tomato Diseases",
                image = R.drawable.image_tomato
            )
        )

        menus.add(
            MainEntity(
                id = "3",
                title = "Potato Diseases",
                image = R.drawable.image_potato
            )
        )

        return menus
    }
}