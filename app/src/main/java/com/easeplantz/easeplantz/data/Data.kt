package com.easeplantz.easeplantz.data

object Data {
    fun mainMenuData(): ArrayList<MainEntity> {
        val menus = ArrayList<MainEntity>()

        menus.add(
            MainEntity(
                id = "1",
                title = "Corn Diseases"
            )
        )

        menus.add(
            MainEntity(
                id = "2",
                title = "Tomato Diseases"
            )
        )

        menus.add(
            MainEntity(
                id = "3",
                title = "Potato Diseases"
            )
        )

        return menus
    }
}