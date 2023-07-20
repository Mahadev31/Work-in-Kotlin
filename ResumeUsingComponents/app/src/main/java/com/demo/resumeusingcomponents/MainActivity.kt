package com.demo.resumeusingcomponents

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.demo.resumeusingcomponents.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    companion object{
         var  firstName:String? = null
         var  lastName:String? = null
         var  mobileNumber:String? = null
         var  address:String? = null
         var  dd:String? = null
         var  mm:String? = null
         var  yy:String? = null
        var  gender: String? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()
    }

    private fun initView() {

        mainBinding.btnNext.setOnClickListener {
            firstName=mainBinding.edtFirstName.text.toString()
            lastName=mainBinding.edtLastName.text.toString()
            mobileNumber=mainBinding.edtMobilNumber.text.toString()
            address=mainBinding.edtAddress.text.toString()
            dd=mainBinding.edtDD.text.toString()
            mm=mainBinding.edtMM.text.toString()
            yy=mainBinding.edtYY.text.toString()

            val selectedId: Int = mainBinding.rgGender.getCheckedRadioButtonId()
            val radioSexButton = findViewById<View>(selectedId) as RadioButton

            gender= radioSexButton.text as String?

            Log.e("TAG", "firstName: $firstName")
            Log.e("TAG", "lastName: $lastName")
            Log.e("TAG", "mobileNumber: $mobileNumber")
            Log.e("TAG", "address: $address")
            Log.e("TAG", "date: $dd  $mm  $yy")
            Log.e("TAG", "gender: $gender")

            var i =Intent(this,SecondActivity::class.java)
            startActivity(i)
        }
    }
}