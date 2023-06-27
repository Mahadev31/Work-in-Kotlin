package com.demo.billingdemo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.billingdemo.InvoiceModelClass
import com.demo.billingdemo.R
import com.demo.billingdemo.SqliteDatabaseHelper
import com.demo.billingdemo.adapter.InvoiceAdapter
import com.demo.billingdemo.databinding.FragmentDisplayInvoiceBinding


class DisplayInvoiceFragment : Fragment() {

    lateinit var displayInvoiceBinding: FragmentDisplayInvoiceBinding

    lateinit var db: SqliteDatabaseHelper

    var invoiceList=ArrayList<InvoiceModelClass>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        displayInvoiceBinding=FragmentDisplayInvoiceBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        db = SqliteDatabaseHelper(requireContext())
        initView()
        return displayInvoiceBinding.root
    }

    private fun initView() {

  var list=db.displayInvoiceData()

//        displayInvoiceBinding.txtDate.setText()
    }

}