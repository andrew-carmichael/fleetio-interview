package com.andrewcarmichael.fleetio.vehicledetail.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object VehicleDetailScreen

fun NavGraphBuilder.vehicleDetailScreen() {
    composable<VehicleDetailScreen> {
        VehicleDetailRoot(modifier = Modifier.fillMaxSize())
    }
}