package com.mytrip.myindiatrip.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.databinding.ActivityDataDisplayBinding
import com.mytrip.myindiatrip.model.PopularModelClass
import com.mytrip.myindiatrip.model.SearchModelClass
import java.util.*

class DataDisplayActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var displayBinding:ActivityDataDisplayBinding

    private var textToSpeech: TextToSpeech? = null
    lateinit var mDbRef: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayBinding=ActivityDataDisplayBinding.inflate(layoutInflater)
        setContentView(displayBinding.root)

        mDbRef = FirebaseDatabase.getInstance().getReference()
        initView()
    }

    private fun initView() {

        var search=intent.getStringExtra("search").toString()
        var key=intent.getStringExtra("Key").toString()
//        var title=""

        mDbRef.child("search_bar").child(search).child(key).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

               var image = snapshot.child("placeImage").value.toString()
               var name = snapshot.child("placeName").value.toString()
               var rating = snapshot.child("placeRating").value.toString()
               var description = snapshot.child("placeDescription").value.toString()
               var location = snapshot.child("placeLocation").value.toString()

                Glide.with(this@DataDisplayActivity).load(image)
                    .placeholder(R.drawable.ic_image).into(displayBinding.imgPlaceDisplay)

                displayBinding.txtPlaceTitle.text = name
                displayBinding.txtPlaceRating.text = rating
                displayBinding.txtPlaceDescription.text = description
                displayBinding.txtPlaceLocation.text = location
//                searchAdapter.notifyDataSetChanged()
                Log.e("Try", "search: "+search )
                Log.e("Try", "key: "+key )
                Log.e("Try", "title: "+title )
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        displayBinding.imgVolumeSpeech!!.isEnabled = false
        textToSpeech = TextToSpeech(this, this)


        displayBinding.imgVolumeSpeech!!.setOnClickListener { speakOut() }

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech!!.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            } else {
                displayBinding.imgVolumeSpeech!!.isEnabled = true
            }
        }
    }
    private fun speakOut() {
        val text = displayBinding.txtPlaceDescription!!.text.toString()
        textToSpeech!!.speak(text, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    public override fun onDestroy() {
        // Shutdown TTS when
        // activity is destroyed
        if (textToSpeech != null) {
            textToSpeech!!.stop()
            textToSpeech!!.shutdown()
        }
        super.onDestroy()
    }


}