package com.andrewcarmichael.fleetio.vehiclelist.domain

import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApi
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.toDomainModel

class FetchVehicle(
    private val vehicleApi: VehicleApi,
) {
    suspend operator fun invoke(vehicleId: Long): Result<VehicleModel> {
        return runCatching {
            require(vehicleId > 0)
            vehicleApi.fetchVehicle(id = vehicleId).fold(
                onSuccess = { dataLayerModel ->
                    dataLayerModel.toDomainModel()
                },
                onFailure = { throwable ->
                    throw throwable
                }
            )
        }
    }
}