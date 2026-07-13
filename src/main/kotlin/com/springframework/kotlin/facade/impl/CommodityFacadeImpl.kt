package com.springframework.kotlin.facade.impl

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.request.CommodityRequest
import com.springframework.kotlin.facade.CommodityFacade
import com.springframework.kotlin.service.CommodityService
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class CommodityFacadeImpl(private val commodityService: CommodityService) : CommodityFacade {
    override fun getAll(): List<CommodityDto> = commodityService.getAll()
    override fun getById(id: UUID): CommodityDto? = commodityService.getById(id)
    override fun create(commodityRequest: CommodityRequest): CommodityDto = commodityService.create(commodityRequest)
    override fun update(id: UUID, commodityRequest: CommodityRequest): CommodityDto? = commodityService.update(id, commodityRequest)
    override fun delete(id: UUID) = commodityService.delete(id)
}
