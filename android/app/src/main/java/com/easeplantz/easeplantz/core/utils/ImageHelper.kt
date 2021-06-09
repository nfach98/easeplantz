package com.easeplantz.easeplantz.core.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore


object ImageHelper {
    fun getPathFromURI(context: Context, contentUri: Uri?): String? {
        var mediaCursor: Cursor? = null
        return try {
            val dataPath = arrayOf(MediaStore.Images.Media.DATA)
            mediaCursor = contentUri?.let { context.contentResolver.query(it, dataPath, null, null, null) }
            val columnIndex: Int = mediaCursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)!!
            mediaCursor?.moveToFirst()
            mediaCursor?.getString(columnIndex)
        } finally {
            mediaCursor?.close()
        }
    }
}