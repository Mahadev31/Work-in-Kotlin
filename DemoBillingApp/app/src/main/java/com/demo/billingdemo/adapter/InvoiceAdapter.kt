package com.demo.billingdemo.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.billingdemo.AddItemModelClass
import com.demo.billingdemo.R

class InvoiceAdapter() : RecyclerView.Adapter<InvoiceAdapter.MyViewHolder>() {

    var list = ArrayList<AddItemModelClass>()

    class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var txtIDShow: TextView = v.findViewById(R.id.txtIDShow)
        var txtItemNameShow: TextView = v.findViewById(R.id.txtItemNameShow)
        var txtQtyShow: TextView = v.findViewById(R.id.txtQtyShow)
        var txtPriceShow: TextView = v.findViewById(R.id.txtPriceShow)
        var txtTotalItemShow: TextView = v.findViewById(R.id.txtTotalItemShow)
        var layoutItem: LinearLayout = v.findViewById(R.id.layoutItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.invoice_details_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.txtIDShow.text = list[position].id.toString()
        holder.txtItemNameShow.text = list[position].itemName
        holder.txtQtyShow.text = list[position].qty
        holder.txtPriceShow.text = list[position].price
        holder.txtTotalItemShow.text = list[position].total


        Log.e("AddAdapter", "Item: " + holder.txtItemNameShow)
        Log.e("AddAdapter", "price: " + holder.txtPriceShow)




    }

    fun updateList(list1: java.util.ArrayList<AddItemModelClass>) {
        list = list1
        notifyDataSetChanged()
    }



}