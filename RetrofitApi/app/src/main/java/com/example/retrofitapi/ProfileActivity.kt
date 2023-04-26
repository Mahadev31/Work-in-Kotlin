package com.example.retrofitapi

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.retrofitapi.databinding.ActivityProfileBinding


class ProfileActivity : AppCompatActivity() {
    lateinit var profileBinding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)

        initView()
    }

    private fun initView() {
        var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)
        profileBinding.btnLogout.setOnClickListener {
            var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
            myEdit.remove("isLogin")
            myEdit.commit()
            var intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
        }
        profileBinding.imgBackP.setOnClickListener {
            onBackPressed()
        }

        var image = sharedPreferences.getString("image", "")

        Glide.with(this).load("$image").placeholder(R.drawable.ic_image)
            .into(profileBinding.imageView)
        profileBinding.txtUserName.text = sharedPreferences.getString("userName", "")
        profileBinding.txtFirstName.text = sharedPreferences.getString("firstName", "")
        profileBinding.txtLastName.text = sharedPreferences.getString("lastName", "")
        profileBinding.txtEmail.text = sharedPreferences.getString("email", "")
        profileBinding.txtGender.text = sharedPreferences.getString("gender", "")
    }
}