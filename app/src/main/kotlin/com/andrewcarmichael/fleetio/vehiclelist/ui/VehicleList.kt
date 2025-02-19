package com.andrewcarmichael.fleetio.vehiclelist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrewcarmichael.fleetio.core.ui.Error
import com.andrewcarmichael.fleetio.core.ui.Loading
import com.andrewcarmichael.fleetio.core.ui.VehicleSummary
import com.andrewcarmichael.fleetio.ui.theme.FleetioTheme
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListIntent.NavigateToVehicleDetail
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListIntentHandler
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListSideEffect
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListViewModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.FakeVehicleData
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun VehicleListRoot(
    onSideEffect: (VehicleListSideEffect) -> Unit,
    viewModel: VehicleListViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiStateFlow.collectAsStateWithLifecycle()
    LaunchedEffect(viewModel) {
        viewModel.sideEffectFlow.collectLatest { sideEffect ->
            onSideEffect(sideEffect)
        }
    }
    VehicleList(
        intentHandler = viewModel,
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun VehicleList(
    intentHandler: VehicleListIntentHandler,
    uiState: VehicleListViewModel.State,
    modifier: Modifier = Modifier,
) {
    when (uiState) {
        VehicleListViewModel.State.Loading -> Loading(modifier = modifier)
        VehicleListViewModel.State.Error -> Error(modifier = modifier)
        is VehicleListViewModel.State.Loaded -> Loaded(
            intentHandler = intentHandler,
            uiState = uiState,
            modifier = modifier,
        )
    }
}

@Composable
private fun Loaded(
    intentHandler: VehicleListIntentHandler,
    uiState: VehicleListViewModel.State.Loaded,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            count = uiState.vehicles.size,
            key = { uiState.vehicles[it].id }
        ) {
            val vehicle = uiState.vehicles[it]
            VehicleSummary(
                onPressed = {
                    intentHandler.handleIntent(NavigateToVehicleDetail(vehicle.id))
                },
                vehicleTitle = vehicle.name,
                vehicleSubtitle = vehicle.description,
                imageModel = vehicle.imageUrl,
                chips = listOf("Chip 1", "Chip 2"),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun InfoChip(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.primaryContainer,
        tonalElevation = 1.dp,
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewLoading() {
    FleetioTheme {
        VehicleList(
            intentHandler = {},
            uiState = VehicleListViewModel.State.Loading,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewError() {
    FleetioTheme {
        VehicleList(
            intentHandler = {},
            uiState = VehicleListViewModel.State.Error,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@Preview
@Composable
private fun PreviewLoaded() {
    FleetioTheme {
        VehicleList(
            intentHandler = {},
            uiState = VehicleListViewModel.State.Loaded(
                vehicles = FakeVehicleData.vehicles.toPersistentList()
            )
        )
    }
}