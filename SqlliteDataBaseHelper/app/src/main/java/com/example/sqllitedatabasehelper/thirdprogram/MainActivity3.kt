package com.example.sqllitedatabasehelper.thirdprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqllitedatabasehelper.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {

    lateinit var main3Binding: ActivityMain3Binding

    lateinit var db:SQliteHelper3



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main3Binding=ActivityMain3Binding.inflate(layoutInflater)
        setContentView(main3Binding.root)

        db= SQliteHelper3(this)
        initview()
    }

    private fun initview() {
        main3Binding.btnSubmit.setOnClickListener {
            val name = main3Binding.edtName.text.toString()
            val mobile = main3Binding.edtMobilNumber.text.toString()
            val address = main3Binding.edtAddress.text.toString()


            db.insertData(name, mobile, address)
        }
        main3Binding.btnDisplay.setOnClickListener{
        var list=    db.DisplayData()


            var adapter=AdapterClass(list)
            var manger=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            main3Binding.recycleView.layoutManager=manger
            main3Binding.recycleView.adapter=adapter
        }
    }
}