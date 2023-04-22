package com.example.myfoodapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myfoodapp.fragment.HomeFragment
import com.example.myfoodapp.fragment.MenuFragment
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.ActivityMainBinding
import com.example.myfoodapp.fragment.CartFragment
import com.example.myfoodapp.fragment.ProfileFragment

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
        mainBinding.linHome.setOnClickListener{
            lordFragment(HomeFragment())
        }
        mainBinding.linMenu.setOnClickListener{
            lordFragment(MenuFragment())
        }
        mainBinding.linCart.setOnClickListener{
            lordFragment(CartFragment())
        }
        mainBinding.linProfile.setOnClickListener{
            lordFragment(ProfileFragment())
        }


    }

     fun lordFragment(fragment :Fragment){
        val fm :FragmentManager=supportFragmentManager
        val ft:FragmentTransaction=fm.beginTransaction()
        ft.replace(R.id.Show,fragment)
        ft.commit()
    }
}