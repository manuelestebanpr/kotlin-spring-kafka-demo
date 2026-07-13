package com.springframework.kotlin.facade.impl

import com.springframework.kotlin.dto.CommodityDto
import com.springframework.kotlin.facade.CommodityFacade
import com.springframework.kotlin.service.CommodityService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.*

@SpringBootTest
class CommodityFacadeTest @Autowired constructor(val commodityFacade: CommodityFacade) {

    @MockitoBean
    lateinit var commodityService: CommodityService


    @Test
    fun `getAll should delegate to service`() {
        val dtos = listOf(CommodityDto(UUID.randomUUID(), "Test"))
        `when`(commodityService.getAll()).thenReturn(dtos)

        val result = commodityFacade.getAll()

        assertEquals(dtos, result)
    }
}
