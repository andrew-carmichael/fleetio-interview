package com.andrewcarmichael.fleetio.vehiclelist.presentation

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.FakeVehicleData
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VehicleListViewModel : ViewModel(), VehicleListIntentHandler {

    private val _uiStateFlow = MutableStateFlow<State>(State.Loading)
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private val _sideEffectFlow = MutableSharedFlow<VehicleListSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    init {
        loadFakeData()
    }

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