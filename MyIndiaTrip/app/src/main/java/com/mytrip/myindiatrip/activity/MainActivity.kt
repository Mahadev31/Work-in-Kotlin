package com.mytrip.myindiatrip.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.databinding.ActivityMainBinding
import com.mytrip.myindiatrip.fragment.HomeFragment
import com.mytrip.myindiatrip.fragment.MyTripPlanFragment
import com.mytrip.myindiatrip.fragment.ProfileFragment
import com.mytrip.myindiatrip.fragment.SaveFragment
import org.imaginativeworld.oopsnointernet.ConnectionCallback
import org.imaginativeworld.oopsnointernet.NoInternetDialog
import org.imaginativeworld.oopsnointernet.NoInternetSnackbar


open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var fragment: Fragment

    // No Internet Dialog
    private var noInternetDialog: NoInternetDialog? = null

    // No Internet Snackbar
    private var noInternetSnackbar: NoInternetSnackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    override fun onResume() {
        super.onResume()

        // No Internet Dialog
        noInternetDialog = NoInternetDialog.Builder(this)
            .apply {
                connectionCallback = object : ConnectionCallback { // Optional
                    override fun hasActiveConnection(hasActiveConnection: Boolean) {
                        // ...
                    }
                }
                cancelable = false // Optional
                noInternetConnectionTitle = "No Internet" // Optional
                noInternetConnectionMessage =
                    "Check your Internet connection and try again." // Optional
                showInternetOnButtons = true // Optional
                pleaseTurnOnText = "Please turn on" // Optional
                wifiOnButtonText = "Wifi" // Optional
                mobileDataOnButtonText = "Mobile data" // Optional

                onAirplaneModeTitle = "No Internet" // Optional
                onAirplaneModeMessage = "You have turned on the airplane mode." // Optional
                pleaseTurnOffText = "Please turn off" // Optional
                airplaneModeOffButtonText = "Airplane mode" // Optional
                showAirplaneModeOffButtons = true // Optional
            }
            .build()

        // No Internet Snackbar
        noInternetSnackbar =
            NoInternetSnackbar.Builder(this, findViewById(android.R.id.content))
                .apply {
                    connectionCallback = object : ConnectionCallback { // Optional
                        override fun hasActiveConnection(hasActiveConnection: Boolean) {
                            // ...
                        }
                    }
                    initView()
                    indefinite = true // Optional
                    noInternetConnectionMessage = "No active Internet connection!" // Optional
                    onAirplaneModeMessage = "You have turned on the airplane mode!" // Optional
                    snackbarActionText = "Settings" // Optional
                    showActionToDismiss = false // Optional
                    snackbarDismissActionText = "OK" // Optional
                }
                .build()
    }

    override fun onPause() {
        super.onPause()

        // No Internet Dialog
        noInternetDialog?.destroy()

        // No Internet Snackbar
        noInternetSnackbar?.destroy()
    }

    private fun initView() {


        binding.chipNavigation.setItemSelected(R.id.home_bottom)
        supportFragmentManager.beginTransaction().replace(R.id.container, HomeFragment())
            .commit()
        binding.chipNavigation.setOnItemSelectedListener{

                when (it) {
                    R.id.home_bottom -> {
                        fragment = HomeFragment()

                        callFragment(fragment)
                    }  R.id.myTrip_bottom -> {
                    fragment = MyTripPlanFragment()
                    callFragment(fragment)
                }
                    R.id.save_bottom -> {
                        fragment = SaveFragment()
                        callFragment(fragment)
                    }
                    R.id.profile_bottom -> {
                        fragment = ProfileFragment()
                        callFragment(fragment)
                    }
                }

            }


    }

    //loading the another fragment in viewPager
    private fun callFragment(fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = manager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
    private var doubleBackToExitPressedOnce: Boolean=false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


}