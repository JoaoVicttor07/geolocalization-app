package com.example.myapplication2

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {


    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback
    private lateinit var locationTextView: TextView
    private lateinit var lastUpdateTextView: TextView

    // Thread e Handler pra gerenciar as atualizações fora da UI
    private lateinit var handlerThread: HandlerThread
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationTextView = findViewById(R.id.locationTextView)
        lastUpdateTextView = findViewById(R.id.lastUpdateTextView)

        // inicializa o cliente de localização
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
            .setMinUpdateIntervalMillis(10000)
            .build()

        // toda vez que tiver uma nova localização
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    updateUIWithLocation(location)
                }
            }
        }
        startLocationThread()
    }

    private fun startLocationThread() {
        handlerThread = HandlerThread("LocationThread").apply { start() }
        handler = Handler(handlerThread.looper)

        // condição para verificar se possui permissão pra localização
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            scheduleLocationUpdates()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
        }
    }

    private fun scheduleLocationUpdates() {
        handler.post(object : Runnable {
            override fun run() {
                // Checa se ainda tem permissão pra localização antes de continuar
                if (ActivityCompat.checkSelfPermission(
                        this@MainActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    try {
                        fusedLocationClient.requestLocationUpdates(
                            locationRequest,
                            locationCallback,
                            handlerThread.looper
                        )
                    } catch (e: SecurityException) {
                        e.printStackTrace()
                    }
                } else {
                    runOnUiThread {
                        locationTextView.text = "Permissão de localização não concedida"
                    }
                }
                handler.postDelayed(this, 10000)
            }
        })
    }

    private fun updateUIWithLocation(location: Location) {
        runOnUiThread {
            locationTextView.text = "Latitude: ${location.latitude}, Longitude: ${location.longitude}"
            val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
            lastUpdateTextView.text = "Última atualização: $currentTime"
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION && grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            scheduleLocationUpdates()
        } else {
            locationTextView.text = "Permissão negada"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fusedLocationClient.removeLocationUpdates(locationCallback)
        handlerThread.quitSafely()
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}
