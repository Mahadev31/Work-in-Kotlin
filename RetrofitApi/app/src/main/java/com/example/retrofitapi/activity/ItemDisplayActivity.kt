package com.example.retrofitapi.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.retrofitapi.*
import com.example.retrofitapi.APIClient
import com.example.retrofitapi.adapter.ItemProductsAdapter
import com.example.retrofitapi.databinding.ActivityItemDisplayBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemDisplayActivity : AppCompatActivity() {
    lateinit var itemDisplayBinding: ActivityItemDisplayBinding

    lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemDisplayBinding = ActivityItemDisplayBinding.inflate(layoutInflater)
        setContentView(itemDisplayBinding.root)

        initView()
    }

    private fun initView() {

        var id=intent.getIntExtra("id",0)

        apiInterface = APIClient.getClient().create(APIInterface::class.java)

        apiInterface.getProductsItem(id).enqueue(object : Callback<ProductsItem> {

            //            }
            override fun onResponse(call: Call<ProductsItem>, response: Response<ProductsItem>) {
                var imageList = response.body()?.images
                var title = response.body()?.title
                var description = response.body()?.description
                var price = response.body()?.price
                var discountPercentage = response.body()?.discountPercentage
                var rating = response.body()?.rating
                var brand = response.body()?.brand
                var category = response.body()?.category
                var stock = response.body()?.stock

                itemDisplayBinding.txtTitleD.text = title
                itemDisplayBinding.txtDescriptionD.text = description
                itemDisplayBinding.txtPriceD.text = price.toString()
                itemDisplayBinding.txtDiscountPercentageD.text = discountPercentage.toString()
                itemDisplayBinding.txtRatingD.text = rating.toString()
                itemDisplayBinding.txtBrandD.text = brand.toString()
                itemDisplayBinding.txtCategoryD.text = category.toString()
                itemDisplayBinding.txtStockD.text = stock.toString()
//               for (i in 0 until list.size){
//                   Log.e("TAG", "onResponse: "+list[i].title )
//               }

                val adapterItem = ItemProductsAdapter(this@ItemDisplayActivity, imageList)
                itemDisplayBinding.viewPgImage.adapter = adapterItem

                val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)

                dotsIndicator.attachTo(itemDisplayBinding.viewPgImage)
            }

            override fun onFailure(call: Call<ProductsItem>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}