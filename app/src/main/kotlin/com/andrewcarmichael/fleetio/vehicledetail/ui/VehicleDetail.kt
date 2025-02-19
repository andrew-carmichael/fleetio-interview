package com.andrewcarmichael.fleetio.vehicledetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun VehicleDetailRoot(
    modifier: Modifier = Modifier
) {
    VehicleDetail(modifier = modifier)
}

@Composable
internal fun VehicleDetail(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Text(text = "Vehicle Detail Screen")
    }
}