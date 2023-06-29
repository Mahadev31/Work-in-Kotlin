package com.demo.billingdemo.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.demo.billingdemo.R
import com.demo.billingdemo.databinding.ActivityMainBinding
import com.demo.billingdemo.fragment.HomeFragment
import com.demo.billingdemo.fragment.InvoiceFragment
import com.demo.billingdemo.fragment.MenuFragment

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {

        setCurrentFragment(HomeFragment())
        mainBinding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.nav_home -> {
                    setCurrentFragment(HomeFragment())
                }
                R.id.nav_invoice -> {
                    setCurrentFragment(InvoiceFragment())
                }
                R.id.nav_menu -> {
                    setCurrentFragment(MenuFragment())
                }

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameView, fragment)
            commit()
        }


    override fun onBackPressed() {
        val fm: android.app.FragmentManager? = fragmentManager
        if (fm!!.backStackEntryCount > 0) {
            Log.i("MainActivity", "popping backstack")
            fm.popBackStack()
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super")

            super.onBackPressed()
            return

        }
    }


}
