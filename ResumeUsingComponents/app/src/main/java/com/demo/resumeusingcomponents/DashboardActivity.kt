package com.demo.resumeusingcomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.resumeusingcomponents.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardBinding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding=ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)

        initView()
    }

    private fun initView() {
      dashboardBinding.txtFirstName.text=  MainActivity.firstName
      dashboardBinding.txtLastName.text=  MainActivity.lastName
      dashboardBinding.txtMobilNumber.text=  MainActivity.mobileNumber
      dashboardBinding.txtAddress.text=  MainActivity.address

      dashboardBinding.txtDD.text=  MainActivity.dd
      dashboardBinding.txtMM.text=  MainActivity.mm
      dashboardBinding.txtYY.text=  MainActivity.yy

      dashboardBinding.txtGender.text= MainActivity.gender.toString()

        dashboardBinding.txtEmail.text=  SecondActivity.email

        dashboardBinding.txtSkill.text=  ThirdActivity.skill
    }
}