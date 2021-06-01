package com.easeplantz.easeplantz.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easeplantz.easeplantz.databinding.ActivityDetectBinding
import com.squareup.picasso.Picasso


class DetectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener { finish() }

        val image = intent.getByteArrayExtra("image")
        val uri = intent.data

        if(image != null) {
            val bmp = BitmapFactory.decodeByteArray(image, 0, image.size)
            binding.ivImage.setImageBitmap(bmp)
        }

        if(uri != null){
            Picasso.get().load(uri).into(binding.ivImage)
        }
    }
}