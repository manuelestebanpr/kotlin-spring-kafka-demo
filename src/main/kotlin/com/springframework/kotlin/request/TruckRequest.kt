package com.springframework.kotlin.request

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls

data class TruckRequest(
    @JsonProperty("longitude", required = true)
    @JsonSetter(nulls = Nulls.FAIL)
    val longitude: Double,
    @JsonProperty("latitude", required = true)
    @JsonSetter(nulls = Nulls.FAIL)
    val latitude: Double,
    @JsonProperty("numberOfPassengers", required = true)
    @JsonSetter(nulls = Nulls.FAIL)
    val numberOfPassengers: Int,
    @JsonProperty("commodities", required = true)
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    val commodities: Set<CommodityRequest> = emptySet()
)
