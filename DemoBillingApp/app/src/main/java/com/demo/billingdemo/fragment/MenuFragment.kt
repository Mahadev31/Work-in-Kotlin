package com.demo.billingdemo.fragment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.demo.billingdemo.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {

lateinit var menuBinding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        menuBinding= FragmentMenuBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        initView()
        return menuBinding.root
    }

    private fun initView() {
        menuBinding.cdCategory.setOnClickListener {

            val fragmentManager: FragmentManager?= fragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(com.demo.billingdemo.R.id.frameView, CategoryFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        menuBinding.cdCustomer.setOnClickListener {

            val fragmentManager: FragmentManager?= fragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(com.demo.billingdemo.R.id.frameView, CustomerFragment())
            fragmentTransaction.commit()
        }
    }


}