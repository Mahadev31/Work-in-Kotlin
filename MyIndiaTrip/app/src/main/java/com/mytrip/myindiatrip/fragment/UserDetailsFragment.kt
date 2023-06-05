package com.mytrip.myindiatrip.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.activity.MainActivity
import com.mytrip.myindiatrip.databinding.FragmentUserDetailsBinding


class UserDetailsFragment : Fragment() {

lateinit var userDetailsBinding: FragmentUserDetailsBinding

    lateinit var mDbRef: DatabaseReference
    lateinit var user: FirebaseUser
    lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userDetailsBinding= FragmentUserDetailsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference("user")
        user=mAuth.currentUser!!


        initView()
        return  userDetailsBinding.root
    }

    private fun initView() {


        var query: Query =mDbRef.orderByChild("email").equalTo(user.email)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    var firstName =postSnapshot.child("firstName").value
                    var lastName =postSnapshot.child("lastName").value

                    userDetailsBinding.txtFirstName.text= firstName.toString()
                    userDetailsBinding.txtLastName.text= lastName.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

        userDetailsBinding.linLogout.setOnClickListener {
            var sharedPreferences = requireActivity().getSharedPreferences("MySharePref",
                AppCompatActivity.MODE_PRIVATE
            )
            var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
            myEdit.remove("isLogin")
            myEdit.commit()
            mAuth.signOut()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(com.mytrip.myindiatrip.R.id.container, ProfileFragment())
                .commit()
        }
    }

}