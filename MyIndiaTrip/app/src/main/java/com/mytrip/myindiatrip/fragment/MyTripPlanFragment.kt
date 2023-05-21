package com.mytrip.myindiatrip.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mytrip.myindiatrip.activity.CurrentLocationActivity
import com.mytrip.myindiatrip.databinding.FragmentHomeBinding
import com.mytrip.myindiatrip.databinding.FragmentMyTripPlanBinding


class MyTripPlanFragment : Fragment() {

    lateinit var tripBinding: FragmentMyTripPlanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tripBinding = FragmentMyTripPlanBinding.inflate(layoutInflater, container, false)
initView()
        return tripBinding.root
    }

    private fun initView() {

        tripBinding.cdCurrentLocation.setOnClickListener {
            var i=Intent(context,CurrentLocationActivity::class.java)
            startActivity(i)
        }
    }


}