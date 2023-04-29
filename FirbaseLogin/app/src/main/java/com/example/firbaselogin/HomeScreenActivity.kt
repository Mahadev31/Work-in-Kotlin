package com.example.firbaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firbaselogin.databinding.ActivityHomeScreenBinding

class HomeScreenActivity : AppCompatActivity() {

    lateinit var homeScreenBinding: ActivityHomeScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeScreenBinding=ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(homeScreenBinding.root)

        initView()
    }

    private fun initView() {
        var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)

//        profileBinding.txtUserName.text = sharedPreferences.getString("userName", "")
        homeScreenBinding.txtFirstName.text = sharedPreferences.getString("firstName", "")
        homeScreenBinding.txtLastName.text = sharedPreferences.getString("lastName", "")
//        profileBinding.txtEmail.text = sharedPreferences.getString("email", "")
//        profileBinding.txtGender.text = sharedPreferences.getString("gender", "")
    }
}