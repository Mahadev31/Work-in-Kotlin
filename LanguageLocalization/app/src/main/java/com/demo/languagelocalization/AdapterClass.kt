package com.demo.languagelocalization

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(var languageList: ArrayList<String>,var click:(String)-> Unit) :
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtLanguage: TextView = itemView.findViewById(R.id.txtLanguage)
        var layout: LinearLayout = itemView.findViewById(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.language_item_file, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtLanguage.text = languageList[position]

        holder.layout.setOnClickListener {
            click.invoke(languageList[position])
        }


    }
}