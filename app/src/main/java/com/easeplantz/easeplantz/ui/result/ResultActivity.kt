package com.easeplantz.easeplantz.ui.result

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.databinding.ActivityResultBinding
import com.easeplantz.easeplantz.ui.main.MainActivity
import com.easeplantz.easeplantz.ui.prediction.PredictionViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ResultActivity : AppCompatActivity() {

    private val viewModel: ResultViewModel by viewModel()
    private lateinit var binding: ActivityResultBinding
    private lateinit var model: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = intent.getStringExtra(MainActivity.EXTRA_MODEL).toString()
        val onClickFinish = View.OnClickListener { finish() }

        binding.home.setOnClickListener(onClickFinish)
        binding.homeNoBg.setOnClickListener(onClickFinish)

       /* viewModel.getPrediction(model, "",false).observe(this, { prediction ->
            if(prediction != null){
                when(prediction){
                    is Resource.Success -> {

                    }
                    else -> {

                    }
                }
            }
        })*/
    }
}