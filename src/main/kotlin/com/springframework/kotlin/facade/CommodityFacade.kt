package com.springframework.kotlin.facade

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.service.CommodityService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CommodityFacade(private val commodityService: CommodityService) {
    fun getAll(): List<CommodityDto> = commodityService.getAll()
    fun getById(id: UUID): CommodityDto? = commodityService.getById(id)
    fun create(commodityDto: CommodityDto): CommodityDto = commodityService.create(commodityDto)
    fun update(id: UUID, commodityDto: CommodityDto): CommodityDto? = commodityService.update(id, commodityDto)
    fun delete(id: UUID) = commodityService.delete(id)
}
