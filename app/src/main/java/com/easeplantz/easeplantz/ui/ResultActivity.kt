package com.easeplantz.easeplantz.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.easeplantz.easeplantz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val onClickFinish = View.OnClickListener { finish() }

        binding.home.setOnClickListener(onClickFinish)
        binding.homeNoBg.setOnClickListener(onClickFinish)
    }
}