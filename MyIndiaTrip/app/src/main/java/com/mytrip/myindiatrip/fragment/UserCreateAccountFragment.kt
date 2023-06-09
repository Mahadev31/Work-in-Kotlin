package com.mytrip.myindiatrip.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.mytrip.myindiatrip.databinding.FragmentUserCreateAccountBinding
import com.mytrip.myindiatrip.model.UserModelClass


class UserCreateAccountFragment : Fragment() {


    lateinit var createAccountBinding: FragmentUserCreateAccountBinding


    var RESULT_LOAD_IMAGE = 100
    lateinit var auth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createAccountBinding =
            FragmentUserCreateAccountBinding.inflate(layoutInflater, container, false)

        auth = Firebase.auth

        initView()
        // Inflate the layout for this fragment
        return createAccountBinding.root
    }

    private fun initView() {

        createAccountBinding.btnChooseImage.setOnClickListener(View.OnClickListener {
            var intent =  Intent()
            intent.type = "image/*";
            intent.action = Intent.ACTION_GET_CONTENT;
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE)
        })


        createAccountBinding.cdSignUp.setOnClickListener {
            var firstName = createAccountBinding.edtFirstC.text.toString()
            var lastName = createAccountBinding.edtLastNameC.text.toString()
            var email = createAccountBinding.edtEmailC.text.toString()
            var password = createAccountBinding.edtPasswordC.text.toString()

            if (firstName.isEmpty()) {
                Toast.makeText(
                    context,
                    "FirstName value is empty. please fill firstName ",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (lastName.isEmpty()) {
                Toast.makeText(
                    context,
                    "LastName value is empty. please fill LastName ",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (email.isEmpty()) {
                Toast.makeText(
                    context,
                    "email value is empty. please fill email ",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else if (password.isEmpty()) {
                Toast.makeText(
                    context,
                    "password value is empty. please fill password ",
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(context, "Account Created Successfully ", Toast.LENGTH_SHORT)
                            .show()
                        requireActivity().supportFragmentManager.beginTransaction()
                            .replace(com.mytrip.myindiatrip.R.id.container, UserLoginFragment())
                            .commit()

                        addUserToDatabase(firstName, lastName, email, auth.currentUser?.uid!!)
                    }
                }.addOnFailureListener {
                    Toast.makeText(context, "" + it.message, Toast.LENGTH_SHORT).show()
                }

            }

        }
        createAccountBinding.txtLoginPage.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(com.mytrip.myindiatrip.R.id.container, UserLoginFragment()).commit()
        }
    }


    private fun addUserToDatabase(firstName: String, lastName: String, email: String, uid: String) {
//        var save:Int=0
        mDbRef = FirebaseDatabase.getInstance().getReference()
var image=""
        mDbRef.child("user").child(uid).setValue(UserModelClass(image,firstName, lastName, email, uid))

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        for (fragment in childFragmentManager.fragments) {
            fragment.onActivityResult(requestCode, resultCode, data)
        }
    }


}


