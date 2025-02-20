package com.andrewcarmichael.fleetio.vehiclelist.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// I used ChatGPT to generate this from the APIs response.

@Serializable
data class Vehicle(
    val id: Int,

    @SerialName("account_id")
    val accountId: Int,

    @SerialName("archived_at")
    val archivedAt: String? = null,

    @SerialName("fuel_type_id")
    val fuelTypeId: Int? = null,

    @SerialName("fuel_type_name")
    val fuelTypeName: String? = null,

    @SerialName("fuel_volume_units")
    val fuelVolumeUnits: String,

    @SerialName("group_id")
    val groupId: Int? = null,

    @SerialName("group_name")
    val groupName: String? = null,

    val name: String,
    val ownership: String,

    @SerialName("current_location_entry_id")
    val currentLocationEntryId: Int? = null,

    @SerialName("system_of_measurement")
    val systemOfMeasurement: String,

    @SerialName("vehicle_type_id")
    val vehicleTypeId: Int,

    @SerialName("vehicle_type_name")
    val vehicleTypeName: String,

    @SerialName("is_sample")
    val isSample: Boolean,

    @SerialName("vehicle_status_id")
    val vehicleStatusId: Int,

    @SerialName("vehicle_status_name")
    val vehicleStatusName: String,

    @SerialName("vehicle_status_color")
    val vehicleStatusColor: String,

    @SerialName("primary_meter_unit")
    val primaryMeterUnit: String,

    @SerialName("primary_meter_value")
    val primaryMeterValue: String,

    @SerialName("primary_meter_date")
    val primaryMeterDate: String?,

    @SerialName("primary_meter_usage_per_day")
    val primaryMeterUsagePerDay: String?,

    @SerialName("secondary_meter_unit")
    val secondaryMeterUnit: String? = null,

    @SerialName("secondary_meter_value")
    val secondaryMeterValue: String,

    @SerialName("secondary_meter_date")
    val secondaryMeterDate: String? = null,

    @SerialName("secondary_meter_usage_per_day")
    val secondaryMeterUsagePerDay: String?,

    @SerialName("in_service_meter_value")
    val inServiceMeterValue: String? = null,

    @SerialName("in_service_date")
    val inServiceDate: String? = null,

    @SerialName("out_of_service_meter_value")
    val outOfServiceMeterValue: String? = null,

    @SerialName("out_of_service_date")
    val outOfServiceDate: String? = null,

    @SerialName("estimated_service_months")
    val estimatedServiceMonths: Int? = null,

    @SerialName("estimated_replacement_mileage")
    val estimatedReplacementMileage: String? = null,

    @SerialName("estimated_resale_price_cents")
    val estimatedResalePriceCents: Int? = null,

    @SerialName("fuel_entries_count")
    val fuelEntriesCount: Int,

    @SerialName("service_entries_count")
    val serviceEntriesCount: Int,

    @SerialName("service_reminders_count")
    val serviceRemindersCount: Int,

    @SerialName("vehicle_renewal_reminders_count")
    val vehicleRenewalRemindersCount: Int,

    @SerialName("comments_count")
    val commentsCount: Int,

    @SerialName("documents_count")
    val documentsCount: Int,

    @SerialName("images_count")
    val imagesCount: Int,

    @SerialName("issues_count")
    val issuesCount: Int,

    @SerialName("work_orders_count")
    val workOrdersCount: Int,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String,

    @SerialName("group_ancestry")
    val groupAncestry: String? = null,

    val color: String? = null,

    @SerialName("license_plate")
    val licensePlate: String? = null,

    val make: String?,
    val model: String? = null,

    @SerialName("registration_expiration_month")
    val registrationExpirationMonth: Int,

    @SerialName("registration_state")
    val registrationState: String? = null,

    val trim: String? = null,
    val vin: String? = null,
    val year: String? = null,

    @SerialName("default_image_url_small")
    val defaultImageUrlSmall: String? = null,

    @SerialName("ai_enabled")
    val aiEnabled: Boolean,

    @SerialName("assetable_type")
    val assetableType: String,

    @SerialName("custom_fields")
    val customFields: Map<String, String> = emptyMap(),

    @SerialName("axle_config_id")
    val axleConfigId: Int? = null
)
