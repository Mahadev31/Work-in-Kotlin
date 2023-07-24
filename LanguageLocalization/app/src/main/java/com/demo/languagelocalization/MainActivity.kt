package com.demo.languagelocalization

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.languagelocalization.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    var languageList = ArrayList<String>()
    var language = ""
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        sharedPreferences = getSharedPreferences("mySharePref", MODE_PRIVATE)
//        if (sharedPreferences.getBoolean("language", false) == true) {
//            updateResources("hi")
//            var i = Intent(this, MainActivity::class.java)
//            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//            startActivity(i)
//            finish()
//        }
        initView()
    }

    fun updateResources(code: String) {
        val locale = Locale(code)
        Locale.setDefault(locale)
        val resource: Resources = resources
        val config: Configuration = resource.configuration
        config.setLocale(locale)
        resource.updateConfiguration(config, resource.displayMetrics)
    }

    private fun initView() {

        languageList.add("English")
        languageList.add("Hindi")
        languageList.add("Gujarati")

        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
        mainBinding.btnLanguage.setOnClickListener {

            var dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_language)

            var adapter = AdapterClass(languageList) { it ->
                if (it.equals("English")) {
                    language = "en"
                    updateResources("en")
                    dialog.dismiss()
                    myEdit.putBoolean("language", true)
                    myEdit.putString("en", language)
                    myEdit.commit()


                    var i = Intent(this, MainActivity::class.java)
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(i)
                }
                if (it.equals("Hindi")) {
                    language = "hi"
                    updateResources("hi")
                    dialog.dismiss()
                    myEdit.putBoolean("language", true)
                    myEdit.putString("hi", language)
                    myEdit.commit()


                    var i = Intent(this, MainActivity::class.java)
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(i)
                }
                if (it.equals("Gujarati")) {
                    language = "gu"
                    updateResources("gu")
                    dialog.dismiss()
                    myEdit.putBoolean("language", true)
                    myEdit.putString("gu", language)
                    myEdit.commit()

                    var i = Intent(this, MainActivity::class.java)
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(i)
                }
                Log.e("TAG", "initView: " + it)

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