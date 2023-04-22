package com.example.sqllitedatabasehelper.forthprogram

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqllitedatabasehelper.databinding.ActivityPracticeBinding
import com.example.sqllitedatabasehelper.databinding.DialogAdditemBinding


class PracticeActivity : AppCompatActivity() {


    lateinit var practiceBinding: ActivityPracticeBinding


    lateinit var db: SQliteHelperClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        practiceBinding = ActivityPracticeBinding.inflate(layoutInflater)
        setContentView(practiceBinding.root)

        db = SQliteHelperClass(this)

        initView()

    }

    private fun initView() {
        practiceBinding.btnAddData.setOnClickListener {
            val dialog = Dialog(this)  //create Dialog Box
            val dialogBinding: DialogAdditemBinding = DialogAdditemBinding.inflate(layoutInflater)
            dialog.setContentView(dialogBinding.root)  //set dialog box xml file

            dialogBinding.btnSubmit.setOnClickListener {
                val name = dialogBinding.edtItem.text.toString()
                val unit = dialogBinding.edtUnit.text.toString().toInt()
                val price = dialogBinding.edtPrice.text.toString().toInt()

                db.insertData(name, unit, price)
                Toast.makeText(this, "Your data is sava", Toast.LENGTH_SHORT).show()
            }
            dialogBinding.btnDisplay.setOnClickListener {

                val list = db.DisplayData()

                val adapter = AdapterClass(list)
                val manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                practiceBinding.recycleView.layoutManager = manger
                practiceBinding.recycleView.adapter = adapter
                dialog.dismiss()
            }
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))   //dialog box TRANSPARENT
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
        }
    }


}
