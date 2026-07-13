package com.springframework.kotlin.facade

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.request.TruckRequest
import java.util.UUID

interface TruckFacade {
    fun getAll(): List<TruckDto>
    fun getById(id: UUID): TruckDto?
    fun create(truckRequest: TruckRequest): TruckDto
    fun update(id: UUID, truckRequest: TruckRequest): TruckDto?
    fun patch(id: UUID, updates: Map<String, Any?>): TruckDto?
    fun delete(id: UUID)
}
