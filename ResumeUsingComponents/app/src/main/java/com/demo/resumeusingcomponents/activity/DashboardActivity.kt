package com.demo.resumeusingcomponents.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.resumeusingcomponents.adapter.MainAdapterClass
import com.demo.resumeusingcomponents.databinding.ActivityDashboardBinding
import com.demo.resumeusingcomponents.modelclass.ModelClass
import com.demo.resumeusingcomponents.sqlite.SQLiteDatabase

class DashboardActivity : AppCompatActivity() {
    lateinit var dashboardBinding: ActivityDashboardBinding

    lateinit var db: SQLiteDatabase
    var list = ArrayList<ModelClass>()
    lateinit var adapter : MainAdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardBinding.root)
        db = SQLiteDatabase(this)

        initView()
    }

    private fun initView() {

        var email = intent.getStringExtra("email")

        dashboardBinding.txtEmail.text = email
         adapter = MainAdapterClass(list, {
            var i = Intent(this, DisplayResumeActivity::class.java)
            i.putExtra("fName", it.firstName)
            i.putExtra("lName", it.lastName)
            i.putExtra("mNumber", it.mobileNumber)
            i.putExtra("address", it.address)
            i.putExtra("email", it.emailId)
            i.putExtra("gender", it.gender)
            i.putExtra("dd", it.dd)
            i.putExtra("mm", it.mm)
            i.putExtra("yy", it.yy)
            i.putExtra("skill", it.skill)
            startActivity(i)
        }, {
            var iconeName = "Update"
            var editIntent = Intent(this, ResumeActivity::class.java)
            editIntent.putExtra("id", it.id)
            editIntent.putExtra("fName", it.firstName)
            editIntent.putExtra("lName", it.lastName)
            editIntent.putExtra("mNumber", it.mobileNumber)
            editIntent.putExtra("address", it.address)
            editIntent.putExtra("gender", it.gender)
            editIntent.putExtra("hobby1", it.dd)
            editIntent.putExtra("hobby2", it.mm)
            editIntent.putExtra("hobby3", it.yy)
            editIntent.putExtra("iconName", iconeName)
            editIntent.putExtra("updateRecord", true)
            startActivity(editIntent)

            Log.e("displayUpdate", "id: " + it.id)
            Log.e("displayUpdate", "fName: " + it.firstName)
        }, { id ->
            db.deleteData(id)
            Toast.makeText(this, "delete record success", Toast.LENGTH_SHORT).show()
            list = db.resumeDataDisplay()
            adapter.updateList(list)

        })
        var manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        dashboardBinding.rcvResume.layoutManager = manger
        dashboardBinding.rcvResume.adapter = adapter

        list = db.resumeDataDisplay()
        adapter.updateList(list)
//        if (MainActivity.firstName == null) {
//            val model = ModelClass()
//         var   email = intent.getStringExtra("email")
//            Log.e("TAG", "initView: "+email )
//            db.displayDatabase(email)
//            dashboardBinding.txtFirstName.text = model.firstName
//            dashboardBinding.txtLastName.text = model.lastName
//            dashboardBinding.txtMobilNumber.text = model.mobileNumber
//            dashboardBinding.txtAddress.text = model.address
//
//            dashboardBinding.txtDD.text = model.dd
//            dashboardBinding.txtMM.text = model.mm
//            dashboardBinding.txtYY.text = model.yy
//
//            dashboardBinding.txtGender.text = model.gender
//
//            dashboardBinding.txtEmail.text = model.emailId
//
//            dashboardBinding.txtSkill.text = model.skill
//        } else {
//            dashboardBinding.txtFirstName.text = MainActivity.firstName
//            dashboardBinding.txtLastName.text = MainActivity.lastName
//            dashboardBinding.txtMobilNumber.text = MainActivity.mobileNumber
//            dashboardBinding.txtAddress.text = MainActivity.address
//
//            dashboardBinding.txtDD.text = MainActivity.dd
//            dashboardBinding.txtMM.text = MainActivity.mm
//            dashboardBinding.txtYY.text = MainActivity.yy
//
//            dashboardBinding.txtGender.text = MainActivity.gender
//
//            dashboardBinding.txtEmail.text = SecondActivity.email
//
//            dashboardBinding.txtSkill.text = ThirdActivity.skills


//        }


    }
}