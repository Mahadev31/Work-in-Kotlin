package com.demo.billingdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.billingdemo.CategoryModelClass
import com.demo.billingdemo.CustomerModelClass
import com.demo.billingdemo.R
import com.demo.billingdemo.SqliteDatabaseHelper

class CustomerAdapterClass(context: Context,var click: (CustomerModelClass) -> Unit)//create invoke
    : RecyclerView.Adapter<CustomerAdapterClass.MyViewHolder>() {
    var list = ArrayList<CustomerModelClass>()  //create model class array list
    var db= SqliteDatabaseHelper(context)
    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var shopName: TextView = v.findViewById(R.id.txtShopName)  //id binding
        var customerName: TextView = v.findViewById(R.id.txtCustomerName)//id binding
        var layoutCustomer: LinearLayout = v.findViewById(R.id.layoutCustomer)//id binding
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.customer_list, parent, false)  //set xml file
        return MyViewHolder(v)
    }
    override fun getItemCount(): Int {
        return list.size  //set array list size
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.shopName.text = list[position].shopName   //variable in set model class variable
        holder.customerName.text = list[position].customerName   //variable in set model class variable
        holder.layoutCustomer.setOnClickListener {

            click.invoke(list[position])  //invoke in set model class
        }
    }
    fun update(list: ArrayList<CustomerModelClass>) {
        this.list = list  //list set in  array list
        notifyDataSetChanged()  //set changer
    }

    fun removeItem(position: Int) {
        db.deleteCustomerData(list[position].id)
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: CustomerModelClass, position: Int) {
        list.add(position, item)
        db.insertCustomerData(item.shopName,item.customerName,item.mobileNumber)
        notifyItemInserted(position)
    }

    fun getData(): ArrayList<CustomerModelClass> {
        return list
    }
}