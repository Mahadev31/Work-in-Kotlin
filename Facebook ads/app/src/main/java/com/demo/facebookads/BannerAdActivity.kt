package com.demo.facebookads

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.AdSize
import com.facebook.ads.AdView


class BannerAdActivity : AppCompatActivity() {
    lateinit var adView: AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner_ad)

        initView()
    }

    private fun initView() {

        adView = AdView(this, "IMG_16_9_APP_INSTALL#YOUR_PLACEMENT_ID", AdSize.BANNER_HEIGHT_50)


// Find the Ad Container
        val adContainer = findViewById<LinearLayout>(R.id.banner_container) as LinearLayout

// Add the ad view to your activity layout
        adContainer.addView(adView)

// Request an ad
        adView.loadAd()
    }
}