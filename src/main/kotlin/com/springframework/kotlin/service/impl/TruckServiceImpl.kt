package com.springframework.kotlin.service.impl

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.request.TruckRequest
import com.springframework.kotlin.mappers.toDto
import com.springframework.kotlin.mappers.toEntity
import com.springframework.kotlin.repository.TruckRepository
import com.springframework.kotlin.repository.CommodityRepository
import com.springframework.kotlin.service.TruckService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class TruckServiceImpl(
    private val truckRepository: TruckRepository,
) : TruckService {

    @Transactional(readOnly = true)
    override fun getAll(): List<TruckDto> = truckRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    override fun getById(id: UUID): TruckDto? = truckRepository.findById(id).orElse(null)?.toDto()

    @Transactional
    override fun create(truckRequest: TruckRequest): TruckDto {
        return truckRepository.save(truckRequest.toEntity()).toDto()
    }

    @Transactional
    override fun update(id: UUID, truckRequest: TruckRequest): TruckDto? {
        val existingTruck = truckRepository.findById(id).orElse(null) ?: return null

        existingTruck.longitude = truckRequest.longitude
        existingTruck.latitude = truckRequest.latitude
        existingTruck.numberOfPassengers = truckRequest.numberOfPassengers
        
        val updatedCommodities = truckRequest.commodities.map { request -> request.toEntity() }.toMutableSet()
        
        existingTruck.commodities.clear()
        existingTruck.commodities.addAll(updatedCommodities)
        
        return truckRepository.save(existingTruck).toDto()
    }

    @Transactional
    override fun patch(id: UUID, updates: Map<String, Any?>): TruckDto? {
        val existingTruck = truckRepository.findById(id).orElse(null) ?: return null
        
        updates["longitude"]?.let { existingTruck.longitude = it as Double }
        updates["latitude"]?.let { existingTruck.latitude = it as Double }
        updates["numberOfPassengers"]?.let { existingTruck.numberOfPassengers += it as Int }
        
        return truckRepository.save(existingTruck).toDto()
    }

    @Transactional
    override fun delete(id: UUID) {
        truckRepository.deleteById(id)
    }
}
