package com.demo.billingdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.billingdemo.CustomerModelClass
import com.demo.billingdemo.R

class CustomerSuggestionAdapter(
    var context: Context,
    var suggestionList: ArrayList<CustomerModelClass>,
    var onClick:( String)-> Unit
) : RecyclerView.Adapter<CustomerSuggestionAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCustomerSug: TextView = itemView.findViewById(R.id.txtCustomerSug)
        var customerSugLayout: LinearLayout = itemView.findViewById(R.id.customerSugLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.customer_suggestion_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return suggestionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtCustomerSug.text = suggestionList[position].shopName

        holder.customerSugLayout.setOnClickListener {
            onClick.invoke(suggestionList[position].shopName)
        }
    }
}