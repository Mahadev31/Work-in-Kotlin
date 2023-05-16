package com.example.mychartapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychartapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    lateinit var mDbRef: DatabaseReference

    lateinit var mAuth: FirebaseAuth

    lateinit var userList: ArrayList<UserModelClass>
    lateinit var adapter: UserAdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        userList = ArrayList()
        adapter = UserAdapterClass(this, userList)
        initView()
    }

    private fun initView() {





        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mainBinding.rcvMessenger.adapter = adapter
        mainBinding.rcvMessenger.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mDbRef.child("user").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (postSnapshot in snapshot.children){
                    val currentUser=postSnapshot.getValue(UserModelClass::class.java)

                    if (mAuth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId  == R.id.logout){
            mAuth.signOut()
            finish()
        return true
        }
        return true
    }
}