package com.springframework.kotlin.facade.impl

import com.springframework.kotlin.dto.TruckDto
import com.springframework.kotlin.facade.TruckFacade
import com.springframework.kotlin.service.TruckService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.util.*

@SpringBootTest
class TruckFacadeTest  @Autowired constructor(val truckFacade: TruckFacade){

    @MockitoBean
    lateinit var truckService: TruckService

    @Test
    fun `getAll should delegate to service`() {
        val dtos = listOf(TruckDto(UUID.randomUUID(), 1.0, 2.0, 1))
        `when`(truckService.getAll()).thenReturn(dtos)

        val result = truckFacade.getAll()

        assertEquals(dtos, result)
    }
}
