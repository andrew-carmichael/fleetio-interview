package com.andrewcarmichael.fleetio.vehiclelist.domain.model

object FakeVehicleData {
    val vehicles = listOf(
        VehicleModel(
            id = 1L,
            name = "Vehicle-001",
            description = "A reliable pickup truck built for heavy duty work.",
            imageUrl = null,
            type = VehicleType.PickupTruck,
            status = VehicleStatus.Active,
            vin = "1FTFW1E5XJFB00001",
            licensePlate = "PKT-001"
        ),
        VehicleModel(
            id = 2L,
            name = "Vehicle-002",
            description = "A compact car currently undergoing maintenance.",
            imageUrl = null,
            type = VehicleType.Car,
            status = VehicleStatus.InShop,
            vin = "1HGCM82633A00002",
            licensePlate = "CAR-002"
        ),
        VehicleModel(
            id = 3L,
            name = "Vehicle-003",
            description = "A spacious van ideal for deliveries and transport.",
            imageUrl = null,
            type = VehicleType.Van,
            status = VehicleStatus.Active,
            vin = "1FTSW21P000000003",
            licensePlate = "VAN-003"
        ),
        VehicleModel(
            id = 4L,
            name = "Vehicle-004",
            description = "A well-maintained car, now out of service for upgrades.",
            imageUrl = null,
            type = VehicleType.Car,
            status = VehicleStatus.OutOfService,
            vin = "1HGCM82633A00004",
            licensePlate = "CAR-004"
        ),
        VehicleModel(
            id = 5L,
            name = "Vehicle-005",
            description = "A durable van that’s ready for heavy logistics tasks.",
            imageUrl = null,
            type = VehicleType.Van,
            status = VehicleStatus.Active,
            vin = "1FTSW21P000000005",
            licensePlate = "VAN-005"
        ),
        VehicleModel(
            id = 6L,
            name = "Vehicle-006",
            description = "A robust pickup truck, currently in the shop for repairs.",
            imageUrl = null,
            type = VehicleType.PickupTruck,
            status = VehicleStatus.InShop,
            vin = "1FTFW1E5XJFB00006",
            licensePlate = "PKT-006"
        ),
        VehicleModel(
            id = 7L,
            name = "Vehicle-007",
            description = "An efficient car, actively on the road.",
            imageUrl = null,
            type = VehicleType.Car,
            status = VehicleStatus.Active,
            vin = "1HGCM82633A00007",
            licensePlate = "CAR-007"
        ),
        VehicleModel(
            id = 8L,
            name = "Vehicle-008",
            description = "A versatile van temporarily out of service.",
            imageUrl = null,
            type = VehicleType.Van,
            status = VehicleStatus.OutOfService,
            vin = "1FTSW21P000000008",
            licensePlate = "VAN-008"
        ),
        VehicleModel(
            id = 9L,
            name = "Vehicle-009",
            description = "A high-performance pickup truck known for its reliability.",
            imageUrl = null,
            type = VehicleType.PickupTruck,
            status = VehicleStatus.Active,
            vin = "1FTFW1E5XJFB00009",
            licensePlate = "PKT-009"
        ),
        VehicleModel(
            id = 10L,
            name = "Vehicle-010",
            description = "A sleek car, currently in shop for scheduled maintenance.",
            imageUrl = null,
            type = VehicleType.Car,
            status = VehicleStatus.InShop,
            vin = "1HGCM82633A00010",
            licensePlate = "CAR-010"
        )
    )
}
