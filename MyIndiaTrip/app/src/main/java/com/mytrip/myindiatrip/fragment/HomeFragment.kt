package com.mytrip.myindiatrip.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mytrip.myindiatrip.activity.SearchActivity
import com.mytrip.myindiatrip.adapter.CategoryAdapter
import com.mytrip.myindiatrip.adapter.CategoryListAdapter
import com.mytrip.myindiatrip.adapter.ImageSliderAdapter
import com.mytrip.myindiatrip.adapter.PopularPlaceAdapter
import com.mytrip.myindiatrip.databinding.FragmentHomeBinding
import com.mytrip.myindiatrip.databinding.ProgressBarBinding
import com.mytrip.myindiatrip.model.CategoryModelClass
import com.mytrip.myindiatrip.model.ImageSliderModel
import com.mytrip.myindiatrip.model.PopularModelClass
import java.util.*


class HomeFragment : Fragment() {


    lateinit var homeBinding: FragmentHomeBinding

    lateinit var mDbRef: DatabaseReference
    lateinit var adapter: CategoryAdapter
    var categoryList = ArrayList<CategoryModelClass>()
    var imageSliderList = ArrayList<ImageSliderModel>()

    lateinit var popularAdapter: PopularPlaceAdapter
    var popularList = ArrayList<PopularModelClass>()

    lateinit var   dialog : Dialog
    var key=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)


         dialog = Dialog(requireContext())
        var progressBarBinding = ProgressBarBinding.inflate(layoutInflater)
        dialog.setContentView(progressBarBinding.root)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.show()

        mDbRef = FirebaseDatabase.getInstance().getReference()

        homeBinding.imgSearch.setOnClickListener {
            var i = Intent(context, SearchActivity::class.java)
            startActivity(i)
        }
        autoVideoPlay()
        category()
        autoImageSlider()
        popularPlace()
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

        adapter = CategoryAdapter(this, categoryList) {
          key= it.key!!
            Log.e("TAG", "categoryList: "+it.key!! )
            Log.e("TAG", "categoryListView: "+key )
        }
        homeBinding.rcvCategory.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.rcvCategory.adapter = adapter

        mDbRef.child("category_data").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(CategoryModelClass::class.java)
                    categoryList.add(currentUser!!)

                }
                dialog.dismiss()
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        categoryListView()

    }

    private fun categoryListView() {



    var    categoryListAdapter = CategoryListAdapter(this, categoryList)
        homeBinding.rcvCategoryList.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.rcvCategoryList.adapter = categoryListAdapter

        mDbRef.child(key).child("heritage").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                categoryList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(CategoryModelClass::class.java)
//
//////                    if (mAuth.currentUser?.uid != currentUser?.uid) {
//                    currentUser?.heritage_image = postSnapshot.child("heritage_image").value.toString()
//                    currentUser?.heritage_name = postSnapshot.child("heritage_name").value.toString()
                    categoryList.add(currentUser!!)

                    Log.e("TAG", "heritage_image: "+ currentUser?.heritage_image  )
//                    }
                }
                categoryListAdapter.notifyDataSetChanged()
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
//                homeBinding.txtCount.text = " ${position + 1}/6"
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.e("TAG", "onPageScrollStateChanged: ")
            }
        })

        var sliderAdapter = ImageSliderAdapter(this, imageSliderList)
//        homeBinding.rcvCategory.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.viewPager.adapter = sliderAdapter

        homeBinding.wormDotsIndicator.attachTo(homeBinding.viewPager)



        mDbRef.child("image_slider").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                imageSliderList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(ImageSliderModel::class.java)
//                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                    currentUser?.image = postSnapshot.child("image").value.toString()
                    currentUser?.name = postSnapshot.child("name").value.toString()
                    imageSliderList.add(currentUser!!)

//                    }
                }
                dialog.dismiss()
                sliderAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

//
//        /*After setting the adapter use the timer */
//
//         sliderRunnable = Runnable {
//            if (currentPage ===  - 1) {
//                currentPage = 0
//            }
//            homeBinding.viewPager.setCurrentItem(currentPage++, true)
//        }
//
//        timer = Timer() // This will create a new Thread
//
//        timer!!.schedule(object : TimerTask() {
//            // task to be scheduled
//            override fun run() {
//                handler.post(sliderRunnable)
//            }
//        }, DELAY_MS, PERIOD_MS)

    }

    private fun popularPlace() {



        popularAdapter = PopularPlaceAdapter(this, popularList)
        homeBinding.rcvPopularPlace.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.rcvPopularPlace.adapter = popularAdapter

        mDbRef.child("popular_place").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                popularList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(PopularModelClass::class.java)
//                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                    currentUser?.popularImage = postSnapshot.child("p_image").value.toString()
                    currentUser?.popularName = postSnapshot.child("p_name").value.toString()
                    popularList.add(currentUser!!)

//                    }
                }
                dialog.dismiss()
                popularAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }


}