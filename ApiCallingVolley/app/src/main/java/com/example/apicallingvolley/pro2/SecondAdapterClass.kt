package com.example.apicallingvolley.pro2

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apicallingvolley.databinding.SecondApiListBinding

class SecondAdapterClass(var context: Context,var dataList1: ArrayList<SecondResponseItem>) : RecyclerView.Adapter<SecondAdapterClass.MyViewHolder>() {
    class MyViewHolder(binding: SecondApiListBinding) :RecyclerView.ViewHolder(binding.root){
var userId=binding.txtuserId
var id=binding.txtId
var title=binding.txtTitle
var completed=binding.txtCompleted
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(SecondApiListBinding.inflate(LayoutInflater.from(context)))
    }

    override fun getItemCount(): Int {
       return  dataList1.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.userId.text=dataList1[position].userId.toString()
        holder.id.text=dataList1[position].id.toString()
        holder.title.text=dataList1[position].title
        holder.completed.text=dataList1[position].completed.toString()
    }
}