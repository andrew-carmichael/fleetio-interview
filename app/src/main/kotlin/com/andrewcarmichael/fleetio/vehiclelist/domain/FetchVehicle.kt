package com.andrewcarmichael.fleetio.vehiclelist.domain

import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApi
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.toDomainModel

class FetchVehicle(
    private val vehicleApi: VehicleApi,
) {
    suspend operator fun invoke(vehicleId: Long): Result<VehicleModel> {
        return vehicleApi.fetchVehicle(id = vehicleId).fold(
            onSuccess = { dataLayerModel ->
                Result.success(dataLayerModel.toDomainModel())
            },
            onFailure = { throwable ->
                Result.failure(throwable)
            }
        )
    }
}