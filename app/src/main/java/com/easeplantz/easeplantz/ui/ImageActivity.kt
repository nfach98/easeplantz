package com.easeplantz.easeplantz.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.easeplantz.easeplantz.R
import kotlinx.android.synthetic.main.activity_image.*

class ImageActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_OPTION = "extra_option"
        private const val REQUEST_CODE = 42
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSIONCODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        button_camera.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent,REQUEST_CODE)

            if(cameraIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(cameraIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this,"Unable to open camera", Toast.LENGTH_SHORT).show()
            }
        }

        button_gallery.setOnClickListener {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //permission denied
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)

                    requestPermissions(permissions, PERMISSIONCODE)

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
        val galleryIntent = Intent(Intent.ACTION_PICK)
        intent.type ="image/*"
        startActivityForResult(galleryIntent, IMAGE_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val takenImage = data?.extras?.get("data") as Bitmap
            //imageVIew.setImageBitmap(takenImage)

        }

        if (requestCode == IMAGE_PICK_CODE && resultCode == Activity.RESULT_OK) {
            //imageView.setImageURI(data?.data)

        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSIONCODE -> {
                if (grantResults.size >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
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