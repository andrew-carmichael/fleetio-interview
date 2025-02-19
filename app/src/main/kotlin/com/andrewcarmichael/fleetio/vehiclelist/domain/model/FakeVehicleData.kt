package com.andrewcarmichael.fleetio.vehiclelist.domain.model

object FakeVehicleData {
    val vehicles = listOf(
        VehicleModel(
            id = 1,
            name = "Vehicle-001",
            description = "A reliable pickup truck built for heavy duty work.",
            imageUrl = null,
            type = VehicleType.PickupTruck,
            status = VehicleStatus.Active
        ),
        VehicleModel(
            id = 2,
            name = "Vehicle-002",
            description = "A compact car currently undergoing maintenance.",
            imageUrl = null,
            type = VehicleType.Car,
            status = VehicleStatus.InShop
        ),
        VehicleModel(
            id = 3,
            name = "Vehicle-003",
            description = "A spacious van ideal for deliveries and transport.",
            imageUrl = null,
            type = VehicleType.Van,
            status = VehicleStatus.Active
        ),
        VehicleModel(
            id = 4,
            name = "Vehicle-004",
            description = "A well-maintained car, now out of service for upgrades.",
            imageUrl = null,
            type = VehicleType.Car,
            status = VehicleStatus.OutOfService
        ),
        VehicleModel(
            id = 5,
            name = "Vehicle-005",
            description = "A durable van that’s ready for heavy logistics tasks.",
            imageUrl = null,
            type = VehicleType.Van,
            status = VehicleStatus.Active
        ),
        VehicleModel(
            id = 6,
            name = "Vehicle-006",
            description = "A robust pickup truck, currently in the shop for repairs.",
            imageUrl = null,
            type = VehicleType.PickupTruck,
            status = VehicleStatus.InShop
        ),
        VehicleModel(
            id = 7,
            name = "Vehicle-007",
            description = "An efficient car, actively on the road.",
            imageUrl = null,
            type = VehicleType.Car,
            status = VehicleStatus.Active
        ),
        VehicleModel(
            id = 8,
            name = "Vehicle-008",
            description = "A versatile van temporarily out of service.",
            imageUrl = null,
            type = VehicleType.Van,
            status = VehicleStatus.OutOfService
        ),
        VehicleModel(
            id = 9,
            name = "Vehicle-009",
            description = "A high-performance pickup truck known for its reliability.",
            imageUrl = null,
            type = VehicleType.PickupTruck,
            status = VehicleStatus.Active
        ),
        VehicleModel(
            id = 10,
            name = "Vehicle-010",
            description = "A sleek car, currently in shop for scheduled maintenance.",
            imageUrl = null,
            type = VehicleType.Car,
            status = VehicleStatus.InShop
        )
    )
}
