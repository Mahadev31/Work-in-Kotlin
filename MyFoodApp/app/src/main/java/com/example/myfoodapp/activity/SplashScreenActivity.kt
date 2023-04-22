package com.example.myfoodapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.myfoodapp.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    lateinit var splashBinding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        view()
    }

    private fun view() {
        val i=Intent(this,MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(i)
            finish()
        },3000)
    }
}