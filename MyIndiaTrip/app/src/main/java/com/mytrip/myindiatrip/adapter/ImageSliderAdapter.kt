package com.mytrip.myindiatrip.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.fragment.HomeFragment
import com.mytrip.myindiatrip.model.ImageSliderModel
import java.util.*
import kotlin.collections.ArrayList

class ImageSliderAdapter(var homeFragment: HomeFragment, var imageSliderList: ArrayList<ImageSliderModel>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageSliderList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val itemView: View =
            LayoutInflater.from(container.context).inflate(R.layout.image_silder_list, container, false)


        val imageView: ImageView = itemView.findViewById<View>(R.id.imgSliderView) as ImageView
        val name: TextView = itemView.findViewById<View>(R.id.txtSliderName) as TextView

        name.text=imageSliderList[position].name.toString()



        Glide.with(homeFragment).load(imageSliderList[position].image)
            .placeholder(R.drawable.ic_image).into(imageView)

        Log.e("TAG", "image slider: " + imageSliderList[position].image)

        Objects.requireNonNull(container).addView(itemView)


        return itemView
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as RelativeLayout)
    }
}