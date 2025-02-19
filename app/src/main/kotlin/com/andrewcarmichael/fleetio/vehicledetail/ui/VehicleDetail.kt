package com.andrewcarmichael.fleetio.vehicledetail.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrewcarmichael.fleetio.core.ui.Error
import com.andrewcarmichael.fleetio.core.ui.Loading
import com.andrewcarmichael.fleetio.core.ui.VehicleSummary
import com.andrewcarmichael.fleetio.vehicledetail.presentation.VehicleDetailViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun VehicleDetailRoot(
    vehicleId: Long,
    viewModel: VehicleDetailViewModel = koinViewModel { parametersOf(vehicleId) },
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    VehicleDetail(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun VehicleDetail(
    uiState: VehicleDetailViewModel.State,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        is VehicleDetailViewModel.State.Loading -> Loading(modifier = modifier)
        is VehicleDetailViewModel.State.Error -> Error(modifier = modifier)
        is VehicleDetailViewModel.State.Loaded -> Loaded(uiState = uiState, modifier = modifier)
    }
}

@Composable
fun Loaded(
    uiState: VehicleDetailViewModel.State.Loaded,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        VehicleSummary(
            onPressed = {},
            vehicleTitle = uiState.vehicle.name,
            vehicleSubtitle = uiState.vehicle.description,
            imageModel = uiState.vehicle.imageUrl,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}