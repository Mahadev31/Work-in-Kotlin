package com.demo.googleads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.demo.googleads.databinding.ActivityInterstitialAdBinding
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

class InterstitialAdActivity : AppCompatActivity() {

    lateinit var interstitialAdBinding: ActivityInterstitialAdBinding

    private var mInterstitialAd: InterstitialAd? = null
    private final var TAG = "InterstitialAdActivity"
    lateinit var adRequest: AdRequest
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        interstitialAdBinding = ActivityInterstitialAdBinding.inflate(layoutInflater)
        setContentView(interstitialAdBinding.root)

        MobileAds.initialize(this) {}
        adRequest = AdRequest.Builder().build()

        initView()
    }

    private fun initView() {
        lordAd()
        var clickButton = 0
        interstitialAdBinding.btnDisplayAd.setOnClickListener {

            if (clickButton <= 2) {
                clickButton++
            } else {
                clickButton=0
                displayAd()
                mInterstitialAd?.show(this)
            }
        }
    }

    private fun displayAd() {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.")
            }

            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(TAG, "Ad dismissed fullscreen content.")
                mInterstitialAd = null
                lordAd()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.")
                mInterstitialAd = null
            }

            override fun onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.")
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.")
            }
        }
    }

    private fun lordAd() {
        InterstitialAd.load(
            this,
            "ca-app-pub-3940256099942544/1033173712",
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, adError.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                }
            })
    }


}