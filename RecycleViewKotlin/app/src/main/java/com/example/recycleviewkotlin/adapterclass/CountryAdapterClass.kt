package com.example.recycleviewkotlin.adapterclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewkotlin.R
import com.example.recycleviewkotlin.modelclass.CountryModelClass

class CountryAdapterClass(var countryList: ArrayList<CountryModelClass>) :
    RecyclerView.Adapter<CountryAdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.img)
        var txtName: TextView = itemView.findViewById(R.id.txtName)
        var layout: LinearLayout = itemView.findViewById(R.id.layCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.country_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.img.setImageResource(countryList.get(position).img)
        holder.txtName.setText(countryList.get(position).name)
    }
}