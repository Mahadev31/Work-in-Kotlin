package com.mytrip.myindiatrip.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.*
import com.mytrip.myindiatrip.adapter.ChildImageSliderAdapter
import com.mytrip.myindiatrip.databinding.ActivityHotelAndDataBinding
import com.mytrip.myindiatrip.model.ModelClass

class HotelAndActivityDataActivity : AppCompatActivity() {

    lateinit var hotelAndDataBinding: ActivityHotelAndDataBinding
    lateinit var mDbRef: DatabaseReference
    var childSliderList=ArrayList<ModelClass>()
    lateinit var search:String
    lateinit var selectItemName: String
    lateinit var child_key:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hotelAndDataBinding= ActivityHotelAndDataBinding.inflate(layoutInflater)
        setContentView(hotelAndDataBinding.root)
        mDbRef = FirebaseDatabase.getInstance().getReference()
        initView()
    }

    private fun initView() {
        hotelAndDataBinding.imgBackDisplay.setOnClickListener {
            onBackPressed()
        }
        search = intent.getStringExtra("search").toString()
        selectItemName = intent.getStringExtra("selectItemName").toString()
        var key = intent.getStringExtra("Key").toString()
        child_key = intent.getStringExtra("child_key").toString()
//        var title=""

        var  childSliderAdapter = ChildImageSliderAdapter(this, childSliderList)
        hotelAndDataBinding.viewPager.adapter = childSliderAdapter
        hotelAndDataBinding.wormDotsIndicator.attachTo(hotelAndDataBinding.viewPager)

        mDbRef.child("my_trip_plan").child(search).child(selectItemName)
        .child(key).child("slider").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                childSliderList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(ModelClass::class.java)
                    childSliderList.add(currentUser!!)

                }
                childSliderAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })




        mDbRef.child("my_trip_plan").child(search).child(selectItemName)
            .child(key)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    var name = snapshot.child("name").value.toString()
                    var rating = snapshot.child("rating").value.toString()
                    var rent = snapshot.child("rent").value.toString()
                    var location = snapshot.child("location").value.toString()
                    var description = snapshot.child("description").value.toString()



                    hotelAndDataBinding.txtTitle.text = name
                    hotelAndDataBinding.txtRating.text = rating
                    hotelAndDataBinding.txtLocation.text = location
                    hotelAndDataBinding.txtRent.text = rent
                    hotelAndDataBinding.txtDescription.text = description
//                searchAdapter.notifyDataSetChanged()
                    Log.e("Try", "key: " + key)
                    Log.e("Try", "child_key: " + child_key)
                    Log.e("Try", "name: " + name)
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }
}