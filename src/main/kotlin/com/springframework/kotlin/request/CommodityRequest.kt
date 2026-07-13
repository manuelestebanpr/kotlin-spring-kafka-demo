package com.springframework.kotlin.request

import com.fasterxml.jackson.annotation.JsonProperty

import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls

data class CommodityRequest(
    @JsonProperty("name", required = true)
    @JsonSetter(nulls = Nulls.FAIL)
    val name: String
)
