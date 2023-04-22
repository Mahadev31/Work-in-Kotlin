package com.example.recycleviewkotlin.activity

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recycleviewkotlin.R
import com.example.recycleviewkotlin.adapterclass.CodeCountryAdapterClass
import com.example.recycleviewkotlin.databinding.ActivityCodeCountryBinding
import com.example.recycleviewkotlin.modelclass.CodeCountryModel

class CodeCountryActivity : AppCompatActivity() {


    lateinit var codeCountryBinding: ActivityCodeCountryBinding   //Activity Biding

    var codeList = ArrayList<Int>()     //Integer Array List define
    var imageList = ArrayList<Int>()     //Integer Array List define
    var nameList = ArrayList<String>()   //String Array List define

    var countryList = ArrayList<CodeCountryModel>()  //ModelClass  Array List define

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        codeCountryBinding = ActivityCodeCountryBinding.inflate(layoutInflater)   // Biding set
        setContentView(codeCountryBinding.root)

        view()
    }

    private fun view() {
        info()  //function define
        for (i in 0..9) {  //for lop define
            var model = CodeCountryModel(
                imageList[i],
                codeList[i],
                nameList[i]
            )   //create object for  model class

            countryList.add(model) // model class add in array list
        }


//      var  listener = object : CodeClickListener {
//            override fun onClick(img: Int, code: Int, name: String) {
//
//                Toast.makeText(this@CodeCountryActivity, "Code :-  " + code +"," +" Name:- " + name, Toast.LENGTH_SHORT).show()
//            }
//        }

        //create object for  Adapter class
        val adapterclass =
            CodeCountryAdapterClass(countryList) { img, code, name ->  //add model class array list & add invoke methode
                Toast.makeText(this, "click " + name, Toast.LENGTH_SHORT).show()

                val dialog = Dialog(this)  //create Dialog Box
                dialog.setContentView(R.layout.dialoge_country)  //set dialog box xml file

                val imageView: ImageView = dialog.findViewById(R.id.image)
                imageView.setImageResource(img)


                val countryCode: TextView = dialog.findViewById(R.id.txtCode)
                countryCode.text = code.toString()

                val countryName: TextView = dialog.findViewById(R.id.txtName)
                countryName.text = name

                dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))   //dialog box TRANSPARENT
                dialog.window?.setLayout(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )

                dialog.show()
            }

        var laymanager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false) //layout manager define
        codeCountryBinding.recycleView.layoutManager =
            laymanager  //layout manager set in recycle view
        codeCountryBinding.recycleView.adapter = adapterclass       //adapter set in recycle view
    }

    private fun info() { //Function Create

//Add value in array list
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

//Add value in array list
        codeList.add(91)//india
        codeList.add(61)//Australia
        codeList.add(1)//Canada
        codeList.add(86)//China
        codeList.add(33)//France
        codeList.add(62)//Indonesia
        codeList.add(90)//Turkey
        codeList.add(81)//Japan
        codeList.add(44)//Vietnam
        codeList.add(44)//United Kingdom

//Add value in array list
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