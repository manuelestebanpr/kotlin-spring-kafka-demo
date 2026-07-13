package com.springframework.kotlin.facade.impl

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.request.TruckRequest
import com.springframework.kotlin.facade.TruckFacade
import com.springframework.kotlin.service.TruckService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class TruckFacadeImpl(private val truckService: TruckService) : TruckFacade {
    override fun getAll(): List<TruckDto> = truckService.getAll()
    override fun getById(id: UUID): TruckDto? = truckService.getById(id)
    override fun create(truckRequest: TruckRequest): TruckDto = truckService.create(truckRequest)
    override fun update(id: UUID, truckRequest: TruckRequest): TruckDto? = truckService.update(id, truckRequest)
    override fun patch(id: UUID, updates: Map<String, Any?>): TruckDto? = truckService.patch(id, updates)
    override fun delete(id: UUID) = truckService.delete(id)
}
