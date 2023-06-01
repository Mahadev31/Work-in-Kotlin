package com.mytrip.myindiatrip.fragment

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.google.firebase.database.*
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.activity.DataDisplayActivity
import com.mytrip.myindiatrip.activity.SearchActivity
import com.mytrip.myindiatrip.adapter.CategoryAdapter
import com.mytrip.myindiatrip.adapter.CategoryListAdapter
import com.mytrip.myindiatrip.adapter.ImageSliderAdapter
import com.mytrip.myindiatrip.adapter.PopularPlaceAdapter
import com.mytrip.myindiatrip.databinding.FragmentHomeBinding
import com.mytrip.myindiatrip.databinding.ProgressBarBinding
import com.mytrip.myindiatrip.model.ModelClass
import java.util.*


class HomeFragment : Fragment() {


    lateinit var homeBinding: FragmentHomeBinding

    lateinit var mDbRef: DatabaseReference
    var categoryList = ArrayList<ModelClass>()
    var categoryItemList = ArrayList<ModelClass>()
    var imageSliderList = ArrayList<ModelClass>()

    lateinit var popularAdapter: PopularPlaceAdapter
    var popularList = ArrayList<ModelClass>()

    lateinit var dialog: Dialog
//      var id: String?=null

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
                homeBinding.imgVolume.setImageResource(R.drawable.ic_volume_up)
                mp.setVolume(1f, 1f)//UnMute

            } else {

                Log.d("TAG", "setVolume OFF")
                homeBinding.imgVolume.setImageResource(R.drawable.ic_volume_off)
                mp.setVolume(0f, 0f) //Mute


            }
            mVolumePlaying = !mVolumePlaying

        }
    }

    private fun category() {
        mDbRef.child("category_data").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                categoryList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(ModelClass::class.java)
                    currentUser?.let { categoryList.add(it) }

                }
                dialog.dismiss()

                var adapter = CategoryAdapter(this@HomeFragment, categoryList) {
                    var key = it.key!!
                    Log.e("TAG", "categoryList: " + it.key)
                    Log.e("TAG", "categoryListView: $id")
                    categoryListView(key)
                }
                homeBinding.rcvCategory.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                homeBinding.rcvCategory.adapter = adapter

                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

    private fun categoryListView(key: String) {
        var newId = key

        Log.e("TAG", "sczc:" + newId)
        if (newId != null) {
            mDbRef.child("category_data").child(newId.toString()).child("place")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        categoryItemList.clear()
                        for (postSnapshot in snapshot.children) {
                            val currentUser = postSnapshot.getValue(ModelClass::class.java)

                            categoryItemList.add(currentUser!!)

                            Log.e("TAG", "image: " + currentUser?.image)
                            //                    }
                        }


                        var categoryListAdapter =
                            CategoryListAdapter(this@HomeFragment, categoryItemList) {
                                var i = Intent(context, DataDisplayActivity::class.java)
                                i.putExtra("Key", newId)
                                i.putExtra("child_key", it.child_key)
                                i.putExtra("category", true)
                                startActivity(i)
                            }
                        homeBinding.rcvCategoryList.layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        homeBinding.rcvCategoryList.adapter = categoryListAdapter

                        categoryListAdapter.notifyDataSetChanged()
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
        }


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

        var sliderAdapter = ImageSliderAdapter(this, imageSliderList) {
            var i = Intent(context, DataDisplayActivity::class.java)
            i.putExtra("Key", it.key)
            i.putExtra("imageSliderList", true)
            startActivity(i)
        }
//        homeBinding.rcvCategory.layoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.viewPager.adapter = sliderAdapter

        homeBinding.wormDotsIndicator.attachTo(homeBinding.viewPager)



        mDbRef.child("image_slider").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                imageSliderList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(ModelClass::class.java)
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


        popularAdapter = PopularPlaceAdapter(this, popularList) {
            var i = Intent(context, DataDisplayActivity::class.java)
            i.putExtra("Key", it.key)
            i.putExtra("popular", true)
            startActivity(i)
        }
        homeBinding.rcvPopularPlace.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        homeBinding.rcvPopularPlace.adapter = popularAdapter

        mDbRef.child("popular_place").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                popularList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(ModelClass::class.java)
//                    if (mAuth.currentUser?.uid != currentUser?.uid) {
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