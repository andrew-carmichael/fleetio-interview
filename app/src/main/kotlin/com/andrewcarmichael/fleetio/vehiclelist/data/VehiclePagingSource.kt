package com.andrewcarmichael.fleetio.vehiclelist.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.andrewcarmichael.fleetio.vehiclelist.data.models.PaginatedVehiclesResponse
import com.andrewcarmichael.fleetio.vehiclelist.data.models.Vehicle

class VehiclePagingSource(
    private val vehicleApi: VehicleApi,
) : PagingSource<String, Vehicle>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Vehicle> {
        val cursor = params.key
        return try {
            val result: Result<PaginatedVehiclesResponse> =
                vehicleApi.fetchVehicles(cursor = cursor, pageSize = params.loadSize)
            result.fold(
                onSuccess = { response ->
                    LoadResult.Page(
                        data = response.records,
                        prevKey = null,
                        nextKey = response.nextCursor
                    )
                },
                onFailure = { throwable ->
                    LoadResult.Error(throwable)
                }
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, Vehicle>): String? {
        return null
    }
}
