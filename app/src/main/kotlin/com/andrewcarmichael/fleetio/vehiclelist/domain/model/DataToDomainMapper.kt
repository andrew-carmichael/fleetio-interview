package com.andrewcarmichael.fleetio.vehiclelist.domain.model

import com.andrewcarmichael.fleetio.vehiclelist.data.models.Vehicle as DataLayerModel

fun DataLayerModel.toDomainModel(): VehicleModel {
    return VehicleModel(
        id = id.toLong(),
        name = name,
        description = toDescription(),
        imageUrl = defaultImageUrlSmall,
        type = toVehicleType(),
        status = toVehicleStatus()
    )
}

fun DataLayerModel.toDescription(): String? {
    return "${year.orEmpty()} ${make.orEmpty()} ${model.orEmpty()}".takeIf { it.isNotBlank() }
}

fun DataLayerModel.toVehicleType(): VehicleType {
    return when (vehicleTypeName) {
        "Car" -> VehicleType.Car
        "Van" -> VehicleType.Van
        "Pickup Truck" -> VehicleType.PickupTruck
        else -> VehicleType.Unknown
    }
}

fun DataLayerModel.toVehicleStatus(): VehicleStatus {
    return when(vehicleStatusName) {
        "Active" -> VehicleStatus.Active
        "Out of Service" -> VehicleStatus.OutOfService
        "In Shop" -> VehicleStatus.InShop
        else -> VehicleStatus.Unknown
    }
}