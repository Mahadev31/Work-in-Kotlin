package com.pro.kidsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pro.kidsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  mainBinding: ActivityMainBinding

     var list=ArrayList<String>()
     var modelList=ArrayList<ModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {
        list.add("A")
        list.add("B")
        list.add("C")
        list.add("D")
        list.add("E")
        list.add("F")
        list.add("G")
        list.add("H")
        list.add("I")
        list.add("J")
        list.add("K")

        for (i in 0..10) {  //for lop define
            var model = ModelClass(
                list[i]
            )   //create object for  model class

            modelList.add(model) // model class add in array list
        }
        var adapter=AdapterClass(this,modelList)
        var manger=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mainBinding.rcvAlphabet.layoutManager=manger
        mainBinding.rcvAlphabet.adapter=adapter
    }


}

class ModelClass( var text:String){


}