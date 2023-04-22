package com.example.retrofitapi

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

class  ItemProductsAdapter(
    var context: Context,
    var imageList: ArrayList<String>?,

) :PagerAdapter (){

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun getCount(): Int {
        return imageList!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val itemView: View =
            LayoutInflater.from(container.context).inflate(R.layout.image_list, container, false)


        var imageView: ImageView = itemView.findViewById(R.id.imageView)


      Glide.with(context).load("${imageList!![position]}").placeholder(R.drawable.ic_image).into(imageView)
        Log.e("image", "instantiateItem:${imageList!![position]} " )

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as LinearLayout)
    }
}