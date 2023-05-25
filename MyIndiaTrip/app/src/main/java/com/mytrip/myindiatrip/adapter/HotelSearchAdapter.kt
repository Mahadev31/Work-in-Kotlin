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
        var imgHotelImage: ImageView = itemView.findViewById(R.id.imgHotelImage)
        var txtHotelName: TextView = itemView.findViewById(R.id.txtHotelName)
        var txtHotelRent: TextView = itemView.findViewById(R.id.txtHotelRent)
        var txtHotelRating: TextView = itemView.findViewById(R.id.txtHotelRating)
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
        holder.txtHotelName.text = hotelList[position].hotelName
        holder.txtHotelRent.text = hotelList[position].hotelRent
        holder.txtHotelRating.text = hotelList[position].hotelRating

        Glide.with(myTripFragment).load(hotelList[position].hotelImage).placeholder(R.drawable.ic_image).into(holder.imgHotelImage)

        Log.e("TAG", "onBindViewHolder: " + hotelList[position].hotelImage)

        holder.layPopular.setOnClickListener {

            notifyDataSetChanged()
        }

    }


}