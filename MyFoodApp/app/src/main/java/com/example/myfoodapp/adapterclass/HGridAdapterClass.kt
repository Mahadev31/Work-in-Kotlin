package com.example.myfoodapp.adapterclass

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.R
import com.example.myfoodapp.modelclass.HGridModelClass

class HGridAdapterClass(var context: Context?, var gridList: ArrayList<HGridModelClass>) :
    RecyclerView.Adapter<HGridAdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.imgHori)
        var name: TextView = itemView.findViewById(R.id.txtName)
        var price: TextView = itemView.findViewById(R.id.txtPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {

        return gridList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


        holder.img.setImageResource(gridList[position].img)
        holder.name.text = gridList[position].name
        holder.price.text = gridList[position].price.toString()
    }
}