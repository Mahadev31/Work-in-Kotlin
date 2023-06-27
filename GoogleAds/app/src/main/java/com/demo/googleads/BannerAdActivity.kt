package com.demo.googleads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.googleads.databinding.ActivityBannerAdBinding
import com.demo.googleads.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds

class BannerAdActivity : AppCompatActivity() {
    lateinit var  bannerAdBinding: ActivityBannerAdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bannerAdBinding= ActivityBannerAdBinding.inflate(layoutInflater)
        setContentView(bannerAdBinding.root)

        MobileAds.initialize(this) {}

        initView()
    }

    private fun initView() {

        val adRequest = AdRequest.Builder().build()
        bannerAdBinding.adView.loadAd(adRequest)

        bannerAdBinding.adView.adListener = object : AdListener() {
            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                // Code to be executed when an ad request fails.
            }

            override fun onAdImpression() {
                // Code to be executed when an impression is recorded
                // for an ad.
            }

            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
        }
    }
}