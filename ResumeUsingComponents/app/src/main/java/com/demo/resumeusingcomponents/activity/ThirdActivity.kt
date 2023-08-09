package com.demo.resumeusingcomponents.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.resumeusingcomponents.databinding.ActivityThirdBinding
import com.demo.resumeusingcomponents.sqlite.SQLiteDatabase

class ThirdActivity : AppCompatActivity() {

    lateinit var thirdBinding: ActivityThirdBinding
    lateinit var db: SQLiteDatabase
    companion object {
        var skills:String?=null

    }
    var skill = StringBuilder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(thirdBinding.root)
        db = SQLiteDatabase(this)
        initView()
    }

    private fun initView() {
        thirdBinding.btnSubmit.setOnClickListener {

            if (thirdBinding.chkC.isChecked) {
                skill.append(thirdBinding.chkC.text.toString() + ",")
            }
            if (thirdBinding.chkCPlush.isChecked) {
                skill.append(thirdBinding.chkCPlush.text.toString() + ",")
            }
            if (thirdBinding.chkJava.isChecked) {
                skill.append(thirdBinding.chkJava.text.toString() + ",")
            }
            if (thirdBinding.chkKotlin.isChecked) {
                skill.append(thirdBinding.chkKotlin.text.toString() + ",")
            }
            if (thirdBinding.chkAndroid.isChecked) {
                skill.append(thirdBinding.chkAndroid.text.toString() + ",")
            }
            if (thirdBinding.chkSQLite.isChecked) {
                skill.append(thirdBinding.chkSQLite.text.toString() + ",")
            }
            if (thirdBinding.chkFigma.isChecked) {
                skill.append(thirdBinding.chkFigma.text.toString() + ",")
            }
            if (thirdBinding.chkWab.isChecked) {
                skill.append(thirdBinding.chkWab.text.toString() + ",")
            }
            if (thirdBinding.chkFlutter.isChecked) {
                skill.append(thirdBinding.chkFlutter.text.toString())
            }
            skills =skill.toString()
            if (!thirdBinding.chkC.isChecked && !thirdBinding.chkCPlush.isChecked && !thirdBinding.chkJava.isChecked && !thirdBinding.chkKotlin.isChecked && !thirdBinding.chkSQLite.isChecked && !thirdBinding.chkAndroid.isChecked && !thirdBinding.chkWab.isChecked && !thirdBinding.chkFlutter.isChecked && !thirdBinding.chkFigma.isChecked) {
                Toast.makeText(this, "Hobby is not selected", Toast.LENGTH_SHORT).show()
            } else {
                var i = Intent(this, DashboardActivity::class.java)
                i.putExtra("email", SecondActivity.email)
                startActivity(i)
                db.insertDatabase(
                    FirstActivity.firstName,
                    FirstActivity.lastName,
                    FirstActivity.mobileNumber,
                    FirstActivity.address,
                    FirstActivity.dd,
                    FirstActivity.mm,
                    FirstActivity.yy,
                    FirstActivity.gender.toString(),
                    SecondActivity.email,
                    SecondActivity.password,
                    ThirdActivity.skills
                )
            }
        }


    }
}

