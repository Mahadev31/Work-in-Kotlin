package com.example.tablayout.adapterclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayout.R

class ChatsAdapterClass(var imageList: ArrayList<Int>, var nameList: ArrayList<String>, var subNameList: ArrayList<String>
) : RecyclerView.Adapter<ChatsAdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img:ImageView=itemView.findViewById(R.id.img)
        var title:TextView=itemView.findViewById(R.id.txtTitle)
        var subName:TextView=itemView.findViewById(R.id.txtSubName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.chats_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
      return  imageList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.img.setImageResource(imageList[position])
        holder.title.setText(nameList[position])
        holder.subName.setText(subNameList[position])
    }


}