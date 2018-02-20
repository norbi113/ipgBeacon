package com.mbww.ipgbeacon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.estimote.scanning_sdk.api.EstimoteBluetoothScannerFactory
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
            val scanner = EstimoteBluetoothScannerFactory(applicationContext).getSimpleScanner()
            val scanHandler = scanner.estimoteTelemetryFullScan()
                    .withBalancedPowerMode()
                    .withOnPacketFoundAction {
                        Log.d("Full Telemetry", "Got Full Telemetry packet: $it")
                    }
                    .start()
    }
}
