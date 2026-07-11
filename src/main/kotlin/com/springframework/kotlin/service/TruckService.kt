package com.springframework.kotlin.service

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.entity.Truck
import com.springframework.kotlin.mapper.toDto
import com.springframework.kotlin.mapper.toEntity
import com.springframework.kotlin.repository.TruckRepository
import com.springframework.kotlin.repository.CommodityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class TruckService(
    private val truckRepository: TruckRepository,
    private val commodityRepository: CommodityRepository
) {

    @Transactional(readOnly = true)
    fun getAll(): List<TruckDto> = truckRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    fun getById(id: UUID): TruckDto? = truckRepository.findById(id).orElse(null)?.toDto()

    @Transactional
    fun create(truckDto: TruckDto): TruckDto {
        val truck = truckDto.toEntity()
        return truckRepository.save(truck).toDto()
    }

    @Transactional
    fun update(id: UUID, truckDto: TruckDto): TruckDto? {
        val existingTruck = truckRepository.findById(id).orElse(null) ?: return null
        
        // BEST PRACTICE: Update the managed entity instead of creating a new one.
        // This allows JPA/Hibernate to track changes and perform dirty checking.
        existingTruck.longitude = truckDto.longitude
        existingTruck.latitude = truckDto.latitude
        existingTruck.numberOfPassengers = truckDto.numberOfPassengers
        
        // Update commodities: Handle existing ones and new ones
        val updatedCommodities = truckDto.commodities.map { dto ->
            if (dto.id != null) {
                commodityRepository.findById(dto.id).orElse(dto.toEntity())
            } else {
                dto.toEntity()
            }
        }.toMutableSet()
        
        existingTruck.commodities.clear()
        existingTruck.commodities.addAll(updatedCommodities)
        
        return truckRepository.save(existingTruck).toDto()
    }

    @Transactional
    fun patch(id: UUID, updates: Map<String, Any?>): TruckDto? {
        val existingTruck = truckRepository.findById(id).orElse(null) ?: return null
        
        updates["longitude"]?.let { existingTruck.longitude = it as Double }
        updates["latitude"]?.let { existingTruck.latitude = it as Double }
        updates["numberOfPassengers"]?.let { existingTruck.numberOfPassengers = it as Int }
        
        // Commodities patching could be more complex depending on requirements (add/remove vs replace)
        // Here we just skip it for simplicity or implement a full replacement if provided
        
        return truckRepository.save(existingTruck).toDto()
    }

    @Transactional
    fun delete(id: UUID) {
        truckRepository.deleteById(id)
    }
}
