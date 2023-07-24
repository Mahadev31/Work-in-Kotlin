package com.demo.resumeusingcomponents

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demo.resumeusingcomponents.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding

    companion object {
        var firstName: String? = null
        var lastName: String? = null
        var mobileNumber: String? = null
        var address: String? = null
        var dd: String? = null
        var mm: String? = null
        var yy: String? = null
        var gender: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {

        mainBinding.btnNext.setOnClickListener {
            firstName = mainBinding.edtFirstName.text.toString()
            lastName = mainBinding.edtLastName.text.toString()
            mobileNumber = mainBinding.edtMobilNumber.text.toString()
            address = mainBinding.edtAddress.text.toString()
            dd = mainBinding.edtDD.text.toString()
            mm = mainBinding.edtMM.text.toString()
            yy = mainBinding.edtYY.text.toString()

//            val selectedId: Int = mainBinding.rgGender.getCheckedRadioButtonId()
//            val radioSexButton = findViewById<View>(selectedId) as RadioButton
//            gender = mainBinding.rgGender.checkedRadioButtonId.toString()
////            gender = radioSexButton.text as String?
//
//            //Gender


            //Gender
            val selectedId: Int = mainBinding.rgGender.getCheckedRadioButtonId()
            val rb = findViewById<RadioButton>(selectedId)


            if (selectedId != -1) {
                gender = rb.text.toString()
            }
            if (firstName!!.isEmpty()) {
                Toast.makeText(this, "First Name is Empty", Toast.LENGTH_SHORT).show()
            } else if (lastName!!.isEmpty()) {
                Toast.makeText(this, "Last Name is Empty", Toast.LENGTH_SHORT).show()
            } else if (mobileNumber!!.isEmpty()) {
                Toast.makeText(this, "Mobile Number is Empty", Toast.LENGTH_SHORT).show()
            }  else if (mobileNumber!!.length <= 10 != mobileNumber!!.length >=10) {
                Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()

            } else if (!Patterns.PHONE.matcher(mobileNumber).matches()) {
                Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()

            }else if (address!!.isEmpty()) {
                Toast.makeText(this, "Address is Empty", Toast.LENGTH_SHORT).show()
            }  else if (dd!!.isEmpty()) {
                Toast.makeText(this, "Date of Birth  is Empty", Toast.LENGTH_SHORT).show()
            } else if (mm!!.isEmpty()) {
                Toast.makeText(this, "Date of Birth  is Empty", Toast.LENGTH_SHORT).show()
            } else if (yy!!.isEmpty()) {
                Toast.makeText(this, "Date of Birth  is Empty", Toast.LENGTH_SHORT).show()
            }  else if (dd!!.length <2 != mm!!.length <2 != yy!!.length <4 ) {
                Toast.makeText(this, "Please Enter Date of Birth  Valid", Toast.LENGTH_SHORT).show()
            }else if (selectedId == -1) {
                Toast.makeText(this, "Gender is not Selected", Toast.LENGTH_SHORT).show()
            } else {

                var i = Intent(this, SecondActivity::class.java)
                startActivity(i)
            }
        }
    }
}