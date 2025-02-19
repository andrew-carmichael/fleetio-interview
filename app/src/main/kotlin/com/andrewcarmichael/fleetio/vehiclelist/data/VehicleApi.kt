package com.andrewcarmichael.fleetio.vehiclelist.data

import com.andrewcarmichael.fleetio.vehiclelist.data.models.PaginatedVehiclesResponse
import com.andrewcarmichael.fleetio.vehiclelist.data.models.Vehicle

interface VehicleApi {

    /**
     * Fetches a single vehicle by id
     *
     * @param id The id of the vehicle
     * @return A [Result<Vehicle>] containing the vehicle.
     */
    suspend fun fetchVehicle(id: Long): Result<Vehicle>

    /**
     * Fetches a paginated list of vehicles from the API.
     *
     * @param page The current page number.
     * @param pageSize The number of vehicles per page.
     * @return A [Result<PaginatedVehiclesResponse>] containing the vehicles and pagination info.
     */
    suspend fun fetchVehicles(page: Int = 0, pageSize: Int = 10): Result<PaginatedVehiclesResponse>
}