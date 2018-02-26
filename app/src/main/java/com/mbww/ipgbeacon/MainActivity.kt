package com.mbww.ipgbeacon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.estimote.scanning_sdk.api.EstimoteBluetoothScannerFactory
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
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
                        if(it.temperatureInCelsiusDegrees > 6) {
                            var checkTemperature: TextView = findViewById<TextView>(R.id.checkTemperature)
                            checkTemperature.setText("Temperature high!")
                        }else{
                            var checkTemperature: TextView = findViewById<TextView>(R.id.checkTemperature)
                            checkTemperature.setText("Temperature low!")
                        }
                        if(it.magnetometer.xAxis >= 0 && it.magnetometer.yAxis >= 0 && it.magnetometer.zAxis >= 0) {
                            var checkMagnetometer: TextView = findViewById<TextView>(R.id.checkMagnetometer)
                            checkMagnetometer.setText("Magnetometer is crazy!")
                        }else{
                            var checkMagnetometer: TextView = findViewById<TextView>(R.id.checkMagnetometer)
                            checkMagnetometer.setText("Magnetometer is calm!")
                        }
                        if(it.pressure > 6) {
                            var checkPressure: TextView = findViewById<TextView>(R.id.checkPressure)
                            checkPressure.setText("Pressure higher!")
                        }else{
                            var checkPresure: TextView = findViewById<TextView>(R.id.checkPressure)
                            checkPresure.setText("Pressure lower!")
                        }
                        if(it.ambientLightInLux > 6) {
                            var checkLight: TextView = findViewById<TextView>(R.id.checkLight)
                            checkLight.setText("Lights on!")
                        }else{
                            var checkLight: TextView = findViewById<TextView>(R.id.checkLight)
                            checkLight.setText("Lights off!")
                        }
                        if(it.motionState == true) {
                            var checkMotion: TextView = findViewById<TextView>(R.id.checkMotion)
                            checkMotion.setText("Motion on!")
                        }else{
                            var checkMotion: TextView = findViewById<TextView>(R.id.checkMotion)
                            checkMotion.setText("Motion off!")
                                                    }

                        val database = FirebaseDatabase.getInstance()

                        val refTemperature = database.getReference("temperature")
                        val dbTemperature = checkTemperature.getText().toString()
                        refTemperature.setValue(dbTemperature)

                        val refMagnetometer = database.getReference("magnetometer")
                        val dbMagnetometer = checkMagnetometer.getText().toString()
                        refMagnetometer.setValue(dbMagnetometer)

                        val refPressure = database.getReference("pressure")
                        val dbPressure = checkPressure.getText().toString()
                        refPressure.setValue(dbPressure)

                        val refLight = database.getReference("light")
                        val dbLight = checkLight.getText().toString()
                        refLight.setValue(dbLight)

                        val refMotion = database.getReference("motion")
                        val dbMotion = checkMotion.getText().toString()
                        refMotion.setValue(dbMotion)

                    }
                    .start()
    }
}
