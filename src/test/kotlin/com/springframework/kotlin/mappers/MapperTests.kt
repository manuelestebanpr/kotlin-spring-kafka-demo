package com.springframework.kotlin.mappers

import com.springframework.kotlin.entity.Commodity
import com.springframework.kotlin.entity.Truck
import com.springframework.kotlin.request.CommodityRequest
import com.springframework.kotlin.request.TruckRequest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class MapperTests {

    @Test
    fun `Truck toDto should map all fields`() {
        val truckId = UUID.randomUUID()
        val commodityId = UUID.randomUUID()
        val commodity = Commodity(id = commodityId, name = "Apples")
        val truck = Truck(
            id = truckId,
            longitude = 10.0,
            latitude = 20.0,
            numberOfPassengers = 2
        ).apply {
            commodities = mutableSetOf(commodity)
        }

        val dto = truck.toDto()

        assertEquals(truckId, dto.id)
        assertEquals(10.0, dto.longitude)
        assertEquals(20.0, dto.latitude)
        assertEquals(2, dto.numberOfPassengers)
        assertEquals(1, dto.commodities.size)
        assertEquals(commodityId, dto.commodities.first().id)
        assertEquals("Apples", dto.commodities.first().name)
    }

    @Test
    fun `TruckRequest toEntity should map fields`() {
        val request = TruckRequest(
            longitude = 30.0,
            latitude = 40.0,
            numberOfPassengers = 3
        )

        val entity = request.toEntity()

        assertEquals(30.0, entity.longitude)
        assertEquals(40.0, entity.latitude)
        assertEquals(3, entity.numberOfPassengers)
    }

    @Test
    fun `Commodity toDto should map all fields`() {
        val id = UUID.randomUUID()
        val commodity = Commodity(id = id, name = "Bananas")

        val dto = commodity.toDto()

        assertEquals(id, dto.id)
        assertEquals("Bananas", dto.name)
    }

    @Test
    fun `CommodityRequest toEntity should map fields`() {
        val request = CommodityRequest(name = "Oranges")

        val entity = request.toEntity()

        assertEquals("Oranges", entity.name)
    }
}
