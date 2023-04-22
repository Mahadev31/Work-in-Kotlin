package com.example.myfoodapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myfoodapp.R
import com.example.myfoodapp.adapterclass.IntroAdapterClass
import com.example.myfoodapp.databinding.ActivityIntroBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class IntroActivity : AppCompatActivity() {
    var imageList: ArrayList<Int> = ArrayList()
    lateinit var intro1Binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intro1Binding= ActivityIntroBinding.inflate(layoutInflater)
        setContentView(intro1Binding.root)

        view()
    }

    private fun view() {
//        intro1Binding.btnNext.setOnClickListener{
//            val i= Intent(this,Intro_2Activity::class.java)
//            startActivity(i)
//        }
//        intro1Binding.btnSkip.setOnClickListener{
//            val i= Intent(this,MainActivity::class.java)
//            startActivity(i)
//        }
//private fun view() {
    imageList.add(R.drawable.intro_1)
    imageList.add(R.drawable.intro_2)


    val adapter = IntroAdapterClass(imageList)
    intro1Binding.viewIntro.adapter = adapter

    val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)

    dotsIndicator.attachTo(   intro1Binding.viewIntro)


    }
}