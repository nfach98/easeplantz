package com.easeplantz.easeplantz.ui.result

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.easeplantz.easeplantz.R
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val background = ResourcesCompat.getDrawable(resources, R.drawable.bg_gradient_green_blue, null)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ResourcesCompat.getColor(resources, android.R.color.transparent, null)
            window.setBackgroundDrawable(background)
        }
        setContentView(binding.root)

        model = intent.getStringExtra(MainActivity.EXTRA_MODEL).toString()
        prediction = intent.getParcelableExtra(EXTRA_PREDICTION)
        val onClickFinish = View.OnClickListener { finish() }

        binding.home.setOnClickListener(onClickFinish)
        binding.homeNoBg.setOnClickListener(onClickFinish)

        Log.d("bisa", prediction.toString())
        if(prediction != null){
            val result = Data(this).mainResultData().filter {
                it.disease.equals(prediction?.disease, ignoreCase = true)
            }

            with(binding){
                Picasso.get().load(prediction!!.url).into(ivDetect)
                tvName.text = result[0].disease
                tvTitle.text = result[0].disease
                tvOverview.text = result[0].description
            }
        }
    }

    companion object {
        const val EXTRA_PREDICTION = "extra_prediction"
    }
}