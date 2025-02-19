package com.andrewcarmichael.fleetio.vehiclelist.data

import com.andrewcarmichael.fleetio.vehiclelist.data.models.PaginatedVehiclesResponse
import com.andrewcarmichael.fleetio.vehiclelist.data.models.Vehicle

interface VehicleApi {

    /**
     * Fetches a single vehicle by id
     *
     * @param page The current page number (starting at 1).
     * @param pageSize The number of vehicles per page.
     * @return A [PaginatedVehiclesResponse] containing the vehicles and pagination info.
     */
    suspend fun fetchVehicle(id: Long): Result<Vehicle>

    /**
     * Fetches a paginated list of vehicles from the API.
     *
     * @param page The current page number (starting at 1).
     * @param pageSize The number of vehicles per page.
     * @return A [PaginatedVehiclesResponse] containing the vehicles and pagination info.
     */
    suspend fun fetchVehicles(page: Int = 0, pageSize: Int = 10): Result<PaginatedVehiclesResponse>
}