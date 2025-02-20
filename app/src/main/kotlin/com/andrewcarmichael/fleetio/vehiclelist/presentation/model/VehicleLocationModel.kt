package com.andrewcarmichael.fleetio.vehiclelist.presentation.model

import androidx.compose.runtime.Immutable

@Immutable
data class VehicleLocationModel(
    val latitude: Double,
    val longitude: Double,
    val address: String?,
    val googleMapUrl: String,
)