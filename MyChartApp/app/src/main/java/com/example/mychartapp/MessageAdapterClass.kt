package com.example.mychartapp

import android.content.Context
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapterClass(var context: Context, var messageList: ArrayList<MessageModelClass>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemReceive = 1
    var itemSent = 2


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            var r = LayoutInflater.from(parent.context)
                .inflate(R.layout.recevie_message_item, parent, false)
            return ReceiveViewHolder(r)
        } else {
            var v = LayoutInflater.from(parent.context)
                .inflate(R.layout.sent_message_item, parent, false)
            return SentViewHolder(v)
        }
    }


    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var currentMessage = messageList[position]

        if (holder.javaClass == SentViewHolder::class.java) {
            //do stuff for sent view holder

            val viewHolder = holder as SentViewHolder
            holder.sentMessage.text = currentMessage.message
        } else {
            //do stuff for receive view holder

            val viewHolder = holder as ReceiveViewHolder
            holder.receiveMessage.text = currentMessage.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        var currentMessage = messageList[position]

        if (FirebaseAuth.getInstance().currentUser?.uid.equals(currentMessage.senderId)) {
            return itemSent
        } else {
            return itemReceive
        }
    }

    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sentMessage: TextView = itemView.findViewById(R.id.txtMessageSend)
    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var receiveMessage: TextView = itemView.findViewById(R.id.txtMessageReceive)
    }

}