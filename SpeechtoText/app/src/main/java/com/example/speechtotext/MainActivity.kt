package com.example.speechtotext

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.speechtotext.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    private val REQUEST_CODE_SPEECH_INPUT = 1
    var mp: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initView()

    }

    private fun initView() {


       mainBinding.imgMic.setOnClickListener {

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
                ) as ArrayList<String>
            mainBinding.tvSpeechToText.text = Objects.requireNonNull(result)[0]

                if (mainBinding.tvSpeechToText.text=="silent") {
                    vibratorFun()
                }else if (mainBinding.tvSpeechToText.text=="vibration"){
                    vibratorFun()
                }else if (mainBinding.tvSpeechToText.text=="sound"){
                    mp = MediaPlayer.create(this, R.raw.dog)
                    mp!!.start()
                }else if (mainBinding.tvSpeechToText.text=="play sound"){
                    mp = MediaPlayer.create(this, R.raw.movie)
                    mp!!.start()
                }else{
                    Toast.makeText(this, "Not Match", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }

    private fun vibratorFun() {
        var vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator!!.vibrate(
                VibrationEffect.createOneShot(1000, VibrationEffect.PARCELABLE_WRITE_RETURN_VALUE)
            )
        } else {
            //deprecated in API 26
            vibrator!!.vibrate(1000)
        }
    }
}