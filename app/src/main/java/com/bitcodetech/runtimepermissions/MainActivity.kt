package com.bitcodetech.runtimepermissions

import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.bitcodetech.runtimepermissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //not a good place
        /*if(
            checkSelfPermission(android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED
        ) {
            //Request the permission
            requestPermissions(
                arrayOf(android.Manifest.permission.READ_SMS),
                1
            )
        }
        else {
            readSMSes()
        }*/


        binding.btnReadSMS.setOnClickListener {

            /*ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(android.Manifest.permission.READ_SMS),
                1
            )*/

            /*ActivityCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )*/

            if(
                checkSelfPermission(android.Manifest.permission.READ_SMS) == PackageManager.PERMISSION_DENIED
            ) {
                //Request the permission
                requestPermissions(
                    arrayOf(android.Manifest.permission.READ_SMS),
                    1
                )
            }
            else {
                readSMSes()
            }
        }

        binding.btnCaptureImage.setOnClickListener {
            if(
                checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                        checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            ) {
                //click picture using camera and store it on external storage
                captureAndStoreImage()
            }
            else {
                requestPermissions(
                    arrayOf(
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    2
                )
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 1) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                readSMSes()
            }
            else {
                mt("To continue the app needs read sms permissions...")
            }
        }

        if(requestCode == 2) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
            grantResults[1] == PackageManager.PERMISSION_GRANTED
                    ) {
                captureAndStoreImage()
            }
            else {
                mt("Need camera and storage permissions to complete operations...")
            }
        }

    }

    private fun captureAndStoreImage() {
        mt("Image Captured and stored")
    }

    private fun readSMSes() {
        mt("Got the messages");
    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}