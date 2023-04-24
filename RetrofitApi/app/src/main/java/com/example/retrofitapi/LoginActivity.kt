package com.example.retrofitapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.retrofitapi.databinding.ActivityLoginBinding
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
        loginBinding.btnLogin.setOnClickListener {
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
                            Handler().postDelayed(Runnable {
                                var i = Intent(this@LoginActivity, ProfileActivity::class.java)
                                i.putExtra("id", id)
                                i.putExtra("userName", userNameD)
                                i.putExtra("email", email)
                                i.putExtra("firstName", firstName)
                                i.putExtra("lastName", lastName)
                                i.putExtra("gender", gender)
                                i.putExtra("image", image)
                                i.putExtra("token", token)

                                // visible the progress bar
                                loginBinding.prProcess.visibility = View.VISIBLE
                                startActivity(i)
                                Toast.makeText(
                                    this@LoginActivity,
                                    "login success",
                                    Toast.LENGTH_SHORT
                                ).show()



                            }, 2000)
                        } else {
                            Toast.makeText(
                                this@LoginActivity,
                                "username and password is incorrect",
                                Toast.LENGTH_SHORT
                            ).show()
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
        // visible the progress bar
        loginBinding.prProcess.visibility = View.GONE
    }
}