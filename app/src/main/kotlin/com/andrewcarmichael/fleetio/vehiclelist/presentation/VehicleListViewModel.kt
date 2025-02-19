package com.andrewcarmichael.fleetio.vehiclelist.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewcarmichael.fleetio.vehiclelist.data.VehicleApi
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.FakeVehicleData
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleStatus
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleType
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
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

    // TODO get rid of this
    private fun loadFakeData() {
        viewModelScope.launch {
            delay(3000)
            _uiStateFlow.update {
                State.Loaded(
                    vehicles = FakeVehicleData.vehicles.toPersistentList(),
                )
            }
        }
    }

    private fun loadVehicles() {
        viewModelScope.launch {
            vehicleApi.fetchVehicles().fold(
                onSuccess = { successResponse ->
                    Log.d(TAG, "loadVehicles: $successResponse")
                    val models = successResponse.records.map { vehicle ->
                        VehicleModel(
                            id = vehicle.id,
                            name = vehicle.name,
                            type = VehicleType.Car,
                            status = VehicleStatus.Active
                        )
                    }
                    _uiStateFlow.update {
                        State.Loaded(
                            vehicles = models.toPersistentList(),
                        )
                    }
                },
                onFailure = { throwable ->
                    Log.d(TAG, "loadVehicles: $throwable")
                }
            )
        }
    }

    override fun handleIntent(intent: VehicleListIntent) {
        when (intent) {
            is VehicleListIntent.NavigateToVehicleDetail -> handleNavigateToVehicleDetail(intent.id)
        }
    }

    private fun handleNavigateToVehicleDetail(id: String) {
        viewModelScope.launch {
            _sideEffectFlow.emit(VehicleListSideEffect.NavigateToVehicleDetail(id))
        }
    }

    @Immutable
    sealed interface State {
        @Immutable
        data object Loading : State

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
    data class NavigateToVehicleDetail(val id: String) : VehicleListIntent
}

sealed interface VehicleListSideEffect {
    data class NavigateToVehicleDetail(val id: String) : VehicleListSideEffect
}