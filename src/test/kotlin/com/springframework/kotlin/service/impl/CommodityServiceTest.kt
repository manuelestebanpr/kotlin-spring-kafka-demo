package com.springframework.kotlin.service.impl

import com.springframework.kotlin.entity.Commodity
import com.springframework.kotlin.repository.CommodityRepository
import com.springframework.kotlin.request.CommodityRequest
import com.springframework.kotlin.service.CommodityService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.*

@SpringBootTest
class CommodityServiceTest @Autowired constructor(val commodityService: CommodityService) {

    @MockitoBean
    lateinit var commodityRepository: CommodityRepository

    @Test
    fun `getAll should return list`() {
        val commodity = Commodity(id = UUID.randomUUID(), name = "Test")
        `when`(commodityRepository.findAll()).thenReturn(listOf(commodity))

        val result = commodityService.getAll()

        assertEquals(1, result.size)
        assertEquals("Test", result[0].name)
    }

    @Test
    fun `create should save`() {
        val request = CommodityRequest(name = "New")
        val commodity = Commodity(id = UUID.randomUUID(), name = "New")
        `when`(commodityRepository.save(any(Commodity::class.java))).thenReturn(commodity)

        val result = commodityService.create(request)

        assertNotNull(result.id)
        assertEquals("New", result.name)
    }

    private fun <T> any(type: Class<T>): T = ArgumentMatchers.any(type)
}
