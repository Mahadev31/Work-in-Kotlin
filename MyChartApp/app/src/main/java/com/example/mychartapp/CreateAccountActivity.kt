package com.example.mychartapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mychartapp.databinding.ActivityCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CreateAccountActivity : AppCompatActivity() {

    lateinit var  createAccountBinding: ActivityCreateAccountBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createAccountBinding= ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(createAccountBinding.root)

        initView()
    }

    private fun initView() {

        auth = FirebaseAuth.getInstance()
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

                        addUserToDatabase(firstName,email,auth.currentUser?.uid!!)

                        Toast.makeText(this, " Account Create successfully", Toast.LENGTH_SHORT).show()
                        var i = Intent(this, MainActivity::class.java)
                        finish()
                        startActivity(i)
                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }


        }




        createAccountBinding.txtLoginPage.setOnClickListener {
            var i= Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    private fun addUserToDatabase(firstName: String,  email: String, uid: String) {

        mDbRef=FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(UserModelClass(firstName,email, uid))
    }
}