package com.demo.resumeusingcomponents

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.demo.resumeusingcomponents.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding

    lateinit var db: SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        db = SQLiteDatabase(this)
        initView()
    }

    private fun initView() {
        loginBinding.btnSignIn.setOnClickListener {

            var emailCheck = loginBinding.edtEmail.text.toString()
            var passwordCheck = loginBinding.edtPassword.text.toString()

            var checkUser = db.displayDatabase(emailCheck,passwordCheck)

            if (checkUser== true) {
//                val i = Intent(this, DashboardActivity::class.java)
//                i.putExtra("email", emailCheck)
//                loginBinding.edtEmail.setText("")
//                loginBinding.edtPassword.setText("")
//                startActivity(i)
                Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show()
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setCancelable(true)
                builder.setTitle("Wrong Credential")
                builder.setMessage("Wrong Credential")
                builder.show()
            }

        }

        loginBinding.linSignUp.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }



}