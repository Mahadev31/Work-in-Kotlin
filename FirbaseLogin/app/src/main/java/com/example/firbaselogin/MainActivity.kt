package com.example.firbaselogin

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firbaselogin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {
//
        var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)
//        if (sharedPreferences.getBoolean("isLogin", false) == true) {
//            var intent = Intent(this, HomeScreenActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
// Initialize Firebase Auth
        auth = Firebase.auth
        mainBinding.btnLogin.setOnClickListener {
            var email = mainBinding.edtEmail.text.toString()
            var password = mainBinding.edtPassword.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "email value is empty. please fill email ", Toast.LENGTH_SHORT)
                    .show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "password value is empty. please fill password ", Toast.LENGTH_SHORT)
                    .show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
                        var i = Intent(this, HomeScreenActivity::class.java)

                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
                        myEdit.putString("email", email)
//                        myEdit.putString("firstName", it.firstName)
//            myEdit.putString("lastName", lastName)
                        myEdit.commit()

                        startActivity(i)

                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        }
        mainBinding.txtCreateAccountPage.setOnClickListener {
            var i=Intent(this,CreateAccountActivity::class.java)
            startActivity(i)
        }
    }
}