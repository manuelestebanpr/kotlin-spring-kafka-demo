package com.springframework.kotlin.controller

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.facade.TruckFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api/trucks")
class TruckController(private val truckFacade: TruckFacade) {

    @GetMapping
    fun getAll(): List<TruckDto> = truckFacade.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): ResponseEntity<TruckDto> {
        val truck = truckFacade.getById(id)
        return if (truck != null) ResponseEntity.ok(truck) else ResponseEntity.notFound().build()
    }

    /**
     * POST: Used for CREATING a new resource.
     * The server determines the final URI of the resource.
     * Example: Creating a new truck with its initial commodities.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody truckDto: TruckDto): TruckDto = truckFacade.create(truckDto)

    /**
     * PUT: Used for UPDATING an existing resource by REPLACING it.
     * It is idempotent: multiple identical requests should have the same effect as a single one.
     * Expects a full representation of the resource.
     * Example: Updating all fields of a truck, including its entire set of commodities.
     */
    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody truckDto: TruckDto): ResponseEntity<TruckDto> {
        val updated = truckFacade.update(id, truckDto)
        return if (updated != null) ResponseEntity.ok(updated) else ResponseEntity.notFound().build()
    }

    /**
     * PATCH: Used for PARTIAL UPDATES to a resource.
     * Only the fields provided in the request are updated.
     * Example: Updating ONLY the latitude and longitude of a truck as it moves, 
     * without sending or affecting its commodities or passenger count.
     */
    @PatchMapping("/{id}")
    fun patch(@PathVariable id: UUID, @RequestBody updates: Map<String, Any?>): ResponseEntity<TruckDto> {
        val updated = truckFacade.patch(id, updates)
        return if (updated != null) ResponseEntity.ok(updated) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) = truckFacade.delete(id)
}
