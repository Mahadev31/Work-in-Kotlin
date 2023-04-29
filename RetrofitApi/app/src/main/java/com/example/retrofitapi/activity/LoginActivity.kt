package com.example.retrofitapi.activity

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitapi.APIClient
import com.example.retrofitapi.APIInterface
import com.example.retrofitapi.LoginResponse
import com.example.retrofitapi.databinding.ActivityLoginBinding
import com.example.retrofitapi.databinding.ProgressBarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding


    lateinit var apiInterface: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        apiInterface = APIClient.getClient().create(APIInterface::class.java)


        initView()
    }

    private fun initView() {


        var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("isLogin", false) == true) {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
//            loginBinding.chkRemember.setChecked(true)
            finish()
        }
        if (loginBinding.chkRemember.isChecked){
            loginBinding.edtUsername.setText(sharedPreferences.getString("userName",""))
            loginBinding.edtPassword.setText(sharedPreferences.getString("password",""))
        }

        loginBinding.btnLogin.setOnClickListener {

            var dialog = Dialog(this)
            var progressBarBinding = ProgressBarBinding.inflate(layoutInflater)
            dialog.setContentView(progressBarBinding.root)

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialog.show()


            var username = loginBinding.edtUsername.text.toString()
            var password = loginBinding.edtPassword.text.toString()

            if (username.isEmpty()) {
                Toast.makeText(this, "Please Enter Username", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
            } else {

                apiInterface.getLogin(username, password).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        if (response.isSuccessful) {
                            var id = response.body()?.id
                            var userNameD = response.body()?.username
                            var email = response.body()?.email
                            var firstName = response.body()?.firstName
                            var lastName = response.body()?.lastName
                            var gender = response.body()?.gender
                            var image = response.body()?.image
                            var token = response.body()?.token

                            Log.e("TAG", "onResponse: " + userNameD + " " + email)

                            var i = Intent(this@LoginActivity, MainActivity::class.java)


                            var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
                            myEdit.putBoolean("isLogin", true)
                            myEdit.putString("userName", userNameD)
                            myEdit.putString("email", email)
                            myEdit.putString("firstName", firstName)
                            myEdit.putString("lastName", lastName)
                            myEdit.putString("gender", gender)
                            myEdit.putString("image", image)
                            myEdit.putString("token", token)
                            myEdit.commit()


                            startActivity(i)
                            finish()

                            if (loginBinding.chkRemember.isChecked) {
                                myEdit.putBoolean("isLogin", true)
                                myEdit.putBoolean("remember", true)
                                myEdit.putString("userName", username)
                                myEdit.putString("password", password)
                                myEdit.commit()
                                Toast.makeText(this@LoginActivity, "remember add", Toast.LENGTH_SHORT).show()
                            }
                            dialog.dismiss()
                            Toast.makeText(
                                this@LoginActivity,
                                "login success",
                                Toast.LENGTH_SHORT
                            ).show()


                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "username and password is incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
                            dialog.dismiss()
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })


            }
        }
    }

    override fun onResume() {
        super.onResume()
//      dialog.dismiss()
    }
}