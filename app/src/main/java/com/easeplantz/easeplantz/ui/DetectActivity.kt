package com.easeplantz.easeplantz.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easeplantz.easeplantz.databinding.ActivityDetectBinding


class DetectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener { finish() }

        val image = intent.getByteArrayExtra("image")

        val bmp = image?.let { BitmapFactory.decodeByteArray(image, 0, it.size) }
        with(binding.ivImage){
            setImageBitmap(bmp)
        }
    }

    override fun onStart() {
        super.onStart()

    }
}