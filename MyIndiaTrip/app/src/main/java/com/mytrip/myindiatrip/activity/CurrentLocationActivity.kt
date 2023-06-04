package com.mytrip.myindiatrip.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.akexorcist.googledirection.DirectionCallback
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.AvoidType
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter
import com.google.android.gms.location.LocationListener
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.databinding.ActivityCurrentLocationBinding
import java.util.*

class CurrentLocationActivity : AppCompatActivity(), OnMapReadyCallback {

    lateinit var binding: ActivityCurrentLocationBinding
    lateinit var myLocation: LatLng
    lateinit var addeddMarker: Marker
    private lateinit var mMap: GoogleMap

    private var locationManager: LocationManager? = null
    private var location: Location? = null
    var curr_latLng: LatLng? = null
    var polyline: Polyline? = null

    private val LOCATION_MIN_UPDATE_TIME = 10
    private val LOCATION_MIN_UPDATE_DISTANCE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCurrentLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        getCurrentLocation()

    }

    private fun getCurrentLocation() {
        try {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                val isGPSEnabled = locationManager!!.isProviderEnabled(LocationManager.GPS_PROVIDER)
                val isNetworkEnabled =
                    locationManager!!.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

                Log.e("TAG", "getCurrentLocation:latt " + isGPSEnabled + " " + isNetworkEnabled)
                if (!isGPSEnabled && !isNetworkEnabled) {
                    //                    showToast(getString(R.string.please_on_your_GPS_location));
                    Toast.makeText(this, "please_on_your_GPS_location", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    location = null
                    if (isGPSEnabled) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.GPS_PROVIDER,
                            LOCATION_MIN_UPDATE_TIME.toLong(),
                            LOCATION_MIN_UPDATE_DISTANCE.toFloat(),
                            object : android.location.LocationListener {
                                override fun onLocationChanged(location: Location) {
                                    var latLng =
                                        LatLng(location.getLatitude(), location.getLongitude());
                                    drawMarker(latLng, false);
                                    locationManager?.removeUpdates(this);
                                }

                            }
                        )
                        location =
                            locationManager!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    }
                    if (isNetworkEnabled) {
                        locationManager!!.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            LOCATION_MIN_UPDATE_TIME.toLong(),
                            LOCATION_MIN_UPDATE_DISTANCE.toFloat(),
                            object : android.location.LocationListener {
                                override fun onLocationChanged(location: Location) {
                                    var latLng =
                                        LatLng(location.getLatitude(), location.getLongitude());
                                    drawMarker(latLng, false);
                                    locationManager?.removeUpdates(this);
                                }

                            }
                        )
                        location =
                            locationManager!!.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                    }


                    Log.e("TAG", "getCurrentLocation:latt " + location?.latitude)
                    if (location != null) {
                        curr_latLng = LatLng(location!!.latitude, location!!.longitude)
                        drawMarker(curr_latLng!!, false)

                        val geocoder: Geocoder = Geocoder(this, Locale.getDefault())
                        val addresses: List<Address>? = geocoder.getFromLocation(location!!.latitude, location!!.longitude, 1) // Here 1 represent max location result to returned, by documents it recommended 1 to 5

                      var  address =
                            addresses!![0].getAddressLine(0) // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()

                        val city = addresses!![0].locality
                        val state = addresses!![0].adminArea
                        val country = addresses!![0].countryName
                        val postalCode = addresses!![0].postalCode
                        val knownName = addresses!![0].featureName

                        mMap.addMarker(MarkerOptions().position(curr_latLng!!).title(address))
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(curr_latLng!!))
//                        drawline(21.226920897072215, 72.83169425889456, TransportMode.DRIVING)

                        return
                    } else {
//                        getCurrentLocation()
                    }


                }
            } else {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        12
                    )
                }
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                        13
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            onBackPressed()
        }


    }


    private fun displayDefaultMarker() {
        myLocation = LatLng(21.21310108420928, 72.89040849101566)
        addeddMarker =
            mMap.addMarker(MarkerOptions().position(myLocation).title("Marker in Sydney"))!!
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation))
    }


    private fun drawMarker(latLng: LatLng, flag: Boolean) {
        if (mMap != null) {
//            mMap!!.clear()
            val markerOptions = MarkerOptions()
            markerOptions.position(latLng)
            //            markerOptions.title(title);
            if (flag) {
//                markerOptions.icon(BitmapFromVector(this, R.drawable.ic_map_event));
                markerOptions.icon(BitmapFromVector(this, R.drawable.location1))
            } else {
                Log.e("TAG", "drawMarker: mark")
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            }
            mMap!!.addMarker(markerOptions)

            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5f))


        }
    }

    private fun BitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)
        vectorDrawable!!.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }


    private fun drawline(lat: Double, longt: Double, mode: String) {
        try {
//            drawMarker(LatLng(lat, longt), false)
            GoogleDirection.withServerKey("AIzaSyBv6cUUv3hbIEDcG69F297b37KqrTjepSg")
                .from(LatLng(myLocation!!.latitude, myLocation!!.longitude))
                .to(LatLng(lat, longt))
                .avoid(AvoidType.FERRIES)
                .avoid(AvoidType.HIGHWAYS)
                .transportMode(mode)
                .execute(object : DirectionCallback {
                    override fun onDirectionSuccess(direction: Direction?) {
                        directionsuccess(direction)
                    }

                    override fun onDirectionFailure(t: Throwable) {}
                })
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("TAG", "drawline:exce " + e.message)
        }
    }

    private fun directionsuccess(direction: Direction?) {
        try {
            if (direction!!.isOK) {
                val route = direction.routeList[0]
                if (route != null && !route.legList.isEmpty()) {
                    val distance = route.legList[0].distance
                    val duration = route.legList[0].duration
                    val directionPositionList = route.legList[0].directionPoint
                    if (!directionPositionList.isEmpty()) {
                        if (polyline != null) {
                            polyline!!.remove()
                        }
                        polyline = mMap!!.addPolyline(
                            DirectionConverter.createPolyline(
                                this,
                                directionPositionList,
                                4,
                                ContextCompat.getColor(this, R.color.purple_200)
                            )
                        )
                        setCameraWithCoordinationBounds(route)
                    } else {
                        Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "noroute_available", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun setCameraWithCoordinationBounds(route: Route) {
        val southwest = route.bound.southwestCoordination.coordination
        val northeast = route.bound.northeastCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100))
    }

//
//    private fun getLatLong() {
//        val locationAddress = GeoCodeLocation()
//        locationAddress.getAddressFromLocation(
//            "BBC Complex Yogichowk Surat",
//            applicationContext,
//            GeoCoderHandler()
//        )
//
//        addeddMarker.remove()
//
//
//    }
//
//    private class GeoCoderHandler : Handler() {
//        override fun handleMessage(message: Message) {
//            val locationAddress: String?
//            locationAddress = when (message.what) {
//                1 -> {
//                    val bundle: Bundle = message.getData()
//                    bundle.getString("address")
//                }
//                else -> null
//            }
//            Log.e("TAG", "handleMessage: ==>" + locationAddress)
//
//
//        }
//    }

}
