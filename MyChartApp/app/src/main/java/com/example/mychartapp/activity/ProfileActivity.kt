package com.example.mychartapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mychartapp.databinding.ActivityProfileBinding
import com.example.mychartapp.model.UserModelClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ProfileActivity : AppCompatActivity() {

    lateinit var profileBinding: ActivityProfileBinding

//    lateinit var userList: ArrayList<UserModelClass>
    lateinit var mDbRef: DatabaseReference
    lateinit var user: FirebaseUser
    lateinit var firebaseDatabase: FirebaseDatabase

    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        profileBinding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(profileBinding.root)

        initView()
    }

    private fun initView() {
        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference("user")
        user=mAuth.currentUser!!

        var query:Query=mDbRef.orderByChild("email").equalTo(user.email)
        query.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    var firstName =postSnapshot.child("firstName").value
                    var lastName =postSnapshot.child("lastName").value
                    var email =postSnapshot.child("email").value

                    profileBinding.txtFistName.text= firstName.toString()
                    profileBinding.txtLastName.text= lastName.toString()
                    profileBinding.txtEmail.text= email.toString()
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })

    }
}