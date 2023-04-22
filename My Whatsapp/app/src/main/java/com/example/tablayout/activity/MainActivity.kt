package com.example.tablayout.activity

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.tablayout.R
import com.example.tablayout.adapterclass.TabAdapterClass
import com.example.tablayout.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener


class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)




        initView()
    }

    private fun initView() {
        mainBinding.tabLayout.addTab(mainBinding.tabLayout.newTab().setText("Chats"))  //tabLayout
        mainBinding.tabLayout.addTab(mainBinding.tabLayout.newTab().setText("Status"))//tabLayout
        mainBinding.tabLayout.addTab(mainBinding.tabLayout.newTab().setText("Calls"))//tabLayout

        val adapter = TabAdapterClass(supportFragmentManager, mainBinding.tabLayout.tabCount)//create object in adapter class
        mainBinding.viewPager.adapter = adapter

        mainBinding.viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(mainBinding.tabLayout)) //change tab on click

        mainBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener { //change tab with view pager
            override fun onTabSelected(tab: TabLayout.Tab) {
                mainBinding.viewPager.currentItem = tab.position


            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

        mainBinding.dayNight.setOnClickListener {
            if (mainBinding.dayNight.isChecked) {
                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate
                            .MODE_NIGHT_NO
                    );
            } else {
                AppCompatDelegate
                    .setDefaultNightMode(
                        AppCompatDelegate
                            .MODE_NIGHT_YES
                    );
            }
        }
        setSupportActionBar(mainBinding.toolbar)  //toolbar set


        getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        mainBinding.toolbar.overflowIcon?.setTint(Color.WHITE)  //option manu 3 dot's color change
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean { //create option menu
        menuInflater.inflate(R.menu.optionitem, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i = item.itemId
        return when (i) {
            R.id.home -> {
                Toast.makeText(this, "Home ", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.broadcast -> {
                Toast.makeText(this, "New broadcast ", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.device -> {
                Toast.makeText(this, "Linked device ", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.messages -> {
                Toast.makeText(this, "Starred messages ", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.payment -> {
                Toast.makeText(this, "Payment ", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.setting -> {
                Toast.makeText(this, "Setting ", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}