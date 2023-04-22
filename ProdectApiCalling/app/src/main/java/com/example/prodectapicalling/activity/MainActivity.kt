package com.example.prodectapicalling.activity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.prodectapicalling.ProductsResponse
import com.example.prodectapicalling.R
import com.example.prodectapicalling.adapter.ProductsAdapterClass
import com.example.prodectapicalling.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var progressDialog : ProgressDialog
    lateinit var mRequestQueue: RequestQueue
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        iniView()
    }

    private fun iniView() {


        mRequestQueue = Volley.newRequestQueue(this)
        var req = JsonObjectRequest(Request.Method.GET, "https://dummyjson.com/products", null,
            { respose ->

                var productList = Gson().fromJson(respose.toString(), ProductsResponse::class.java)


                var adapterClass = ProductsAdapterClass(this, productList)
                {
                    Handler().postDelayed(Runnable {
                         progressDialog = ProgressDialog(this)
                        progressDialog.show()
                        progressDialog.setContentView(R.layout.progress_bar)
                        progressDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))   //dialog box TRANSPARENT
                        var i = Intent(this, ItemDisplayActivity::class.java)
                        i.putExtra("id", it.id)
                        Log.e("main_Id", "item_Id: " + it.id)
                        startActivity(i)

                    }, 4000)



                }
                var manager = GridLayoutManager(this, 2)
                mainBinding.rcvProduct.layoutManager = manager
                mainBinding.rcvProduct.adapter = adapterClass
            }, { error ->
                Log.e("error", "initView: " + error.message)
            }
        )
        mRequestQueue.add(req)


    }
    override fun onBackPressed() {
        progressDialog.dismiss()
    }
}