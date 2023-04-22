package com.example.tablayout.adapterclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tablayout.R

class StatusAdapterClass(
    var imageList: ArrayList<Int>,
    var nameList: ArrayList<String>, var n: (Int, String) -> Unit  //create invoke methode

) : RecyclerView.Adapter<StatusAdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var img: ImageView = itemView.findViewById(R.id.img)
        var title: TextView = itemView.findViewById(R.id.txtTitle)
        var layStory: LinearLayout = itemView.findViewById(R.id.layStory)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.status_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.img.setImageResource(imageList[position])
        holder.title.setText(nameList[position])
        holder.layStory.setOnClickListener(View.OnClickListener {
            n.invoke(
                imageList[position], nameList[position]

            )
        })

    }
}