package com.andrewcarmichael.fleetio.vehiclelist.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// I used ChatGPT to generate this from the APIs response.

@Serializable
data class LocationEntry(
    val id: Long,
    @SerialName("locatable_type")
    val locatableType: String,
    @SerialName("locatable_id")
    val locatableId: Long,
    val date: String,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    @SerialName("contact_id")
    val contactId: Long? = null,
    val address: String,
    @SerialName("is_current")
    val isCurrent: Boolean,
    @SerialName("item_type")
    val itemType: String,
    @SerialName("item_id")
    val itemId: Long,
    @SerialName("vehicle_id")
    val vehicleId: Long,
    val location: String,
    @SerialName("address_components")
    val addressComponents: AddressComponents,
    val geolocation: Geolocation
)

@Serializable
data class AddressComponents(
    @SerialName("street_number")
    val streetNumber: String,
    val street: String,
    val city: String,
    val region: String,
    @SerialName("region_short")
    val regionShort: String,
    val country: String,
    @SerialName("country_short")
    val countryShort: String,
    @SerialName("postal_code")
    val postalCode: String
)

@Serializable
data class Geolocation(
    val latitude: Double,
    val longitude: Double
)
