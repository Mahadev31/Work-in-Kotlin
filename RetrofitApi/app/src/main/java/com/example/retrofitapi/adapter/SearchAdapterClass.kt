package com.example.retrofitapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapi.ProductsItem
import com.example.retrofitapi.R
import com.example.retrofitapi.databinding.RcvSearchListBinding

class SearchAdapterClass(
    var context: Context,
    var searchList: ArrayList<ProductsItem>?,
//    var click: (ProductsItem) -> Unit
) :
    RecyclerView.Adapter<SearchAdapterClass.MyViewHolder>() {
    class MyViewHolder(binding: RcvSearchListBinding) : RecyclerView.ViewHolder(binding.root) {
        //        var ids = binding.txtId
        var title = binding.txtTitleS
        var price = binding.txtPriceS
        var rating = binding.txtRatingS
        var description = binding.txtDescriptionS
        var discountPercentage = binding.txtDiscountPercentageS
        var imgProducts = binding.imgProductsS
        var cdView = binding.cdViewS
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RcvSearchListBinding.inflate(LayoutInflater.from(context)))
    }
    override fun getItemCount(): Int {
        return searchList!!.size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = searchList!![position].title.toString()
        holder.price.text = searchList!![position].price.toString()
        holder.rating.text = searchList!![position].rating.toString()
        holder.description.text = searchList!![position].description.toString()
        holder.discountPercentage.text = searchList!![position].discountPercentage.toString()
        Glide.with(context)
            .load("https://i.dummyjson.com/data/products/${searchList!![position].id}/thumbnail.jpg")
            .placeholder(R.drawable.ic_image).into(holder.imgProducts)
    }
}