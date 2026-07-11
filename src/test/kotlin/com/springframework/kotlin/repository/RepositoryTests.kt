package com.springframework.kotlin.repository

import com.springframework.kotlin.entity.Commodity
import com.springframework.kotlin.entity.Truck
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import java.util.*

@SpringBootTest
@Transactional
class RepositoryTests @Autowired constructor(
    val truckRepository: TruckRepository,
    val commodityRepository: CommodityRepository
) {

    @Test
    fun `should save truck with commodities`() {
        val commodity = Commodity(name = "Apples")
        val savedCommodity = commodityRepository.save(commodity)

        val truck = Truck(
            longitude = 10.0,
            latitude = 20.0,
            numberOfPassengers = 2,
            commodities = mutableSetOf(savedCommodity)
        )
        val savedTruck = truckRepository.save(truck)

        assertNotNull(savedTruck.id)
        assertEquals(1, savedTruck.commodities.size)
        assertEquals("Apples", savedTruck.commodities.first().name)
    }

    @Test
    fun `should find truck by id`() {
        val truck = Truck(longitude = 1.0, latitude = 2.0, numberOfPassengers = 3)
        val saved = truckRepository.save(truck)
        
        val found = truckRepository.findById(saved.id)
        assertTrue(found.isPresent)
        assertEquals(saved.id, found.get().id)
    }
}
