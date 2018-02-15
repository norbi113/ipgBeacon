package com.mbww.ipgbeacon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.estimote.cloud_plugin.common.EstimoteCloudCredentials
import com.estimote.scanning_sdk.api.EstimoteBluetoothScannerFactory


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val cloudCredentials = EstimoteCloudCredentials("ipgbeacon-6iu" , "09fa4b5a21e4267d566b1b5485ad50c4")
        val scanner = EstimoteBluetoothScannerFactory(applicationContext).getSimpleScanner()
        val scanHandler = scanner.estimoteTelemetryFullScan()
                .withBalancedPowerMode()
                .withOnPacketFoundAction {
                    Log.d("Full Telemetry", "Got Full Telemetry packet: $it")
                }
                .start()
    }
}
