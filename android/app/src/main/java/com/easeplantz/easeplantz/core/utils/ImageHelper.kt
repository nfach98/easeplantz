package com.easeplantz.easeplantz.core.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


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

    @Throws(IOException::class)
    fun getBytes(inputStream: InputStream): ByteArray? {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)
        var len = 0
        while (inputStream.read(buffer).also { len = it } != -1) {
            byteBuffer.write(buffer, 0, len)
        }
        return byteBuffer.toByteArray()
    }
}