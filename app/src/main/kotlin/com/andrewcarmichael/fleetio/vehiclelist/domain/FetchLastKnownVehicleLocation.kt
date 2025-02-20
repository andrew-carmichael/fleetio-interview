package com.andrewcarmichael.fleetio.vehiclelist.domain

import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApi
import com.andrewcarmichael.fleetio.vehiclelist.data.models.LocationEntry

class FetchLastKnownVehicleLocation(
    private val vehicleApi: VehicleApi,
) {

    suspend operator fun invoke(vehicleId: Long, locationEntryId: Long): Result<LastKnownLocation> {
        return runCatching {
            require(vehicleId > 0)
            require(locationEntryId > 0)
            vehicleApi.fetchLastKnownVehicleLocation(
                vehicleId = vehicleId,
                locationEntryId = locationEntryId,
            ).fold(
                onSuccess = { dataLayerModel ->
                    dataLayerModel.toDomainModel()
                },
                onFailure = { throwable ->
                    throw throwable
                }
            )
        }
    }

    data class LastKnownLocation(
        val address: String?,
        val latitude: Double,
        val longitude: Double,
    )

    private fun LocationEntry.toDomainModel(): LastKnownLocation {
        return LastKnownLocation(
            address = address,
            latitude = geolocation.latitude,
            longitude = geolocation.longitude,
        )
    }
}