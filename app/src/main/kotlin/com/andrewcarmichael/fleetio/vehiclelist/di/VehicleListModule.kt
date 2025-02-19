package com.andrewcarmichael.fleetio.vehiclelist.di

import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApi
import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApiKtorNetworkImpl
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleDetailViewModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val vehicleListFeatureModule = module {
    single<VehicleApi> { VehicleApiKtorNetworkImpl(get()) }
    viewModel { VehicleListViewModel(get()) }
    viewModel { params -> VehicleDetailViewModel(vehicleId = params.get(), vehicleApi = get()) }
}