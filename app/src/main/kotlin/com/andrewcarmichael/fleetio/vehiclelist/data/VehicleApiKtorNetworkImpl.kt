package com.andrewcarmichael.fleetio.vehiclelist.data

import android.util.Log
import com.andrewcarmichael.fleetio.vehiclelist.data.models.PaginatedVehiclesResponse
import com.andrewcarmichael.fleetio.vehiclelist.data.models.Vehicle
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

internal class VehicleApiKtorNetworkImpl(
    private val httpClient: HttpClient,
) : VehicleApi {

    // TODO test
    override suspend fun fetchVehicle(id: Long): Result<Vehicle> {
        return runCatching {
            require(id > 0)
            val httpResponse = httpClient.get("https://secure.fleetio.com/api/v1/vehicles/${id}")
            val body = httpResponse.body<Vehicle>()
            body
        }
    }

    override suspend fun fetchVehicles(page: Int, pageSize: Int): Result<PaginatedVehiclesResponse> {
        return runCatching {
            val httpResponse = httpClient.get("https://secure.fleetio.com/api/v1/vehicles")
            Log.d(TAG, "fetchVehicles: ${httpResponse.bodyAsText()}")
            val body = httpResponse.body<PaginatedVehiclesResponse>()
            body
        }
    }

    companion object {
        private const val TAG = "VehicleApiKtorNetworkImpl"
    }
}

