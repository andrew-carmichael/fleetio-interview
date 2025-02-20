package com.andrewcarmichael.fleetio.vehiclelist.data

import com.andrewcarmichael.fleetio.vehiclelist.data.models.LocationEntry
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
     * @param cursor The current page cursor.
     * @param pageSize The number of vehicles per page.
     * @return A [Result<PaginatedVehiclesResponse>] containing the vehicles and pagination info.
     */
    suspend fun fetchVehicles(cursor: String? = null, pageSize: Int = DEFAULT_PAGE_SIZE): Result<PaginatedVehiclesResponse>

    /**
     * Fetch the last known location of a vehicle.
     * @param vehicleId The id of the vehicle
     * @param locationEntryId The location id.
     * @return A [Result<LocationEntry>]
     */
    suspend fun fetchLastKnownVehicleLocation(vehicleId: Long, locationEntryId: Long): Result<LocationEntry>

    companion object {
        const val DEFAULT_PAGE_SIZE = 50
    }
}