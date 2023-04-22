package com.example.recycleviewkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.recycleviewkotlin.R
import com.example.recycleviewkotlin.adapterclass.CountryAdapterClass
import com.example.recycleviewkotlin.databinding.ActivityCountryBinding
import com.example.recycleviewkotlin.databinding.ActivityMainBinding
import com.example.recycleviewkotlin.modelclass.CountryModelClass

class CountryActivity : AppCompatActivity() {

    lateinit var countryBinding: ActivityCountryBinding

    var imageList = ArrayList<Int>()
    var nameList = ArrayList<String>()

    var countryList = ArrayList<CountryModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        countryBinding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(countryBinding.root)

        view()
    }

    private fun view() {
        info()

        for (i in 0..9) {
            var model = CountryModelClass(imageList[i], nameList[i])

            countryList.add(model)
        }

        var adapter = CountryAdapterClass(countryList)
        var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        countryBinding.recycleViewCountry.layoutManager = manager
        countryBinding.recycleViewCountry.adapter = adapter
    }

    private fun info() {


        imageList.add(R.drawable.flag_of_india)
        imageList.add(R.drawable.flag_of_australia)
        imageList.add(R.drawable.flag_of_canada)
        imageList.add(R.drawable.flag_of_china)
        imageList.add(R.drawable.flag_of_france)
        imageList.add(R.drawable.flag_of_indonesia)
        imageList.add(R.drawable.flag_of_turkey)
        imageList.add(R.drawable.flag_of_japan)
        imageList.add(R.drawable.flag_of_vietnam)
        imageList.add(R.drawable.flag_of_united_kingdom)

        nameList.add("India ")
        nameList.add("Australia ")
        nameList.add("Canada ")
        nameList.add("China ")
        nameList.add("France")
        nameList.add("Indonesia")
        nameList.add("Turkey")
        nameList.add("Japan")
        nameList.add("Vietnam")
        nameList.add("United Kingdom")
    }
}