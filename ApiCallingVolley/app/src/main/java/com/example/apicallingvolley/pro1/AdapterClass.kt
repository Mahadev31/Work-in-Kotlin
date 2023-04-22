package com.example.apicallingvolley.pro1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallingvolley.R

class AdapterClass(var dataList: ArrayList<ResponseItem>) :
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.txtId)
        var userID: TextView = itemView.findViewById(R.id.txtUserId)
        var title: TextView = itemView.findViewById(R.id.txtTitle)
        var body: TextView = itemView.findViewById(R.id.txtBody)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.api_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.text = dataList[position].id.toString()
        holder.userID.text = dataList[position].userId.toString()
        holder.title.text = dataList[position].title
        holder.body.text = dataList[position].body
    }
}