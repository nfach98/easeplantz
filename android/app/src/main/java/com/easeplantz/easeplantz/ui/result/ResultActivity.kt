package com.easeplantz.easeplantz.ui.result

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.domain.model.Prediction
import com.easeplantz.easeplantz.core.utils.Data
import com.easeplantz.easeplantz.databinding.ActivityResultBinding
import com.easeplantz.easeplantz.ui.main.MainActivity
import com.easeplantz.easeplantz.ui.prediction.PredictionViewModel
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.viewModel

class ResultActivity : AppCompatActivity() {

    private val viewModel: ResultViewModel by viewModel()
    private lateinit var binding: ActivityResultBinding
    private lateinit var model: String
    private var prediction: Prediction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = intent.getStringExtra(MainActivity.EXTRA_MODEL).toString()
        prediction = intent.getParcelableExtra(EXTRA_PREDICTION)
        val onClickFinish = View.OnClickListener { finish() }

        binding.home.setOnClickListener(onClickFinish)
        binding.homeNoBg.setOnClickListener(onClickFinish)

        Log.d("bisa", prediction.toString())
//        if(prediction != null){
//            Log.d("bisa", "bisa")
//            val results = Data.mainResultData()
//        }
    }

    companion object {
        const val EXTRA_PREDICTION = "extra_prediction"
    }
}