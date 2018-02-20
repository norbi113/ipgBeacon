package com.mbww.ipgbeacon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import com.estimote.scanning_sdk.api.EstimoteBluetoothScannerFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val scanner = EstimoteBluetoothScannerFactory(applicationContext).getSimpleScanner()
            val scanHandler = scanner.estimoteTelemetryFullScan()
                    .withBalancedPowerMode()
                    .withOnPacketFoundAction {
                        Log.d("Full Telemetry", "Got Full Telemetry packet: $it")
                        val temperature = it.temperatureInCelsiusDegrees
                        var textView: TextView = findViewById<TextView>(R.id.telemetryData)
                    }
                    .start()
    }
}
