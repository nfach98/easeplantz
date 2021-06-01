package com.easeplantz.easeplantz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.easeplantz.easeplantz.R
import com.easeplantz.easeplantz.databinding.ActivityDetectBinding

class DetectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.home.setOnClickListener { finish() }
    }
}