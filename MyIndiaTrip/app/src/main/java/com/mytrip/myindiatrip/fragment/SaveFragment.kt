package com.mytrip.myindiatrip.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.adapter.SaveAdapter
import com.mytrip.myindiatrip.databinding.FragmentSaveBinding
import com.mytrip.myindiatrip.model.ModelClass

class SaveFragment : Fragment() {


//     val list= ArrayList<SaveModelClass>()
    lateinit var mDbRef: DatabaseReference
lateinit var saveBinding: FragmentSaveBinding
var placeList=ArrayList<ModelClass>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        saveBinding= FragmentSaveBinding.inflate(layoutInflater, container, false)
        mDbRef = FirebaseDatabase.getInstance().getReference()

        initView()
        return saveBinding.root
    }

    private fun initView() {

    }

}

//class SaveModelClass (var key:String,var name:String,var image:String,var rating:String,var location:String,var save:Int){
//
//}
