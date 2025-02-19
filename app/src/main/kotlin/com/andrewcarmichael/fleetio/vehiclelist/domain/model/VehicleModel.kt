package com.andrewcarmichael.fleetio.vehiclelist.domain.model

import androidx.annotation.StringRes
import com.andrewcarmichael.fleetio.R.string

data class VehicleModel(
    val id: Long,
    val name: String,
    val description: String?,
    val imageUrl: String?,
    val type: VehicleType,
    val status: VehicleStatus,
)

enum class VehicleType(@StringRes displayStringRes: Int) {
    Car(string.vehicleTypeCar),
    Van(string.vehicleTypeVan),
    PickupTruck(string.vehicleTypePickupTruck),
    Unknown(string.vehicleTypeUnknown)
}

enum class VehicleStatus(@StringRes displayString: Int) {
    Active(string.vehicleStatusActive),
    InShop(string.vehicleStatusInShop),
    OutOfService(string.vehicleStatusOutOfService),
    Unknown(string.vehicleStatusUnknown),
}
