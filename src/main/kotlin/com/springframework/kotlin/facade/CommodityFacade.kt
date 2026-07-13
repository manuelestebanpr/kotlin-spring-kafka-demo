package com.springframework.kotlin.facade

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.request.CommodityRequest
import java.util.UUID

interface CommodityFacade {
    fun getAll(): List<CommodityDto>
    fun getById(id: UUID): CommodityDto?
    fun create(commodityRequest: CommodityRequest): CommodityDto
    fun update(id: UUID, commodityRequest: CommodityRequest): CommodityDto?
    fun delete(id: UUID)
}
