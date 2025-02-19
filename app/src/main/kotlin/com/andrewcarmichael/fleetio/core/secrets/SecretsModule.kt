package com.andrewcarmichael.fleetio.core.secrets

import com.andrewcarmichael.fleetio.BuildConfig
import org.koin.dsl.module

val secretsModule = module {
    single {
        FleetIoApiSecrets(
            accountToken = BuildConfig.FLEETIO_ACCOUNT_TOKEN,
            authorizationToken = BuildConfig.FLEETIO_AUTHORIZATION_TOKEN,
        )
    }
}