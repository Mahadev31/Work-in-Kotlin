package com.demo.resumeusingcomponents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

            email=secondBinding.edtEmail.text.toString()
            password=secondBinding.edtPassword.text.toString()
            confirm_password=secondBinding.edtConfirmPassword.text.toString()

            Log.e("TAG", "email: $email " )
            Log.e("TAG", "password: $password " )
            Log.e("TAG", "confirm_password: $confirm_password " )

            var i =Intent(this,ThirdActivity::class.java)
            startActivity(i)
        }
    }
}