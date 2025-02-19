package com.andrewcarmichael.fleetio.vehiclelist.ui

import android.R
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.andrewcarmichael.fleetio.ui.theme.FleetioTheme
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListIntent
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListIntent.NavigateToVehicleDetail
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListIntentHandler
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListSideEffect
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListViewModel
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
        is VehicleListViewModel.State.Loaded -> Loaded(
            intentHandler = intentHandler,
            uiState = uiState,
            modifier = modifier,
        )
    }
}

@Composable
private fun Loading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
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
            val item = uiState.vehicles[it]
            Row(
                modifier = Modifier.fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black)
                    .clickable(
                        onClick = {
                            intentHandler.handleIntent(NavigateToVehicleDetail(id = item.id.toString()))
                        }
                    )
                    .padding(16.dp)
            )  {
                Text(
                    text = item.name
                )
            }
        }
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