package com.mytrip.myindiatrip.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.databinding.FragmentUserCreateAccountBinding
import com.mytrip.myindiatrip.databinding.FragmentUserLoginBinding

class UserCreateAccountFragment : Fragment() {


    lateinit var createAccountFragment: FragmentUserCreateAccountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createAccountFragment= FragmentUserCreateAccountBinding.inflate(layoutInflater,container,false)

        initView()
        // Inflate the layout for this fragment
        return createAccountFragment.root
    }

    private fun initView() {

        createAccountFragment.txtLoginPage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(com.mytrip.myindiatrip.R.id.container, UserLoginFragment()).commit()
        }
    }


}