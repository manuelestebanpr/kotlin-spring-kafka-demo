package com.springframework.kotlin.mapper

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.entity.Commodity
import com.springframework.kotlin.entity.Truck

fun Commodity.toDto() = CommodityDto(
    id = this.id,
    name = this.name
)

fun CommodityDto.toEntity() = Commodity(
    id = this.id ?: java.util.UUID.randomUUID(),
    name = this.name
)

fun Truck.toDto() = TruckDto(
    id = this.id,
    longitude = this.longitude,
    latitude = this.latitude,
    numberOfPassengers = this.numberOfPassengers,
    commodities = this.commodities.map { it.toDto() }.toSet()
)

fun TruckDto.toEntity() = Truck(
    id = this.id ?: java.util.UUID.randomUUID(),
    longitude = this.longitude,
    latitude = this.latitude,
    numberOfPassengers = this.numberOfPassengers,
    commodities = this.commodities.map { it.toEntity() }.toMutableSet()
)
