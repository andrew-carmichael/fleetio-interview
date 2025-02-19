package com.andrewcarmichael.fleetio

import android.app.Application
import android.util.Log
import com.andrewcarmichael.fleetio.core.network.networkModule
import com.andrewcarmichael.fleetio.core.secrets.secretsModule
import com.andrewcarmichael.fleetio.vehiclelist.di.vehicleListFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FleetioAndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate: calling startKoin")
        startKoin {
            androidContext(this@FleetioAndroidApplication)
            modules(
                secretsModule,
                networkModule,
                vehicleListFeatureModule,
            )
        }
        Log.d(TAG, "onCreate: finished")
    }

    companion object {
        private const val TAG = "FleetioAndroidApplication"
    }
}