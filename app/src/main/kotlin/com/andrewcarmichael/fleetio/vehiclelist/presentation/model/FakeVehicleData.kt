package com.andrewcarmichael.fleetio.vehiclelist.presentation.model

object FakeVehicleData {
    val vehicles = listOf(
        VehicleModel(
            id = 1,
            name = "Honda Civic",
            type = VehicleType.Car,
            status = VehicleStatus.Active
        ),
        VehicleModel(
            id = 2,
            name = "Toyota Camry",
            type = VehicleType.Car,
            status = VehicleStatus.InShop
        ),
        VehicleModel(
            id = 3,
            name = "Ford Transit",
            type = VehicleType.Van,
            status = VehicleStatus.OutOfService
        ),
        VehicleModel(
            id = 4,
            name = "Chevrolet Silverado",
            type = VehicleType.PickupTruck,
            status = VehicleStatus.Active
        ),
        VehicleModel(
            id = 5,
            name = "Ram 1500",
            type = VehicleType.PickupTruck,
            status = VehicleStatus.InShop
        ),
        VehicleModel(
            id = 6,
            name = "Nissan NV200",
            type = VehicleType.Van,
            status = VehicleStatus.Active
        )
    )
}