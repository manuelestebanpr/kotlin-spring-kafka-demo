package com.springframework.kotlin.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class CommodityDto(
    @JsonProperty("id")
    val id: UUID,
    @JsonProperty("name")
    val name: String
)
