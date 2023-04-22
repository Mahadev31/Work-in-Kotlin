package com.example.tablayout.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tablayout.R
import com.example.tablayout.adapterclass.ChatsAdapterClass
import com.example.tablayout.adapterclass.StatusAdapterClass
import com.example.tablayout.databinding.FragmentChatsBinding
import com.example.tablayout.databinding.FragmentStatusBinding

class StatusFragment : Fragment() {
    lateinit var statusBinding: FragmentStatusBinding

    var imageList = ArrayList<Int>()
    var nameList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        statusBinding = FragmentStatusBinding.inflate(layoutInflater, container, false)

        initView()
        return statusBinding.root
    }

    private fun initView() {

        info()
        val adapter = StatusAdapterClass(
            imageList,
            nameList
        ) { img, name ->  //add model class array list & add invoke methode
            Toast.makeText(context, "click " + name, Toast.LENGTH_SHORT).show()
        }
        statusBinding.recycleStatus.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        statusBinding.recycleStatus.adapter = adapter
    }


    private fun info() {
        imageList.add(R.drawable.m2)
        imageList.add(R.drawable.lion)
        imageList.add(R.drawable.meditating_hanuman)
        imageList.add(R.drawable.tiger)
        imageList.add(R.drawable.parsuram)
        imageList.add(R.drawable.devraj_indra1)

        nameList.add("Mahadev")
        nameList.add("Lion")
        nameList.add("Meditating Hanuman")
        nameList.add("Tiger")
        nameList.add("Parsuram")
        nameList.add("Devraj_indra")


    }
}