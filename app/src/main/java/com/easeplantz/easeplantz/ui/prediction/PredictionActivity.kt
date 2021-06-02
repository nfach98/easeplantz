package com.easeplantz.easeplantz.ui.prediction

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.easeplantz.easeplantz.databinding.ActivityDetectBinding
import com.easeplantz.easeplantz.ui.result.ResultActivity
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel


class PredictionActivity : AppCompatActivity() {

    private val viewModel: PredictionViewModel by viewModel()
    private lateinit var binding: ActivityDetectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getByteArrayExtra("image")
        val uri = intent.data

        if(image != null) {
            val bmp = BitmapFactory.decodeByteArray(image, 0, image.size)
            binding.ivImage.setImageBitmap(bmp)
        }

        if(uri != null){
            Picasso.get().load(uri).fit().centerCrop().into(binding.ivImage)
        }

        binding.home.setOnClickListener { finish() }

        binding.btnDetect.setOnClickListener {
            startActivity(Intent(this@PredictionActivity, ResultActivity::class.java))
            finish()
        }
    }
}