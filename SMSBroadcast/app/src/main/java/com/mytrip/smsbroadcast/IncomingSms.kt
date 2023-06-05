package com.mytrip.smsbroadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

class IncomingSms: BroadcastReceiver() {
    // Get the object of SmsManager
    val sms: SmsManager = SmsManager.getDefault()
    override fun onReceive(context: Context?, intent: Intent?) {


        // Retrieves a map of extended data from the intent.

        // Retrieves a map of extended data from the intent.
        val bundle: Bundle? = intent!!.extras

        try {
            if (bundle != null) {
                val pdusObj = bundle.get("pdus") as Array<Any>
                for (i in pdusObj.indices) {
                    val currentMessage: SmsMessage =
                        SmsMessage.createFromPdu(pdusObj[i] as ByteArray)
                    val phoneNumber: String = currentMessage.getDisplayOriginatingAddress()
                    val message: String = currentMessage.getDisplayMessageBody()
                    Log.i("SmsReceiver", "senderNum: $phoneNumber; message: $message")


                    // Show Alert
                    val duration: Int = Toast.LENGTH_LONG
                    val toast: Toast = Toast.makeText(
                        context,
                        "senderNum: $phoneNumber, message: $message", duration
                    )
                    toast.show()
                } // end for loop
            } // bundle is null
        } catch (e: Exception) {
            Log.e("SmsReceiver", "Exception smsReceiver$e")
        }
    }
}