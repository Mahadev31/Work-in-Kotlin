package com.demo.billingdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.billingdemo.CategoryModelClass
import com.demo.billingdemo.R

class ItemSuggestionAdapter(
    var context: Context,
    var onClick:( String, String)-> Unit
) : RecyclerView.Adapter<ItemSuggestionAdapter.MyViewHolder>() {

    var suggestionList= ArrayList<CategoryModelClass>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtItemSuggestion: TextView = itemView.findViewById(R.id.txtItemSug)
        var txtPriceSug: TextView = itemView.findViewById(R.id.txtPriceSug)
        var suggestionLayout: LinearLayout = itemView.findViewById(R.id.suggestionLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_suggestion_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return suggestionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtItemSuggestion.text = suggestionList[position].itemName
        holder.txtPriceSug.text = suggestionList[position].salePrice

        holder.suggestionLayout.setOnClickListener {
            onClick.invoke(suggestionList[position].itemName,suggestionList[position].salePrice)
        }
    }

    fun updateList(suggestionList: java.util.ArrayList<CategoryModelClass>) {
       this. suggestionList=suggestionList
        notifyDataSetChanged()
    }
}