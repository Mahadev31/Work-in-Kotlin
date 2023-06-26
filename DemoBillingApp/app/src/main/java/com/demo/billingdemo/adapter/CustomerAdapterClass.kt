package com.demo.billingdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.billingdemo.CustomerModelClass
import com.demo.billingdemo.R

class CustomerAdapterClass(var click: (CustomerModelClass) -> Unit)//create invoke
    : RecyclerView.Adapter<CustomerAdapterClass.MyViewHolder>() {
    var list = ArrayList<CustomerModelClass>()  //create model class array list

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var companyName: TextView = v.findViewById(R.id.txtCompanyName)  //id binding
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
        holder.companyName.text = list[position].companyName   //variable in set model class variable
        holder.customerName.text = list[position].customerName   //variable in set model class variable
        holder.layoutCustomer.setOnClickListener {

            click.invoke(list[position])  //invoke in set model class
        }
    }
    fun update(list: ArrayList<CustomerModelClass>) {
        this.list = list  //list set in  array list
        notifyDataSetChanged()  //set changer
    }
}