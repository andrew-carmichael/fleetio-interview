package com.andrewcarmichael.fleetio.vehiclelist.domain

import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApi
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.toDomainModel

//class FetchVehicles(
//    private val vehicleApi: VehicleApi,
//) {
//    suspend operator fun invoke(page: Int = 0, pageSize: Int = 10): Result<List<VehicleModel>> {
//        return runCatching {
//            require(page >= 0)
//            require(pageSize > 0)
//            vehicleApi.fetchVehicles(page = page, pageSize = pageSize)
//                .fold(
//                   onSuccess = { dataLayerResponse ->
//                       (dataLayerResponse.records.map { record -> record.toDomainModel() })
//                   },
//                    onFailure = {
//                        throw it
//                    },
//                )
//        }
//    }
//}