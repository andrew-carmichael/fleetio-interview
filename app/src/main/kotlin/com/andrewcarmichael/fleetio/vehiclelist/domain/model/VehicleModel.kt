package com.andrewcarmichael.fleetio.vehiclelist.domain.model

data class VehicleModel(
    val id: Long,
    val name: String,
    val description: String?,
    val imageUrl: String?,
    val type: VehicleType,
    val status: VehicleStatus,
    val vin: String? = null,
    val licensePlate: String? = null,
)

enum class VehicleType() {
    Car(),
    Van(),
    PickupTruck(),
    Unknown()
}

enum class VehicleStatus() {
    Active(),
    InShop(),
    OutOfService(),
    Unknown(),
}