package com.springframework.kotlin.service

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.entity.Commodity
import com.springframework.kotlin.mapper.toDto
import com.springframework.kotlin.mapper.toEntity
import com.springframework.kotlin.repository.CommodityRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CommodityService(private val commodityRepository: CommodityRepository) {

    @Transactional(readOnly = true)
    fun getAll(): List<CommodityDto> = commodityRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    fun getById(id: UUID): CommodityDto? = commodityRepository.findById(id).orElse(null)?.toDto()

    @Transactional
    fun create(commodityDto: CommodityDto): CommodityDto {
        val commodity = commodityDto.toEntity()
        return commodityRepository.save(commodity).toDto()
    }

    @Transactional
    fun update(id: UUID, commodityDto: CommodityDto): CommodityDto? {
        val existing = commodityRepository.findById(id).orElse(null) ?: return null
        existing.name = commodityDto.name
        return commodityRepository.save(existing).toDto()
    }

    @Transactional
    fun delete(id: UUID) {
        commodityRepository.deleteById(id)
    }
}
