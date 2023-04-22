package com.example.tablayout.adapterclass

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tablayout.fragment.CallsFragment
import com.example.tablayout.fragment.ChatsFragment
import com.example.tablayout.fragment.StatusFragment


class TabAdapterClass(supportFragmentManager: FragmentManager, val tabCount: Int) :
    FragmentPagerAdapter(supportFragmentManager) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {

                return ChatsFragment()
            }
            1 -> {

                return StatusFragment()
            }
            2 -> {

                return CallsFragment()
            }

            else -> return ChatsFragment()
        }

    }


}