package com.example.sqllitedatabasehelper.secondprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqllitedatabasehelper.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    lateinit var  main2Binding: ActivityMain2Binding


    lateinit var db:SqLiteHelper2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        main2Binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(main2Binding.root)

        db = SqLiteHelper2(this)

        initView()
    }

    private fun initView() {
        main2Binding.btnSubmit.setOnClickListener {
            val name = main2Binding.edtName.text.toString()
            val mobil = main2Binding.edtMobilNumber.text.toString()


            db.insertRecord(name, mobil)
        }
        main2Binding.btnDisplay.setOnClickListener{
            db.displayRecord()
        }
    }
}