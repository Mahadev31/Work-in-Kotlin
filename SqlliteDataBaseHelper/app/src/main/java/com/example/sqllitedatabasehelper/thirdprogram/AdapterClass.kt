package com.example.sqllitedatabasehelper.thirdprogram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqllitedatabasehelper.R

class AdapterClass(var list: ArrayList<Modelclass>) :RecyclerView.Adapter<AdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id:TextView=itemView.findViewById(R.id.txtID)
        var name:TextView=itemView.findViewById(R.id.txtName)
        var moblie:TextView=itemView.findViewById(R.id.txtMobile)
        var address:TextView=itemView.findViewById(R.id.txtAddress)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       var view=LayoutInflater.from(parent.context).inflate(R.layout.recycle_list,parent,false)
        return  MyViewHolder(view)
    }

    override fun getItemCount(): Int {
     return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.text = list[position].id.toString()
        holder.name.text = list[position].name
        holder.moblie.text = list[position].mobile
        holder.address.text = list[position].address
    }
}