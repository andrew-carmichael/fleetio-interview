package com.andrewcarmichael.fleetio.vehiclelist.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewcarmichael.fleetio.vehiclelist.domain.FetchLastKnownVehicleLocation
import com.andrewcarmichael.fleetio.vehiclelist.domain.FetchVehicle
import com.andrewcarmichael.fleetio.vehiclelist.domain.GetStaticGoogleMapUrl
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleLocationModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.toPresentationModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleModel as VehicleDomainModel

class VehicleDetailViewModel(
    vehicleId: Long,
    private val fetchVehicle: FetchVehicle,
    private val fetchLastKnownVehicleLocation: FetchLastKnownVehicleLocation,
    private val getStaticGoogleMapUrl: GetStaticGoogleMapUrl,
) : ViewModel(), VehicleDetailIntentHandler {

    private val _sideEffectFlow = MutableSharedFlow<VehicleDetailSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    private val _uiStateFlow = MutableStateFlow<State>(State.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private val vehicleLoadedEvents = MutableSharedFlow<VehicleDomainModel>()

    init {
        observeVehicleSuccessfullyLoaded()
        loadVehicleDetails(vehicleId)
    }

    private fun loadVehicleDetails(id: Long) {
        require(id > 0)
        viewModelScope.launch {
            _uiStateFlow.update {
                fetchVehicle(vehicleId = id).fold(
                    onSuccess = { domainModel ->
                        Log.d(TAG, "loadVehicleDetails success: $domainModel")
                        vehicleLoadedEvents.emit(domainModel)
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

    //
    // Wait for a vehicle to be loaded, then use the vehicle's last known location id to fetch
    // the location details. From there, use the location details to construct a URL for a
    // static Google map.
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeVehicleSuccessfullyLoaded() {
        viewModelScope.launch {
            vehicleLoadedEvents
                .filter { it.lastKnownLocationId != null }
                .flatMapLatest { vehicleDomainModel ->
                    flow {
                        val result = fetchLastKnownVehicleLocation(
                            vehicleId = vehicleDomainModel.id,
                            locationEntryId = vehicleDomainModel.lastKnownLocationId!!
                        )
                        if (result.isSuccess) {
                            result.getOrNull()?.let { emit(it) }
                        }
                    }
                }
                .map { location ->
                    VehicleLocationModel(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        address = location.address,
                        googleMapUrl = getStaticGoogleMapUrl(
                            latitude = location.latitude,
                            longitude = location.longitude,
                        )
                    )
                }
                .collectLatest { locationModel ->
                    _uiStateFlow.update { old ->
                        if (old is State.Loaded) {
                            old.copy(
                                vehicleLocationModel = locationModel,
                            )
                        } else old
                    }
                }
        }
    }

    override fun handleIntent(intent: VehicleDetailIntent) {
        when (intent) {
            is VehicleDetailIntent.NavigateToMap -> handleNavigateToMap()
        }
    }

    private fun handleNavigateToMap() {
        viewModelScope.launch {
            (uiStateFlow.value as? State.Loaded)?.vehicleLocationModel?.let { locationModel ->
                _sideEffectFlow.emit(
                    VehicleDetailSideEffect.NavigateToMap(
                        latitude = locationModel.latitude,
                        longitude = locationModel.longitude,
                    )
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
            val vehicle: VehicleModel,
            val vehicleLocationModel: VehicleLocationModel? = null,
        ) : State
    }

    companion object {
        private const val TAG = "VehicleDetailViewModel"
    }
}

fun interface VehicleDetailIntentHandler {
    fun handleIntent(intent: VehicleDetailIntent)
}

sealed interface VehicleDetailIntent {
    data class NavigateToMap(val temp: Int) : VehicleDetailIntent
}

sealed interface VehicleDetailSideEffect {
    data class NavigateToMap(
        val latitude: Double,
        val longitude: Double,
    ) : VehicleDetailSideEffect
}