package com.mytrip.myindiatrip.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.mytrip.myindiatrip.adapter.ChildImageSliderAdapter
import com.mytrip.myindiatrip.databinding.ActivityHotelAndDataBinding
import com.mytrip.myindiatrip.fragment.MapsFragment
import com.mytrip.myindiatrip.model.ModelClass
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject


class HotelAndActivityDataActivity : AppCompatActivity() , PaymentResultListener {

    lateinit var hotelAndDataBinding: ActivityHotelAndDataBinding
    lateinit var mDbRef: DatabaseReference
    var childSliderList=ArrayList<ModelClass>()
    lateinit var search:String
    lateinit var selectItemName: String
    lateinit var child_key:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hotelAndDataBinding= ActivityHotelAndDataBinding.inflate(layoutInflater)
        setContentView(hotelAndDataBinding.root)
        mDbRef = FirebaseDatabase.getInstance().getReference()

        initView()
        payment()
    }



    private fun initView() {
        hotelAndDataBinding.imgBackDisplay.setOnClickListener {
            onBackPressed()
        }
        search = intent.getStringExtra("search").toString()
        selectItemName = intent.getStringExtra("selectItemName").toString()
        var key = intent.getStringExtra("Key").toString()
        child_key = intent.getStringExtra("child_key").toString()


        // Declaring fragment manager from making data
        // transactions using the custom fragment
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = MapsFragment()


        var  childSliderAdapter = ChildImageSliderAdapter(this, childSliderList)
        hotelAndDataBinding.viewPager.adapter = childSliderAdapter
        hotelAndDataBinding.wormDotsIndicator.attachTo(hotelAndDataBinding.viewPager)

        mDbRef.child("my_trip_plan").child(search).child(selectItemName)
        .child(key).child("slider").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                childSliderList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(ModelClass::class.java)
                    childSliderList.add(currentUser!!)

                }
                childSliderAdapter.notifyDataSetChanged()

                val mBundle = Bundle()
                mBundle.putString("search",search)
                mBundle.putString("selectItemName",selectItemName)
                mBundle.putString("Key",key)
                mBundle.putBoolean("myTrip",true)
                mFragment.arguments = mBundle
                mFragmentTransaction.add(com.mytrip.myindiatrip.R.id.frameMap, mFragment).commit()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })




        mDbRef.child("my_trip_plan").child(search).child(selectItemName)
            .child(key)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    var name = snapshot.child("name").value.toString()
                    var rating = snapshot.child("rating").value.toString()
                    var rent = snapshot.child("rent").value.toString()
                    var location = snapshot.child("location").value.toString()
                    var description = snapshot.child("description").value.toString()



                    hotelAndDataBinding.txtTitle.text = name
                    hotelAndDataBinding.txtRating.text = rating
                    hotelAndDataBinding.txtLocation.text = location
                    hotelAndDataBinding.txtRent.text = rent
                    hotelAndDataBinding.txtDescription.text = description
//                searchAdapter.notifyDataSetChanged()
                    Log.e("Try", "key: " + key)
                    Log.e("Try", "child_key: " + child_key)
                    Log.e("Try", "name: " + name)
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
    }

    private fun payment() {
// adding on click listener to our button.
        // adding on click listener to our button.
        hotelAndDataBinding.btnAddToCart.setOnClickListener{
                // on below line we are getting
                // amount that is entered by user.
//                val samount = hotelAndDataBinding.txtRent.text.toString()
                val samount =8723

                // rounding off the amount.
                val amount = Math.round(samount.toFloat() * 100)
//                val amount = Math.round(8723 * 100)

                // initialize Razorpay account.
                val checkout = Checkout()

                // set your id as below
                checkout.setKeyID("rzp_test_VvywymDefJZC9R")

                // set image
                checkout.setImage(com.mytrip.myindiatrip.R.drawable.logo)

                // initialize json object
                val `object` = JSONObject()
                try {
                    // to put name
                    `object`.put("name", "My India Trip")

                    // put description
                    `object`.put("description", " payment")

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
                    checkout.open(this@HotelAndActivityDataActivity, `object`)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }

    }

  override  fun onPaymentSuccess(s: String) {
        // this method is called on payment success.
        Toast.makeText(this, "Payment is successful : $s", Toast.LENGTH_SHORT).show()
    }

   override fun onPaymentError(i: Int, s: String) {
        // on payment failed.
        Toast.makeText(this, "Payment Failed due to error : $s", Toast.LENGTH_SHORT).show()
    }
}