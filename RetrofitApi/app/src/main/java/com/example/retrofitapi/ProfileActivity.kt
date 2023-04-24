package com.example.retrofitapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.retrofitapi.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    lateinit var profileBinding: ActivityProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding= ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)

        initView()
    }

    private fun initView() {
        profileBinding.imgBackP.setOnClickListener{
          onBackPressed()
        }
        var image=intent.getStringExtra("image")
        var firstName=intent.getStringExtra("firstName")
        var lastName=intent.getStringExtra("lastName")
        var email=intent.getStringExtra("email")
        var username=intent.getStringExtra("userName")
        var gender=intent.getStringExtra("gender")


        Glide.with(this).load("$image").placeholder(R.drawable.ic_image).into(profileBinding.imageView)
        profileBinding.txtFirstName.text=firstName
        profileBinding.txtLastName.text=lastName
        profileBinding.txtEmail.text=email
        profileBinding.txtUserName.text=username
        profileBinding.txtGender.text=gender

    }
}