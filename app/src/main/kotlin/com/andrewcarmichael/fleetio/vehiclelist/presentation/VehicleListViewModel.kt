package com.andrewcarmichael.fleetio.vehiclelist.presentation

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewcarmichael.fleetio.vehiclelist.domain.FetchVehicles
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.VehicleModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VehicleListViewModel(
    private val fetchVehicles: FetchVehicles,
) : ViewModel(), VehicleListIntentHandler {

    private val _uiStateFlow = MutableStateFlow<State>(State.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private val _sideEffectFlow = MutableSharedFlow<VehicleListSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    init {
        loadVehicles()
    }

    private fun loadVehicles() {
        viewModelScope.launch {
            _uiStateFlow.update {
                fetchVehicles().fold(
                    onSuccess = { vehicles ->
                        Log.d(TAG, "loadVehicles success: loaded ${vehicles.size}")
                        State.Loaded(
                            vehicles = vehicles.toPersistentList(),
                            isLoadingMore = false,
                        )
                    },
                    onFailure = {
                        Log.d(TAG, "loadVehicles failed: $it")
                        State.Error
                    },
                )
            }
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