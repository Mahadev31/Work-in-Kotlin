package com.example.run_time_permission

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.run_time_permission.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()

    }

    private fun initView() {
        mainBinding.btnStorage.setOnClickListener {
            if (checkPermission()) {

                Toast.makeText(this, "Permission already granted.", Toast.LENGTH_LONG).show()

            } else {

                requestPermission();
            }
        }
        mainBinding.btnCamera.setOnClickListener {
            if (checkPermission()) {

                Toast.makeText(this, "Permission already granted.", Toast.LENGTH_LONG).show()

            } else {

                requestPermission();
            }
        }
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(applicationContext, WRITE_EXTERNAL_STORAGE)
        val result1 = ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE)
        val result2 = ContextCompat.checkSelfPermission(applicationContext, CAMERA)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED && result2 == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE, CAMERA),
            100
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            100 -> if (grantResults.size > 0) {
                val writeExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED
                val camera = grantResults[2] == PackageManager.PERMISSION_GRANTED
                if (writeExternalStorage && readExternalStorage && camera)
                    Toast.makeText(
                        this,
                        "Permission Granted",
                        Toast.LENGTH_LONG
                    ).show()
                else {
                    Toast.makeText(
                        this,
                        "Permission Denied",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
    }
}





