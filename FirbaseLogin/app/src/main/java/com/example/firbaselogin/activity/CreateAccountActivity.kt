package com.example.firbaselogin.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firbaselogin.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class CreateAccountActivity : AppCompatActivity() {

    lateinit var  createAccountBinding: ActivityCreateAccountBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAccountBinding= ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(createAccountBinding.root)

        initView()
    }

    private fun initView() {
        var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLogin", false) == true) {
            var intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
            finish()
        }
        auth = Firebase.auth
        createAccountBinding.btnCreateAccount.setOnClickListener {
            var firstName = createAccountBinding.edtFirstC.text.toString()
            var lastName = createAccountBinding.edtLastNameC.text.toString()
            var email = createAccountBinding.edtEmailC.text.toString()
            var password = createAccountBinding.edtPasswordC.text.toString()
            if (firstName.isEmpty()) {
                Toast.makeText(this, "FirstName value is empty. please fill firstName ", Toast.LENGTH_SHORT)
                    .show()
            }else if (lastName.isEmpty()) {
            Toast.makeText(this, "LastName value is empty. please fill LastName ", Toast.LENGTH_SHORT)
                .show()
            } else if (email.isEmpty()) {
                Toast.makeText(this, "email value is empty. please fill email ", Toast.LENGTH_SHORT)
                    .show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "password value is empty. please fill password ", Toast.LENGTH_SHORT)
                    .show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, " Account Create successfully", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
            var i = Intent(this, HomeScreenActivity::class.java)

            var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
            myEdit.putBoolean("isLogin", true)
            myEdit.putString("email", email)
            myEdit.putString("firstName", firstName)
            myEdit.putString("lastName", lastName)
            myEdit.commit()

            startActivity(i)

        }




        createAccountBinding.txtLoginPage.setOnClickListener {
            var i= Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}