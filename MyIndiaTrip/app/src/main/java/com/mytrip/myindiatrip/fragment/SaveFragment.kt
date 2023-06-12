package com.mytrip.myindiatrip.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.activity.DataDisplayActivity
import com.mytrip.myindiatrip.adapter.SaveAdapter
import com.mytrip.myindiatrip.databinding.FragmentSaveBinding
import com.mytrip.myindiatrip.model.ModelClass

class SaveFragment : Fragment() {


//     val list= ArrayList<SaveModelClass>()

    lateinit var saveBinding: FragmentSaveBinding

    lateinit var mDbRef: DatabaseReference
    lateinit var auth: FirebaseAuth
    var placeList = ArrayList<ModelClass>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        saveBinding = FragmentSaveBinding.inflate(layoutInflater, container, false)
        mDbRef = FirebaseDatabase.getInstance().getReference()
        auth = Firebase.auth
        initView()
        return saveBinding.root
    }

    private fun initView() {

        saveBinding.tabLayout.addTab(saveBinding.tabLayout.newTab().setText("place"))  //tabLayout
        saveBinding.tabLayout.addTab(saveBinding.tabLayout.newTab().setText("hotel"))//tabLayout
        saveBinding.tabLayout.addTab(saveBinding.tabLayout.newTab().setText("activity"))//tabLayout

        var search: String = "surat"
        var selectItemName: String = "place"
        var adapter = SaveAdapter(requireContext(), {

            var clickIntent = Intent(context, DataDisplayActivity::class.java)
            clickIntent.putExtra("search", search)
            clickIntent.putExtra("selectItemName", selectItemName)
            clickIntent.putExtra("Key", it.key)
            clickIntent.putExtra("myTrip", true)
            Log.e("TAG", "myTripKey: " + it.key)
            Log.e("TAG", "myTrip_selected: " + selectItemName)
            startActivity(clickIntent)
        }, { save, key ->


            mDbRef.child("my_trip_plan").child(search!!).child(selectItemName).child(key)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {


                        var name = snapshot.child("name").value.toString()
                        var image = snapshot.child("image").value.toString()
                        var location = snapshot.child("location").value.toString()
                        var description = snapshot.child("description").value.toString()
                        var rent = snapshot.child("rent").value.toString()
                        var rating = snapshot.child("rating").value.toString()

                        Log.e("TAG", "onDataChange:name " + name)

//                mDbRef.child("my_trip_plan").child(search).child(selectItemName).child(key)
//                    .child("save_data").child(auth.currentUser?.uid!!).child("place")
//                    .child(key).setValue(
//                        SaveModelClass(
//                            name,
//                            image,
//                            location,
//                            description,
//                            rating,
//                            rent,
//                            key, save
//                        )
//                    )

                        mDbRef.child("user").child(auth.currentUser?.uid!!).child("save_data")
                            .child("place").child(key).setValue(
                                SaveModelClass(
                                    name,
                                    image,
                                    location,
                                    description,
                                    rating,
                                    rent,
                                    key,
                                    save
                                )
                            )
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })
        })
        saveBinding.rcvSaveList.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        saveBinding.rcvSaveList.adapter = adapter

        mDbRef.child("user").child(auth.currentUser?.uid!!).child("save_data")
            .child("place")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    placeList.clear()
                    for (postSnapshot in snapshot.children) {
                        val currentUser =
                            postSnapshot.getValue(ModelClass::class.java)
                        currentUser?.let { placeList.add(it) }

                    }
                    adapter.updateList(placeList)

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

}


