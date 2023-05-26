package com.mytrip.razorpaypaymentgateway

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject


class MainActivity : AppCompatActivity() , PaymentResultListener {

    private lateinit var amountEdt: EditText
    private lateinit var payBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initializing all our variables.
        // initializing all our variables.
        amountEdt = findViewById(R.id.idEdtAmount)
        payBtn = findViewById(R.id.idBtnPay)

        // adding on click listener to our button.

        // adding on click listener to our button.
        payBtn.setOnClickListener{
                // on below line we are getting
                // amount that is entered by user.
                val samount = amountEdt.getText().toString()

                // rounding off the amount.
                val amount = Math.round(samount.toFloat() * 100)

                // initialize Razorpay account.
                val checkout = Checkout()

                // set your id as below
                checkout.setKeyID("rzp_test_VvywymDefJZC9R")

                // set image
                checkout.setImage(R.drawable.logo)

                // initialize json object
                val `object` = JSONObject()
                try {
                    // to put name
                    `object`.put("name", "Razorpay payment")

                    // put description
                    `object`.put("description", "Test payment")

                    // to set theme color
                    `object`.put("theme.color", "")

                    // put the currency
                    `object`.put("currency", "INR")

                    // put amount
                    `object`.put("amount", amount)

                    // put mobile number
                    `object`.put("prefill.contact", "9284064503")

                    // put email
                    `object`.put("prefill.email", "chaitanyamunje@gmail.com")

                    // open razorpay to checkout activity
                    checkout.open(this@MainActivity, `object`)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

    }

    override fun onPaymentSuccess(p0: String?) {
        // this method is called on payment success.
        Toast.makeText(this, "Payment is successful : " + p0, Toast.LENGTH_SHORT).show();
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : " + p1, Toast.LENGTH_SHORT).show()
    }


}