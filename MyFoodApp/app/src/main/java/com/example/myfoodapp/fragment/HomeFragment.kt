package com.example.myfoodapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfoodapp.R
import com.example.myfoodapp.adapterclass.HGridAdapterClass
import com.example.myfoodapp.adapterclass.HomeAdapterClass
import com.example.myfoodapp.modelclass.HGridModelClass
import com.example.myfoodapp.modelclass.HomeModelClass

class HomeFragment : Fragment() {

    var imageList = ArrayList<Int>()
    var nameList = ArrayList<String>()
    var horizontalList = ArrayList<HomeModelClass>()
    lateinit var recycleViewHorizontal: RecyclerView

    //    Grid
    var gImageList = ArrayList<Int>()
    var gNameList = ArrayList<String>()
    var gPriceList = ArrayList<Int>()
    var gridList = ArrayList<HGridModelClass>()
    lateinit var recycleViewGrid: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)


    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        infoHorizontal()
        recycleViewHorizontal = itemView.findViewById(R.id.recycleViewHorizontal)
        val adapterClass = HomeAdapterClass(context, horizontalList)

        recycleViewHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recycleViewHorizontal.setAdapter(adapterClass)

//gridView
        infoGridView()
        recycleViewGrid = itemView.findViewById(R.id.recycleViewGrid)
        val adapterClass1 = HGridAdapterClass(context, gridList)

        recycleViewGrid.setLayoutManager(
            GridLayoutManager(context, 3)
        )
        recycleViewGrid.setAdapter(adapterClass1)
    }

    private fun infoHorizontal() {


        imageList.add(R.drawable.pizza)
        imageList.add(R.drawable.burger)
        imageList.add(R.drawable.pasta)
        imageList.add(R.drawable.sandvic)
        imageList.add(R.drawable.beverages)
        imageList.add(R.drawable.dessert)

        nameList.add("pizza")
        nameList.add("burger")
        nameList.add("pasta")
        nameList.add("sandvic")
        nameList.add("beverages")
        nameList.add("dessert")


        for (i in 0..imageList.size - 1) {
            val model = HomeModelClass(imageList[i], nameList[i])

            horizontalList.add(model)
        }

    }

    private fun infoGridView() {


        gImageList.add(R.drawable.paneer_pizza)
        gImageList.add(R.drawable.udupi_special_huli_dosa)
        gImageList.add(R.drawable.salads)
        gImageList.add(R.drawable.margherita_pizza)
        gImageList.add(R.drawable.burger)
        gImageList.add(R.drawable.brownie_milkshake)

        gNameList.add("Paneer pizza")
        gNameList.add("Udupi Special Huli Dosa")
        gNameList.add("Healthy Barley and Salad")
        gNameList.add("Margherita  pizza")
        gNameList.add("Chese Burger")
        gNameList.add("Brownie Milkshake")

        gPriceList.add(576)
        gPriceList.add(656)
        gPriceList.add(546)
        gPriceList.add(756)
        gPriceList.add(566)
        gPriceList.add(366)

        for (i in 0..gImageList.size - 1) {
            val model1 = HGridModelClass(gImageList[i], gNameList[i], gPriceList[i])

            gridList.add(model1)
        }

    }

}