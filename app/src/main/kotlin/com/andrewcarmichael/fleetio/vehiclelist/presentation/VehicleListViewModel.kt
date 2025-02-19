package com.andrewcarmichael.fleetio.vehiclelist.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApi
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleStatus
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VehicleListViewModel(
    private val vehicleApi: VehicleApi,
) : ViewModel(), VehicleListIntentHandler {

    private val _uiStateFlow = MutableStateFlow<State>(State.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private val _sideEffectFlow = MutableSharedFlow<VehicleListSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    init {
        loadVehicles()
    }

    // TODO update this for pagination etc.
    private fun loadVehicles() {
        viewModelScope.launch {
            vehicleApi.fetchVehicles().fold(
                onSuccess = { successResponse ->
                    Log.d(TAG, "loadVehicles success: $successResponse")
                    // TODO this mapping isn't correct exactly
                    successResponse.records.map { vehicle ->
                        VehicleModel(
                            id = vehicle.id.toLong(),
                            name = vehicle.name,
                            description = "${vehicle.year} ${vehicle.make} ${vehicle.model}",
                            imageUrl = vehicle.defaultImageUrlSmall,
                            type = VehicleType.Car,
                            status = VehicleStatus.Active
                        )
                    }.also { vehicleModels ->
                        _uiStateFlow.update {
                            State.Loaded(
                                vehicles = vehicleModels.toPersistentList()
                            )
                        }
                    }
                },
                onFailure = { throwable ->
                    Log.d(TAG, "loadVehicles failure: $throwable")
                    _uiStateFlow.update {
                        State.Error
                    }
                }
            )
        }
    }

    override fun handleIntent(intent: VehicleListIntent) {
        when (intent) {
            is VehicleListIntent.NavigateToVehicleDetail -> handleNavigateToVehicleDetail(intent.id)
        }
    }

    private fun handleNavigateToVehicleDetail(id: Long) {
        viewModelScope.launch {
            _sideEffectFlow.emit(VehicleListSideEffect.NavigateToVehicleDetail(id))
        }
    }

    // TODO add error state
    @Immutable
    sealed interface State {
        @Immutable
        data object Loading : State

        @Immutable
        data object Error : State

        @Immutable
        data class Loaded(
            val vehicles: ImmutableList<VehicleModel>,
            val isLoadingMore: Boolean = false,
        ) : State
    }

    companion object {
        const val TAG = "VehicleListViewModel"
    }
}

fun interface VehicleListIntentHandler {
    fun handleIntent(intent: VehicleListIntent)
}

sealed interface VehicleListIntent {
    data class NavigateToVehicleDetail(val id: Long) : VehicleListIntent
}

sealed interface VehicleListSideEffect {
    data class NavigateToVehicleDetail(val id: Long) : VehicleListSideEffect
}