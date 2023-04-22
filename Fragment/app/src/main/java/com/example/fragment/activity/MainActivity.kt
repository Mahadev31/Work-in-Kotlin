package com.example.fragment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fragment.R
import com.example.fragment.databinding.ActivityMainBinding
import com.example.fragment.fragment.HomeFragment
import com.example.fragment.fragment.MusicFragment
import com.example.fragment.fragment.ProfileFragment
import com.example.fragment.fragment.SearchFragment

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        view()
    }

    private fun view() {
        lordFragment(HomeFragment())
        mainBinding.linHome.setOnClickListener {
            lordFragment(HomeFragment())
        }
        mainBinding.linMusic.setOnClickListener {
            lordFragment(MusicFragment())
        }
        mainBinding.linSearch.setOnClickListener {
            lordFragment(SearchFragment())
        }
        mainBinding.linProfile.setOnClickListener {
            lordFragment(ProfileFragment())
        }
    }

    fun lordFragment(fragment: Fragment) {
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        ft.replace(R.id.Show, fragment)
        ft.commit()
    }
}



