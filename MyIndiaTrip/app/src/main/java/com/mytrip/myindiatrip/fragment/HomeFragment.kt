package com.mytrip.myindiatrip.fragment

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mytrip.myindiatrip.adapter.CategoryAdapter
import com.mytrip.myindiatrip.adapter.ImageSliderAdapter
import com.mytrip.myindiatrip.databinding.FragmentHomeBinding
import com.mytrip.myindiatrip.model.CategoryModelClass
import com.mytrip.myindiatrip.model.ImageSliderModel
import java.util.*


class HomeFragment : Fragment() {


    lateinit var homeBinding: FragmentHomeBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var mDbRef: DatabaseReference
    lateinit var mAuth: FirebaseAuth
    lateinit var adapter: CategoryAdapter
    var categoryList=ArrayList<CategoryModelClass>()
    var imageSliderList=ArrayList<ImageSliderModel>()

    var currentPage = 0
    var timer: Timer? = null
    val DELAY_MS: Long = 500 //delay in milliseconds before task is to be executed

    val PERIOD_MS: Long = 3000 // time in milliseconds between successive task executions.


    val videoUrl =
        "https://firebasestorage.googleapis.com/v0/b/my-india-trip.appspot.com/o/itro1_360.mp4?alt=media&token=598e0bc3-df56-43fa-a3cb-16777d313957"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        firebaseDatabase = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

        autoVideoPlay()
        category()
        autoImageSlider()
        return homeBinding.root


    }




    private fun autoVideoPlay() {
        val videoUrl =
            "https://firebasestorage.googleapis.com/v0/b/my-india-trip.appspot.com/o/itro1_360.mp4?alt=media&token=598e0bc3-df56-43fa-a3cb-16777d313957"

        //video play
        // Uri object to refer the
        // resource from the videoUrl
        val uri = Uri.parse(videoUrl)
        // sets the resource from the
        // videoUrl to the videoView
        homeBinding.videoView.setVideoURI(uri)
        homeBinding.videoView.setOnPreparedListener { mp -> setVolumeControl(mp) }
        homeBinding.videoView.start()


    }

    private fun setVolumeControl(mp: MediaPlayer) {

        mp.setVolume(0f, 0f) //Mute

        var mVolumePlaying = true
        homeBinding.imgVolume.setOnClickListener {
            if (mVolumePlaying) {
                Log.d("TAG", "setVolume ON")
                homeBinding.imgVolume.setImageResource(com.mytrip.myindiatrip.R.drawable.ic_volume_up)
                mp.setVolume(1f, 1f)//UnMute

            } else {

                Log.d("TAG", "setVolume OFF")
                homeBinding.imgVolume.setImageResource(com.mytrip.myindiatrip.R.drawable.ic_volume_off)
                mp.setVolume(0f, 0f) //Mute


            }
            mVolumePlaying = !mVolumePlaying

        }
    }

    private fun category() {

        adapter = CategoryAdapter(this,categoryList)
        homeBinding.rcvCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.rcvCategory.adapter = adapter

        mDbRef.child("category_data").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(CategoryModelClass::class.java)
//                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                    currentUser?.categoryImage= postSnapshot.child("category_image").value.toString()
                    currentUser?.categoryName=postSnapshot.child("category_name").value.toString()
                    categoryList.add(currentUser!!)

//                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

    private fun autoImageSlider() {
        //Image Slider
        homeBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.e("TAG", "onPageScrolled: ")
            }

            override fun onPageSelected(position: Int) {
                Log.e("TAG", "onPageSelected: ")
                homeBinding.txtCount.text = " ${position + 1}/6"
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.e("TAG", "onPageScrollStateChanged: ")
            }
        })

        var sliderAdapter = ImageSliderAdapter(this,imageSliderList)
//        homeBinding.rcvCategory.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.viewPager.adapter = sliderAdapter

        mDbRef.child("image_slider").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                imageSliderList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(ImageSliderModel::class.java)
//                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                    currentUser?.image= postSnapshot.child("image").value.toString()
//                    currentUser?.categoryName=postSnapshot.child("category_name").value.toString()
                    imageSliderList.add(currentUser!!)

//                    }
                }
                sliderAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })






        /*After setting the adapter use the timer */

        /*After setting the adapter use the timer */
        val handler = Handler()
        val Update = Runnable {
            if (currentPage ===  - 1) {
                currentPage = 0
            }
            homeBinding.viewPager.setCurrentItem(currentPage++, true)
        }

        timer = Timer() // This will create a new Thread

        timer!!.schedule(object : TimerTask() {
            // task to be scheduled
            override fun run() {
                handler.post(Update)
            }
        }, DELAY_MS, PERIOD_MS)
    }
}