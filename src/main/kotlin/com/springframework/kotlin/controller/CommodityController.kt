package com.springframework.kotlin.controller

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.request.CommodityRequest
import com.springframework.kotlin.facade.CommodityFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class CommodityController(private val commodityFacade: CommodityFacade) {

    companion object {
        const val BASE_URL = "/api/v1/commodities"
        const val BASE_URL_ID = "$BASE_URL/{id}"
    }

    @GetMapping(BASE_URL)
    fun getAll(): List<CommodityDto> = commodityFacade.getAll()

    @GetMapping(BASE_URL_ID)
    fun getById(@PathVariable id: UUID): ResponseEntity<CommodityDto> =
        commodityFacade.getById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody commodityRequest: CommodityRequest): CommodityDto = commodityFacade.create(commodityRequest)

    @PutMapping(BASE_URL_ID)
    fun update(@PathVariable id: UUID, @RequestBody commodityRequest: CommodityRequest): ResponseEntity<CommodityDto> =
        commodityFacade.update(id, commodityRequest)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping(BASE_URL_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) = commodityFacade.delete(id)
}
