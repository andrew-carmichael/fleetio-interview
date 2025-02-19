package com.andrewcarmichael.fleetio.vehiclelist.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.andrewcarmichael.fleetio.ui.theme.FleetioTheme

@Composable
fun Loading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    FleetioTheme {
        Loading(
            modifier = Modifier.fillMaxSize()
        )
    }
}
