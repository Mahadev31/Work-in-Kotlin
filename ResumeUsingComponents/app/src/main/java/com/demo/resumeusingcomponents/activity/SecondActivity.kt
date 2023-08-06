package com.demo.resumeusingcomponents.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.demo.resumeusingcomponents.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var secondBinding: ActivitySecondBinding

    companion object{
        var email:String?=null
        var password:String?=null
        var confirm_password:String?=null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        secondBinding=ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)

        initView()
    }

    private fun initView() {
        secondBinding.btnNext.setOnClickListener {

            email =secondBinding.edtEmail.text.toString()
            password =secondBinding.edtPassword.text.toString()
            confirm_password =secondBinding.edtConfirmPassword.text.toString()

            if (email!!.isEmpty()) {
                Toast.makeText(this, "Email  is Empty", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show()

            } else if (password!!.isEmpty()) {
                Toast.makeText(this, "Password is Empty", Toast.LENGTH_SHORT).show()
            } else if (password!!.length < 8) {
                Toast.makeText(this, "Minimum 8 Character Password", Toast.LENGTH_SHORT).show()

            } else if (!password!!.matches(".*[A-Z].*".toRegex())) {

                Toast.makeText(this, "Must Contain 1 Upper-case Character", Toast.LENGTH_SHORT).show()

            } else if (!password!!.matches(".*[a-z].*".toRegex())) {
                Toast.makeText(this, "Must Contain 1 Lower-case Character", Toast.LENGTH_SHORT).show()

            } else if (!password!!.matches(".*[@#\$%^&+=].*".toRegex())) {
                Toast.makeText(
                    this,
                    "Must Contain 1 Special Character (@#\$%^&+=)",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (confirm_password!!.isEmpty()) {
                Toast.makeText(this, "Confirm Password is Empty", Toast.LENGTH_SHORT).show()
            }else if (!confirm_password!!.equals(password)) {
                Toast.makeText(this, "Confirm Password Do not Match", Toast.LENGTH_SHORT).show()
            }else {
                var i = Intent(this, ThirdActivity::class.java)
                startActivity(i)
            }
        }
    }
}