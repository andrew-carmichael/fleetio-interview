package com.andrewcarmichael.fleetio.core.network

data class FleetioApiBaseUrl(
    val hostname: String,
    val pathSegments: List<String>,
)