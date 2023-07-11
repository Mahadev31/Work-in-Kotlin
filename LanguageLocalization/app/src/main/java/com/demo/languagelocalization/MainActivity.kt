package com.demo.languagelocalization

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.languagelocalization.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    var languageList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {

        languageList.add("English")
        languageList.add("Hindi")
        languageList.add("Gujarati")

        mainBinding.btnLanguage.setOnClickListener {

            var dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_language)

            var adapter = AdapterClass(languageList){it->
                dialog.dismiss()
            }
            var manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

            val rcv_language: RecyclerView = dialog.findViewById(R.id.rcv_language)

            rcv_language.adapter = adapter
            rcv_language.layoutManager = manager

            dialog.window?.setGravity(Gravity.BOTTOM)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
        }
    }
}