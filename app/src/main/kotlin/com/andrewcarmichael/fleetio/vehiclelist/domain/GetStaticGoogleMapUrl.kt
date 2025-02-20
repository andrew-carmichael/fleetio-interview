package com.andrewcarmichael.fleetio.vehiclelist.domain

import com.andrewcarmichael.fleetio.core.secrets.GoogleMapsSecrets

// I used ChatGPT to put together the URL for the map image.

class GetStaticGoogleMapUrl(
    private val googleMapsSecrets: GoogleMapsSecrets,
) {
    operator fun invoke(
        latitude: Double,
        longitude: Double,
        width: Int = 400,
        height: Int = 400,
        zoom: Int = 15,
        mapType: String = "roadmap",
    ): String {
        return "https://maps.googleapis.com/maps/api/staticmap?" +
                "center=$latitude,$longitude" +
                "&zoom=$zoom" +
                "&size=${width}x$height" +
                "&scale=2" +
                "&maptype=$mapType" +
                "&markers=color:red%7Clabel:V%7C$latitude,$longitude" +
                "&key=${googleMapsSecrets.apiKey}"
    }
}

