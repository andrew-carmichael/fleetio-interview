package com.andrewcarmichael.fleetio.vehiclelist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListSideEffect
import kotlinx.serialization.Serializable

@Serializable
data object VehicleListScreen

fun NavGraphBuilder.vehicleListScreen(
    onSideEffect: (VehicleListSideEffect) -> Unit,
) {
    composable<VehicleListScreen> {
        VehicleListRoot(
            onSideEffect = onSideEffect,
            modifier = Modifier.fillMaxSize(),
        )
    }
}
