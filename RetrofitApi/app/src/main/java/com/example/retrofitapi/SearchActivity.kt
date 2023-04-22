package com.example.retrofitapi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retrofitapi.databinding.ActivitySearchBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {
    lateinit var searchBinding: ActivitySearchBinding
    lateinit var apiInterface: APIInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)

        iniView()
    }

    private fun iniView() {
        apiInterface = APIClient.getClient().create(APIInterface::class.java)

        searchBinding.btnSearch.setOnClickListener {
            var searchText = searchBinding.edtSearch.text.toString()


            apiInterface.getProductsSearch(searchText)
                .enqueue(object : Callback<AllProductsResponse<ProductsItem>> {
                    override fun onResponse(
                        call: Call<AllProductsResponse<ProductsItem>>,
                        response: Response<AllProductsResponse<ProductsItem>>
                    ) {
                        var searchList = response.body()?.products
                        if (searchText == "laptop") {
                            var adapter = SearchAdapterClass(this@SearchActivity, searchList)
                            var manager = GridLayoutManager(this@SearchActivity, 2)
                            searchBinding.rcvSearchView.layoutManager = manager
                            searchBinding.rcvSearchView.adapter = adapter

                        } else {
                            Toast.makeText(this@SearchActivity, "Value is not available", Toast.LENGTH_SHORT).show()

                        }
                    }

                    override fun onFailure(
                        call: Call<AllProductsResponse<ProductsItem>>,
                        t: Throwable
                    ) {
                    }

                })

        }

    }
}

