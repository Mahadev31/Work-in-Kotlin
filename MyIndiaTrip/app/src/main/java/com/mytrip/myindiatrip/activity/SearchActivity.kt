package com.mytrip.myindiatrip.activity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.mytrip.myindiatrip.adapter.SearchAdapter
import com.mytrip.myindiatrip.databinding.ActivitySearchBinding
import com.mytrip.myindiatrip.databinding.ProgressBarBinding
import com.mytrip.myindiatrip.model.SearchModelClass

class SearchActivity : AppCompatActivity() {
    lateinit var searchBinding: ActivitySearchBinding
    var placeList = ArrayList<SearchModelClass>()
    lateinit var searchAdapter: SearchAdapter
    lateinit var mDbRef: DatabaseReference
    lateinit var dialog:Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchBinding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(searchBinding.root)

        mDbRef = FirebaseDatabase.getInstance().getReference()

        initView()
    }

    private fun initView() {
        searchBinding.imgBackSearch.setOnClickListener {
          onBackPressed()
        }

        searchBinding.imgSearch.setOnClickListener {
            dialog = Dialog(this)
            var progressBarBinding = ProgressBarBinding.inflate(layoutInflater)
            dialog.setContentView(progressBarBinding.root)

            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window?.setLayout(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            dialog.show()

           var search = searchBinding.edtSearchText.text.toString()

        searchAdapter = SearchAdapter(this,placeList) {
            var clickIntent = Intent(this@SearchActivity, DataDisplayActivity::class.java)
            clickIntent.putExtra("search",search)
            clickIntent.putExtra("Key", it.key)
            Log.e("TAG", "initView: "+it.key )
            startActivity(clickIntent)
        }
            searchBinding.rcvSearchPlace.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        searchBinding.rcvSearchPlace.adapter=searchAdapter

        mDbRef.child("search_bar").child(search).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                placeList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(SearchModelClass::class.java)
                    currentUser?.let { placeList.add(it) }

                }
                searchAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        }
    }
}