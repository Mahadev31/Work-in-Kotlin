package com.example.recycleviewkotlin.adapterclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewkotlin.R
import com.example.recycleviewkotlin.modelclass.CodeCountryModel

class CodeCountryAdapterClass(
    var countryList: ArrayList<CodeCountryModel> = ArrayList<CodeCountryModel>(),  //create Adapter class and pass parameter using constructor
    var n: (Int, Int, String) -> Unit  //create invoke methode
) : RecyclerView.Adapter<CodeCountryAdapterClass.MyViewHolder>() {  //create View holder
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img: ImageView = view.findViewById(R.id.imgFlag)
        var txtCode: TextView = view.findViewById(R.id.txtCode)
        var txtName: TextView = view.findViewById(R.id.txtName)
        var layout: LinearLayout = view.findViewById(R.id.layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.code_country_list, parent, false)  //set xml file for show data
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size  // set array list  size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.img.setImageResource(countryList[position].img)
        holder.txtCode.setText(countryList[position].code.toString())
        holder.txtName.setText(countryList[position].name)

        holder.layout.setOnClickListener(View.OnClickListener {
//            listener.onClick(countryList[position].img,countryList[position].code,countryList[position].name)
            n.invoke(    //set invoke methode
                countryList[position].img,
                countryList[position].code,
                countryList[position].name
            )
        })
    }
}