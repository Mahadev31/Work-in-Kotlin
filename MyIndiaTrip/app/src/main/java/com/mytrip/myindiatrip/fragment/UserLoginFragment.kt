package com.mytrip.myindiatrip.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mytrip.myindiatrip.databinding.FragmentUserLoginBinding

class UserLoginFragment : Fragment() {

    lateinit var userLoginBinding: FragmentUserLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userLoginBinding=FragmentUserLoginBinding.inflate(layoutInflater,container,false)

        initView()
        // Inflate the layout for this fragment
        return userLoginBinding.root
    }

    private fun initView() {

        userLoginBinding.txtCreateAccountPage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(com.mytrip.myindiatrip.R.id.container, UserCreateAccountFragment()).commit()
        }
    }


}