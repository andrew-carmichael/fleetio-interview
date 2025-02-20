package com.andrewcarmichael.fleetio.vehiclelist.di

import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApi
import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApiKtorNetworkImpl
import com.andrewcarmichael.fleetio.vehiclelist.data.VehiclePagingSource
import com.andrewcarmichael.fleetio.vehiclelist.domain.FetchLastKnownVehicleLocation
import com.andrewcarmichael.fleetio.vehiclelist.domain.FetchVehicle
import com.andrewcarmichael.fleetio.vehiclelist.domain.FetchVehiclePages
import com.andrewcarmichael.fleetio.vehiclelist.domain.GetStaticGoogleMapUrl
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleDetailViewModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val vehicleListFeatureModule = module {
    factory<VehicleApi> { VehicleApiKtorNetworkImpl(get()) }
    factory { FetchVehicle(get()) }
    factory { VehiclePagingSource(get()) }
    factory { FetchVehiclePages(get()) }
    factory { FetchLastKnownVehicleLocation(get()) }
    factory { GetStaticGoogleMapUrl(get()) }
    viewModel { VehicleListViewModel(get()) }
    viewModel { params ->
        VehicleDetailViewModel(
            vehicleId = params.get(),
            fetchVehicle = get(),
            fetchLastKnownVehicleLocation = get(),
            getStaticGoogleMapUrl = get(),
        )
    }
}