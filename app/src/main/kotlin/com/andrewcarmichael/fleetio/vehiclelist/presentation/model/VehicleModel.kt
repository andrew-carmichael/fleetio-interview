package com.andrewcarmichael.fleetio.vehiclelist.presentation.model

import androidx.annotation.StringRes
import com.andrewcarmichael.fleetio.R.string
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleStatus
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleType
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleModel as DomainModel

data class VehicleModel(
    val id: Long,
    val name: String,
    val description: String?,
    val imageUrl: String?,
    val tags: List<Tag>,
    val vin: String?,
    val licensePlate: String?,
)

sealed interface Tag {
    val displayStringRes: Int

    enum class VehicleType(@StringRes override val displayStringRes: Int) : Tag {
        Car(string.vehicleTypeCar),
        Van(string.vehicleTypeVan),
        PickupTruck(string.vehicleTypePickupTruck),
        Unknown(string.vehicleTypeUnknown)
    }

    enum class VehicleStatus(@StringRes override val displayStringRes: Int) : Tag {
        Active(string.vehicleStatusActive),
        InShop(string.vehicleStatusInShop),
        OutOfService(string.vehicleStatusOutOfService),
        Unknown(string.vehicleStatusUnknown),
    }
}

fun DomainModel.toPresentationModel(): VehicleModel {
    return VehicleModel(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        tags = listOf(status.toTag(), type.toTag()),
        vin = vin,
        licensePlate = licensePlate,
    )
}

private fun VehicleType.toTag(): Tag.VehicleType {
    return when (this) {
        VehicleType.Car -> Tag.VehicleType.Car
        VehicleType.Van -> Tag.VehicleType.Van
        VehicleType.PickupTruck -> Tag.VehicleType.PickupTruck
        VehicleType.Unknown -> Tag.VehicleType.Unknown
    }
}

private fun VehicleStatus.toTag(): Tag.VehicleStatus {
    return when (this) {
        VehicleStatus.Active -> Tag.VehicleStatus.Active
        VehicleStatus.InShop -> Tag.VehicleStatus.InShop
        VehicleStatus.OutOfService -> Tag.VehicleStatus.OutOfService
        VehicleStatus.Unknown -> Tag.VehicleStatus.Unknown
    }
}