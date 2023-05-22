package com.mytrip.myindiatrip.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.fragment.MyTripPlanFragment
import com.mytrip.myindiatrip.model.HotelSearchModelClass
import kotlin.collections.ArrayList

class HotelSearchAdapter(
    var myTripFragment: MyTripPlanFragment,
    var hotelList: ArrayList<HotelSearchModelClass>
) : RecyclerView.Adapter<HotelSearchAdapter.MyViewHolder>() {
//    private var selectedItemPosition: Int = -1

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.imgHotel)
        var PopularName: TextView = itemView.findViewById(R.id.txtPopular)
        var layPopular: RelativeLayout = itemView.findViewById(R.id.layPopular)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.hotel_item_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return hotelList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.PopularName.text = hotelList[position].image

//        Glide.with(myTripFragment).load(hotelList[position].image)
//            .placeholder(R.drawable.ic_image).into(holder.image)
//
//        Log.e("TAG", "onBindViewHolder: " + hotelList[position].image)

        holder.layPopular.setOnClickListener {

            notifyDataSetChanged()
        }

    }


}