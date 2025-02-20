package com.andrewcarmichael.fleetio.core.secrets

import android.os.Build
import com.andrewcarmichael.fleetio.BuildConfig
import kotlin.math.sin
import org.koin.dsl.module

val secretsModule = module {
    single {
        FleetIoApiSecrets(
            accountToken = BuildConfig.FLEETIO_ACCOUNT_TOKEN,
            authorizationToken = BuildConfig.FLEETIO_AUTHORIZATION_TOKEN,
        )
    }
    single {
        GoogleMapsSecrets(
            apiKey = BuildConfig.GOOGLE_MAPS_API_KEY,
        )
    }
}