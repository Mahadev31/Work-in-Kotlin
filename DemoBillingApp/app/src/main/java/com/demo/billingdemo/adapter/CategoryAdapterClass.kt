package com.demo.billingdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.billingdemo.CategoryModelClass
import com.demo.billingdemo.R

class CategoryAdapterClass(var click: (CategoryModelClass) -> Unit)//create invoke
    : RecyclerView.Adapter<CategoryAdapterClass.MyViewHolder>() {
    var list = ArrayList<CategoryModelClass>()  //create model class array list

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var itemName: TextView = v.findViewById(R.id.txtItem)  //id binding
        var s_Price: TextView = v.findViewById(R.id.txtS_Price)//id binding
        var layoutCategory: LinearLayout = v.findViewById(R.id.layoutCategory)//id binding
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.category_list, parent, false)  //set xml file
        return MyViewHolder(v)
    }
    override fun getItemCount(): Int {
        return list.size  //set array list size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemName.text = list[position].itemName   //variable in set model class variable
        holder.s_Price.text = list[position].salePrice   //variable in set model class variable
        holder.layoutCategory.setOnClickListener {

            click.invoke(list[position])  //invoke in set model class
        }
    }
    fun update(list: ArrayList<CategoryModelClass>) {
        this.list = list  //list set in  array list
        notifyDataSetChanged()  //set changer
    }
}