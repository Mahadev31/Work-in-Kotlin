package com.example.retrofitapi.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitapi.ProductsItem
import com.example.retrofitapi.R
import com.example.retrofitapi.databinding.RcvProductsListBinding

class ProductsAdapterClass(
    var context: Context,
    var productList: ArrayList<ProductsItem>?,
    var click: (ProductsItem) -> Unit
) :
    RecyclerView.Adapter<ProductsAdapterClass.MyViewHolder>() {

    class MyViewHolder(binding: RcvProductsListBinding) : RecyclerView.ViewHolder(binding.root) {
        //        var ids = binding.txtId
        var title = binding.txtTitle
        var price = binding.txtPrice
        var rating = binding.txtRating
        var description = binding.txtDescription
        var discountPercentage = binding.txtDiscountPercentage
        var imgProducts = binding.imgProducts
        var cdView = binding.cdView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(RcvProductsListBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
        return productList!!.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = productList!![position].title.toString()
        holder.price.text = productList!![position].price.toString()
        holder.rating.text = productList!![position].rating.toString()
        holder.description.text = productList!![position].description.toString()
        holder.discountPercentage.text = productList!![position].discountPercentage.toString()

        Glide.with(context)
            .load("https://i.dummyjson.com/data/products/${productList!![position].id}/thumbnail.jpg")
            .placeholder(
                R.drawable.ic_image
            ).into(holder.imgProducts)

        holder.cdView.setOnClickListener {
            click.invoke(productList!![position])

        }
    }
}