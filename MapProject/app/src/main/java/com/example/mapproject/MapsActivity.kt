package com.example.mapproject

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mapproject.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var address: String = " "

    var latitude: Double = 0.0
    var longitude: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initView()
    }

    private fun initView() {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        binding.btnSearch.setOnClickListener {
        mMap = googleMap




            latitude = binding.edtLatitude.text.toString().toDouble()
            longitude = binding.edtLongitude.text.toString().toDouble()
            // Add a marker in Sydney and move the camera
            val sydney = LatLng(latitude, longitude)

            val geocoder: Geocoder = Geocoder(this, Locale.getDefault())
            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

             address =
                addresses!![0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

            val city = addresses!![0].locality
            val state = addresses!![0].adminArea
            val country = addresses!![0].countryName
            val postalCode = addresses!![0].postalCode
            val knownName = addresses!![0].featureName

              mMap.addMarker(MarkerOptions().position(sydney).title(address))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

            val zoomLevel = 13.0f //This goes up to 21
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))


        }
    }


}