package com.andrewcarmichael.fleetio

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleDetailSideEffect
import com.andrewcarmichael.fleetio.vehiclelist.ui.VehicleDetailScreen
import com.andrewcarmichael.fleetio.vehiclelist.ui.vehicleDetailScreen
import com.andrewcarmichael.fleetio.vehiclelist.presentation.VehicleListSideEffect
import com.andrewcarmichael.fleetio.vehiclelist.ui.VehicleListScreen
import com.andrewcarmichael.fleetio.vehiclelist.ui.vehicleListScreen

@Composable
fun FleetioApplication(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.systemBarsPadding(),
    ) {
        FleetioNavigation(modifier = Modifier.fillMaxSize())
    }
}

@Composable
internal fun FleetioNavigation(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = VehicleListScreen,
        modifier = modifier,
    ) {
        vehicleListScreen(
            onSideEffect = { sideEffect ->
                when (sideEffect) {
                    is VehicleListSideEffect.NavigateToVehicleDetail -> navController.navigate(VehicleDetailScreen(vehicleId = sideEffect.id))
                }
            }
        )
        vehicleDetailScreen(
            onSideEffect = { sideEffect ->
                when (sideEffect) {
                    is VehicleDetailSideEffect.NavigateToMap -> {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("geo:${sideEffect.latitude},${sideEffect.longitude}?q=${sideEffect.latitude},${sideEffect.longitude}")
                            )
                        )
                    }
                }
            }
        )
    }
}