package com.example.mychartapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mychartapp.R
import com.example.mychartapp.model.UserModelClass
import com.example.mychartapp.activity.ChatActivity

class UserAdapterClass(var context: Context, var userList: ArrayList<UserModelClass>) :
    RecyclerView.Adapter<UserAdapterClass.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtFirstName: TextView = itemView.findViewById(R.id.txtFirstName)
        var txtLastName: TextView = itemView.findViewById(R.id.txtLastName)


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
        holder.txtFirstName.text =currentUser.firstName
        holder.txtLastName.text =currentUser.lastName

        holder.itemView.setOnClickListener{
            var i=Intent(context, ChatActivity::class.java)
            i.putExtra("firstName",currentUser.firstName)
            i.putExtra("lastName",currentUser.lastName)
            i.putExtra("uid",currentUser?.uid)
            context.startActivity(i)
        }

    }
}