package com.mbww.ipgbeacon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
                        var temperature: TextView = findViewById<TextView>(R.id.temperature)
                        temperature.setText("Temperature: ${it.temperatureInCelsiusDegrees}")
                        var magnetometer: TextView = findViewById<TextView>(R.id.magnetometer)
                        magnetometer.setText("Magnetometer: ${it.magnetometer}")
                        var pressure: TextView = findViewById<TextView>(R.id.pressure)
                        pressure.setText("Pressure: ${it.pressure}")
                        var ambientLight: TextView = findViewById<TextView>(R.id.ambientLight)
                        ambientLight.setText("Ambient Light: ${it.ambientLightInLux}")
                        var motionState: TextView = findViewById<TextView>(R.id.motionState)
                        motionState.setText("Motion State: ${it.motionState}")
                    }
                    .start()
    }
}
