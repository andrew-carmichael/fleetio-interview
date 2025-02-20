package com.andrewcarmichael.fleetio.vehiclelist.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleDetailSideEffect
import kotlinx.serialization.Serializable

@Serializable
data class VehicleDetailScreen(val vehicleId: Long)

fun NavGraphBuilder.vehicleDetailScreen(
    onSideEffect: (VehicleDetailSideEffect) -> Unit,
) {
    composable<VehicleDetailScreen> { backStackEntry ->
        val destination: VehicleDetailScreen = backStackEntry.toRoute()
        VehicleDetailRoot(
            vehicleId = destination.vehicleId,
            onSideEffect = onSideEffect,
            modifier = Modifier.fillMaxSize()
        )
    }
}