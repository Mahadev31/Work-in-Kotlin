package com.example.myfoodapp.adapterclass

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.R
import com.example.myfoodapp.modelclass.HomeModelClass

class HomeAdapterClass(var context: Context?, var horizontalList: ArrayList<HomeModelClass>) :
    RecyclerView.Adapter<HomeAdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img:ImageView=itemView.findViewById(R.id.imgHori)
        var name:TextView=itemView.findViewById(R.id.txtName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.horizontal_list,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {

        return horizontalList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


       holder.img.setImageResource(horizontalList[position].img)
        holder.name.text = horizontalList[position].name
    }
}