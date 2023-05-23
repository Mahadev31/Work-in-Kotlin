package com.mytrip.myindiatrip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.activity.SearchActivity
import com.mytrip.myindiatrip.model.SearchModelClass

class SearchAdapter(var context: Context, var placeList: ArrayList<SearchModelClass>) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPlace: ImageView = itemView.findViewById(R.id.imgPlace)
        var txtPlaceName: TextView = itemView.findViewById(R.id.txtPlaceName)
        var txtLocation: TextView = itemView.findViewById(R.id.txtLocation)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  placeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(placeList[position].placeImage)
            .placeholder(R.drawable.ic_image).into(holder.imgPlace)

        holder.txtPlaceName.text=placeList[position].placeName
        holder.txtLocation.text=placeList[position].placeLocation
    }
}