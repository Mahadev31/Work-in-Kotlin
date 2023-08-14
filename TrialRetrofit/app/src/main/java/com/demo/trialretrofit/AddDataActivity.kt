package com.demo.trialretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demo.trialretrofit.databinding.ActivityAddDataBinding

class AddDataActivity : AppCompatActivity() {

    lateinit var addDataBinding: ActivityAddDataBinding
    lateinit var db:SqLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addDataBinding=ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(addDataBinding.root)
        db=SqLiteDatabase(this)
        intiView()
    }

    private fun intiView() {
        addDataBinding.btnSave.setOnClickListener {
            var itemName=addDataBinding.edtItemName.text.toString()
            var price=addDataBinding.edtPrice.text.toString()

            db.insertData(itemName,price)
        }
    }
}