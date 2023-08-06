package com.demo.resumeusingcomponents.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.resumeusingcomponents.R
import com.demo.resumeusingcomponents.modelclass.ModelClass
import java.util.ArrayList

class MainAdapterClass(var list: ArrayList<ModelClass>,
                       var click:(ModelClass)->Unit, var edit:(ModelClass)->Unit, var delet:(Int)->Unit) : RecyclerView.Adapter<MainAdapterClass.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fName: TextView = itemView.findViewById(R.id.txt_fNameD)
        var lName: TextView = itemView.findViewById(R.id.txt_lNameD)
        var mNumber: TextView = itemView.findViewById(R.id.txt_mNumberD)
        var address: TextView = itemView.findViewById(R.id.txt_addressD)
        var imgEdtD: ImageView = itemView.findViewById(R.id.imgEdtD)
        var imgDeleteD: ImageView = itemView.findViewById(R.id.imgDeleteD)
        var img_dpD: ImageView = itemView.findViewById(R.id.img_dpD)
        var lin_layout: LinearLayout = itemView.findViewById(R.id.lin_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.rcv_main_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.fName.text = list[position].firstName
        holder.lName.text = list[position].lastName
        holder.mNumber.text = list[position].mobileNumber
        holder.address.text = list[position].address
//        holder.img_dpD.setImageResource(list[position].img)


        Log.e("adapter", "first name:"+holder.fName)
        Log.e("adapter", "last name: "+holder.lName)
        Log.e("adapter", "mobil number: "+holder.mNumber)
        Log.e("adapter", "address: "+holder.address)

        holder.lin_layout.setOnClickListener{
            click.invoke(list[position])
        }
        holder.imgEdtD.setOnClickListener{
            edit.invoke(list[position])
        }
        holder.imgDeleteD.setOnClickListener{
            delet.invoke(list[position].id)
        }
    }
//
    fun updateList(list: ArrayList<ModelClass>) {
        this.list = ArrayList()
        this.list.addAll(list)
        notifyDataSetChanged()
    }
}