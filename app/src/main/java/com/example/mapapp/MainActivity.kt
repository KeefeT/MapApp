package com.example.mapapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mapapp.ui.theme.MapAppTheme
import com.google.android.gms.maps.GoogleMapOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import android.Manifest
import androidx.compose.runtime.Composable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                } else -> {
                // No location access granted.
            }
            }
        }

        locationPermissionRequest.launch(arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION))

        enableEdgeToEdge()
        setContent {
            MapAppTheme {


                GoogleMap(
                    googleMapOptionsFactory = {
                        val mapOptions = GoogleMapOptions()
                        mapOptions.mapToolbarEnabled(true)
                        mapOptions.compassEnabled(true)
                        mapOptions.zoomControlsEnabled(true)
                        mapOptions.zOrderOnTop(false)
                        mapOptions
                    },
                    properties = MapProperties(isMyLocationEnabled = true)
                )

            }
        }
    }

    @Composable
    fun PermissionReq() {

    }
}
