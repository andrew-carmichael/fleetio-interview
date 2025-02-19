package com.andrewcarmichael.fleetio.core.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginatedVehiclesResponse(
    @SerialName("start_cursor")
    val startCursor: String,

    @SerialName("next_cursor")
    val nextCursor: String? = null,

    @SerialName("per_page")
    val perPage: Int,

    @SerialName("estimated_remaining_count")
    val estimatedRemainingCount: Int,

    @SerialName("filtered_by")
    val filteredBy: List<String> = emptyList(),

    @SerialName("sorted_by")
    val sortedBy: List<SortedBy>,

    @SerialName("records")
    val records: List<Vehicle>
)
