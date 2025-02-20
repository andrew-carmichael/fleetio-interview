package com.andrewcarmichael.fleetio.vehiclelist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrewcarmichael.fleetio.R.string
import com.andrewcarmichael.fleetio.ui.theme.FleetioTheme
import com.andrewcarmichael.fleetio.vehiclelist.domain.model.FakeVehicleData
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleDetailViewModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.Tag
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.toPresentationModel
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
            vehicleModel = uiState.vehicle,
            showTags = false,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        VehicleDetails(
            model = uiState.vehicle,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
private fun VehicleDetails(
    model: VehicleModel,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            model.tags.forEach { tag ->
                DetailRow(
                    label = when (tag) {
                        is Tag.VehicleStatus -> stringResource(string.vehicleDetailStatusLabel)
                        is Tag.VehicleType -> stringResource(string.vehicleTypeLabel)
                        else -> return@forEach
                    },
                    value = stringResource(tag.displayStringRes),
                )
            }
            model.vin?.let { vin ->
                DetailRow(
                    label = stringResource(string.vehicleDetailVin),
                    value = vin,
                )
            }
            model.licensePlate?.let { licensePlate ->
                DetailRow(
                    label = stringResource(string.vehicleDetailLicensePlate),
                    value = licensePlate,
                )
            }
        }
    }
}

@Composable
private fun DetailRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
    ) {
        Text(text = label)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = value)
    }
}

@Preview
@Composable
private fun PreviewLoaded() {
    FleetioTheme {
        Loaded(
            uiState = VehicleDetailViewModel.State.Loaded(
                vehicle = FakeVehicleData.vehicles.first().toPresentationModel()
            ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}