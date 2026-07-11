package com.springframework.kotlin.controller

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.facade.CommodityFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/commodities")
class CommodityController(private val commodityFacade: CommodityFacade) {

    @GetMapping
    fun getAll(): List<CommodityDto> = commodityFacade.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<CommodityDto> {
        val commodity = commodityFacade.getById(id)
        return if (commodity != null) ResponseEntity.ok(commodity) else ResponseEntity.notFound().build()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody commodityDto: CommodityDto): CommodityDto = commodityFacade.create(commodityDto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody commodityDto: CommodityDto): ResponseEntity<CommodityDto> {
        val updated = commodityFacade.update(id, commodityDto)
        return if (updated != null) ResponseEntity.ok(updated) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) = commodityFacade.delete(id)
}
