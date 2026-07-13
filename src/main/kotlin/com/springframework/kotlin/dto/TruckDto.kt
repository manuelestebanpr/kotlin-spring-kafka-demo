package com.springframework.kotlin.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class TruckDto(
    @JsonProperty("id")
    val id: UUID,
    @JsonProperty("longitude")
    val longitude: Double,
    @JsonProperty("latitude")
    val latitude: Double,
    @JsonProperty("numberOfPassengers")
    val numberOfPassengers: Int,
    @JsonProperty("commodities")
    val commodities: Set<CommodityDto> = emptySet()
)
