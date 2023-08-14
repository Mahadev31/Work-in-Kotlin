package com.demo.trialretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class Adapter(var context: Context) : RecyclerView.Adapter<Adapter.MyViewHolder>() {
    var list=ArrayList<ModelClass>()
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v=LayoutInflater.from(parent.context).inflate(R.layout.rcv_item_list,parent,false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun updateList(list: ArrayList<ModelClass>) {
        this.list= ArrayList()
        this.list=list
        notifyDataSetChanged()
    }
}