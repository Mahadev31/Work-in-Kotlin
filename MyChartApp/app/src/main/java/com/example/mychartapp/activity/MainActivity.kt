package com.example.mychartapp.activity

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychartapp.R
import com.example.mychartapp.adapter.UserAdapterClass
import com.example.mychartapp.model.UserModelClass
import com.example.mychartapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

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

        setSupportActionBar(mainBinding.toolbarMain)
        mainBinding.toolbarMain.overflowIcon?.setTint(Color.WHITE)  //option manu 3 dot's color change
        supportActionBar?.setDisplayShowTitleEnabled(false)



        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mainBinding.rcvMessenger.adapter = adapter
        mainBinding.rcvMessenger.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(UserModelClass::class.java)

                    if (mAuth.currentUser?.uid != currentUser?.uid) {

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
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if (item.itemId == R.id.profile) {
            var profile = Intent(this, ProfileActivity::class.java)
            startActivity(profile)
        } else if (item.itemId == R.id.createGroup) {
            var create = Intent(this, CreateGroupActivity::class.java)
            startActivity(create)
        }  else if (item.itemId == R.id.logout) {
            var sharedPreferences = getSharedPreferences("MySharePref", MODE_PRIVATE)
            var myEdit: SharedPreferences.Editor = sharedPreferences.edit()
            myEdit.remove("isLogin")
            myEdit.commit()
            mAuth.signOut()
            finish()
            return true
        }
        return true
    }
}