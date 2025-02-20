package com.andrewcarmichael.fleetio.vehiclelist.data

import com.andrewcarmichael.fleetio.vehiclelist.data.models.LocationEntry
import com.andrewcarmichael.fleetio.vehiclelist.data.models.PaginatedVehiclesResponse
import com.andrewcarmichael.fleetio.vehiclelist.data.models.Vehicle
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

internal class VehicleApiKtorNetworkImpl(
    private val httpClient: HttpClient,
) : VehicleApi {

    override suspend fun fetchVehicle(id: Long): Result<Vehicle> {
        return runCatching {
            require(id > 0)
            val httpResponse = httpClient.get("vehicles/${id}")
            val body = httpResponse.body<Vehicle>()
            body
        }
    }

    override suspend fun fetchVehicles(cursor: String?, pageSize: Int): Result<PaginatedVehiclesResponse> {
        return runCatching {
            val httpResponse = httpClient.get("vehicles") {
                url {
                    cursor?.let {
                        parameter("start_cursor", it)
                    }
                    parameter("per_page", pageSize)
                }
            }
            val body = httpResponse.body<PaginatedVehiclesResponse>()
            body
        }
    }

    override suspend fun fetchLastKnownVehicleLocation(
        vehicleId: Long,
        locationEntryId: Long
    ): Result<LocationEntry> {
        return runCatching {
            val httpResponse = httpClient.get("vehicles/${vehicleId}/location_entries/${locationEntryId}")
            val body = httpResponse.body<LocationEntry>()
            body
        }
    }
}

