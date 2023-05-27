package com.mytrip.myindiatrip.fragment

import android.Manifest
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.*
import com.mytrip.myindiatrip.activity.SearchLocationActivity
import com.mytrip.myindiatrip.adapter.HotelSearchAdapter
import com.mytrip.myindiatrip.databinding.FragmentMyTripPlanBinding
import com.mytrip.myindiatrip.databinding.ProgressBarBinding
import com.mytrip.myindiatrip.model.HotelSearchModelClass
import java.util.*


class MyTripPlanFragment : Fragment() {

    lateinit var tripBinding: FragmentMyTripPlanBinding

    lateinit var mDbRef: DatabaseReference

    lateinit var adapter: HotelSearchAdapter
    var hotelList = ArrayList<HotelSearchModelClass>()
lateinit var dialog:Dialog
    // Initialize variables
    var city:String=""
//     var search:String=""
    var client: FusedLocationProviderClient? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tripBinding = FragmentMyTripPlanBinding.inflate(layoutInflater, container, false)

        mDbRef = FirebaseDatabase.getInstance().getReference()

        dialog = Dialog(requireContext())
        var progressBarBinding = ProgressBarBinding.inflate(layoutInflater)
        dialog.setContentView(progressBarBinding.root)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.show()

        tripBinding.txtCurrentLocation.setOnClickListener {
            var i = Intent(context, SearchLocationActivity::class.java)
            startActivity(i)
        }
        // Initialize location client
        client = LocationServices
            .getFusedLocationProviderClient(
                requireActivity()
            )

        // check condition
        if (ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            // When permission is granted
            // Call method
            getCurrentLocation()
        } else {
            // When permission is not granted
            // Call method
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                100
            )
        }


        // Return view
        searchItem()
        return tripBinding.root
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
        // Check condition
        if (requestCode == 100 && grantResults.size > 0
            && (grantResults[0] + grantResults[1]
                    == PackageManager.PERMISSION_GRANTED)
        ) {
            // When permission are granted
            // Call  method
            getCurrentLocation()
        } else {
            // When permission are denied
            // Display toast
            Toast
                .makeText(
                    activity,
                    "Permission denied",
                    Toast.LENGTH_SHORT
                )
                .show()
        }
    }

    private fun getCurrentLocation() {
        // Initialize Location manager
        val locationManager = activity
            ?.getSystemService(
                Context.LOCATION_SERVICE
            ) as LocationManager
        // Check condition
        if (locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )
            || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )
        ) {
            // When location service is enabled
            // Get last location
            client!!.lastLocation.addOnCompleteListener(
                object : OnCompleteListener<Location?> {
                    override fun onComplete(
                        task: Task<Location?>
                    ) {

                        // Initialize location
                        val location: Location? = task.result
                        // Check condition
                        if (location != null) {
                            // When location result is not
                            // null set latitude

                            val geocoder: Geocoder = Geocoder(context!!, Locale.getDefault())
                            val addresses: List<Address>? = geocoder.getFromLocation(
                                location.latitude,
                                location.longitude,
                                1
                            ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                            var address =
                                addresses!![0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                             city = addresses!![0].locality
                            val state = addresses!![0].adminArea
                            val country = addresses!![0].countryName
                            val postalCode = addresses!![0].postalCode
                            val knownName = addresses!![0].featureName

                            tripBinding.txtCurrentLocation.text = city.toString()
//
                        } else {
                            // When location result is null
                            // initialize location request
                            val locationRequest: LocationRequest = LocationRequest()
                                .setPriority(
                                    LocationRequest.PRIORITY_HIGH_ACCURACY
                                )
                                .setInterval(10000)
                                .setFastestInterval(
                                    1000
                                )
                                .setNumUpdates(1)

                            // Initialize location call back
                            val locationCallback: LocationCallback = object : LocationCallback() {
                                override fun onLocationResult(
                                    locationResult: LocationResult
                                ) {
                                    // Initialize
                                    // location
                                    val location1: Location? = locationResult
                                        .lastLocation
                                    // Set latitude
//
                                    val geocoder: Geocoder =
                                        Geocoder(context!!, Locale.getDefault())
                                    val addresses: List<Address>? = geocoder.getFromLocation(
                                        location?.latitude!!, location?.longitude!!, 1
                                    ) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                                    var address =
                                        addresses!![0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                                     city = addresses!![0].locality
                                    val state = addresses!![0].adminArea
                                    val country = addresses!![0].countryName
                                    val postalCode = addresses!![0].postalCode
                                    val knownName = addresses!![0].featureName

                                    tripBinding.txtCurrentLocation.text = city.toString()
                                }
                            }

                            // Request location updates
                            if (ActivityCompat.checkSelfPermission(
                                    requireContext(),
                                    Manifest.permission.ACCESS_FINE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                    requireContext(),
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                ) != PackageManager.PERMISSION_GRANTED
                            ) {

                                return
                            }
                            client!!.requestLocationUpdates(
                                locationRequest,
                                locationCallback,
                                Looper.myLooper()
                            )
                        }
                    }
                })
        } else {
            // When location service is not enabled
            // open location setting
            startActivity(
                Intent(
                    Settings.ACTION_LOCATION_SOURCE_SETTINGS
                )
                    .setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK
                    )
            )
        }
    }


    private fun searchItem() {

        setByDefualtAdapter()

        tripBinding.edtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                setAdapter()
            }
            true
        }
        tripBinding.imgSearchT.setOnClickListener {
            setAdapter()


        }
    }


    private fun setAdapter() {
        dialog = Dialog(requireContext())
        var progressBarBinding = ProgressBarBinding.inflate(layoutInflater)
        dialog.setContentView(progressBarBinding.root)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        dialog.show()

        var search: String? = null
            search = tripBinding.edtSearch.text.toString()

        if (search.isEmpty()) {
            Toast.makeText(context, "Pleas Enter value", Toast.LENGTH_SHORT).show()
        } else {


            adapter = HotelSearchAdapter(this, hotelList)
            tripBinding.rcvSuggestionItem.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            tripBinding.rcvSuggestionItem.adapter = adapter

            mDbRef.child("hotels").child(search).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    hotelList.clear()
                    for (postSnapshot in snapshot.children) {
                        val currentUser = postSnapshot.getValue(HotelSearchModelClass::class.java)
                        hotelList.add(currentUser!!)
                        Log.e("TAG", "search: " + currentUser.hotelImage)

                    }
                    dialog.dismiss()
                    adapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("TAG", "onCancelled: ")
                }

            })
        }
    }

    private fun setByDefualtAdapter() {
        adapter = HotelSearchAdapter(this, hotelList)
        tripBinding.rcvSuggestionItem.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        tripBinding.rcvSuggestionItem.adapter = adapter

        mDbRef.child("hotels").child("surat").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                hotelList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(HotelSearchModelClass::class.java)
                    hotelList.add(currentUser!!)
                    Log.e("TAG", "search: " + currentUser.hotelImage)

                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("TAG", "onCancelled: ")
            }

        })
    }
}