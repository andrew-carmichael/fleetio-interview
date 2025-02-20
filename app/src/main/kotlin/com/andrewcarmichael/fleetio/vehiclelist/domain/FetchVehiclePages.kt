package com.andrewcarmichael.fleetio.vehiclelist.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.andrewcarmichael.fleetio.vehiclelist.data.VehiclePagingSource
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FetchVehiclePages(
    private val vehiclePagingSource: VehiclePagingSource
) {
    operator fun invoke(pageSize: Int = 10): Flow<PagingData<VehicleModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { vehiclePagingSource },
        ).flow.map { pagingData ->
            pagingData.map { dataLayerModel ->
                dataLayerModel.toDomainModel()
            }
        }
    }
}