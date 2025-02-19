package com.andrewcarmichael.fleetio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.andrewcarmichael.fleetio.ui.theme.FleetioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FleetioTheme {
                FleetioApplication(modifier = Modifier.fillMaxSize())
            }
        }
    }
}