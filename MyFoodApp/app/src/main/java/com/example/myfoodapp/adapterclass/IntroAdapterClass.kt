package com.example.myfoodapp.adapterclass

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.example.myfoodapp.R
import java.util.*
import kotlin.collections.ArrayList

class IntroAdapterClass(var imageList: ArrayList<Int>) : PagerAdapter() {
    override fun getCount(): Int {
        return imageList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as FrameLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {


        val itemView: View =
            LayoutInflater.from(container.context).inflate(R.layout.intro_image_list, container, false)


        val imageView: ImageView = itemView.findViewById<View>(R.id.imageView) as ImageView


        imageView.setImageResource(imageList.get(position))


        Objects.requireNonNull(container).addView(itemView)


        return itemView
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(`object` as FrameLayout)
    }
}