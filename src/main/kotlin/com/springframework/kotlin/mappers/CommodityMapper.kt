package com.springframework.kotlin.mappers

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.entity.Commodity
import com.springframework.kotlin.request.CommodityRequest

fun Commodity.toDto() = CommodityDto(
    id = this.id!!,
    name = this.name
)

fun CommodityRequest.toEntity() = Commodity(
    name = this.name
)