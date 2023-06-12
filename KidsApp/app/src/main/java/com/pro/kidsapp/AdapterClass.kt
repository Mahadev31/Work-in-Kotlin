package com.pro.kidsapp

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class AdapterClass(var context: Context, var list: ArrayList<ModelClass>) :
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {

    private var tts: TextToSpeech? = null

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.txtAlphabet)
        var layout: LinearLayout = itemView.findViewById(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.alphabet_item_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       holder.text.text = list[position].text
//        var text=holder.text
            holder.layout.setOnClickListener {

            Toast.makeText(context, "Click", Toast.LENGTH_SHORT).show()

            tts = TextToSpeech(context) { status ->
                // edit from original answer: I put double equal on this line
                if (status == TextToSpeech.SUCCESS) {
                    tts?.language = Locale.UK
                    tts!!.speak(  list[position].description, TextToSpeech.QUEUE_FLUSH, null,"")
                }
            }
        }


    }

}
