// Localizado em app/src/main/java/com/example/myapplication2/MainActivity.kt
package com.example.myapplication2

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationTextView = findViewById(R.id.locationTextView)
        lastUpdateTextView = findViewById(R.id.lastUpdateTextView)

        // Instancia o cliente de localização
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Configura o pedido de localização
        locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
            .setMinUpdateIntervalMillis(10000)
            .build()

        // Configura o callback que será chamado a cada atualização
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    updateUIWithLocation(location)
                }
            }
        }

        startLocationUpdates()
    }

    private fun startLocationUpdates() {
        // Verifica se a permissão foi concedida
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Se não houver permissão, solicita ao usuário
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION
            )
            return
        }
        // Inicia as atualizações de localização
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun updateUIWithLocation(location: Location) {
        // Atualiza a localização atual no TextView
        locationTextView.text = "Latitude: ${location.latitude}, Longitude: ${location.longitude}"

        // Atualiza o TextView de última atualização com a hora atual formatada
        val currentTime = getCurrentTime()
        lastUpdateTextView.text = "Última atualização: $currentTime"
    }

    private fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(Date())
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                startLocationUpdates()
            } else {
                locationTextView.text = "Permissão negada"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Para as atualizações de localização ao destruir a atividade
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 1
    }
}
