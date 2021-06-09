package com.easeplantz.easeplantz.ui.prediction

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.easeplantz.easeplantz.R
import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.databinding.ActivityPredictionBinding
import com.easeplantz.easeplantz.ui.main.MainActivity
import com.easeplantz.easeplantz.ui.result.ResultActivity
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream


class PredictionActivity : AppCompatActivity() {

    private val viewModel: PredictionViewModel by viewModel()
    private lateinit var binding: ActivityPredictionBinding
    private lateinit var model: String
    private lateinit var file: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictionBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val background = ResourcesCompat.getDrawable(resources, R.drawable.bg_gradient_green_blue, null)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ResourcesCompat.getColor(resources, android.R.color.transparent, null)
            window.setBackgroundDrawable(background)
        }
        setContentView(binding.root)

        model = intent.getStringExtra(MainActivity.EXTRA_MODEL).toString()
        val image = intent.getByteArrayExtra("image")
        val uri = intent.data

        if(image != null) {
            val bmp = BitmapFactory.decodeByteArray(image, 0, image.size)
            binding.ivImage.setImageBitmap(bmp)

            file = File(cacheDir, "$model.jpg")
            file.createNewFile()
            val fos = FileOutputStream(file)
            fos.write(image)
            fos.flush()
            fos.close()
        }

        if(uri != null){
            Picasso.get().load(uri).fit().centerCrop().into(binding.ivImage)

            file = File(uri.path)
        }

        binding.home.setOnClickListener { finish() }

        binding.btnDetect.setOnClickListener {
            val body = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val part = MultipartBody.Part.createFormData("predict-img", file.name, body)

            binding.loading.start()
            binding.layout.transitionToEnd()

            viewModel.getPrediction(model, part, true).observe(this, { prediction ->
                if(prediction != null){
                    when(prediction){
//                        is Resource.Loading -> {
//                            binding.loading.start()
//                            binding.layout.transitionToEnd()
//                        }
                        is Resource.Success -> {
                            val intent = Intent(this@PredictionActivity, ResultActivity::class.java)
                            intent.putExtra(MainActivity.EXTRA_MODEL, model)
                            intent.putExtra(ResultActivity.EXTRA_PREDICTION, prediction.data)
                            startActivity(intent)
                            finish()
                        }
                        else -> {
                            binding.loading.stop()
                            binding.layout.transitionToStart()
                            Toast.makeText(
                                this@PredictionActivity,
                                "Failed request. Please try again",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
    }
}