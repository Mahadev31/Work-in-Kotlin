package com.example.imageslider.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.example.imageslider.R
import com.example.imageslider.adapterclass.AdapterClass
import com.example.imageslider.databinding.ActivityMainBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {
    var imageList: ArrayList<Int> = ArrayList()

    lateinit var mainBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        view()
    }

    private fun view() {
        imageList.add(R.drawable.img_1)
        imageList.add(R.drawable.img_2)
        imageList.add(R.drawable.img_3)
        imageList.add(R.drawable.img_4)
        imageList.add(R.drawable.img_5)
        imageList.add(R.drawable.img_6)

        mainBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.e("TAG", "onPageScrolled: " )
            }

            override fun onPageSelected(position: Int) {
                Log.e("TAG", "onPageSelected: " )
                mainBinding.txtCount.text=" ${position+1}/6"
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.e("TAG", "onPageScrollStateChanged: ", )
            }
        })
        val adapter = AdapterClass(imageList)
        mainBinding.viewPager.adapter = adapter

        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)

        dotsIndicator.attachTo(  mainBinding.viewPager)
    }
}