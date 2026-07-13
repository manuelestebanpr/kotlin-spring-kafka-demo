package com.springframework.kotlin.service.impl

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.request.CommodityRequest
import com.springframework.kotlin.mappers.toDto
import com.springframework.kotlin.mappers.toEntity
import com.springframework.kotlin.repository.CommodityRepository
import com.springframework.kotlin.service.CommodityService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CommodityServiceImpl(private val commodityRepository: CommodityRepository) : CommodityService {

    @Transactional(readOnly = true)
    override fun getAll(): List<CommodityDto> = commodityRepository.findAll().map { it.toDto() }

    @Transactional(readOnly = true)
    override fun getById(id: UUID): CommodityDto? = commodityRepository.findById(id).orElse(null)?.toDto()

    @Transactional
    override fun create(commodityRequest: CommodityRequest): CommodityDto {
        return commodityRepository.save(commodityRequest.toEntity()).toDto()
    }

    @Transactional
    override fun update(id: UUID, commodityRequest: CommodityRequest): CommodityDto? {
        val existing = commodityRepository.findById(id).orElse(null) ?: return null
        existing.name = commodityRequest.name
        return commodityRepository.save(existing).toDto()
    }

    @Transactional
    override fun delete(id: UUID) {
        commodityRepository.deleteById(id)
    }
}
