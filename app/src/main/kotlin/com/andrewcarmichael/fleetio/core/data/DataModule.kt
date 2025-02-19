package com.andrewcarmichael.fleetio.core.data

import org.koin.dsl.module

val dataModule = module {
    single<VehicleApi> { VehicleApiKtorNetworkImpl(get()) }
}