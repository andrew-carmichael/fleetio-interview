package com.andrewcarmichael.fleetio.vehiclelist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.andrewcarmichael.fleetio.vehiclelist.domain.FetchVehiclePages
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.toPresentationModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class VehicleListViewModel(
    private val fetchVehiclePages: FetchVehiclePages,
) : ViewModel(), VehicleListIntentHandler {

    private val _sideEffectFlow = MutableSharedFlow<VehicleListSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    val pagedVehicleFlow = fetchVehiclePages.invoke().map { pagingData ->
        pagingData.map { domainModel ->
            domainModel.toPresentationModel()
        }
    }.cachedIn(viewModelScope)

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