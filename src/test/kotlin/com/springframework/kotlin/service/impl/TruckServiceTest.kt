package com.springframework.kotlin.service.impl

import com.springframework.kotlin.entity.Truck
import com.springframework.kotlin.repository.TruckRepository
import com.springframework.kotlin.request.TruckRequest
import com.springframework.kotlin.service.TruckService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.*

@SpringBootTest
class TruckServiceTest @Autowired constructor(val truckService: TruckService) {

    @MockitoBean
    lateinit var truckRepository: TruckRepository

    @Test
    fun `getAll should return list of truck dtos`() {
        val truck = Truck(id = UUID.randomUUID(), longitude = 1.0, latitude = 2.0, numberOfPassengers = 1)
        `when`(truckRepository.findAll()).thenReturn(listOf(truck))

        val result = truckService.getAll()

        assertEquals(1, result.size)
        assertEquals(truck.id, result[0].id)
        verify(truckRepository).findAll()
    }

    @Test
    fun `getById should return truck dto when found`() {
        val id = UUID.randomUUID()
        val truck = Truck(id = id, longitude = 1.0, latitude = 2.0, numberOfPassengers = 1)
        `when`(truckRepository.findById(id)).thenReturn(Optional.of(truck))

        val result = truckService.getById(id)

        assertNotNull(result)
        assertEquals(id, result?.id)
    }

    @Test
    fun `create should save and return dto`() {
        val request = TruckRequest(longitude = 10.0, latitude = 20.0, numberOfPassengers = 2)
        val truck = Truck(id = UUID.randomUUID(), longitude = 10.0, latitude = 20.0, numberOfPassengers = 2)
        
        // Mocking save to return a truck with ID
        `when`(truckRepository.save(any(Truck::class.java))).thenReturn(truck)

        val result = truckService.create(request)

        assertNotNull(result.id)
        assertEquals(10.0, result.longitude)
    }

    private fun <T> any(type: Class<T>): T = org.mockito.ArgumentMatchers.any(type)
}
