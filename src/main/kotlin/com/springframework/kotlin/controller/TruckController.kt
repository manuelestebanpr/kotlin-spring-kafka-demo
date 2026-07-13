package com.springframework.kotlin.controller

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.request.TruckRequest
import com.springframework.kotlin.facade.TruckFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class TruckController(private val truckFacade: TruckFacade) {

    companion object {
        const val BASE_URL = "/api/trucks"
        const val BASE_URL_ID = "$BASE_URL/{id}"
    }

    @GetMapping(BASE_URL)
    fun getAll(): List<TruckDto> = truckFacade.getAll()

    @GetMapping(BASE_URL_ID)
    fun getById(@PathVariable id: UUID): ResponseEntity<TruckDto> =
        truckFacade.getById(id)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PostMapping(BASE_URL)
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody truckRequest: TruckRequest): TruckDto = truckFacade.create(truckRequest)

    @PutMapping(BASE_URL_ID)
    fun update(@PathVariable id: UUID, @RequestBody truckRequest: TruckRequest): ResponseEntity<TruckDto> =
        truckFacade.update(id, truckRequest)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @PatchMapping(BASE_URL_ID)
    fun patch(@PathVariable id: UUID, @RequestBody updates: Map<String, Any?>): ResponseEntity<TruckDto> =
        truckFacade.patch(id, updates)?.let { ResponseEntity.ok(it) } ?: ResponseEntity.notFound().build()

    @DeleteMapping(BASE_URL_ID)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) = truckFacade.delete(id)
}
