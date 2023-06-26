package com.demo.billingdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.billingdemo.R
import com.demo.billingdemo.databinding.FragmentDisplayInvoiceBinding


class DisplayInvoiceFragment : Fragment() {

    lateinit var displayInvoiceBinding: FragmentDisplayInvoiceBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        displayInvoiceBinding=FragmentDisplayInvoiceBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        initView()
        return displayInvoiceBinding.root
    }

    private fun initView() {

    }

}