package com.example.speechtotext

import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var iv_mic: ImageView? = null
    private var tv_Speech_to_text: TextView? = null
    private val REQUEST_CODE_SPEECH_INPUT = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

    }

    private fun initView() {
        iv_mic = findViewById(R.id.iv_mic)
        tv_Speech_to_text = findViewById(R.id.tv_speech_to_text)

        iv_mic?.setOnClickListener { it: View? ->

            intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

         
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);





        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                var result: ArrayList<String> = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS
                ) as ArrayList<String>;
                tv_Speech_to_text?.setText(
                    Objects.requireNonNull(result).get(0)
                );
            }
        }
    }
}