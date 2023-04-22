package com.example.sqllitedatabasehelper.firstprogram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqllitedatabasehelper.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var db = SqliteHelper(this)
        db .info()
    }
}