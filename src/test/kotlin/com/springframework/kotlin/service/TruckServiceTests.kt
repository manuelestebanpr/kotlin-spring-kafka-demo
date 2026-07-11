package com.springframework.kotlin.service

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.entity.Truck
import com.springframework.kotlin.repository.CommodityRepository
import com.springframework.kotlin.repository.TruckRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.argThat
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.*

@SpringBootTest
class TruckServiceTests @Autowired constructor(
    val truckService: TruckService
) {

    @MockitoBean
    lateinit var truckRepository: TruckRepository

    @MockitoBean
    lateinit var commodityRepository: CommodityRepository

    @Test
    fun `should create truck`() {
        val truckDto = TruckDto(longitude = 1.0, latitude = 2.0, numberOfPassengers = 3)
        val truck = Truck(id = UUID.randomUUID(), longitude = 1.0, latitude = 2.0, numberOfPassengers = 3)
        
        `when`(truckRepository.save(any(Truck::class.java))).thenReturn(truck)

        val result = truckService.create(truckDto)

        assertNotNull(result.id)
        assertEquals(1.0, result.longitude)
        verify(truckRepository).save(any(Truck::class.java))
    }

    @Test
    fun `should update truck using second constructor`() {
        val truckId = UUID.randomUUID()
        val existingTruck = Truck(id = truckId, longitude = 1.0, latitude = 2.0, numberOfPassengers = 3)
        val truckDto = TruckDto(id = truckId, longitude = 5.0, latitude = 6.0, numberOfPassengers = 4)
        
        `when`(truckRepository.findById(truckId)).thenReturn(Optional.of(existingTruck))
        `when`(truckRepository.save(any(Truck::class.java))).thenAnswer { it.arguments[0] as Truck }

        val result = truckService.update(truckId, truckDto)

        assertNotNull(result)
        assertEquals(5.0, result!!.longitude)
        assertEquals(4, result.numberOfPassengers)
        verify(truckRepository).save(argThat { it.longitude == 5.0 && it.numberOfPassengers == 4 })
    }
}
