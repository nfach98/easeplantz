package com.easeplantz.easeplantz.ui.prediction

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.easeplantz.easeplantz.R
import com.easeplantz.easeplantz.core.data.Resource
import com.easeplantz.easeplantz.core.utils.ImageHelper
import com.easeplantz.easeplantz.databinding.ActivityPredictionBinding
import com.easeplantz.easeplantz.ui.main.MainActivity
import com.easeplantz.easeplantz.ui.result.ResultActivity
import com.otaliastudios.cameraview.CameraUtils
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.PictureFormat
import com.squareup.picasso.Picasso
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.zip.Deflater


class PredictionActivity : AppCompatActivity() {

    companion object {
        var pictureResult: PictureResult? = null
    }

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
        val uri = intent.data

        if(uri != null){
            Picasso.get().load(uri).fit().centerCrop().into(binding.ivImage)
            val uriPath = ImageHelper.getPathFromURI(this, uri)
            file = File(uriPath)
        }

        with(binding){
            val loading = ValueAnimator.ofFloat(1f, 1f)
            loading.duration = 300
            loading.repeatCount = ValueAnimator.INFINITE
            loading.repeatMode = ValueAnimator.REVERSE
            loading.addUpdateListener { animation ->
                val scale = animation.animatedValue as Float
                leaves.scaleX = scale
                leaves.scaleY = scale
                leaves.rotation = leaves.rotation + 2
            }

            val idString = when(model){
                "corn" -> R.string.corn
                "tomato" -> R.string.tomato
                "potato" -> R.string.potato
                else -> R.string.corn
            }
            tvPlant.text = String.format(resources.getString(R.string.plant_disease), resources.getString(idString))

            home.setOnClickListener { finish() }

            btnDetect.setOnClickListener {
                val body = RequestBody.create("image/jpg".toMediaTypeOrNull(), file)
                val part = MultipartBody.Part.createFormData("predict-img", file.name, body)

                loading.start()
                layout.transitionToEnd()

                viewModel.getPrediction(model, part, true).observe(this@PredictionActivity, { prediction ->
                    if(prediction != null){
                        when(prediction){
//                        is Resource.Loading -> {
//                            loading.start()
//                            layout.transitionToEnd()
//                        }
                            is Resource.Success -> {
                                val intent = Intent(this@PredictionActivity, ResultActivity::class.java)
                                intent.putExtra(MainActivity.EXTRA_MODEL, model)
                                intent.putExtra(ResultActivity.EXTRA_PREDICTION, prediction.data)
                                startActivity(intent)
                                finish()
                            }
                            else -> {
                                loading.end()
                                layout.transitionToStart()
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

            if(pictureResult != null){
                try {
                    pictureResult?.toBitmap(1000, 1000) { bitmap -> ivImage.setImageBitmap(bitmap) }

                    val extension = when (pictureResult?.format) {
                        PictureFormat.JPEG -> "jpg"
                        PictureFormat.DNG -> "dng"
                        else -> throw RuntimeException("Unknown format.")
                    }
                    file = File(filesDir, "picture.$extension")
                    pictureResult?.data?.let {

                        CameraUtils.writeToFile(it, file) { file ->
                            if (file == null) {
                                Toast.makeText(
                                    this@PredictionActivity,
                                    "Error while writing file.",
                                    Toast.LENGTH_SHORT).show()
                            }

                            Log.d("ukuran", (file?.length().toString().toDouble()/1024).toString())
                        }
                    }
                } catch (e: UnsupportedOperationException) {
                    ivImage.setImageDrawable(ColorDrawable(Color.GREEN))
                    Toast.makeText(this@PredictionActivity, "Can't preview this format: " + pictureResult?.format, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!isChangingConfigurations) {
            pictureResult = null
        }
    }
}