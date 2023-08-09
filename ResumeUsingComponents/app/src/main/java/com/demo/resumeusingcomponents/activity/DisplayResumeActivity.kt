package com.demo.resumeusingcomponents.activity

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.resumeusingcomponents.databinding.ActivityDisplayResumeBinding

class DisplayResumeActivity : AppCompatActivity() {
    lateinit var displayBinding: ActivityDisplayResumeBinding

    //
//    lateinit var displayList: ArrayList<MainModelClass>
//    lateinit var model:MainModelClass
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayBinding = ActivityDisplayResumeBinding.inflate(layoutInflater)
        setContentView(displayBinding.root)

        initView()
    }

    private fun initView() {
        displayBinding.imgBackU.setOnClickListener {
            var back = Intent(this, FirstActivity::class.java)
            startActivity(back)
        }
        displayBinding.txtDoneU.setOnClickListener {
            var back = Intent(this, FirstActivity::class.java)
            startActivity(back)
        }
        var bitmap = intent.getParcelableExtra("image") as Bitmap?
        displayBinding.imgDpU.setImageBitmap(bitmap)

        id = intent.getIntExtra("id", 0)
        var fName = intent.getStringExtra("fName").toString()
        var lName = intent.getStringExtra("lName").toString()
        var mNumber = intent.getStringExtra("mNumber").toString()
        var address = intent.getStringExtra("address").toString()
        var email = intent.getStringExtra("email").toString()
        var gender = intent.getStringExtra("gender").toString()
        var dd = intent.getStringExtra("dd").toString()
        var mm = intent.getStringExtra("mm").toString()
        var yy = intent.getStringExtra("yy").toString()

        displayBinding.txtFNameU.text = fName
        displayBinding.txtLNameU.text = lName
        displayBinding.txtMobileU.text = mNumber
        displayBinding.txtAddressU.text = address
        displayBinding.txtEmailU.text = email
        displayBinding.txtGenderU.text = gender
        displayBinding.txtDdU.text = dd
        displayBinding.txtMmU.text = mm
        displayBinding.txtYyU.text = yy

//        model=MainModelClass(id,fName,lName,mNumber,address,gender,hobby1,hobby2,hobby3)

//        displayBinding.imgEditU.setOnClickListener {
//            var iconeName = "Update"
//            var editIntent = Intent(this, ResumeActivity::class.java)
//            editIntent.putExtra("id",model.id)
//            editIntent.putExtra("fName", model.fName)
//            editIntent.putExtra("lName", model.lName)
//            editIntent.putExtra("mNumber",model. mNumber)
//            editIntent.putExtra("address", model.address)
//            editIntent.putExtra("gender", model.gender)
//            editIntent.putExtra("hobby1",model. hobby1)
//            editIntent.putExtra("hobby2", model.hobby2)
//            editIntent.putExtra("hobby3", model.hobby3)
//            editIntent.putExtra("iconName", iconeName)
//            editIntent.putExtra("updateRecord", true)
//            startActivity(editIntent)
//
//            Log.e("displayUpdate", "id: "+model.id )
//            Log.e("displayUpdate", "fName: "+model.fName )
//        }
    }
}