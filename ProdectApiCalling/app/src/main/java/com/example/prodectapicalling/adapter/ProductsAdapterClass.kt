package com.example.prodectapicalling.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.prodectapicalling.ProductsItem
import com.example.prodectapicalling.ProductsResponse
import com.example.prodectapicalling.R
import com.example.prodectapicalling.databinding.RcvProductsListBinding

class ProductsAdapterClass(
    var context: Context,
    var productList: ProductsResponse,
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
        return productList.products.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = productList.products[position].title.toString()
        holder.price.text = productList.products[position].price.toString()
        holder.rating.text = productList.products[position].rating.toString()
        holder.description.text = productList.products[position].description.toString()
        holder.discountPercentage.text = productList.products[position].discountPercentage.toString()

        Glide.with(context)
            .load("https://i.dummyjson.com/data/products/${productList.products[position].id}/thumbnail.jpg")
            .placeholder(
                R.drawable.ic_image
            ).into(holder.imgProducts)

        holder.cdView.setOnClickListener {
            click.invoke(productList.products[position])

        }
    }
}