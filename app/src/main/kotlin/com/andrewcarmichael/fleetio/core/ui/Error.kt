package com.andrewcarmichael.fleetio.core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.andrewcarmichael.fleetio.R.drawable
import com.andrewcarmichael.fleetio.R.string
import com.andrewcarmichael.fleetio.ui.theme.FleetioTheme

@Composable
fun Error(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(drawable.ic_error),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(string.errorGeneric)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    FleetioTheme {
        Error(
            modifier = Modifier.fillMaxSize(),
        )
    }
}