package com.example.recycleviewkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewkotlin.ClickListener
import com.example.recycleviewkotlin.adapterclass.AdapterCalss
import com.example.recycleviewkotlin.databinding.ActivityMainBinding
import com.example.recycleviewkotlin.modelclass.ModelClass

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    var list: ArrayList<ModelClass> = ArrayList()
    var idList: ArrayList<Int> = ArrayList()
    var nameList: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        view()
    }

    private fun view() {
        info()
        for (i in 0..4) {
            var model = ModelClass( idList[i], nameList[i])

            list.add(model)
        }
//
//        var listener:ClickListener= object : ClickListener {
//            override fun onClick(id: Int, name: String) {
//                Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
//            }
//        }



        var adapter = AdapterCalss(list,listener= object : ClickListener {
            override fun onClick(id: Int, name: String) {
                Toast.makeText(this@MainActivity, "Click", Toast.LENGTH_SHORT).show()
            }
        })
        var layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mainBinding.recycleView.layoutManager=layoutManager
        mainBinding.recycleView.adapter=adapter

    }

    private fun info() {
        idList.add(1)
        idList.add(2)
        idList.add(3)
        idList.add(4)
        idList.add(5)


        nameList.add("Sanjay")
        nameList.add("Hemanshu")
        nameList.add("Raj")
        nameList.add("Vijay")
        nameList.add("Jay")
    }
}