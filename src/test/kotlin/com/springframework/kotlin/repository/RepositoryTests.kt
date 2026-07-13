package com.springframework.kotlin.repository

import com.springframework.kotlin.entity.Commodity
import com.springframework.kotlin.entity.Truck
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class RepositoryTests @Autowired constructor(val truckRepository: TruckRepository,
                                             val commodityRepository: CommodityRepository) {

    @Test
    fun `should save and find truck with commodities`() {
        val commodity = Commodity(name = "Electronics")
        val truck = Truck(
            longitude = 12.34,
            latitude = 56.78,
            numberOfPassengers = 2
        ).apply {
            commodities.add(commodity)
        }

        val savedTruck = truckRepository.save(truck)

        val foundTruck = truckRepository.findById(savedTruck.id!!).orElseThrow()
        assertEquals(12.34, foundTruck.longitude)
        assertEquals(1, foundTruck.commodities.size)
        assertEquals("Electronics", foundTruck.commodities.first().name)
    }

    @Test
    fun `should find commodities by name`() {
        val commodity = Commodity(name = "Fuel")
        commodityRepository.save(commodity)

        val found = commodityRepository.findAll().find { it.name == "Fuel" }
        assertNotNull(found)
        assertEquals("Fuel", found?.name)
    }
}
