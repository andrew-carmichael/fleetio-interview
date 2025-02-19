package com.andrewcarmichael.fleetio.vehicledetail.di

import com.andrewcarmichael.fleetio.vehicledetail.presentation.VehicleDetailViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val vehicleDetailFeatureModule = module {
    viewModel { params -> VehicleDetailViewModel(vehicleId = params.get(), vehicleApi = get()) }
}