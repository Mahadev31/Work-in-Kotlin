package com.demo.googleads

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.googleads.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {
        mainBinding.btnBannerAd.setOnClickListener {
            var i=Intent(this,BannerAdActivity::class.java)
            startActivity(i)
        }
        mainBinding.btnInterstitialAd.setOnClickListener {
            var i=Intent(this,InterstitialAdActivity::class.java)
            startActivity(i)
        }
    }
}