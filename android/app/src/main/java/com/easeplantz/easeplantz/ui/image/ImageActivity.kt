package com.easeplantz.easeplantz.ui.image

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.easeplantz.easeplantz.R
import com.easeplantz.easeplantz.databinding.ActivityImageBinding
import com.easeplantz.easeplantz.ui.main.MainActivity
import com.easeplantz.easeplantz.ui.prediction.PredictionActivity
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.controls.Flash
import com.otaliastudios.cameraview.gesture.Gesture
import com.otaliastudios.cameraview.gesture.GestureAction


class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding
    private lateinit var model: String
    private var flashMode = 0

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CAMERA = 1001
        private const val PERMISSION_GALLERY = 1002
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val background = ResourcesCompat.getDrawable(resources, R.drawable.bg_gradient_green_blue, null)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ResourcesCompat.getColor(resources, android.R.color.transparent, null)
            window.setBackgroundDrawable(background)
        }
        setContentView(binding.root)

        model = intent.getStringExtra(MainActivity.EXTRA_MODEL).toString()

        with(binding){
            val idString = when(model){
                "corn" -> R.string.corn
                "tomato" -> R.string.tomato
                "potato" -> R.string.potato
                else -> R.string.corn
            }
            tvPlant.text = String.format(resources.getString(R.string.plant_disease), resources.getString(idString))

            camera.setLifecycleOwner(this@ImageActivity)
            camera.mapGesture(Gesture.PINCH, GestureAction.ZOOM)
            camera.mapGesture(Gesture.TAP, GestureAction.AUTO_FOCUS)
            camera.addCameraListener(object : CameraListener() {
                override fun onPictureTaken(result: PictureResult) {
                    super.onPictureTaken(result)
                    Log.d("camerane", "iso")
                    val intent = Intent(this@ImageActivity, PredictionActivity::class.java)
                    intent.putExtra("image", result.data)
                    intent.putExtra(MainActivity.EXTRA_MODEL, model)
                    startActivity(intent)
                    /*try{
                        val intent = Intent(this@ImageActivity, PredictionActivity::class.java)
                        intent.putExtra("image", result.data)
                        intent.putExtra(MainActivity.EXTRA_MODEL, model)
                        startActivity(intent)
                    }
                    catch (e:Throwable){
                        e.printStackTrace()
                    }*/
                }
            })

            home.setOnClickListener { finish() }

            btnFlash.setOnClickListener {
                if(flashMode < 2) flashMode++
                else flashMode = 0
                setFlash(flashMode)
            }

            btnCamera.setOnClickListener {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                        val permissions = arrayOf(Manifest.permission.CAMERA)
                        requestPermissions(permissions, PERMISSION_CAMERA)
                    }
                    else {
                        camera.takePicture()
                    }
                }
                else{
                    camera.takePicture()
                }
            }

            btnGallery.setOnClickListener {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                        requestPermissions(permissions, PERMISSION_GALLERY)
                    }
                    else {
                        pickImageFromGallery()
                    }
                }
                else {
                    pickImageFromGallery()
                }
            }
        }
    }

    private fun pickImageFromGallery() {
        val galleryIntent = Intent()
        galleryIntent.type ="image/*"
        galleryIntent.action = Intent.ACTION_PICK
        startActivityForResult(galleryIntent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.data
            val intent = Intent(this@ImageActivity, PredictionActivity::class.java)
            intent.data = takenImage
            intent.putExtra(MainActivity.EXTRA_MODEL, model)
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    binding.camera.takePicture()
                }
                else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
            PERMISSION_GALLERY -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery()
                }
                else{
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun setFlash(mode: Int){
        when(mode){
            0 -> {
                binding.btnFlash.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_flash_off_24, null))
                binding.camera.flash = Flash.OFF
            }
            1 -> {
                binding.btnFlash.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_flash_on_24, null))
                binding.camera.flash = Flash.ON
            }
            2 -> {
                binding.btnFlash.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_baseline_flash_auto_24, null))
                binding.camera.flash = Flash.AUTO
            }
        }
    }
}