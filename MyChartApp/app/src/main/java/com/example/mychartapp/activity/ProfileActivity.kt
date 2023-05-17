package com.example.mychartapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mychartapp.databinding.ActivityProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileActivity : AppCompatActivity() {

    lateinit var profileBinding: ActivityProfileBinding

    lateinit var mDbRef: DatabaseReference

    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding=ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)

        initView()
    }

    private fun initView() {
        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()


    }
}