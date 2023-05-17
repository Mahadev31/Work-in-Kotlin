package com.example.mychartapp.activity

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.mychartapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        initView()
    }

    private fun initView() {
        var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLogin", false) == true) {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        loginBinding.txtCreateAccountPage.setOnClickListener {
            var i = Intent(this, CreateAccountActivity::class.java)
            startActivity(i)
        }

// Initialize Firebase Auth
        auth = Firebase.auth
        loginBinding.btnLogin.setOnClickListener {
            var email = loginBinding.edtEmail.text.toString()
            var password = loginBinding.edtPassword.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(this, "email value is empty. please fill email ", Toast.LENGTH_SHORT)
                    .show()
            } else if (password.isEmpty()) {
                Toast.makeText(
                    this,
                    "password value is empty. please fill password ",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "login success", Toast.LENGTH_SHORT).show()
                        var i = Intent(this, MainActivity::class.java)

                        var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                        myEdit.putBoolean("isLogin", true)
                        myEdit.putString("email", email)
//                        myEdit.putString("firstName", it.firstName)
//            myEdit.putString("lastName", lastName)
                        myEdit.commit()
                        finish()
                        startActivity(i)

                    }
                }.addOnFailureListener {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }

        }

        loginBinding.txtForgotPass.setOnClickListener {

            var builder = AlertDialog.Builder(this)
            builder.setTitle("Recover Password")

            var linearLayout = LinearLayout(this)

            var edtEmail = EditText(this)
            edtEmail.setHint("Email")
            edtEmail.inputType=InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

            linearLayout.addView(edtEmail)
            linearLayout.setPadding(10,10,10,10)

            builder.setView(linearLayout)


            builder.setPositiveButton(
                "Recover",
                DialogInterface.OnClickListener() { dialog: DialogInterface, i: Int ->
                    var email = edtEmail.text.toString().trim()
                    beginRecovery(email)
                })
            builder.setNegativeButton(
                "Cansel",
                DialogInterface.OnClickListener() { dialog: DialogInterface, i: Int ->
                    dialog.dismiss()
                })
            builder.create().show()
        }
    }

    private fun beginRecovery(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(this, "Email Sent", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        }

    }
}