package com.andrewcarmichael.fleetio.vehiclelist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.andrewcarmichael.fleetio.R.string
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListIntent.NavigateToVehicleDetail
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListIntentHandler
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListSideEffect
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListViewModel
import com.andrewcarmichael.fleetio.vehiclelist.presentation.model.VehicleModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun VehicleListRoot(
    onSideEffect: (VehicleListSideEffect) -> Unit,
    viewModel: VehicleListViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val pagedVehicleState = viewModel.pagedVehicleFlow.collectAsLazyPagingItems()
    LaunchedEffect(viewModel) {
        viewModel.sideEffectFlow.collectLatest { sideEffect ->
            onSideEffect(sideEffect)
        }
    }
    VehicleList(
        intentHandler = viewModel,
        pagedVehicleState = pagedVehicleState,
        modifier = modifier,
    )
}

@Composable
internal fun VehicleList(
    intentHandler: VehicleListIntentHandler,
    pagedVehicleState: LazyPagingItems<VehicleModel>,
    modifier: Modifier = Modifier,
) {
    when (val refreshState = pagedVehicleState.loadState.refresh) {
        LoadState.Loading -> Loading(modifier = modifier)
        is LoadState.Error -> Error(modifier = modifier)
        is LoadState.NotLoading -> Loaded(
            intentHandler = intentHandler,
            pagedVehicleState = pagedVehicleState,
            modifier = modifier,
        )
    }
}

@Composable
private fun Loaded(
    intentHandler: VehicleListIntentHandler,
    pagedVehicleState: LazyPagingItems<VehicleModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(count = pagedVehicleState.itemCount) { index ->
            pagedVehicleState[index]?.let { vehicle ->
                VehicleSummary(
                    onPressed = {
                        intentHandler.handleIntent(NavigateToVehicleDetail(vehicle.id))
                    },
                    vehicleModel = vehicle,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        pagedVehicleState.apply {
            when (loadState.append) {
                LoadState.Loading -> {
                    item {
                        Loading(modifier = Modifier.fillMaxWidth().padding(16.dp))
                    }
                }
                is LoadState.Error -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth().padding(16.dp)
                        ) {
                            Text(text = stringResource(string.errorGeneric))
                        }
                    }
                }
                is LoadState.NotLoading -> Unit
            }
        }
    }
}
