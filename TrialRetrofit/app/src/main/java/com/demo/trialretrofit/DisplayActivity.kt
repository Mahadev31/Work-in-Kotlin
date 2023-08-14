package com.demo.trialretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.trialretrofit.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity() {
    lateinit var displayBinding: ActivityDisplayBinding

    var list = ArrayList<ModelClass>()

    lateinit var adapter: Adapter
    lateinit var db: SqLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayBinding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(displayBinding.root)
        db = SqLiteDatabase(this)
        initView()
    }

    private fun initView() {
        displayBinding.btnAddItem.setOnClickListener {
            var i = Intent(this, AddDataActivity::class.java)
            startActivity(i)
        }

        list = db.displayData()
        adapter = Adapter(this)
        var manger = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        displayBinding.rcvView.layoutManager = manger
        displayBinding.rcvView.adapter = adapter

        adapter.updateList(list)
    }
}