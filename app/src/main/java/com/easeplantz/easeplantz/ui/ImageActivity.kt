package com.easeplantz.easeplantz.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easeplantz.easeplantz.databinding.ActivityImageBinding
import com.otaliastudios.cameraview.CameraListener
import com.otaliastudios.cameraview.PictureResult
import com.otaliastudios.cameraview.gesture.Gesture
import com.otaliastudios.cameraview.gesture.GestureAction
import java.io.ByteArrayOutputStream


class ImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageBinding

    companion object {
        const val EXTRA_OPTION = "extra_option"
        private const val REQUEST_CODE = 42
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISSION_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.camera.setLifecycleOwner(this)
        binding.camera.mapGesture(Gesture.PINCH, GestureAction.ZOOM)
        binding.camera.mapGesture(Gesture.TAP, GestureAction.AUTO_FOCUS)
        binding.camera.addCameraListener(object : CameraListener() {
            override fun onPictureTaken(result: PictureResult) {
                super.onPictureTaken(result)
                val intent = Intent(this@ImageActivity, DetectActivity::class.java)
                intent.putExtra("image", result.data)
                startActivity(intent)
            }
        })

        binding.home.setOnClickListener { finish() }

        binding.btnCamera.setOnClickListener {
            binding.camera.takePicture()
        }

        binding.btnGallery.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //permission denied
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                }
                else {
                    //permission already granted
                    pickImageFromGallery()
                }
            }
            else {
                //system OS is < Marshmallow
                pickImageFromGallery()
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

        /*if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.extras?.get("data") as Bitmap
            val intent = Intent(this@ImageActivity, DetectActivity::class.java)
            intent.putExtra("image", takenImage)
            startActivity(intent)
        }*/

        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.data
            val intent = Intent(this@ImageActivity, DetectActivity::class.java)
            intent.data = takenImage
            startActivity(intent)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_CODE -> {
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
}