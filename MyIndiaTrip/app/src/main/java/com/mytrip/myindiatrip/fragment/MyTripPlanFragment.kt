package com.mytrip.myindiatrip.fragment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.mytrip.myindiatrip.activity.CurrentLocationActivity
import com.mytrip.myindiatrip.databinding.FragmentMyTripPlanBinding
import java.util.*


class MyTripPlanFragment : Fragment() {

    lateinit var tripBinding: FragmentMyTripPlanBinding

    // Initialize variables
    var btLocation: Button? = null
//    var tvLatitude: TextView? = null
//    var tvLongitude:TextView? = null
    var client: FusedLocationProviderClient? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tripBinding = FragmentMyTripPlanBinding.inflate(layoutInflater, container, false)


        // Initialize location client

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
        initView()
        return tripBinding.root
    }

    private fun initView() {

        tripBinding.cdCurrentLocation.setOnClickListener {
            var i = Intent(context, CurrentLocationActivity::class.java)
            startActivity(i)
        }
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
                        val location: Location = task.getResult()!!
                        // Check condition
                        if (location != null) {
                            // When location result is not
                            // null set latitude

                            val geocoder: Geocoder = Geocoder(context!!, Locale.getDefault())
                            val addresses: List<Address>? = geocoder.getFromLocation(location.latitude,  location.longitude, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                        var     address =
                                addresses!![0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                            val city = addresses!![0].locality
                            val state = addresses!![0].adminArea
                            val country = addresses!![0].countryName
                            val postalCode = addresses!![0].postalCode
                            val knownName = addresses!![0].featureName

                            tripBinding.txtCurrentLocation.text=address.toString()
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
//                                    tvLatitude?.setText(
//                                        java.lang.String.valueOf(
//                                            location1
//                                                ?.getLatitude()
//                                        )
//                                    )
//                                    // Set longitude
//                                    tvLongitude?.setText(
//                                        java.lang.String.valueOf(
//                                            location1
//                                                ?.getLongitude()
//                                        )
//                                    )
//                                    val myLocation = LatLng(  location.latitude,  location.longitude)
                                    val geocoder: Geocoder = Geocoder(context!!, Locale.getDefault())
                                    val addresses: List<Address>? = geocoder.getFromLocation(
                                        location.latitude ,  location.longitude, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                                    var     address =
                                        addresses!![0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                                    val city = addresses!![0].locality
                                    val state = addresses!![0].adminArea
                                    val country = addresses!![0].countryName
                                    val postalCode = addresses!![0].postalCode
                                    val knownName = addresses!![0].featureName

                                    tripBinding.txtCurrentLocation.text=city.toString()
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
                                //  Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
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
}