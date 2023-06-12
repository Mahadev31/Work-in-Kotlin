package com.pro.kidsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.pro.kidsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  mainBinding: ActivityMainBinding

     var alphabetList=ArrayList<String>()
     var descriptionList=ArrayList<String>()
     var modelList=ArrayList<ModelClass>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {

        insertdata()


        for (i in 0..10) {  //for lop define
            var model = ModelClass(
                alphabetList[i],
                descriptionList[i]
            )   //create object for  model class

            modelList.add(model) // model class add in array list
        }
        var adapter=AdapterClass(this,modelList)
        var manger=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mainBinding.rcvAlphabet.layoutManager=manger
        mainBinding.rcvAlphabet.adapter=adapter
    }

    private fun insertdata() {
        alphabetList.add("A")
        alphabetList.add("B")
        alphabetList.add("C")
        alphabetList.add("D")
        alphabetList.add("E")
        alphabetList.add("F")
        alphabetList.add("G")
        alphabetList.add("H")
        alphabetList.add("I")
        alphabetList.add("J")
        alphabetList.add("K")
        alphabetList.add("L")
        alphabetList.add("M")
        alphabetList.add("N")
        alphabetList.add("O")
        alphabetList.add("P")
        alphabetList.add("Q")
        alphabetList.add("R")
        alphabetList.add("S")
        alphabetList.add("T")
        alphabetList.add("U")
        alphabetList.add("V")
        alphabetList.add("W")
        alphabetList.add("X")
        alphabetList.add("Y")
        alphabetList.add("Z")



        descriptionList.add("A for Apple")
        descriptionList.add("B for Ball")
        descriptionList.add("C for Cat")
        descriptionList.add("D for Dog")
        descriptionList.add("E for Egg")
        descriptionList.add("F for Fish")
        descriptionList.add("G for Got")
        descriptionList.add("H for Hat")
        descriptionList.add("I for Ice Cream")
        descriptionList.add("J for Joker")
        descriptionList.add("K for King")
        descriptionList.add("L for Lion")
        descriptionList.add("M for Mouse")
        descriptionList.add("N for Nurse")
        descriptionList.add("O for Orange")
        descriptionList.add("P for Parrot")
        descriptionList.add("Q for Quin ")
        descriptionList.add("R for Rat")
        descriptionList.add("S for Sun ")
        descriptionList.add("T for Tea")
        descriptionList.add("U for Umbrella")
        descriptionList.add("V for Van")
        descriptionList.add("W fow Whale ")
        descriptionList.add("X for X Ray")
        descriptionList.add("Y for Yellow")
        descriptionList.add("Z for Zebra")


    }


}

class ModelClass(var text: String,var description: String){


}