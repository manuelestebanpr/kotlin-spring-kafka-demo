package com.springframework.kotlin.facade

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.service.TruckService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class TruckFacade(private val truckService: TruckService) {
    fun getAll(): List<TruckDto> = truckService.getAll()
    fun getById(id: UUID): TruckDto? = truckService.getById(id)
    fun create(truckDto: TruckDto): TruckDto = truckService.create(truckDto)
    fun update(id: UUID, truckDto: TruckDto): TruckDto? = truckService.update(id, truckDto)
    fun patch(id: UUID, updates: Map<String, Any?>): TruckDto? = truckService.patch(id, updates)
    fun delete(id: UUID) = truckService.delete(id)
}
