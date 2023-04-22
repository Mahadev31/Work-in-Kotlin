package com.example.sqllitedatabasehelper.forthprogram

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sqllitedatabasehelper.R

class AdapterClass(var list: ArrayList<ModelClass>) :
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.txtID)
        var name: TextView = itemView.findViewById(R.id.txtITEM)
        var unit: TextView = itemView.findViewById(R.id.txtUNIT)
        var price: TextView = itemView.findViewById(R.id.txtPRICE)
        var total: TextView = itemView.findViewById(R.id.txtTOTAL)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_list_1, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.text = list[position].id.toString()
        holder.name.text = list[position].name
        holder.unit.text = list[position].unit.toString()
        holder.price.text = list[position].price.toString()
        holder.total.text = list[position].total.toString()
    }
}