package com.example.bottomnavigationdrawer.fragment

import android.R
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.firebase.database.FirebaseDatabase
import com.mytrip.myindiatrip.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    lateinit var homeBinding: FragmentHomeBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    private var mVolumePlaying = true

    val videoUrl = "https://firebasestorage.googleapis.com/v0/b/my-india-trip.appspot.com/o/itro1_360.mp4?alt=media&token=598e0bc3-df56-43fa-a3cb-16777d313957"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        initView()
        return homeBinding.root



    }

    private fun initView() {

        //video play
        // Uri object to refer the
        // resource from the videoUrl
        val uri = Uri.parse(videoUrl)
        // sets the resource from the
        // videoUrl to the videoView
        homeBinding.videoView.setVideoURI(uri)
        homeBinding.videoView.setOnPreparedListener { mp -> setVolumeControl(mp) }
        homeBinding.videoView.start()

        //Image Slider
        homeBinding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.e("TAG", "onPageScrolled: " )
            }

            override fun onPageSelected(position: Int) {
                Log.e("TAG", "onPageSelected: " )
                homeBinding.txtCount.text=" ${position+1}/6"
            }

            override fun onPageScrollStateChanged(state: Int) {
                Log.e("TAG", "onPageScrollStateChanged: ", )
            }
        })
//        val adapter = AdapterClass(imageList)
//        homeBinding.viewPager.adapter = adapter
    }
    private fun setVolumeControl(mp: MediaPlayer) {
        val volume: ImageView =        homeBinding.imgVolume
        volume .setOnClickListener { v: View? ->
            if (mVolumePlaying) {
                Log.d("TAG", "setVolume OFF")
//                volume.setImageResource(R.drawable.ic_volume_off_black_36_dp_80alpha)
                mp.setVolume(0f, 0f)
            } else {
                Log.d("TAG", "setVolume ON")
//                volume.setImageResource(R.drawable.ic_volume_up_black_36dp_80alpha)
                mp.setVolume(1f, 1f)
            }
            mVolumePlaying = !mVolumePlaying
        }
    }

}