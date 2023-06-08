package com.pro.validationfrom

import android.R
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Patterns
import android.view.View
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.pro.validationfrom.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    var countryList = ArrayList<String?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)


        initView()
    }

    private fun initView() {
        countryList.add("India")
        countryList.add("Belize")
        countryList.add("Bangladesh")
        countryList.add("Australia")
        countryList.add("Japan")
        countryList.add("United Kingdom")
        countryList.add("China")
        countryList.add("Denmark")




        mainBinding.rgGender.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->

                val radioButton = group.findViewById<View>(checkedId) as RadioButton
                Toast.makeText(this, radioButton.text, Toast.LENGTH_SHORT).show()

            })
        mainBinding.btnSubmit.setOnClickListener {
            var firstName = mainBinding.edtFirstName.text.toString()
            var lastName = mainBinding.edtLastName.text.toString()
            var mobileNumber = mainBinding.edtMobileNumber.text.toString()
            var email = mainBinding.edtEmail.text.toString()
            var password = mainBinding.edtPassword.text.toString()
            var confPassword = mainBinding.edtConfPassword.text.toString()
            var selectedId = mainBinding.rgGender.checkedRadioButtonId


            if (firstName.isEmpty()) {
                Toast.makeText(this, "Please Enter First Name", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (lastName.isEmpty()) {
                Toast.makeText(this, "Please Enter Last Name", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (mobileNumber.isEmpty()) {
                Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (mobileNumber.length <= 9) {
                Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (!Patterns.PHONE.matcher(mobileNumber).matches()) {
                Toast.makeText(this, "Please Enter Valid Mobile Number", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (email.isEmpty()) {
                Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (password.length < 8) {
                Toast.makeText(this, "Minimum 8 Character Password", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (!password.matches(".*[A-Z].*".toRegex())) {

                Toast.makeText(this, "Must Contain 1 Upper-case Character", Toast.LENGTH_SHORT)
                    .show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }

            } else if (!password.matches(".*[a-z].*".toRegex())) {
                Toast.makeText(this, "Must Contain 1 Lower-case Character", Toast.LENGTH_SHORT)
                    .show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }

            } else if (!password.matches(".*[@#\$%^&+=].*".toRegex())) {
                Toast.makeText(
                    this,
                    "Must Contain 1 Special Character (@#\$%^&+=)",
                    Toast.LENGTH_SHORT
                ).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (confPassword.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please Enter Confirm Password",
                    Toast.LENGTH_SHORT
                ).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }

            } else if (!confPassword.equals(password)) {
                Toast.makeText(this, "Password do not match", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else if (selectedId == -1) {
                Toast.makeText(this, "Gender Not  selected ", Toast.LENGTH_SHORT).show()
                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }


            } else if (!mainBinding.chkHobby1.isChecked && !mainBinding.chkHobby2.isChecked && !mainBinding.chkHobby3.isChecked) {
                Toast.makeText(this, "Hobby Not  selected ", Toast.LENGTH_SHORT).show()

                var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator!!.vibrate(
                        VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vibrator!!.vibrate(500)
                }
            } else {

                var adapter: ArrayAdapter<*> =
                    ArrayAdapter<Any?>(
                        this,
                        R.layout.simple_spinner_item,
                        countryList as List<Any?>
                    )
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
                mainBinding.spinnerCountry.adapter = adapter

                Toast.makeText(this, "Submit", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



