package com.example.prodectapicalling.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.prodectapicalling.ProductsItem
import com.example.prodectapicalling.R
import com.example.prodectapicalling.adapter.ItemProductsAdapter
import com.example.prodectapicalling.databinding.ActivityItemDisplayBinding
import com.google.gson.Gson
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class ItemDisplayActivity : AppCompatActivity() {
    lateinit var itemDisplayBinding: ActivityItemDisplayBinding

    lateinit var mRequestItemb: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemDisplayBinding = ActivityItemDisplayBinding.inflate(layoutInflater)
        setContentView(itemDisplayBinding.root)

        initView()
    }

    private fun initView() {
        itemDisplayBinding.imgBack.setOnClickListener {   //move to one activity to second activity
            onBackPressed()
        }
        mRequestItemb = Volley.newRequestQueue(this)
        var id = intent.getIntExtra("id", 0)
        Log.e("item_Id", "item_Id: " + id)

        itemDisplayBinding.viewPgImage.adapter
        var req = JsonObjectRequest(
            Request.Method.GET, "https://dummyjson.com/products/$id", null,
            { respose ->

                var productList = Gson().fromJson(respose.toString(), ProductsItem::class.java)

                itemDisplayBinding.txtTitleD.text = productList.title
                itemDisplayBinding.txtDescriptionD.text = productList.description
                itemDisplayBinding.txtPriceD.text = productList.price.toString()
                itemDisplayBinding.txtDiscountPercentageD.text = productList.discountPercentage.toString()
                itemDisplayBinding.txtRatingD.text = productList.rating.toString()
                itemDisplayBinding.txtBrandD.text = productList.brand
                itemDisplayBinding.txtCategoryD.text = productList.category
                itemDisplayBinding.txtStockD.text = productList.stock.toString()

                Log.e("TAG", "title: " + itemDisplayBinding.txtTitleD)

                val adapterItem = ItemProductsAdapter(this, productList.images)
                itemDisplayBinding.viewPgImage.adapter = adapterItem

                val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)

                dotsIndicator.attachTo(itemDisplayBinding.viewPgImage)
            }, { error ->
                Log.e("error", "initView: " + error.message)
            }
        )
        mRequestItemb.add(req)

    }
}