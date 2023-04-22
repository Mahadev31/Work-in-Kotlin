package com.example.recycleviewkotlin.adapterclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleviewkotlin.ClickListener
import com.example.recycleviewkotlin.R
import com.example.recycleviewkotlin.modelclass.ModelClass

class AdapterCalss(var list: ArrayList<ModelClass>,var listener: ClickListener) :
    RecyclerView.Adapter<AdapterCalss.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var txtId: TextView = view.findViewById(R.id.txtId)
        var txtName: TextView = view.findViewById(R.id.txtName)
        var layoutView: LinearLayout = view.findViewById(R.id.layoutView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_list, parent, false)

        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.txtId.setText(list.get(position).id.toString())
        holder.txtName.setText(list.get(position).name.toString())
        holder.layoutView.setOnClickListener(View.OnClickListener {
            listener.onClick(list.get(position).id,list.get(position).name)
        }




        )
    }
}