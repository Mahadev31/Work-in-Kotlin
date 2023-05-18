package com.example.bottomnavigationdrawer.fragment

import android.R
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.FirebaseDatabase
import com.mytrip.myindiatrip.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    lateinit var homeBinding: FragmentHomeBinding
    lateinit var firebaseDatabase: FirebaseDatabase

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

        homeBinding.videoView.start()
    }


}