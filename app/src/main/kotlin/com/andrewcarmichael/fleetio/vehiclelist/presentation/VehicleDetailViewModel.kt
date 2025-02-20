package com.andrewcarmichael.fleetio.vehiclelist.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewcarmichael.fleetio.vehiclelist.domain.FetchVehicle
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.toPresentationModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VehicleDetailViewModel(
    vehicleId: Long,
    private val fetchVehicle: FetchVehicle,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<State>(State.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    init {
        loadVehicleDetails(vehicleId)
    }

    private fun loadVehicleDetails(id: Long) {
        require(id > 0)
        viewModelScope.launch {
            _uiStateFlow.update {
                fetchVehicle(vehicleId = id).fold(
                    onSuccess = { domainModel ->
                        Log.d(TAG, "loadVehicleDetails success: $domainModel")
                        State.Loaded(vehicle = domainModel.toPresentationModel())
                    },
                    onFailure = {
                        Log.d(TAG, "loadVehicleDetails failure: $it ")
                        State.Error
                    },
                )
            }
        }
    }

    @Immutable
    sealed interface State {
        @Immutable
        data object Loading : State

        @Immutable
        data object Error : State

        @Immutable
        data class Loaded(
            val vehicle: VehicleModel
        ) : State
    }

    companion object {
        private const val TAG = "VehicleDetailViewModel"
    }
}