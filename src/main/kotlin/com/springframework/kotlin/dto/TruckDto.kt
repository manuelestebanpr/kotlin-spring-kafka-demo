package com.springframework.kotlin.dto

import java.util.UUID

data class TruckDto(
    val id: UUID? = null,
    val longitude: Double,
    val latitude: Double,
    val numberOfPassengers: Int,
    val commodities: Set<CommodityDto> = emptySet()
)
