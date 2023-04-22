package com.example.retrofitapi

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIInterface {
    @GET("/products")
    fun getAllProducts(): Call<AllProductsResponse<Any?>>

    @GET("/products/{id}")
    fun getProductsItem(@Path("id") id:Int): Call<ProductsItem>

    @GET("/products/search")
    fun getProductsSearch(@Query("q") searchText: String): Call<AllProductsResponse<ProductsItem>>
}