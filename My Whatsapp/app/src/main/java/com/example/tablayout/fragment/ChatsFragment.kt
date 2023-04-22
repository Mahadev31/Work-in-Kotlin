package com.example.tablayout.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tablayout.R
import com.example.tablayout.adapterclass.ChatsAdapterClass
import com.example.tablayout.databinding.FragmentChatsBinding

class ChatsFragment : Fragment() {

    lateinit var chatsBinding: FragmentChatsBinding

    var imageList = ArrayList<Int>()
    var nameList = ArrayList<String>()
    var subNameList = ArrayList<String>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        chatsBinding = FragmentChatsBinding.inflate(layoutInflater, container, false)

        initView()
        return chatsBinding.root
    }

    private fun initView() {

        info()
        val adapter = ChatsAdapterClass(imageList, nameList, subNameList)
        chatsBinding.recycleChats.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        chatsBinding.recycleChats.adapter = adapter


    }

    private fun info() {

        imageList.clear()

        imageList.add(R.drawable.m2)
        imageList.add(R.drawable.lion)
        imageList.add(R.drawable.meditating_hanuman)
        imageList.add(R.drawable.tiger)
        imageList.add(R.drawable.parsuram)
        imageList.add(R.drawable.devraj_indra1)
        imageList.add(R.drawable.badami)
        imageList.add(R.drawable.somnath)
        imageList.add(R.drawable.ramkrishna3)
        imageList.add(R.drawable.tiger)
        imageList.add(R.drawable.parsuram)
        imageList.add(R.drawable.devraj_indra1)


        nameList.clear()

        nameList.add("Mahadev")
        nameList.add("Lion")
        nameList.add("Meditating Hanuman")
        nameList.add("Tiger")
        nameList.add("Parsuram")
        nameList.add("Devraj_indra")
        nameList.add("Rajendra")
        nameList.add("Somnath")
        nameList.add("RamKrishna")
        nameList.add("Tiger")
        nameList.add("Parsuram")
        nameList.add("Devraj_indra")

        subNameList.clear()

        subNameList.add("Mahadev bless you")
        subNameList.add("King in Forest")
        subNameList.add("Shree Ram Bhakt ")
        subNameList.add("National Animal")
        subNameList.add("Lord Vishnu Avtar ")
        subNameList.add("Swarg King")
        subNameList.add("Mahadev bless you")
        subNameList.add("King in Forest")
        subNameList.add("Shree Ram Bhakt ")
        subNameList.add("National Animal")
        subNameList.add("Lord Vishnu Avtar ")
        subNameList.add("Swarg King")
    }


}