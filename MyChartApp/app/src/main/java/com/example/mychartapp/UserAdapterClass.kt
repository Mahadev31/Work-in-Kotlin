package com.example.mychartapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class UserAdapterClass(var context: Context, var userList: ArrayList<UserModelClass>) :
    RecyclerView.Adapter<UserAdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtName: TextView = itemView.findViewById(R.id.txt_name)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var user = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_layout, parent, false)
        return MyViewHolder(user)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentUser = userList[position]
        holder.txtName.text =currentUser.name

        holder.itemView.setOnClickListener{
            var i=Intent(context,ChatActivity::class.java)
            i.putExtra("name",currentUser.name)
            i.putExtra("uid",currentUser?.uid)
            context.startActivity(i)
        }

    }
}