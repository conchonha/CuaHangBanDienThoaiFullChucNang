package com.example.myapplication.Activity

import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.GoogleMap
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.CameraUpdateFactory

class Activity_Vitrishop : AppCompatActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__vitrishop)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        setactionBar()
    }

    private fun setactionBar() {
        toolbar = findViewById(R.id.tollbarvitri)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar?.setNavigationIcon(R.drawable.back)
        toolbar?.setNavigationOnClickListener(View.OnClickListener { finish() })
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val cuahangsach = LatLng(15.972540, 108.249584)
        mMap?.addMarker(
            MarkerOptions().position(cuahangsach).title("Truuong Cao Dang CNTT-DHDN")
                .snippet("Duong Nam Ky Khoi Nghia").icon(BitmapDescriptorFactory.defaultMarker())
        )
        mMap?.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val cameraPosition = CameraPosition.Builder().target(cuahangsach).zoom(15f).build()
        mMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}