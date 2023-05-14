package com.example.mapproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mapproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {
        mainBinding.btnLetLon.setOnClickListener{
            var i=Intent(this,MapsActivity::class.java)
            startActivity(i)
        }

        mainBinding.btnAddressSearch.setOnClickListener{
            var search=Intent(this,SearchMapsActivity::class.java)
            startActivity(search)
        }
        mainBinding.btnDrawingRoute.setOnClickListener{
            var route=Intent(this,RouteMapsActivity::class.java)
            startActivity(route)
        }
    }
}