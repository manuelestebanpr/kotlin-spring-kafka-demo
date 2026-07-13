package com.springframework.kotlin.mappers

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.entity.Truck
import com.springframework.kotlin.request.TruckRequest

fun Truck.toDto() = TruckDto(
    id = this.id!!,
    longitude = this.longitude,
    latitude = this.latitude,
    numberOfPassengers = this.numberOfPassengers,
    commodities = this.commodities.map { it.toDto() }.toSet()
)

fun TruckRequest.toEntity() = Truck(
    longitude = this.longitude,
    latitude = this.latitude,
    numberOfPassengers = this.numberOfPassengers
)
