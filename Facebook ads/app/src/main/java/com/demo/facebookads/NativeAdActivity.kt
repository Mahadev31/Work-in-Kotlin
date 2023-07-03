package com.demo.facebookads

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.ads.*


class NativeAdActivity : AppCompatActivity() {

    private val TAG = "NativeAdActivity".javaClass.simpleName
    lateinit var nativeAd: NativeAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_native_ad)

        AdSettings.addTestDevice("4193978b-d4e4-4ad2-9b81-114b60895ecc")

        loadNativeAd()
    }


    private fun loadNativeAd() {
        // Instantiate a NativeAd object.
        // NOTE: the placement ID will eventually identify this as your App, you can ignore it for
        // now, while you are testing and replace it later when you have signed up.
        // While you are using this temporary code you will only get test ads and if you release
        // your code like this to the Google Play your users will not receive ads (you will get a no fill error).
        nativeAd = NativeAd(this, "YOUR_PLACEMENT_ID")
        val nativeAdListener: NativeAdListener = object : NativeAdListener {
            override fun onMediaDownloaded(ad: Ad) {
                // Native ad finished downloading all assets
                Log.e(TAG, "Native ad finished downloading all assets.")
            }

            override fun onError(ad: Ad?, adError: AdError) {
                // Native ad failed to load
                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage())
            }

            override fun onAdLoaded(ad: Ad) {
                // Native ad is loaded and ready to be displayed
                Log.d(TAG, "Native ad is loaded and ready to be displayed!")
            }

            override fun onAdClicked(ad: Ad) {
                // Native ad clicked
                Log.d(TAG, "Native ad clicked!")
            }

            override fun onLoggingImpression(ad: Ad) {
                // Native ad impression
                Log.d(TAG, "Native ad impression logged!")
            }
        }

        // Request an ad
        nativeAd.loadAd(
            nativeAd.buildLoadAdConfig()
                .withAdListener(nativeAdListener)
                .build()
        )
    }
}