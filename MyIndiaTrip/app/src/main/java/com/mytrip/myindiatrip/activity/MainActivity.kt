package com.mytrip.myindiatrip.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mytrip.myindiatrip.fragment.SaveFragment
import com.mytrip.myindiatrip.fragment.HomeFragment
import com.mytrip.myindiatrip.fragment.MyTripPlanFragment
import com.mytrip.myindiatrip.fragment.ProfileFragment
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var fragment: Fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initView()
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
}